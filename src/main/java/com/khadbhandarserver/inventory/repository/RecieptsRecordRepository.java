package com.khadbhandarserver.inventory.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.khadbhandarserver.inventory.entity.RecieptsRecord;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface RecieptsRecordRepository extends JpaRepository<RecieptsRecord, Long> {

	@Modifying(flushAutomatically = true, clearAutomatically = true)
	@Query("UPDATE RecieptsRecord rr  SET rr.recipientName=:recipientName," + "rr.recipientAddress=:recipientAddress,"
			+ "rr.recipientMobileNumber=:recipientMobileNumber," + "rr.recieptDate=:recieptDate,"
			+ "rr.recieptAmount=:recieptAmount," + "rr.recieptPaymentMode=:recieptPaymentMode"
			+ " WHERE rr.recieptId=:recieptId")
	public void updateRecieptRecord(@Param("recipientName") String recipientName,
			@Param("recipientAddress") String recipientAddress,
			@Param("recipientMobileNumber") String recipientMobileNumber, @Param("recieptDate") LocalDate recieptDate,
			@Param("recieptAmount") double recieptAmount, @Param("recieptPaymentMode") String recieptPaymentMode,
			@Param("recieptId") Long recieptId);
}
