package com.khadbhandarserver.inventory.entity;

import org.hibernate.annotations.Type;

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
@Table(name = "STOCK_DETAILS")
@Entity
public class StockDetails {
	@TableGenerator(allocationSize = 1,initialValue = 0,name = "stock_details_sequence")
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE,generator ="stock_details_sequence" )
	private Long stockId;
	@Column(length = 50,nullable = false,unique = true)
	private String stockName;
	@Column(length = 50,nullable = false)
	private String stockCategory;
	@Column(nullable = false)
	private int stockQuantity;
	@Column(length = 20,nullable = false)
	private String stockUnit;
	@Column(nullable = false)
	private double stockPrice;
	@Column( columnDefinition="Decimal(20,3)")
	private double totalStockAmount;
	

}
