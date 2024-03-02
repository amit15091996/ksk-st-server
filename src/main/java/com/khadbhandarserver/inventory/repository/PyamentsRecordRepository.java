package com.khadbhandarserver.inventory.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.khadbhandarserver.inventory.entity.PyamentsRecord;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface PyamentsRecordRepository extends JpaRepository<PyamentsRecord, Long>{

	@Modifying(flushAutomatically = true,clearAutomatically = true)
	@Query("UPDATE PyamentsRecord pr  SET pr.payeeName=:payeeName,"
			+ "pr.paymentAmountPerUnit=:paymentAmountPerUnit,"
			+ "pr.paidProductGroup=:paidProductGroup,"
			+ "pr.paymentDate=:paymentDate,"
			+ "pr.paidProductQuantity=:paidProductQuantity,"
			+ "pr.TotalPaidAmount=:TotalPaidAmount"
			+ " WHERE pr.paymentId=:paymentId")
	public void updatePaymentRecord(
			@Param("payeeName") String payeeName,
			@Param("paymentAmountPerUnit") double paymentAmountPerUnit,
			@Param("paidProductGroup") String paidProductGroup,
			@Param("paymentDate")LocalDate paymentDate,
			@Param("paidProductQuantity") int paidProductQuantity,
			@Param("TotalPaidAmount") double TotalPaidAmount,
			@Param("paymentId") Long paymentId
			);
	
	
}
