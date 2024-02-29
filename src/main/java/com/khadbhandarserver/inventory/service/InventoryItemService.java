package com.khadbhandarserver.inventory.service;

import java.util.Map;

import com.khadbhandarserver.inventory.dto.InventoryItemDto;

public interface InventoryItemService {

	
	Map<Object, Object> insertInventoryItem(InventoryItemDto inventoryItemDto);
	Map<Object, Object> deleteInventoryItem(Long inventoryItemId);
	Map<Object, Object> updateInventoryItem(Long inventoryItemId,InventoryItemDto inventoryItemDto);

}
