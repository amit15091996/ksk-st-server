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
public class PyamentsRecordDto {

	@NotBlank(message = "Please enter  payee Name(Mandatory)")
	private String payeeName;
	@NotBlank(message = "Please enter product group(Mandatory)")
	private String paidProductGroup;
	@NotNull(message = "Please enter per unit payment amount(Mandatory)")
	private double paymentAmountPerUnit;
	@NotNull(message = "Please enter payment date (Mandatory)")
	private LocalDate paymentDate;

}
