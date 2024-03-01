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
import com.khadbhandarserver.inventory.dto.InventoryItemDto;
import com.khadbhandarserver.inventory.dto.LedgerDetailsDto;
import com.khadbhandarserver.inventory.dto.PurchaseRecordDto;
import com.khadbhandarserver.inventory.dto.SalesRecordDto;
import com.khadbhandarserver.inventory.dto.StockDetailsDto;
import com.khadbhandarserver.inventory.service.InventoryItemService;
import com.khadbhandarserver.inventory.service.LedgerDetailsService;
import com.khadbhandarserver.inventory.service.SalesRecordService;
import com.khadbhandarserver.inventory.service.StockDetailsService;
import com.khadbhandarserver.inventory.service.PurchaseRecordService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("khad/bhandar/inventory")
public class InventoryController {
	
	@Autowired
	private LedgerDetailsService ledgerDetailsService;
	
	@Autowired
	private StockDetailsService stockDetailsService;
	
	@Autowired
	private InventoryItemService inventoryItemService;
	
	@Autowired
	private SalesRecordService salesRecordService;
	
	@Autowired
	private PurchaseRecordService purchaseRecordService;
	
	@PostMapping("/insert-ledger-details")
	public ResponseEntity<Map<Object, Object>> saveLedgerDetails(@RequestBody @Valid LedgerDetailsDto ledgerDetailsDto){
		
		return ResponseEntity.ok(this.ledgerDetailsService.ledgerDetails(ledgerDetailsDto));
	}
	
	@PutMapping("/update-ledger-details/{ledgerId}")
	public ResponseEntity<Map<Object, Object>> updateLedgerDetails(@PathVariable(name = "ledgerId") Long ledgerId,@RequestBody @Valid LedgerDetailsDto ledgerDetailsDto){
		
		return ResponseEntity.ok(this.ledgerDetailsService.updateLedgerDetails(ledgerId, ledgerDetailsDto));
	}
	
	@DeleteMapping("/delete-ledger-details/{ledgerId}")
	public ResponseEntity<Map<Object, Object>> deleteLedgerDetails(@PathVariable(name = "ledgerId") Long ledgerId){
		
		return ResponseEntity.ok(this.ledgerDetailsService.deleteLedgerDetails(ledgerId));
	}
	
	@PostMapping("/insert-stock-details")
	public ResponseEntity<Map<Object, Object>> saveStockDetails(@RequestBody @Valid StockDetailsDto stockDetailsDto){
		
		return ResponseEntity.ok(this.stockDetailsService.insertStockDetails(stockDetailsDto));
	}
	
	@PutMapping("/update-stock-details/{stockId}")
	public ResponseEntity<Map<Object, Object>> updateStockDetails(@PathVariable(name = "stockId") Long stockId,@RequestBody @Valid StockDetailsDto stockDetailsDto){
		
		return ResponseEntity.ok(this.stockDetailsService.updateStockDetails(stockId, stockDetailsDto));
	}

	@DeleteMapping("/delete-stock-details/{stockId}")
	public ResponseEntity<Map<Object, Object>> deleteStockDetails(@PathVariable(name = "stockId") Long stockId){
		
		return ResponseEntity.ok(this.stockDetailsService.deleteStockDetails(stockId));
	}
	
	
	@PostMapping("/insert-inventory-item")
	public ResponseEntity<Map<Object, Object>> saveInventoryItem(@RequestBody @Valid InventoryItemDto inventoryItemDto){
		
		
		
		return ResponseEntity.ok(this.inventoryItemService.insertInventoryItem(inventoryItemDto));
	}
	
	@PutMapping("/update-inventory-item/{inventoryId}")
	public ResponseEntity<Map<Object, Object>> updateInventoryItem(@PathVariable(name = "inventoryId") Long inventoryId,@RequestBody @Valid InventoryItemDto inventoryItemDto){
		
		return ResponseEntity.ok(this.inventoryItemService.updateInventoryItem(inventoryId, inventoryItemDto));
	}

	@DeleteMapping("/delete-inventory-item/{inventoryId}")
	public ResponseEntity<Map<Object, Object>> deleteInventoryItem(@PathVariable(name = "inventoryId") Long inventoryId){
		
		return ResponseEntity.ok(this.inventoryItemService.deleteInventoryItem(inventoryId));
	}
	
	
	
	@PostMapping("/insert-sales-record")
	public ResponseEntity<Map<Object, Object>> saveSalesRecord(@RequestBody @Valid SalesRecordDto salesRecordDto){
		
		return ResponseEntity.ok(this.salesRecordService.insertSoldItem(salesRecordDto));
	}
	
	@PutMapping("/update-sales-record/{soldItemId}")
	public ResponseEntity<Map<Object, Object>> updateSalesRecord(@PathVariable(name = "soldItemId") Long soldItemId,@RequestBody @Valid SalesRecordDto salesRecordDto){
		
		return ResponseEntity.ok(this.salesRecordService.updateSoldItem(soldItemId, salesRecordDto));
	}

	@DeleteMapping("/delete-sales-record/{soldItemId}")
	public ResponseEntity<Map<Object, Object>> deleteSalesRecord(@PathVariable(name = "soldItemId") Long soldItemId){
		
		return ResponseEntity.ok(this.salesRecordService.deleteSoldItem(soldItemId));
	}
	
	
	
	@PostMapping("/insert-purchase-record")
	public ResponseEntity<Map<Object, Object>> savePurchaseRecord(@RequestBody @Valid PurchaseRecordDto purchaseRecordDto){
		
		return ResponseEntity.ok(this.purchaseRecordService.insertPurchaseRecord(purchaseRecordDto));
	}
	
	@PutMapping("/update-purchase-record/{purchasedItemId}")
	public ResponseEntity<Map<Object, Object>> updatePurchaseRecord(@PathVariable(name = "purchasedItemId") Long purchasedItemId,@RequestBody @Valid PurchaseRecordDto purchaseRecordDto){
		
		return ResponseEntity.ok(this.purchaseRecordService.updatePurchaseRecord(purchasedItemId, purchaseRecordDto));
	}

	@DeleteMapping("/delete-purchase-record/{purchasedItemId}")
	public ResponseEntity<Map<Object, Object>> deletePurchaseRecord(@PathVariable(name = "purchasedItemId") Long purchasedItemId){
		
		return ResponseEntity.ok(this.purchaseRecordService.deletePurchaseRecord(purchasedItemId));
	}
	
	
	
	
	
	
	
}
