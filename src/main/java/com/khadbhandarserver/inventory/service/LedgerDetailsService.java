package com.khadbhandarserver.inventory.service;

import java.util.Map;

import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import com.khadbhandarserver.inventory.dto.LedgerDetailsDto;
import com.khadbhandarserver.inventory.entity.LedgerDetails;

public interface LedgerDetailsService {
	Map<Object, Object> ledgerDetails(LedgerDetailsDto ledgerDetailsDto);
}
