package com.khadbhandarserver.inventory.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.TableGenerator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "RECIEPT_RECORD")
@Entity
public class RecieptsRecord {

	@TableGenerator(allocationSize = 1, initialValue = 0, name = "reciept_details_sequence")
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "reciept_details_sequence")
	private long recieptId;
	@Column(length = 50, nullable = false)
	private String recipientName;
	@Column(length = 500, nullable = false)
	private String recipientAddress;
	@Column(length = 10, nullable = false)
	private String recipientMobileNumber;
	@Column(nullable = false)
	@JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd")
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	private LocalDate recieptDate;
	@Column(nullable = false)
	private double recieptAmount;
	@Column(nullable = false, length = 20)
	private String recieptPaymentMode;
	@Column(length = 30, nullable = false)
	private String invoiceNumber;
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonBackReference
	private SalesRecords salesRecords;

}
