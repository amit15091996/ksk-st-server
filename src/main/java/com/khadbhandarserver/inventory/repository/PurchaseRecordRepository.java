package com.khadbhandarserver.inventory.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.khadbhandarserver.inventory.entity.PurchaseRecord;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface PurchaseRecordRepository extends JpaRepository<PurchaseRecord, Long> {
	@Modifying(flushAutomatically = true,clearAutomatically = true)
	@Query("UPDATE PurchaseRecord pr  SET pr.purchasedItemName=:purchasedItemName,"
			+ "pr.purchasedItemCategory=:purchasedItemCategory,"
			+ "pr.purchasedItemQuantity=:purchasedItemQuantity,"
			+ "pr.purchasedItemUnit=:purchasedItemUnit,"
			+ "pr.purchasedItemPrice=:purchasedItemPrice,"
			+ "pr.purchasedItemTotalAmount=:purchasedItemTotalAmount,"
			+ "pr.purchaseDate=:purchaseDate,"
			+ "pr.purchasedFrom=:purchasedFrom WHERE pr.purchasedItemId=:purchasedItemId")
	public void updatePurchaseRecord(
			@Param("purchasedItemName") String purchasedItemName,
			@Param("purchasedItemCategory") String purchasedItemCategory,
			@Param("purchasedItemQuantity") int purchasedItemQuantity,
			@Param("purchasedItemUnit") String purchasedItemUnit,
			@Param("purchasedItemPrice") double purchasedItemPrice,
			@Param("purchasedItemTotalAmount") double purchasedItemTotalAmount,
			@Param("purchaseDate")LocalDate purchaseDate,
			@Param("purchasedFrom")String purchasedFrom,
			@Param("purchasedItemId") Long purchasedItemId
			);
	
	
	@Modifying(flushAutomatically = true,clearAutomatically = true)
	@Query("UPDATE PurchaseRecord pr  SET pr.isPaymentDone=:isPaymentDone WHERE pr.purchasedItemId=:purchasedItemId")
	public void updateIsPaymentDoneColumn(
			@Param("isPaymentDone") boolean isPaymentDone,
			@Param("purchasedItemId") Long purchasedItemId
			);
}
