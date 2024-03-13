package com.khadbhandarserver.inventory.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.khadbhandarserver.inventory.dto.SalesRecordDto;
import com.khadbhandarserver.inventory.dto.UpdatedSalesRecord;
import com.khadbhandarserver.inventory.entity.StockDetails;

@Component
public class SalesUtil {
	
   public UpdatedSalesRecord stockRecordUpdate(List<SalesRecordDto> salesRecordDto,StockDetails stockDetails) {
     
	 UpdatedSalesRecord updateRecord=new UpdatedSalesRecord();
	
   int requestedQuantity=salesRecordDto.stream().filter(f->f.getSoldItemName().equalsIgnoreCase(stockDetails.getStockName())).map(quantity->quantity.getSoldItemQuantity()).reduce(0,(totalquantity,quantity)->totalquantity+quantity);
   updateRecord.setTotalQuantity(requestedQuantity);
   updateRecord.setStockDetails(stockDetails);
		
		return updateRecord;
	}

}
