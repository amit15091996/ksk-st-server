package com.khadbhandarserver.inventory.serviceImplementation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khadbhandarserver.inventory.entity.ProductCategory;
import com.khadbhandarserver.inventory.helper.AppConstant;
import com.khadbhandarserver.inventory.repository.ProductCategoryRepository;
import com.khadbhandarserver.inventory.service.ProductCategoryService;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService{

	@Autowired
	private ProductCategoryRepository productCategoryRepository;
	
	@Override
	public Map<Object, Object> getCategoryWiseData(String CategoryName) {
		  Map<Object, Object> CategoryMap=new HashMap<>();
		  
		if(CategoryName.equalsIgnoreCase(AppConstant.ALL)) {
			CategoryMap.put(AppConstant.statusCode, AppConstant.ok);
			 CategoryMap.put(AppConstant.status, AppConstant.success);
			 CategoryMap.put(AppConstant.statusMessage, AppConstant.dataFetchedSuccesfully);
			 CategoryMap.put(AppConstant.response,this.productCategoryRepository.findAll());
			 
			 return CategoryMap;
		}
		  
		else {
			  
			  List<ProductCategory> productCategory=this.productCategoryRepository.findByCategoryName(CategoryName);
			  
			  CategoryMap.put(AppConstant.statusCode, AppConstant.ok);
			  CategoryMap.put(AppConstant.status, AppConstant.success);
			  CategoryMap.put(AppConstant.statusMessage, AppConstant.dataFetchedSuccesfully);
			  CategoryMap.put(AppConstant.response,productCategory.size()>0? productCategory.get(0):productCategory);
			
			  return CategoryMap;
		}
		
	}

	@Override
	public Map<Object, Object> getAllCategory() {
        Map<Object, Object> CategoryMap=new HashMap<>();
		  
		  CategoryMap.put(AppConstant.statusCode, AppConstant.ok);
		  CategoryMap.put(AppConstant.status, AppConstant.success);
		  CategoryMap.put(AppConstant.statusMessage, AppConstant.dataFetchedSuccesfully);
		  CategoryMap.put(AppConstant.response,this.productCategoryRepository.findAll());
		
		  return CategoryMap;
	}

}
