package com.khadbhandarserver.inventory.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.khadbhandarserver.inventory.dto.SalesRecordDto;
import com.khadbhandarserver.inventory.entity.StockDetails;

@Component
public class SalesUtil {
	
   public Map<Object, Object> stockRecordUpdate(List<SalesRecordDto> salesRecordDto,StockDetails stockDetails) {
     
	Map<Object, Object> salesMap=new HashMap<>();
	   
   int requestedQuantity=salesRecordDto.stream().filter(f->f.getSoldItemName().equalsIgnoreCase(stockDetails.getStockName())).map(quantity->quantity.getSoldItemQuantity()).reduce(0,(totalquantity,quantity)->totalquantity+quantity);
	   
    salesMap.put("requestedQuantity",requestedQuantity );
    salesMap.put("stockName",stockDetails );
		
		return salesMap;
	}

}
