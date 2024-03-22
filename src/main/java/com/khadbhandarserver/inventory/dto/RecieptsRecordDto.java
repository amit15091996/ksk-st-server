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
public class RecieptsRecordDto {

	@NotBlank(message = "Please enter  Recipient Name(Mandatory)")
	private String recipientName;
	@NotBlank(message = "Please enter Recipient address(Mandatory)")
	private String recipientAddress;
	@NotBlank(message = "Please enter Recipient mobile number (Mandatory)")
	private String recipientMobileNumber;
	@NotNull(message = "Please enter reciept amount(Mandatory)")
	private double recieptAmount;
	@NotNull(message = "Please enter reciept date (Mandatory)")
	private LocalDate recieptDate;
	@NotBlank(message = "Please enter payment mode (Mandatory)")
	private String recieptPaymentMode;

}
