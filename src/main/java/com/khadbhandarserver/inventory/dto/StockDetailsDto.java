package com.khadbhandarserver.inventory.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StockDetailsDto {

	@NotBlank(message = "Please enter stock name (Mandatory)")
	private String stockName;
	@NotBlank(message = "Please enter stock name(Mandatory)")
	private String stockGroup;
	@NotNull(message = "Please enter stock quantity(Mandatory)")
	private int stockQuantity;
	@NotBlank(message = "Please enter stock unit(Mandatory)")
	private String stockUnit;
	@NotNull(message = "Please enter stock price(Mandatory)")
	private double stockPrice;
	
}
