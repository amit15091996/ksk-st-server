package com.khadbhandarserver.inventory.dto;

import java.util.List;


import com.khadbhandarserver.inventory.entity.RecieptsRecord;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AllSalesRecordDto {
	private Long soldItemId;
	private boolean isRecieptGenerated;
	private RecieptsRecord recieptsRecord;
	private List<SalesRecordDto> salesList;

}
