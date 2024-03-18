package com.khadbhandarserver.inventory.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.khadbhandarserver.inventory.entity.LedgerDetails;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface LedgerDetailsRepository extends JpaRepository<LedgerDetails, Long> {
	@Modifying(flushAutomatically = true,clearAutomatically = true)
	@Query("UPDATE LedgerDetails ld  SET ld.customerName=:customerName,ld.fatherName=:fatherName,ld.address=:address,ld.mobileNumber=:mobileNumber,ld.customerArea=:customerArea WHERE ld.ledgerId=:ledgerId")
	public void updateLedgerDetals(
			@Param("customerName") String customerName,
			@Param("fatherName") String fatherName,
			@Param("address") String address,
			@Param("mobileNumber") String mobileNumber,
			@Param("customerArea") String customerArea,
			@Param("ledgerId") Long ledgerId
			);
	
	List<LedgerDetails> findByCustomerName(String customerName);

}
