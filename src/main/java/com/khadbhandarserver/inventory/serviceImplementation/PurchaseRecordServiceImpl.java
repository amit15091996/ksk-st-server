package com.khadbhandarserver.inventory.serviceImplementation;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.khadbhandarserver.inventory.dto.PurchaseRecordDto;
import com.khadbhandarserver.inventory.dto.PurchaseRecordResponseDto;
import com.khadbhandarserver.inventory.entity.PurchaseRecord;
import com.khadbhandarserver.inventory.exception.BadRequest;
import com.khadbhandarserver.inventory.exception.NotFoundException;
import com.khadbhandarserver.inventory.helper.AppConstant;
import com.khadbhandarserver.inventory.repository.PurchaseRecordRepository;
import com.khadbhandarserver.inventory.service.PurchaseRecordService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PurchaseRecordServiceImpl implements PurchaseRecordService {

	@Autowired
	private PurchaseRecordRepository purchaseRecordRepository;

	
	private final ObjectMapper objectMapper = new ObjectMapper();
	
	
	@Override
	public Map<Object, Object> insertPurchaseRecord(List<PurchaseRecordDto> purchaseRecordDto) {
		Map<Object, Object> purchaseRecordMap = new HashMap<>();

		try {
			PurchaseRecord purchaseRecord = new PurchaseRecord();
			purchaseRecord.setPurchasedItemList(this.objectMapper.writeValueAsString(purchaseRecordDto));
			purchaseRecord.setPurchasedFrom(purchaseRecordDto.size()>0?purchaseRecordDto.get(0).getPurchasedFrom():"");
			purchaseRecord.setPurchaseDate(purchaseRecordDto.size()>0?purchaseRecordDto.get(0).getPurchaseDate():LocalDate.now());
			
			PurchaseRecord purchaseRecordSaved = this.purchaseRecordRepository.save(purchaseRecord);
			if (purchaseRecordSaved != null) {
				purchaseRecordMap.put(AppConstant.statusCode, AppConstant.ok);
				purchaseRecordMap.put(AppConstant.status, AppConstant.success);
				purchaseRecordMap.put(AppConstant.statusMessage, AppConstant.dataSubmitedsuccessfully);
			}
		} catch (Exception e) {
			throw new BadRequest(e.getMessage());
		}
		return purchaseRecordMap;
	}

	@Override
	public Map<Object, Object> deletePurchaseRecord(Long purchasedItemId) {
		Map<Object, Object> purchaseRecordMap = new HashMap<>();
		if (this.purchaseRecordRepository.findById(purchasedItemId).isPresent()) {
			this.purchaseRecordRepository.deleteById(purchasedItemId);
			purchaseRecordMap.put(AppConstant.statusCode, AppConstant.ok);
			purchaseRecordMap.put(AppConstant.status, AppConstant.success);
			purchaseRecordMap.put(AppConstant.statusMessage, AppConstant.dataDeletedSuccesFully);
		} else {
			throw new NotFoundException(AppConstant.noRecordFound + purchasedItemId);
		}
		return purchaseRecordMap;
	}

	@Override
	public Map<Object, Object> updatePurchaseRecord(Long purchasedItemId, PurchaseRecordDto purchaseRecordDto) {
		Map<Object, Object> purchaseRecordMap = new HashMap<>();

		if (this.purchaseRecordRepository.findById(purchasedItemId).isPresent()) {
			try {

//				this.purchaseRecordRepository.updatePurchaseRecord(purchaseRecordDto.getPurchasedItemName(),
//						purchaseRecordDto.getPurchasedItemCategory(), purchaseRecordDto.getPurchasedItemQuantity(),
//						purchaseRecordDto.getPurchasedItemUnit(), purchaseRecordDto.getPurchasedItemPrice(),
//						purchaseRecordDto.getPurchasedItemQuantity() * purchaseRecordDto.getPurchasedItemPrice(),
//						purchaseRecordDto.getPurchaseDate(), purchaseRecordDto.getPurchasedFrom(), purchasedItemId);

				purchaseRecordMap.put(AppConstant.statusCode, AppConstant.ok);
				purchaseRecordMap.put(AppConstant.status, AppConstant.success);
				purchaseRecordMap.put(AppConstant.statusMessage,
						AppConstant.recordUpdatedSuccessFully + purchasedItemId);
			} catch (Exception e) {
				throw new BadRequest(e.getMessage());
			}
		} else {
			throw new NotFoundException(AppConstant.noRecordFound + purchasedItemId);
		}

		return purchaseRecordMap;
	}

	@Override
	public Map<Object, Object> getAllPurchaseRecord() {
		Map<Object, Object> purchaseRecordMap = new HashMap<>();

		purchaseRecordMap.put(AppConstant.statusCode, AppConstant.ok);
		purchaseRecordMap.put(AppConstant.status, AppConstant.success);
		purchaseRecordMap.put(AppConstant.statusMessage, AppConstant.dataFetchedSuccesfully);
		purchaseRecordMap.put(AppConstant.response, this.purchaseRecordRepository.findAll().stream().map(l->{
			PurchaseRecordResponseDto purchaseRecordResponseDto=new PurchaseRecordResponseDto();
			purchaseRecordResponseDto.setPurchasedId(l.getPurchasedItemId());
			purchaseRecordResponseDto.setPurchaseDate(l.getPurchaseDate());
			purchaseRecordResponseDto.setPurchasedFrom(l.getPurchasedFrom());
			purchaseRecordResponseDto.setPyamentsRecord(l.getPyamentsRecord());
			try {
				purchaseRecordResponseDto.setPurchaseList(this.objectMapper.readValue(l.getPurchasedItemList(),
							this.objectMapper.getTypeFactory().constructCollectionType(List.class, PurchaseRecordDto.class)));
			} catch (JsonProcessingException e) {
				log.info(e.getMessage());
			}
			return purchaseRecordResponseDto;
		}));

		return purchaseRecordMap;
	}

}
