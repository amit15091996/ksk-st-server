package com.khadbhandarserver.inventory.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.khadbhandarserver.inventory.dto.LedgerDetailsDto;
import com.khadbhandarserver.inventory.service.LedgerDetailsService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("khad/bhandar/inventory")
public class InventoryController {
	
	@Autowired
	private LedgerDetailsService ledgerDetailsService;
	
	@PostMapping("/insert-ledger")
	public ResponseEntity<Map<Object, Object>> saveLedgerDetails(@RequestBody @Valid LedgerDetailsDto ledgerDetailsDto){
		
		return ResponseEntity.ok(this.ledgerDetailsService.ledgerDetails(ledgerDetailsDto));
	}

}
