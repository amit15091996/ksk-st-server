package com.khadbhandarserver.inventory.serviceImplementation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.khadbhandarserver.inventory.dto.AllSalesRecordDto;
import com.khadbhandarserver.inventory.dto.SalesRecordDto;
import com.khadbhandarserver.inventory.entity.ProductCategory;
import com.khadbhandarserver.inventory.entity.SalesRecords;
import com.khadbhandarserver.inventory.helper.AppConstant;
import com.khadbhandarserver.inventory.repository.ProductCategoryRepository;
import com.khadbhandarserver.inventory.repository.SalesRecordRepository;
import com.khadbhandarserver.inventory.service.ProductCategoryService;
import com.khadbhandarserver.inventory.util.StocksUtil;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductCategoryServiceImpl implements ProductCategoryService {

	@Autowired
	private ProductCategoryRepository productCategoryRepository;
	@Autowired
	private SalesRecordRepository salesRecordRepository;
	
	private final ObjectMapper objectMapper = new ObjectMapper();

	@Autowired
	private StocksUtil stocksUtil;
	

	@Override
	public Map<Object, Object> getCategoryWiseData(String CategoryName,String month,String fromDate,String toDate) {
		Map<Object, Object> CategoryMap = new HashMap<>();
		
//------------------------category wise search----------------------------------//		
		
	if(CategoryName !=null) {
		List<SalesRecords> salesRecord = this.salesRecordRepository.findAll();
		
		if(!salesRecord.isEmpty()) {
	    	  if (CategoryName.equalsIgnoreCase(AppConstant.ALL)) {
	  			CategoryMap.put(AppConstant.statusCode, AppConstant.ok);
	  			CategoryMap.put(AppConstant.status, AppConstant.success);
	  			CategoryMap.put(AppConstant.statusMessage, AppConstant.dataFetchedSuccesfully);
	  			CategoryMap.put(AppConstant.response, salesRecord.stream().map(item -> {
	  				AllSalesRecordDto allSalesRecord = new AllSalesRecordDto();
	  				try {
	  					allSalesRecord.setSalesList(this.objectMapper.readValue(item.getSoldItemList(),
	  							this.objectMapper.getTypeFactory().constructCollectionType(List.class, SalesRecordDto.class)));
	  				} catch (JsonProcessingException e) {
	  					log.info(e.getMessage());
	  				}

	  				return allSalesRecord.getSalesList();
	  			}).flatMap(k->k.stream()).collect(Collectors.toList()));

	  			return CategoryMap;
	  		}

	  		else {

	  			CategoryMap.put(AppConstant.statusCode, AppConstant.ok);
	  			CategoryMap.put(AppConstant.status, AppConstant.success);
	  			CategoryMap.put(AppConstant.statusMessage, AppConstant.dataFetchedSuccesfully);
	  			CategoryMap.put(AppConstant.response,salesRecord.stream().map(item -> {
	  				AllSalesRecordDto allSalesRecord = new AllSalesRecordDto();
	  				try {
	  					allSalesRecord.setSalesList(this.objectMapper.readValue(item.getSoldItemList(),
	  							this.objectMapper.getTypeFactory().constructCollectionType(List.class, SalesRecordDto.class)));
	  				} catch (JsonProcessingException e) {
	  					log.info(e.getMessage());
	  				}

	  				return allSalesRecord.getSalesList();
	  			}).flatMap(k->k.stream()).filter(m->m.getSoldItemCategory().equalsIgnoreCase(CategoryName)).collect(Collectors.toList()));

	  			return CategoryMap;
	  		} 
		}
		 else {
	    	  CategoryMap.put(AppConstant.statusCode, AppConstant.ok);
				CategoryMap.put(AppConstant.status, AppConstant.success);
				CategoryMap.put(AppConstant.statusMessage, AppConstant.dataFetchedSuccesfully);
				CategoryMap.put(AppConstant.response, salesRecord);
	    	  
				return CategoryMap;
	      }
		
	}
	
//------------------------category wise search----------------------------------//	

//------------------------month wise search----------------------------------//	
	else if(month !=null) {
		
		log.info(month);
		List<SalesRecords> salesRecord = this.salesRecordRepository.findAll();
		CategoryMap.put(AppConstant.statusCode, AppConstant.ok);
			CategoryMap.put(AppConstant.status, AppConstant.success);
			CategoryMap.put(AppConstant.statusMessage, AppConstant.dataFetchedSuccesfully);
			CategoryMap.put(AppConstant.response, salesRecord.stream().map(item -> {
				AllSalesRecordDto allSalesRecord = new AllSalesRecordDto();
				try {
					allSalesRecord.setSalesList(this.objectMapper.readValue(item.getSoldItemList(),
							this.objectMapper.getTypeFactory().constructCollectionType(List.class, SalesRecordDto.class)));
				
				} catch (JsonProcessingException e) {
					log.info(e.getMessage());
				}

				return allSalesRecord.getSalesList();
			}).flatMap(k->k.stream()).filter(l->l.getSellDate().toString().split("-")[1].equals(month)).collect(Collectors.toList()));

			return CategoryMap;
				
	}
	
//------------------------month wise search----------------------------------//	
	

//------------------------fromdate todate wise search----------------------------------//	
	else if(fromDate !=null && toDate !=null) {
		
		log.info("fromDate:-"+fromDate+" ----- "+"toDate:-"+toDate);
		List<SalesRecords> salesRecord = this.salesRecordRepository.findAll();
		CategoryMap.put(AppConstant.statusCode, AppConstant.ok);
			CategoryMap.put(AppConstant.status, AppConstant.success);
			CategoryMap.put(AppConstant.statusMessage, AppConstant.dataFetchedSuccesfully);
			CategoryMap.put(AppConstant.response, 
					
					this.stocksUtil.getAllSalesBetweenDates(fromDate, toDate, salesRecord.stream().map(item -> {
						AllSalesRecordDto allSalesRecord = new AllSalesRecordDto();
						try {
							allSalesRecord.setSalesList(this.objectMapper.readValue(item.getSoldItemList(),
									this.objectMapper.getTypeFactory().constructCollectionType(List.class, SalesRecordDto.class)));
						
						} catch (JsonProcessingException e) {
							log.info(e.getMessage());
						}

						return allSalesRecord.getSalesList();
					}).flatMap(k->k.stream()).collect(Collectors.toList())));

			
			return CategoryMap;
		
	}
	

//------------------------fromdate todate wise search----------------------------------//	
		
      else {
    	  CategoryMap.put(AppConstant.statusCode, AppConstant.ok);
			CategoryMap.put(AppConstant.status, AppConstant.success);
			CategoryMap.put(AppConstant.statusMessage, AppConstant.dataFetchedSuccesfully);
			CategoryMap.put(AppConstant.response, Collections.EMPTY_LIST);
    	  
			return CategoryMap;
      }
		
	}

	@Override
	public Map<Object, Object> getAllCategory() {
		Map<Object, Object> CategoryMap = new HashMap<>();
		CategoryMap.put(AppConstant.statusCode, AppConstant.ok);
		CategoryMap.put(AppConstant.status, AppConstant.success);
		CategoryMap.put(AppConstant.statusMessage, AppConstant.dataFetchedSuccesfully);
		CategoryMap.put(AppConstant.response, this.productCategoryRepository.findAll());

		return CategoryMap;
	}

}
