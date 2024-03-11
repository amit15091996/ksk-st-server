package com.khadbhandarserver.inventory.service;

import java.util.List;
import java.util.Map;

import com.khadbhandarserver.inventory.dto.SalesRecordDto;

public interface SalesRecordService {

	Map<Object, Object> insertSoldItem(List<SalesRecordDto> salesRecordDto);
	Map<Object, Object> deleteSoldItem(Long soldItemId);
	Map<Object, Object> updateSoldItem(Long soldItemId,SalesRecordDto salesRecordDto);
	Map<Object, Object> getAllSoldItem();
	
	
}
