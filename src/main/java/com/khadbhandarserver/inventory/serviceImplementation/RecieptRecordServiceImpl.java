package com.khadbhandarserver.inventory.serviceImplementation;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khadbhandarserver.inventory.dto.RecieptsRecordDto;
import com.khadbhandarserver.inventory.entity.RecieptsRecord;
import com.khadbhandarserver.inventory.entity.SalesRecords;
import com.khadbhandarserver.inventory.exception.BadRequest;
import com.khadbhandarserver.inventory.exception.NotFoundException;
import com.khadbhandarserver.inventory.helper.AppConstant;
import com.khadbhandarserver.inventory.repository.SalesRecordRepository;
import com.khadbhandarserver.inventory.repository.RecieptsRecordRepository;
import com.khadbhandarserver.inventory.service.RecieptRecordService;
import com.khadbhandarserver.inventory.util.InvoiceGenerator;

@Service
public class RecieptRecordServiceImpl implements RecieptRecordService {
	
	@Autowired
	private SalesRecordRepository salesRecordRepository;
	
	@Autowired
	private RecieptsRecordRepository RecieptsRecordRepository;
	
	@Autowired
	private InvoiceGenerator invoiceGenerator;
	

	@Override
	public Map<Object, Object> insertRecieptRecord(Long soldItemId,RecieptsRecordDto recieptsRecordDto) {
		  Map<Object, Object> recieptRecordMap=new HashMap<>();		  
		  RecieptsRecord recieptsRecord=new RecieptsRecord();
		  
		  Optional<SalesRecords> salesRecords=this.salesRecordRepository.findById(soldItemId);
		  
		  
		  if(salesRecords.isPresent()) {
			  
			  recieptsRecord.setRecipientName(recieptsRecordDto.getRecipientName());
			  recieptsRecord.setRecieptAmount(recieptsRecordDto.getRecieptAmount());
			  recieptsRecord.setRecieptDate(recieptsRecordDto.getRecieptDate());
			  recieptsRecord.setRecieptPaymentMode(recieptsRecordDto.getRecieptPaymentMode());
			  recieptsRecord.setRecipientAddress(recieptsRecordDto.getRecipientAddress());
			  recieptsRecord.setRecipientMobileNumber(recieptsRecordDto.getRecipientMobileNumber());
			  recieptsRecord.setInvoiceNumber(this.invoiceGenerator.InvoiceNumber(LocalDateTime.now()));
			  recieptsRecord.setSalesRecords(salesRecords.get());	        
			
			try {
				RecieptsRecord recieptsRecordSaved=this.RecieptsRecordRepository.save(recieptsRecord);
			if( recieptsRecordSaved !=null) {
				this.salesRecordRepository.updateSalesRecordRecieptGenerationColumn(true,soldItemId);
				recieptRecordMap.put(AppConstant.statusCode, AppConstant.ok);
				recieptRecordMap.put(AppConstant.status, AppConstant.success);
				recieptRecordMap.put(AppConstant.statusMessage, AppConstant.dataSubmitedsuccessfully);
			}
			}
			catch(Exception e) {
				throw new BadRequest(e.getMessage());
			}
			
		  }else {throw new NotFoundException(AppConstant.noRecordFound + soldItemId);}
		   return recieptRecordMap; 
	}

	@Override
	public Map<Object, Object> deleteRecieptRecord(Long recieptId) {
		 Map<Object, Object> recieptRecordMap=new HashMap<>();
		 
		 Optional<RecieptsRecord> recieptRecord=this.RecieptsRecordRepository.findById(recieptId);
			if(recieptRecord.isPresent()) {
			this.RecieptsRecordRepository.deleteById(recieptId);
			this.salesRecordRepository.updateSalesRecordRecieptGenerationColumn(false,recieptRecord.get().getSalesRecords().getSoldItemId());
			recieptRecordMap.put(AppConstant.statusCode, AppConstant.ok);
			recieptRecordMap.put(AppConstant.status, AppConstant.success);
			recieptRecordMap.put(AppConstant.statusMessage, AppConstant.dataDeletedSuccesFully);
			}
			else {
				throw new NotFoundException(AppConstant.noRecordFound + recieptId);
			}
			return recieptRecordMap;
	}

	@Override
	public Map<Object, Object> updateRecieptRecord(Long recieptId, RecieptsRecordDto recieptsRecordDto) {
		Map<Object, Object> recieptRecordMap=new HashMap<>();
		
		  if(this.RecieptsRecordRepository.findById(recieptId).isPresent()) {
			try {
			
				this.RecieptsRecordRepository.updateRecieptRecord(
						recieptsRecordDto.getRecipientName(),
						recieptsRecordDto.getRecipientAddress(),
						recieptsRecordDto.getRecipientMobileNumber(),
						recieptsRecordDto.getRecieptDate(),
						recieptsRecordDto.getRecieptAmount(),
						recieptsRecordDto.getRecieptPaymentMode(),
						recieptId
						);
			
		
				recieptRecordMap.put(AppConstant.statusCode, AppConstant.ok);
				recieptRecordMap.put(AppConstant.status, AppConstant.success);
				recieptRecordMap.put(AppConstant.statusMessage, AppConstant.recordUpdatedSuccessFully +recieptId);
			}
			catch (Exception e) {throw new BadRequest(e.getMessage());}
			}
			else {throw new NotFoundException(AppConstant.noRecordFound + recieptId);}
		
		return recieptRecordMap;
	}

	@Override
	public Map<Object, Object> getAllRecieptRecord() {
		  Map<Object, Object> recieptRecordMap=new HashMap<>();
			
		  recieptRecordMap.put(AppConstant.statusCode, AppConstant.ok);
		  recieptRecordMap.put(AppConstant.status, AppConstant.success);
		  recieptRecordMap.put(AppConstant.statusMessage, AppConstant.dataFetchedSuccesfully);
		  recieptRecordMap.put(AppConstant.response, this.RecieptsRecordRepository.findAll());
			  
			return recieptRecordMap;
	}

}
