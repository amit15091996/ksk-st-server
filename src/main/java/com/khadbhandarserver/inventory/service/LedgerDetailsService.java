package com.khadbhandarserver.inventory.service;

import java.util.Map;

import com.khadbhandarserver.inventory.dto.LedgerDetailsDto;

public interface LedgerDetailsService {
	Map<Object, Object> ledgerDetails(LedgerDetailsDto ledgerDetailsDto);

	Map<Object, Object> deleteLedgerDetails(Long Id);

	Map<Object, Object> updateLedgerDetails(Long ledgerId, LedgerDetailsDto ledgerDetailsDto);

	Map<Object, Object> getAllLedgerDetails();

	Map<Object, Object> getLedgerdetailsByName(String customerName);
}
