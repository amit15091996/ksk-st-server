package com.khadbhandarserver.inventory.serviceImplementation;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.khadbhandarserver.inventory.dto.PurchaseRecordDto;
import com.khadbhandarserver.inventory.entity.PurchaseRecord;
import com.khadbhandarserver.inventory.exception.BadRequest;
import com.khadbhandarserver.inventory.exception.NotFoundException;
import com.khadbhandarserver.inventory.helper.AppConstant;
import com.khadbhandarserver.inventory.repository.PurchaseRecordRepository;
import com.khadbhandarserver.inventory.service.PurchaseRecordService;


@Service
public class PurchaseRecordServiceImpl implements PurchaseRecordService {

	@Autowired
	private PurchaseRecordRepository purchaseRecordRepository;
	
	
	@Override
	public Map<Object, Object> insertPurchaseRecord(PurchaseRecordDto purchaseRecordDto) {
		 Map<Object, Object> purchaseRecordMap=new HashMap<>();
			
		 PurchaseRecord purchaseRecord=new PurchaseRecord();
		
		 purchaseRecord.setPurchaseDate(purchaseRecordDto.getPurchaseDate());
		 purchaseRecord.setPurchasedFrom(purchaseRecordDto.getPurchasedFrom());
		 purchaseRecord.setPurchasedItemCategory(purchaseRecordDto.getPurchasedItemCategory());
		 purchaseRecord.setPurchasedItemName(purchaseRecordDto.getPurchasedItemName());
		 purchaseRecord.setPurchasedItemUnit(purchaseRecordDto.getPurchasedItemUnit());
		 purchaseRecord.setPurchasedItemQuantity(purchaseRecordDto.getPurchasedItemQuantity());
		 purchaseRecord.setPurchasedItemPrice(purchaseRecordDto.getPurchasedItemPrice());
		 purchaseRecord.setPurchasedItemTotalAmount(purchaseRecordDto.getPurchasedItemQuantity()*purchaseRecordDto.getPurchasedItemPrice());
		 
			try {
				PurchaseRecord purchaseRecordSaved=this.purchaseRecordRepository.save(purchaseRecord);
			if( purchaseRecordSaved !=null) {
				purchaseRecordMap.put(AppConstant.statusCode, AppConstant.ok);
				purchaseRecordMap.put(AppConstant.status, AppConstant.success);
				purchaseRecordMap.put(AppConstant.statusMessage, AppConstant.dataSubmitedsuccessfully);
			}
			}
			catch(Exception e) {
				throw new BadRequest(e.getMessage());
			}
		   return purchaseRecordMap; 
	}

	@Override
	public Map<Object, Object> deletePurchaseRecord(Long purchasedItemId) {
		 Map<Object, Object> purchaseRecordMap=new HashMap<>();
			if(this.purchaseRecordRepository.findById(purchasedItemId).isPresent()) {
			this.purchaseRecordRepository.deleteById(purchasedItemId);
			purchaseRecordMap.put(AppConstant.statusCode, AppConstant.ok);
			purchaseRecordMap.put(AppConstant.status, AppConstant.success);
			purchaseRecordMap.put(AppConstant.statusMessage, AppConstant.dataDeletedSuccesFully);
			}
			else {
				throw new NotFoundException(AppConstant.noRecordFound + purchasedItemId);
			}
			return purchaseRecordMap;
	}

	@Override
	public Map<Object, Object> updatePurchaseRecord(Long purchasedItemId, PurchaseRecordDto purchaseRecordDto) {
		Map<Object, Object> purchaseRecordMap=new HashMap<>();
		
		  if(this.purchaseRecordRepository.findById(purchasedItemId).isPresent()) {
			try {
			
			this.purchaseRecordRepository.updatePurchaseRecord(
					purchaseRecordDto.getPurchasedItemName(),
					purchaseRecordDto.getPurchasedItemCategory(),
					purchaseRecordDto.getPurchasedItemQuantity(),
					purchaseRecordDto.getPurchasedItemUnit(),
					purchaseRecordDto.getPurchasedItemPrice(),
					purchaseRecordDto.getPurchasedItemQuantity()*purchaseRecordDto.getPurchasedItemPrice(),
					purchaseRecordDto.getPurchaseDate(),
					purchaseRecordDto.getPurchasedFrom(),
					purchasedItemId
					);
		
				purchaseRecordMap.put(AppConstant.statusCode, AppConstant.ok);
				purchaseRecordMap.put(AppConstant.status, AppConstant.success);
				purchaseRecordMap.put(AppConstant.statusMessage, AppConstant.recordUpdatedSuccessFully +purchasedItemId);
			}
			catch (Exception e) {throw new BadRequest(e.getMessage());}
			}
			else {throw new NotFoundException(AppConstant.noRecordFound + purchasedItemId);}
		
		return purchaseRecordMap;
	}

	@Override
	public Map<Object, Object> getAllPurchaseRecord() {
		  Map<Object, Object> purchaseRecordMap=new HashMap<>();
			
		  purchaseRecordMap.put(AppConstant.statusCode, AppConstant.ok);
		  purchaseRecordMap.put(AppConstant.status, AppConstant.success);
		  purchaseRecordMap.put(AppConstant.statusMessage, AppConstant.dataFetchedSuccesfully);
		  purchaseRecordMap.put(AppConstant.response, this.purchaseRecordRepository.findAll());
			  
			return purchaseRecordMap;
	}

}
