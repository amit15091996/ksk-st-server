package com.khadbhandarserver.inventory.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
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
@Table(name = "SALES_RECORD")
@Entity
public class SalesRecords {

	@TableGenerator(allocationSize = 1,initialValue = 0,name = "sale_record_sequence")
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE,generator ="sale_record_sequence" )
	private Long soldItemId;
	@Column(columnDefinition="LONGTEXT",nullable = false)
	private String soldItemList;
	@Column(nullable = false,columnDefinition = "boolean")
	private boolean isRecieptGenerated;
	@OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval = true,mappedBy = "salesRecords")
	@JsonManagedReference
	private RecieptsRecord recieptsRecord;
	@JsonFormat(shape = Shape.STRING,pattern = "yyyy-MM-dd")
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	private LocalDate sellDate;
	@Column(length = 50,nullable = false)
	private String partyName;
	
}
