package com.khadbhandarserver.inventory.dto;

import java.util.Map;

import com.khadbhandarserver.inventory.entity.StockDetails;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UpdatedSalesRecord {
	
	private int totalQuantity;
	private StockDetails stockDetails;
  
}
