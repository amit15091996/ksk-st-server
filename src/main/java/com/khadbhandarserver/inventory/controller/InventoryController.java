package com.khadbhandarserver.inventory.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.khadbhandarserver.inventory.dto.InventoryItemDto;
import com.khadbhandarserver.inventory.dto.LedgerDetailsDto;
import com.khadbhandarserver.inventory.dto.PurchaseRecordDto;
import com.khadbhandarserver.inventory.dto.PyamentsRecordDto;
import com.khadbhandarserver.inventory.dto.RecieptsRecordDto;
import com.khadbhandarserver.inventory.dto.SalesRecordDto;
import com.khadbhandarserver.inventory.dto.StockDetailsDto;
import com.khadbhandarserver.inventory.service.InventoryItemService;
import com.khadbhandarserver.inventory.service.LedgerDetailsService;
import com.khadbhandarserver.inventory.service.ProductCategoryService;
import com.khadbhandarserver.inventory.service.SalesRecordService;
import com.khadbhandarserver.inventory.service.StockDetailsService;
import com.khadbhandarserver.inventory.serviceImplementation.SalesRecordServiceImpl;
import com.khadbhandarserver.inventory.service.PurchaseRecordService;
import com.khadbhandarserver.inventory.service.PyamentsRecordService;
import com.khadbhandarserver.inventory.service.RecieptRecordService;

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
	private RecieptRecordService recieptRecordService;
	
	@Autowired
	private PurchaseRecordService purchaseRecordService;
	
	@Autowired
	private PyamentsRecordService pyamentsRecordService;
	
	@Autowired
	private ProductCategoryService productCategoryService;
	
	
	
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
	
	
	@GetMapping("/get-all-ledger-details")
	public ResponseEntity<Map<Object, Object>> getAllLedgerDetailsRecord(){
		
		return ResponseEntity.ok(this.ledgerDetailsService.getAllLedgerDetails());
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
	
	
	@GetMapping("/get-all-stock-details")
	public ResponseEntity<Map<Object, Object>> getAllStockDetailsData(){
		
		return ResponseEntity.ok(this.stockDetailsService.getAllStockDetails());
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
	
	@GetMapping("/get-all-inventory-item")
	public ResponseEntity<Map<Object, Object>> getAllInventoryItemRecord(){
		
		return ResponseEntity.ok(this.inventoryItemService.getAllInventoryItem());
	}
	
	
	
	@PostMapping("/insert-sales-record")
	public ResponseEntity<Map<Object, Object>> saveSalesRecord(@RequestBody @Valid  List<SalesRecordDto>salesRecordDto) throws JsonProcessingException{
		
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
	
	@GetMapping("/get-all-sales-record")
	public ResponseEntity<Map<Object, Object>> getAllSalesRecord() throws JsonMappingException, JsonProcessingException{
		
		return ResponseEntity.ok(this.salesRecordService.getAllSoldItem());
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
	
	@GetMapping("/get-all-purchase-record")
	public ResponseEntity<Map<Object, Object>> getAllPurchaseRecordData(){
		
		return ResponseEntity.ok(this.purchaseRecordService.getAllPurchaseRecord());
	}
	
	
	
	@PostMapping("/insert-reciept-record/{soldItemId}")
	public ResponseEntity<Map<Object, Object>> saveRecieptRecord(@PathVariable("soldItemId")Long soldItemId ,@RequestBody @Valid RecieptsRecordDto recieptsRecordDto){
		
		return ResponseEntity.ok(this.recieptRecordService.insertRecieptRecord(soldItemId, recieptsRecordDto));
	}
	
	@PutMapping("/update-reciept-record/{recieptId}")
	public ResponseEntity<Map<Object, Object>> updateRecieptRecord(@PathVariable(name = "recieptId") Long recieptId,@RequestBody @Valid RecieptsRecordDto recieptsRecordDto){
		
		return ResponseEntity.ok(this.recieptRecordService.updateRecieptRecord(recieptId, recieptsRecordDto));
	}

	@DeleteMapping("/delete-reciept-record/{recieptId}")
	public ResponseEntity<Map<Object, Object>> deleteRecieptRecord(@PathVariable(name = "recieptId") Long recieptId){
		
		return ResponseEntity.ok(this.recieptRecordService.deleteRecieptRecord(recieptId));
	}
	
	@GetMapping("/get-all-reciept-record")
	public ResponseEntity<Map<Object, Object>> getAllRecieptRecord(){
		
		return ResponseEntity.ok(this.recieptRecordService.getAllRecieptRecord());
	}
	
	
	
	@PostMapping("/insert-payment-record/{purchasedItemId}")
	public ResponseEntity<Map<Object, Object>> savePaymentRecord(@PathVariable("purchasedItemId")Long purchasedItemId ,@RequestBody @Valid PyamentsRecordDto pyamentsRecordDto){
		
		return ResponseEntity.ok(this.pyamentsRecordService.insertPaymentRecord(purchasedItemId, pyamentsRecordDto));
	}
	
	@PutMapping("/update-payment-record/{paymentId}")
	public ResponseEntity<Map<Object, Object>> updatePaymentRecord(@PathVariable(name = "paymentId") Long paymentId,@RequestBody @Valid PyamentsRecordDto pyamentsRecordDto){
		
		return ResponseEntity.ok(this.pyamentsRecordService.updatePaymentRecord(paymentId, pyamentsRecordDto));
	}

	@DeleteMapping("/delete-payment-record/{paymentId}")
	public ResponseEntity<Map<Object, Object>> deletePaymentRecord(@PathVariable(name = "paymentId") Long paymentId){
		
		return ResponseEntity.ok(this.pyamentsRecordService.deletePaymentRecord(paymentId));
	}
	
	@GetMapping("/get-all-payment-record")
	public ResponseEntity<Map<Object, Object>> getAllPaymentRecordData(){
		
		return ResponseEntity.ok(this.pyamentsRecordService.getAllPaymentRecord());
	}
	
	@GetMapping("/get-product-via-category/{categoryName}")
	public ResponseEntity<Map<Object, Object>> getDataViaCategory(@PathVariable("categoryName") String CategoryName ){
		
		return ResponseEntity.ok(this.productCategoryService.getCategoryWiseData(CategoryName));
	}
	
	@GetMapping("/get-all-category")
	public ResponseEntity<Map<Object, Object>> getAllCategory(){
		
		return ResponseEntity.ok(this.productCategoryService.getAllCategory());
	}
	
	@GetMapping("/get-all-sales-record-by-party-name/{partyName}")
	public ResponseEntity<Map<Object, Object>> getAllSalesRecordViaPartyName(@PathVariable("partyName") String partyName) throws JsonMappingException, JsonProcessingException{
		
		log.info(partyName);
		return ResponseEntity.ok(this.salesRecordService.getSoldItemByPartyName(partyName));
	}
	
	
	
	
}
