package com.khadbhandarserver.inventory.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SalesRecordDto {

	@NotBlank(message = "Please enter sold Item name (Mandatory)")
	private String soldItemName;
	@NotBlank(message = "Please enter sold Item category(Mandatory)")
	private String soldItemCategory;
	@NotNull(message = "Please enter sold Item quantity(Mandatory)")
	private int soldItemQuantity;
	@NotBlank(message = "Please enter party name(sold to)(Mandatory)")
	private String partyName;
	@NotNull(message = "Please enter sold Item price(Mandatory)")
	private double soldItemPrice;
	@NotNull(message = "Please enter item sell date(Mandatory)")
	private LocalDate sellDate;
	
}
