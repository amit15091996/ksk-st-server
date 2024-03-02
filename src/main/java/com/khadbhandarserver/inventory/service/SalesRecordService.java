package com.khadbhandarserver.inventory.service;

import java.util.Map;

import com.khadbhandarserver.inventory.dto.SalesRecordDto;

public interface SalesRecordService {

	Map<Object, Object> insertSoldItem(SalesRecordDto salesRecordDto);
	Map<Object, Object> deleteSoldItem(Long soldItemId);
	Map<Object, Object> updateSoldItem(Long soldItemId,SalesRecordDto salesRecordDto);
	Map<Object, Object> getAllSoldItem();
	
	
}
