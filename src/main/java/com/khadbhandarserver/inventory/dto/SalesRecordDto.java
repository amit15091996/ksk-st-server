package com.khadbhandarserver.inventory.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
	@JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd")
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@NotNull(message = "Please enter item sell date(Mandatory)")
	private LocalDate sellDate;

}
