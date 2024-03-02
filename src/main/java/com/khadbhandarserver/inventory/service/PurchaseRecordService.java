package com.khadbhandarserver.inventory.service;

import java.util.Map;

import com.khadbhandarserver.inventory.dto.PurchaseRecordDto;

public interface PurchaseRecordService {

	Map<Object, Object> insertPurchaseRecord(PurchaseRecordDto purchaseRecordDto);
	Map<Object, Object> deletePurchaseRecord(Long purchasedItemId);
	Map<Object, Object> updatePurchaseRecord(Long purchasedItemId,PurchaseRecordDto purchaseRecordDto);
	Map<Object, Object> getAllPurchaseRecord();
	
	
}
