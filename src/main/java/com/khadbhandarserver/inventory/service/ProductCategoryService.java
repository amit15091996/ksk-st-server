package com.khadbhandarserver.inventory.service;

import java.util.Map;


public interface ProductCategoryService {

	Map<Object, Object> getCategoryWiseData(String CategoryName);
	Map<Object, Object> getAllCategory();
	
}
