package com.khadbhandarserver.inventory.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.khadbhandarserver.inventory.dto.LedgerDetailsDto;
import com.khadbhandarserver.inventory.service.LedgerDetailsService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("khad/bhandar/inventory")
public class InventoryController {
	
	@Autowired
	private LedgerDetailsService ledgerDetailsService;
	
	@PostMapping("/insert-ledger-details")
	public ResponseEntity<Map<Object, Object>> saveLedgerDetails(@RequestBody @Valid LedgerDetailsDto ledgerDetailsDto){
		
		return ResponseEntity.ok(this.ledgerDetailsService.ledgerDetails(ledgerDetailsDto));
	}
	
	@PutMapping("/update-ledger-details/{ledgerId}")
	public ResponseEntity<Map<Object, Object>> updateLedgerDetails(@PathVariable(name = "ledgerId") Long ledgerId,@RequestBody @Valid LedgerDetailsDto ledgerDetailsDto){
		
		return ResponseEntity.ok(this.ledgerDetailsService.updateLedgerDetails(ledgerId, ledgerDetailsDto));
	}
	
	@DeleteMapping("/delete-ledger-details/{ledgerId}")
	public ResponseEntity<Map<Object, Object>> DeleteLedgerDetails(@PathVariable(name = "ledgerId") Long ledgerId){
		
		return ResponseEntity.ok(this.ledgerDetailsService.deleteLedgerDetails(ledgerId));
	}

}
