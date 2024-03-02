package com.khadbhandarserver.inventory.service;

import java.util.Map;

import com.khadbhandarserver.inventory.dto.RecieptsRecordDto;

public interface RecieptRecordService {

	Map<Object, Object> insertRecieptRecord(Long soldItemId,RecieptsRecordDto recieptsRecordDto);
	Map<Object, Object> deleteRecieptRecord(Long recieptId);
	Map<Object, Object> updateRecieptRecord(Long recieptId,RecieptsRecordDto recieptsRecordDto);
	Map<Object, Object> getAllRecieptRecord();
	
	
	
	
}
