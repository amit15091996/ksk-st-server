package com.khadbhandarserver.inventory.service;

import java.util.Map;

public interface ProductCategoryService {

	Map<Object, Object> getCategoryWiseData(String CategoryName,String month,String fromDate,String toDate);

	Map<Object, Object> getAllCategory();

}
