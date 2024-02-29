package com.khadbhandarserver.inventory.serviceImplementation;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.khadbhandarserver.inventory.dto.InventoryItemDto;
import com.khadbhandarserver.inventory.entity.InventoryItem;
import com.khadbhandarserver.inventory.exception.BadRequest;
import com.khadbhandarserver.inventory.exception.NotFoundException;
import com.khadbhandarserver.inventory.helper.AppConstant;
import com.khadbhandarserver.inventory.service.InventoryItemService;
import com.khadbhandarserver.inventory.repository.InventoryItemRepository;

@Service
public class InventoryItemServiceImpl implements InventoryItemService {

	@Autowired
	private InventoryItemRepository inventoryItemRepository;
	
	@Override
	public Map<Object, Object> insertInventoryItem(InventoryItemDto inventoryItemDto) {

         Map<Object, Object> inventoryItemMap=new HashMap<>();
		
        InventoryItem inventoryItem=new InventoryItem();
        inventoryItem.setInventoryItemName(inventoryItemDto.getInventoryItemName());
        inventoryItem.setInventoryItemGroup(inventoryItemDto.getInventoryItemGroup());
        inventoryItem.setInventoryItemPrice(inventoryItemDto.getInventoryItemPrice());
        inventoryItem.setInventoryItemQuantity(inventoryItemDto.getInventoryItemQuantity());
        inventoryItem.setInventoryItemUnit(inventoryItemDto.getInventoryItemUnit());
        inventoryItem.setInventoryItemTotalAmount(inventoryItemDto.getInventoryItemQuantity()*inventoryItemDto.getInventoryItemPrice());
		
		try {
			 InventoryItem inventoryItemSaved=this.inventoryItemRepository.save(inventoryItem);
		if( inventoryItemSaved !=null) {
			inventoryItemMap.put(AppConstant.statusCode, AppConstant.ok);
			inventoryItemMap.put(AppConstant.status, AppConstant.success);
			inventoryItemMap.put(AppConstant.statusMessage, AppConstant.dataSubmitedsuccessfully);
		}
		}
		catch(Exception e) {
			throw new BadRequest(e.getMessage());
		}
	   return inventoryItemMap; 
		
	}

	@Override
	public Map<Object, Object> deleteInventoryItem(Long inventoryItemId) {
		
       Map<Object, Object> inventoryItemMap=new HashMap<>();
		
		if(this.inventoryItemRepository.findById(inventoryItemId).isPresent()) {
		this.inventoryItemRepository.deleteById(inventoryItemId);
		inventoryItemMap.put(AppConstant.statusCode, AppConstant.ok);
		inventoryItemMap.put(AppConstant.status, AppConstant.success);
		inventoryItemMap.put(AppConstant.statusMessage, AppConstant.dataDeletedSuccesFully);
		}
		else {
			throw new NotFoundException(AppConstant.noRecordFound + inventoryItemId);
		}
		return inventoryItemMap;
		
	}

	@Override
	public Map<Object, Object> updateInventoryItem(Long inventoryItemId, InventoryItemDto inventoryItemDto) {
		Map<Object, Object> inventoryItemMap=new HashMap<>();
		
		  if(this.inventoryItemRepository.findById(inventoryItemId).isPresent()) {
			try {
			
				this.inventoryItemRepository.updateInventoryItemDetals(
						inventoryItemDto.getInventoryItemName(),
						inventoryItemDto.getInventoryItemGroup(),
						inventoryItemDto.getInventoryItemQuantity(),
						inventoryItemDto.getInventoryItemUnit(),
						inventoryItemDto.getInventoryItemPrice(),
						inventoryItemDto.getInventoryItemQuantity()*inventoryItemDto.getInventoryItemPrice(),
						inventoryItemId
						);
						
		
			inventoryItemMap.put(AppConstant.statusCode, AppConstant.ok);
			inventoryItemMap.put(AppConstant.status, AppConstant.success);
			inventoryItemMap.put(AppConstant.statusMessage, AppConstant.recordUpdatedSuccessFully +inventoryItemId);
			}
			catch (Exception e) {throw new BadRequest(e.getMessage());}
			}
			else {throw new NotFoundException(AppConstant.noRecordFound + inventoryItemId);}
		
		
		return inventoryItemMap;
	}

}
