package com.khadbhandarserver.inventory.serviceImplementation;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khadbhandarserver.inventory.dto.PyamentsRecordDto;
import com.khadbhandarserver.inventory.entity.PurchaseRecord;
import com.khadbhandarserver.inventory.entity.PyamentsRecord;
import com.khadbhandarserver.inventory.exception.BadRequest;
import com.khadbhandarserver.inventory.exception.NotFoundException;
import com.khadbhandarserver.inventory.helper.AppConstant;
import com.khadbhandarserver.inventory.repository.PurchaseRecordRepository;
import com.khadbhandarserver.inventory.repository.PyamentsRecordRepository;
import com.khadbhandarserver.inventory.service.PyamentsRecordService;

@Service
public class PyamentsRecordServiceImpl implements PyamentsRecordService {
	
	@Autowired
	private PurchaseRecordRepository purchaseRecordRepository;
	
	@Autowired
	private PyamentsRecordRepository pyamentsRecordRepository;

	@Override
	public Map<Object, Object> insertPaymentRecord(Long purchasedItemId, PyamentsRecordDto pyamentsRecordDto) {
		  Map<Object, Object> paymentRecordMap=new HashMap<>();		  
		  PyamentsRecord pyamentsRecord=new PyamentsRecord();
		  
		  Optional<PurchaseRecord> purchaseRecord=this.purchaseRecordRepository.findById(purchasedItemId);
		  
		  
		  if(purchaseRecord.isPresent()) {
			  pyamentsRecord.setPayeeName(pyamentsRecordDto.getPayeeName());
			  pyamentsRecord.setPaymentDate(pyamentsRecordDto.getPaymentDate());
			  pyamentsRecord.setPaidProductGroup(pyamentsRecordDto.getPaidProductGroup());
			  pyamentsRecord.setPaidProductQuantity(pyamentsRecordDto.getPaidProductQuantity());
			  pyamentsRecord.setPaymentAmountPerUnit(pyamentsRecordDto.getPaymentAmountPerUnit());
			  pyamentsRecord.setTotalPaidAmount(pyamentsRecordDto.getPaidProductQuantity()*pyamentsRecordDto.getPaymentAmountPerUnit());
			  pyamentsRecord.setPurchaseRecord(purchaseRecord.get()); 
			
			try {
				PyamentsRecord PyamentsRecordSaved=this.pyamentsRecordRepository.save(pyamentsRecord);
			if( PyamentsRecordSaved !=null) {
				this.purchaseRecordRepository.updateIsPaymentDoneColumn(true, purchasedItemId);
				paymentRecordMap.put(AppConstant.statusCode, AppConstant.ok);
				paymentRecordMap.put(AppConstant.status, AppConstant.success);
				paymentRecordMap.put(AppConstant.statusMessage, AppConstant.dataSubmitedsuccessfully);
			}
			}
			catch(Exception e) {
				throw new BadRequest(e.getMessage());
			}
			
		  }else {throw new NotFoundException(AppConstant.noRecordFound + purchasedItemId );}
		   return paymentRecordMap; 
	}

	@Override
	public Map<Object, Object> deletePaymentRecord(Long paymentId) {
		 Map<Object, Object> paymentRecordMap=new HashMap<>();
		 
		 Optional<PyamentsRecord> paymentsRecord=this.pyamentsRecordRepository.findById(paymentId);
		 
			if(paymentsRecord.isPresent()) {
			this.pyamentsRecordRepository.deleteById(paymentId);
			this.purchaseRecordRepository.updateIsPaymentDoneColumn(false, paymentsRecord.get().getPurchaseRecord().getPurchasedItemId());
			paymentRecordMap.put(AppConstant.statusCode, AppConstant.ok);
			paymentRecordMap.put(AppConstant.status, AppConstant.success);
			paymentRecordMap.put(AppConstant.statusMessage, AppConstant.dataDeletedSuccesFully);
			}
			else {
				throw new NotFoundException(AppConstant.noRecordFound + paymentId);
			}
			return paymentRecordMap;
	}

	@Override
	public Map<Object, Object> updatePaymentRecord(Long paymentId, PyamentsRecordDto pyamentsRecordDto) {
		Map<Object, Object> paymentRecordMap=new HashMap<>();
		
		  if(this.pyamentsRecordRepository.findById(paymentId).isPresent()) {
			try {
			
				this.pyamentsRecordRepository.updatePaymentRecord(
						pyamentsRecordDto.getPayeeName(),
						pyamentsRecordDto.getPaymentAmountPerUnit(),
						pyamentsRecordDto.getPaidProductGroup(),
						pyamentsRecordDto.getPaymentDate(),
						pyamentsRecordDto.getPaidProductQuantity(),
						pyamentsRecordDto.getPaidProductQuantity()*pyamentsRecordDto.getPaymentAmountPerUnit(),
						paymentId
						);
			
		
				paymentRecordMap.put(AppConstant.statusCode, AppConstant.ok);
				paymentRecordMap.put(AppConstant.status, AppConstant.success);
				paymentRecordMap.put(AppConstant.statusMessage, AppConstant.recordUpdatedSuccessFully +paymentId);
			}
			catch (Exception e) {throw new BadRequest(e.getMessage());}
			}
			else {throw new NotFoundException(AppConstant.noRecordFound + paymentId);}
		
		return paymentRecordMap;
	}

	@Override
	public Map<Object, Object> getAllPaymentRecord() {
		  Map<Object, Object> paymentRecordMap=new HashMap<>();
			
		  paymentRecordMap.put(AppConstant.statusCode, AppConstant.ok);
		  paymentRecordMap.put(AppConstant.status, AppConstant.success);
		  paymentRecordMap.put(AppConstant.statusMessage, AppConstant.dataFetchedSuccesfully);
		  paymentRecordMap.put(AppConstant.response, this.pyamentsRecordRepository.findAll());
			  
			return paymentRecordMap;
	}


}
