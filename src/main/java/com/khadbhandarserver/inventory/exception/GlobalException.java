package com.khadbhandarserver.inventory.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import com.khadbhandarserver.inventory.exception.CustomException;

import com.khadbhandarserver.inventory.helper.AppConstant;

@RestControllerAdvice
public class GlobalException {
	

	
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<Map<Object, Object>> noDataAvailable(NotFoundException notFoundException,WebRequest request){
		
		
  CustomException customException=new CustomException(
		  AppConstant.Not_Found,
		  AppConstant.Not_Found_desc,
		  LocalDateTime.now(),
		  notFoundException.getMessage(),
		  request.getDescription(false)
		  );
		
		Map<Object, Object> notFound=new HashMap<>();
		
		notFound.put(AppConstant.statusCode, customException.getStatusCode());
		notFound.put(AppConstant.status, customException.getStatus());
		notFound.put(AppConstant.timeStamp, customException.getTimestamp().toString());
		notFound.put(AppConstant.statusMessage, customException.getMessage());
		notFound.put(AppConstant.description, customException.getDescription());
		return ResponseEntity.ok(notFound);
		
	}
	
	
	
	
	

}
