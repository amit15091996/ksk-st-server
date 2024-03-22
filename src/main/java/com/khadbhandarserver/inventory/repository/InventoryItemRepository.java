package com.khadbhandarserver.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.khadbhandarserver.inventory.entity.InventoryItem;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface InventoryItemRepository extends JpaRepository<InventoryItem, Long> {

	@Modifying(flushAutomatically = true, clearAutomatically = true)
	@Query("UPDATE InventoryItem ii  SET ii.inventoryItemName=:inventoryItemName,ii.inventoryItemCategory=:inventoryItemCategory,ii.inventoryItemQuantity=:inventoryItemQuantity,ii.inventoryItemUnit=:inventoryItemUnit,ii.inventoryItemPrice=:inventoryItemPrice,ii.inventoryItemTotalAmount=:inventoryItemTotalAmount WHERE ii.inventoryItemId=:inventoryItemId")
	public void updateInventoryItemDetals(@Param("inventoryItemName") String inventoryItemName,
			@Param("inventoryItemCategory") String inventoryItemCategory,
			@Param("inventoryItemQuantity") int inventoryItemQuantity,
			@Param("inventoryItemUnit") String inventoryItemUnit,
			@Param("inventoryItemPrice") double inventoryItemPrice,
			@Param("inventoryItemTotalAmount") double inventoryItemTotalAmount,
			@Param("inventoryItemId") Long inventoryItemId);
}
