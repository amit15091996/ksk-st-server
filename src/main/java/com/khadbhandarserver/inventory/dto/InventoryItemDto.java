package com.khadbhandarserver.inventory.dto;

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
public class InventoryItemDto {
	
	@NotBlank(message = "Please enter Inventory Item name (Mandatory)")
	private String inventoryItemName;
	@NotBlank(message = "Please enter Inventory Item category (Mandatory)")
	private String inventoryItemCategory;
	@NotNull(message = "Please enter Inventory Item quantity(Mandatory)")
	private int inventoryItemQuantity;
	@NotBlank(message = "Please enter Inventory Item unit(Mandatory)")
	private String inventoryItemUnit;
	@NotNull(message = "Please enter Inventory Item price(Mandatory)")
	private double inventoryItemPrice;

}
