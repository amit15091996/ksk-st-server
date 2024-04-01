package com.khadbhandarserver.inventory.serviceImplementation;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khadbhandarserver.inventory.dto.PyamentsRecordDto;
import com.khadbhandarserver.inventory.entity.PyamentsRecord;
import com.khadbhandarserver.inventory.exception.BadRequest;
import com.khadbhandarserver.inventory.exception.NotFoundException;
import com.khadbhandarserver.inventory.helper.AppConstant;
import com.khadbhandarserver.inventory.repository.PyamentsRecordRepository;
import com.khadbhandarserver.inventory.service.PyamentsRecordService;

@Service
public class PyamentsRecordServiceImpl implements PyamentsRecordService {

	
	@Autowired
	private PyamentsRecordRepository pyamentsRecordRepository;

	@Override
	public Map<Object, Object> insertPaymentRecord(Long purchasedItemId, PyamentsRecordDto pyamentsRecordDto) {
		Map<Object, Object> paymentRecordMap = new HashMap<>();
		   PyamentsRecord pyamentsRecord = new PyamentsRecord();
			pyamentsRecord.setPayeeName(pyamentsRecordDto.getPayeeName());
			pyamentsRecord.setPaymentDate(pyamentsRecordDto.getPaymentDate());
			pyamentsRecord.setPaidProductGroup(pyamentsRecordDto.getPaidProductGroup());
			pyamentsRecord.setPaymentAmount(pyamentsRecordDto.getPaymentAmountPerUnit());
			

			try {
				PyamentsRecord PyamentsRecordSaved = this.pyamentsRecordRepository.save(pyamentsRecord);
				if (PyamentsRecordSaved != null) {
					paymentRecordMap.put(AppConstant.statusCode, AppConstant.ok);
					paymentRecordMap.put(AppConstant.status, AppConstant.success);
					paymentRecordMap.put(AppConstant.statusMessage, AppConstant.dataSubmitedsuccessfully);
				}
			} catch (Exception e) {
				throw new BadRequest(e.getMessage());
			}

		
		return paymentRecordMap;
	}

	@Override
	public Map<Object, Object> deletePaymentRecord(Long paymentId) {
		Map<Object, Object> paymentRecordMap = new HashMap<>();

		Optional<PyamentsRecord> paymentsRecord = this.pyamentsRecordRepository.findById(paymentId);

		if (paymentsRecord.isPresent()) {
			this.pyamentsRecordRepository.deleteById(paymentId);
			paymentRecordMap.put(AppConstant.statusCode, AppConstant.ok);
			paymentRecordMap.put(AppConstant.status, AppConstant.success);
			paymentRecordMap.put(AppConstant.statusMessage, AppConstant.dataDeletedSuccesFully);
		} else {
			throw new NotFoundException(AppConstant.noRecordFound + paymentId);
		}
		return paymentRecordMap;
	}

	@Override
	public Map<Object, Object> updatePaymentRecord(Long paymentId, PyamentsRecordDto pyamentsRecordDto) {
		Map<Object, Object> paymentRecordMap = new HashMap<>();

		if (this.pyamentsRecordRepository.findById(paymentId).isPresent()) {
			try {

				this.pyamentsRecordRepository.updatePaymentRecord(pyamentsRecordDto.getPayeeName(),
						pyamentsRecordDto.getPaymentDate(),
						pyamentsRecordDto.getPaymentAmountPerUnit(), pyamentsRecordDto.getPaidProductGroup(),
						paymentId);

				paymentRecordMap.put(AppConstant.statusCode, AppConstant.ok);
				paymentRecordMap.put(AppConstant.status, AppConstant.success);
				paymentRecordMap.put(AppConstant.statusMessage, AppConstant.recordUpdatedSuccessFully + paymentId);
			} catch (Exception e) {
				throw new BadRequest(e.getMessage());
			}
		} else {
			throw new NotFoundException(AppConstant.noRecordFound + paymentId);
		}

		return paymentRecordMap;
	}

	@Override
	public Map<Object, Object> getAllPaymentRecord() {
		Map<Object, Object> paymentRecordMap = new HashMap<>();

		paymentRecordMap.put(AppConstant.statusCode, AppConstant.ok);
		paymentRecordMap.put(AppConstant.status, AppConstant.success);
		paymentRecordMap.put(AppConstant.statusMessage, AppConstant.dataFetchedSuccesfully);
		paymentRecordMap.put(AppConstant.response, this.pyamentsRecordRepository.findAll());

		return paymentRecordMap;
	}

}
