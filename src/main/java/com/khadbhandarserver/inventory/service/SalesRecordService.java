package com.khadbhandarserver.inventory.service;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.khadbhandarserver.inventory.dto.SalesRecordDto;

public interface SalesRecordService {

	Map<Object, Object> insertSoldItem(List<SalesRecordDto> salesRecordDto) throws JsonProcessingException;

	Map<Object, Object> deleteSoldItem(Long soldItemId);

	Map<Object, Object> updateSoldItem(Long soldItemId, SalesRecordDto salesRecordDto);

	Map<Object, Object> getAllSoldItem() throws JsonMappingException, JsonProcessingException;

	Map<Object, Object> getSoldItemByPartyName(String partyName) throws JsonMappingException, JsonProcessingException;

}
