package com.khadbhandarserver.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.khadbhandarserver.inventory.entity.LedgerDetails;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface LedgerDetailsRepository extends JpaRepository<LedgerDetails, Long> {

}
