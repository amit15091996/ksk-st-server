package com.khadbhandarserver.inventory.serviceImplementation;

import java.util.HashMap;
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
	public Map<Object, Object> insertSoldItem(SalesRecordDto salesRecordDto) {
		  Map<Object, Object> salesRecordMap=new HashMap<>();
			
		  SalesRecords salesRecords=new SalesRecords();
		  salesRecords.setSellDate(salesRecordDto.getSellDate());
		  salesRecords.setSoldItemCategory(salesRecordDto.getSoldItemCategory());
		  salesRecords.setSoldItemName(salesRecordDto.getSoldItemName());
		  salesRecords.setSoldItemUnit(salesRecordDto.getSoldItemUnit());
		  salesRecords.setSoldItemPrice(salesRecordDto.getSoldItemPrice());
		  salesRecords.setSoldItemQuantity(salesRecordDto.getSoldItemQuantity());
		  salesRecords.setSoldItemTotalAmount(salesRecordDto.getSoldItemPrice()*salesRecordDto.getSoldItemQuantity());
	        
			
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
			
				this.salesRecordRepository.updateSalesRecord(
						salesRecordDto.getSoldItemName(),
						salesRecordDto.getSoldItemCategory(),
						salesRecordDto.getSoldItemQuantity(),
						salesRecordDto.getSoldItemUnit(),
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

}
