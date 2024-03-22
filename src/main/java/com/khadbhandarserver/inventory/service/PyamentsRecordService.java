package com.khadbhandarserver.inventory.service;

import java.util.Map;
import com.khadbhandarserver.inventory.dto.PyamentsRecordDto;

public interface PyamentsRecordService {
	Map<Object, Object> insertPaymentRecord(Long purchasedItemId, PyamentsRecordDto pyamentsRecordDto);

	Map<Object, Object> deletePaymentRecord(Long paymentId);

	Map<Object, Object> updatePaymentRecord(Long paymentId, PyamentsRecordDto pyamentsRecordDto);

	Map<Object, Object> getAllPaymentRecord();
}
