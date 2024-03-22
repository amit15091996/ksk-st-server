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
public class PurchaseRecordDto {

	@NotBlank(message = "Please enter purchased Item name (Mandatory)")
	private String purchasedItemName;
	@NotBlank(message = "Please enter purchased Item category(Mandatory)")
	private String purchasedItemCategory;
	@NotNull(message = "Please enter purchased Item quantity(Mandatory)")
	private int purchasedItemQuantity;
	@NotBlank(message = "Please enter purchased Item unit(Mandatory)")
	private String purchasedItemUnit;
	@NotNull(message = "Please enter purchased Item price(Mandatory)")
	private double purchasedItemPrice;
	@NotNull(message = "Please enter item purchased date(Mandatory)")
	private LocalDate purchaseDate;
	@NotBlank(message = "Please enter Item purchased from(Mandatory)")
	private String purchasedFrom;

}
