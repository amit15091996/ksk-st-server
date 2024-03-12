package com.khadbhandarserver.inventory.serviceImplementation;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.khadbhandarserver.inventory.dto.AllSalesRecordDto;
import com.khadbhandarserver.inventory.dto.SalesRecordDto;
import com.khadbhandarserver.inventory.dto.UpdatedSalesRecord;
import com.khadbhandarserver.inventory.entity.SalesRecords;
import com.khadbhandarserver.inventory.entity.StockDetails;
import com.khadbhandarserver.inventory.exception.BadRequest;
import com.khadbhandarserver.inventory.exception.NotFoundException;
import com.khadbhandarserver.inventory.helper.AppConstant;
import com.khadbhandarserver.inventory.repository.SalesRecordRepository;
import com.khadbhandarserver.inventory.repository.StockDetailsRepository;
import com.khadbhandarserver.inventory.service.SalesRecordService;
import com.khadbhandarserver.inventory.util.SalesUtil;

@Service
public class SalesRecordServiceImpl implements SalesRecordService {

	@Autowired
	private SalesRecordRepository salesRecordRepository;
	
	@Autowired
	private StockDetailsRepository stockDetailsRepository;
	
	@Autowired
	private SalesUtil salesUtil;
	
	private final  ObjectMapper objectMapper=new ObjectMapper();
	
	@Override
	public Map<Object, Object> insertSoldItem( List<SalesRecordDto> salesRecordDto) throws JsonProcessingException {
		  Map<Object, Object> salesRecordMap=new HashMap<>();
	      SalesRecords salesRecords=new SalesRecords();
		  salesRecords.setSoldItemList(this.objectMapper.writeValueAsString(salesRecordDto));
		  
	       List<UpdatedSalesRecord> updateRecord=salesRecordDto.stream().map(item->{
			List<StockDetails> stockDetails=this.stockDetailsRepository.findByStockName(item.getSoldItemName());
			if( stockDetails.stream().findFirst().isPresent()) {
			 return	this.salesUtil.stockRecordUpdate(salesRecordDto, stockDetails.stream().findFirst().get());
			 
			}
			else {
				throw new NotFoundException("Item Not Found With Name "+item.getSoldItemName());
			}
		}).collect(Collectors.toList());
		  

	   
	if(updateRecord.stream().distinct().map(item->item.getTotalQuantity()).reduce(0,(total,quantity)->total+quantity)< 
	      updateRecord.stream().distinct().map(item->item.getStockDetails().getStockQuantity()).reduce(0,(total,quantity)->total+quantity)
			) {
		
		updateRecord.stream().distinct().forEach(k->{
			 this.stockDetailsRepository.updateStockDetalsOnSales( k.getStockDetails().getStockQuantity()-k.getTotalQuantity(),
					  (k.getStockDetails().getStockQuantity()-k.getTotalQuantity())* k.getStockDetails().getStockPrice(),
					  k.getStockDetails().getStockId());
		});
	}
	
	else {
		
		throw new BadRequest("Total Requested item quantity is greater then available quantity  "+"Requested Quantity:- "+updateRecord.stream().distinct().map(item->item.getTotalQuantity()).reduce(0,(total,quantity)->total+quantity)+"  Available Quantity:- " + updateRecord.stream().distinct().map(item->item.getStockDetails().getStockQuantity()).reduce(0,(total,quantity)->total+quantity));
	}
	       
		
		  
			try {
				SalesRecords salesRecordsSaved=this.salesRecordRepository.save(salesRecords);
			if( salesRecordsSaved !=null) {
				salesRecordMap.put(AppConstant.statusCode, AppConstant.ok);
				salesRecordMap.put(AppConstant.status, AppConstant.success);
				salesRecordMap.put(AppConstant.statusMessage, AppConstant.dataSubmitedsuccessfully);
			}
			}
			catch(Exception e) {
				throw new BadRequest(e.getMessage());
			}
		   return salesRecordMap; 
			
	}

	@Override
	public Map<Object, Object> deleteSoldItem(Long soldItemId) {
		  Map<Object, Object> salesRecordMap=new HashMap<>();
			if(this.salesRecordRepository.findById(soldItemId).isPresent()) {
			this.salesRecordRepository.deleteById(soldItemId);
			salesRecordMap.put(AppConstant.statusCode, AppConstant.ok);
			salesRecordMap.put(AppConstant.status, AppConstant.success);
			salesRecordMap.put(AppConstant.statusMessage, AppConstant.dataDeletedSuccesFully);
			}
			else {
				throw new NotFoundException(AppConstant.noRecordFound + soldItemId);
			}
			return salesRecordMap;
	}

	@Override
	public Map<Object, Object> updateSoldItem(Long soldItemId, SalesRecordDto salesRecordDto) {
		Map<Object, Object> salesRecordMap=new HashMap<>();
		
		  if(this.salesRecordRepository.findById(soldItemId).isPresent()) {
			try {
			
//				this.salesRecordRepository.updateSalesRecord(
//						salesRecordDto.getSoldItemName(),
//						salesRecordDto.getSoldItemCategory(),
//						salesRecordDto.getSoldItemQuantity(),
//						salesRecordDto.getPartyName(),
//						salesRecordDto.getSoldItemPrice(),
//						salesRecordDto.getSoldItemPrice()*salesRecordDto.getSoldItemQuantity(),
//						salesRecordDto.getSellDate(),
//						soldItemId
//						);
		
				salesRecordMap.put(AppConstant.statusCode, AppConstant.ok);
				salesRecordMap.put(AppConstant.status, AppConstant.success);
				salesRecordMap.put(AppConstant.statusMessage, AppConstant.recordUpdatedSuccessFully +soldItemId);
			}
			catch (Exception e) {throw new BadRequest(e.getMessage());}
			}
			else {throw new NotFoundException(AppConstant.noRecordFound + soldItemId);}
		
		return salesRecordMap;
	}

	@Override
	public Map<Object, Object> getAllSoldItem()  {
		 Map<Object, Object> salesRecordMap=new HashMap<>();
		List<SalesRecords> salesRecord=this.salesRecordRepository.findAll();
  
		  salesRecordMap.put(AppConstant.statusCode, AppConstant.ok);
		  salesRecordMap.put(AppConstant.status, AppConstant.success);
		  salesRecordMap.put(AppConstant.statusMessage, AppConstant.dataFetchedSuccesfully);
		  salesRecordMap.put(AppConstant.response, salesRecord.stream().map(item->{
	        	        AllSalesRecordDto allSalesRecord=new AllSalesRecordDto();
						allSalesRecord.setRecieptGenerated(item.isRecieptGenerated());
						allSalesRecord.setRecieptsRecord(item.getRecieptsRecord());
						allSalesRecord.setSoldItemId(item.getSoldItemId());
						try {
							allSalesRecord.setSalesList(this.objectMapper.readValue(item.getSoldItemList(), this.objectMapper.getTypeFactory().constructCollectionType(List.class, SalesRecordDto.class)));
						} catch (JsonProcessingException e) {
							e.printStackTrace();
						}
						
				  return allSalesRecord;
			  }).collect(Collectors.toList()));
			  
			return salesRecordMap;
	}

}
