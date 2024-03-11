package com.khadbhandarserver.inventory.serviceImplementation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.khadbhandarserver.inventory.dto.SalesRecordDto;
import com.khadbhandarserver.inventory.entity.SalesRecords;
import com.khadbhandarserver.inventory.exception.BadRequest;
import com.khadbhandarserver.inventory.exception.NotFoundException;
import com.khadbhandarserver.inventory.helper.AppConstant;
import com.khadbhandarserver.inventory.repository.SalesRecordRepository;
import com.khadbhandarserver.inventory.service.SalesRecordService;

@Service
public class SalesRecordServiceImpl implements SalesRecordService {

	@Autowired
	private SalesRecordRepository salesRecordRepository;
	
	@Override
	public Map<Object, Object> insertSoldItem( List<SalesRecordDto> salesRecordDto) {
		  Map<Object, Object> salesRecordMap=new HashMap<>();
	      List<SalesRecords> bulkRecord=new ArrayList<>();
		 
	      for(SalesRecordDto sr:salesRecordDto) {
	    	  SalesRecords salesRecords=new SalesRecords();
			  salesRecords.setSellDate(sr.getSellDate());
			  salesRecords.setSoldItemCategory(sr.getSoldItemCategory());
			  salesRecords.setSoldItemName(sr.getSoldItemName());
			  salesRecords.setPartyName(sr.getPartyName());
			  salesRecords.setSoldItemPrice(sr.getSoldItemPrice());
			  salesRecords.setSoldItemQuantity(sr.getSoldItemQuantity());
			  salesRecords.setSoldItemTotalAmount(sr.getSoldItemPrice()*sr.getSoldItemQuantity());
			  bulkRecord.add(salesRecords);
	      }
		  
			try {
				List<SalesRecords> salesRecordsSaved=this.salesRecordRepository.saveAll(bulkRecord);
			if( !salesRecordsSaved.isEmpty()) {
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
			
				this.salesRecordRepository.updateSalesRecord(
						salesRecordDto.getSoldItemName(),
						salesRecordDto.getSoldItemCategory(),
						salesRecordDto.getSoldItemQuantity(),
						salesRecordDto.getPartyName(),
						salesRecordDto.getSoldItemPrice(),
						salesRecordDto.getSoldItemPrice()*salesRecordDto.getSoldItemQuantity(),
						salesRecordDto.getSellDate(),
						soldItemId
						);
		
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
	public Map<Object, Object> getAllSoldItem() {
		  Map<Object, Object> salesRecordMap=new HashMap<>();
			
		  salesRecordMap.put(AppConstant.statusCode, AppConstant.ok);
		  salesRecordMap.put(AppConstant.status, AppConstant.success);
		  salesRecordMap.put(AppConstant.statusMessage, AppConstant.dataFetchedSuccesfully);
		  salesRecordMap.put(AppConstant.response, this.salesRecordRepository.findAll());
			  
			return salesRecordMap;
	}

}
