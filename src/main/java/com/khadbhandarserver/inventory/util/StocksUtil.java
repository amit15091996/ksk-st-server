package com.khadbhandarserver.inventory.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.khadbhandarserver.inventory.dto.SalesRecordDto;

@Component
public class StocksUtil {

	public List<SalesRecordDto> getAllSalesBetweenDates(String  fromDate,String toDate,List<SalesRecordDto> salesRecordData){
		
		
		
		
		return salesRecordData.stream().filter(f ->
		           
		          f.getSellDate().isEqual(LocalDate.parse(fromDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"))) ||
		
		         (f.getSellDate().isAfter(LocalDate.parse(fromDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"))) &&
				
				f.getSellDate().isBefore(LocalDate.parse(toDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"))))   ||
		        f.getSellDate().isEqual(LocalDate.parse(toDate, DateTimeFormatter.ofPattern("yyyy-MM-dd")))
				
				).collect(Collectors.toList());

		
	}
	
	

}
