package com.khadbhandarserver.inventory.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.khadbhandarserver.inventory.entity.SalesRecords;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface SalesRecordRepository extends JpaRepository<SalesRecords, Long> {

//	@Modifying(flushAutomatically = true,clearAutomatically = true)
//	@Query("UPDATE SalesRecords sr  SET sr.soldItemName=:soldItemName,sr.soldItemCategory=:soldItemCategory,sr.soldItemQuantity=:soldItemQuantity,sr.partyName=:partyName,sr.soldItemPrice=:soldItemPrice,sr.soldItemTotalAmount=:soldItemTotalAmount,sr.sellDate=:sellDate WHERE sr.soldItemId=:soldItemId")
//	public void updateSalesRecord(
//			@Param("soldItemName") String soldItemName,
//			@Param("soldItemCategory") String soldItemCategory,
//			@Param("soldItemQuantity") int soldItemQuantity,
//			@Param("partyName") String partyName,
//			@Param("soldItemPrice") double soldItemPrice,
//			@Param("soldItemTotalAmount") double soldItemTotalAmount,
//			@Param("sellDate")LocalDate sellDate,
//			@Param("soldItemId") Long soldItemId
//			);
//	
	
	@Modifying(flushAutomatically = true,clearAutomatically = true)
	@Query("UPDATE SalesRecords srr  SET srr.isRecieptGenerated=:isRecieptGenerated WHERE srr.soldItemId=:soldItemId")
	public void updateSalesRecordRecieptGenerationColumn(
			@Param("isRecieptGenerated") boolean isRecieptGenerated,
			@Param("soldItemId") Long soldItemId
			);
	
}
