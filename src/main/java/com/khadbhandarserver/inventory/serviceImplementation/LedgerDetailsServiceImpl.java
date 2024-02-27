package com.khadbhandarserver.inventory.serviceImplementation;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khadbhandarserver.inventory.dto.LedgerDetailsDto;
import com.khadbhandarserver.inventory.entity.LedgerDetails;
import com.khadbhandarserver.inventory.exception.BadRequest;
import com.khadbhandarserver.inventory.helper.AppConstant;
import com.khadbhandarserver.inventory.repository.LedgerDetailsRepository;
import com.khadbhandarserver.inventory.service.LedgerDetailsService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class LedgerDetailsServiceImpl implements LedgerDetailsService {

	@Autowired
	private  LedgerDetailsRepository ledgerDetailsRepository;
	
	@Override
	public Map<Object, Object> ledgerDetails(LedgerDetailsDto ledgerDetailsDto){
		
		Map<Object, Object> responseMap=new HashMap<>();
		LedgerDetails ledgerDetails=new LedgerDetails();
		ledgerDetails.setAddress(ledgerDetailsDto.getAddress());
		ledgerDetails.setCustomerName(ledgerDetailsDto.getCustomerName());
		ledgerDetails.setFatherName(ledgerDetailsDto.getFatherName());
		ledgerDetails.setMobileNumber(ledgerDetailsDto.getMobileNumber());
		try {
		LedgerDetails LedgerResponse=this.ledgerDetailsRepository.save(ledgerDetails);
		if( LedgerResponse !=null) {
			responseMap.put(AppConstant.statusCode, AppConstant.ok);
			responseMap.put(AppConstant.status, AppConstant.success);
			responseMap.put(AppConstant.statusMessage, AppConstant.dataSubmitedsuccessfully);
		}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			throw new BadRequest(e.getMessage());
		}
	   return responseMap;
		
	}

}
