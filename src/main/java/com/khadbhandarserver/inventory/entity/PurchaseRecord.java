package com.khadbhandarserver.inventory.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name ="PURCHASE_RECORD" )
@Entity
public class PurchaseRecord {
	@TableGenerator(allocationSize = 1,initialValue = 0,name = "purchase_record_sequence")
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE,generator ="purchase_record_sequence" )
	private Long purchasedItemId;
	@Column(length = 50,nullable = false)
	private String purchasedItemName;
	@Column(length = 50,nullable = false)
	private String purchasedItemCategory;
	@Column(nullable = false)
	private int purchasedItemQuantity;
	@Column(length = 20,nullable = false)
	private String purchasedItemUnit;
	@Column(nullable = false)
	private double purchasedItemPrice;
	@Column( columnDefinition="Decimal(20,3)")
	private double purchasedItemTotalAmount;
	@Column(nullable = false)
	@JsonFormat(shape = Shape.STRING,pattern = "yyyy-MM-dd")
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	private LocalDate purchaseDate;
	@Column(length = 50,nullable = false)
	private String purchasedFrom;

}
