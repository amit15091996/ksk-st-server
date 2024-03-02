package com.khadbhandarserver.inventory.serviceImplementation;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khadbhandarserver.inventory.dto.LedgerDetailsDto;
import com.khadbhandarserver.inventory.entity.LedgerDetails;
import com.khadbhandarserver.inventory.exception.BadRequest;
import com.khadbhandarserver.inventory.exception.NotFoundException;
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
		ledgerDetails.setCustomerArea(ledgerDetailsDto.getCustomerArea());
		try {
		LedgerDetails LedgerResponse=this.ledgerDetailsRepository.save(ledgerDetails);
		if( LedgerResponse !=null) {
			responseMap.put(AppConstant.statusCode, AppConstant.ok);
			responseMap.put(AppConstant.status, AppConstant.success);
			responseMap.put(AppConstant.statusMessage, AppConstant.dataSubmitedsuccessfully);
		}
		}
		catch(Exception e) {
			throw new BadRequest(e.getMessage());
		}
	   return responseMap;
		
	}

	@Override
	public Map<Object, Object> deleteLedgerDetails(Long Id) {
		Map<Object, Object> responseMap=new HashMap<>();
		
			if(this.ledgerDetailsRepository.findById(Id).isPresent()) {
			this.ledgerDetailsRepository.deleteById(Id);
			responseMap.put(AppConstant.statusCode, AppConstant.ok);
			responseMap.put(AppConstant.status, AppConstant.success);
			responseMap.put(AppConstant.statusMessage, AppConstant.dataDeletedSuccesFully);
			}
			else {
				throw new NotFoundException(AppConstant.noRecordFound + Id);
			}
	
		
		return responseMap;
	}

	@Override
	public Map<Object, Object> updateLedgerDetails(Long ledgerId,LedgerDetailsDto ledgerDetailsDto) {
		Map<Object, Object> responseMap=new HashMap<>();
		
	  if(this.ledgerDetailsRepository.findById(ledgerId).isPresent()) {
		try {
		this.ledgerDetailsRepository.updateLedgerDetals(
					ledgerDetailsDto.getCustomerName(),
					ledgerDetailsDto.getFatherName(),
					ledgerDetailsDto.getAddress(),
					ledgerDetailsDto.getMobileNumber(),
					ledgerDetailsDto.getCustomerArea(),
					ledgerId
					);
	
			responseMap.put(AppConstant.statusCode, AppConstant.ok);
			responseMap.put(AppConstant.status, AppConstant.success);
			responseMap.put(AppConstant.statusMessage, AppConstant.recordUpdatedSuccessFully +ledgerId);
		
		
		}
		catch (Exception e) {
			throw new BadRequest(e.getMessage());
		}
		
		}
		else {
			throw new NotFoundException(AppConstant.noRecordFound + ledgerId);
		}

	
	return responseMap;
	}

	@Override
	public Map<Object, Object> getAllLedgerDetails() {
		  Map<Object, Object> responseMap=new HashMap<>();
			
		  responseMap.put(AppConstant.statusCode, AppConstant.ok);
		  responseMap.put(AppConstant.status, AppConstant.success);
		  responseMap.put(AppConstant.statusMessage, AppConstant.dataFetchedSuccesfully);
		  responseMap.put(AppConstant.response, this.ledgerDetailsRepository.findAll());
			  
			return responseMap;
	}

}
