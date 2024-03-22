package com.khadbhandarserver.inventory.service;

import java.util.Map;

import com.khadbhandarserver.inventory.dto.StockDetailsDto;

public interface StockDetailsService {
	Map<Object, Object> insertStockDetails(StockDetailsDto stockDetailsDto);

	Map<Object, Object> deleteStockDetails(Long stockId);

	Map<Object, Object> updateStockDetails(Long stockId, StockDetailsDto stockDetailsDto);

	Map<Object, Object> getAllStockDetails();
}
