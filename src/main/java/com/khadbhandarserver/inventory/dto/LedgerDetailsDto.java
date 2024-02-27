package com.khadbhandarserver.inventory.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LedgerDetailsDto {
	@NotBlank(message = "Please enter Customer Name(Mandatory)")
	private String customerName;
	@NotBlank(message = "Please enter Father Name(Mandatory)")
	private String fatherName;
	@NotBlank(message = "Please enter Address(Mandatory)")
	private String address;
	@NotBlank(message = "Please enter Mobile Number(Mandatory),mobile number should be 10 digit")
	private String mobileNumber;


}