package com.khadbhandarserver.inventory.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.TableGenerator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import com.khadbhandarserver.inventory.entity.ProductCategory;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "INVENTORY_ITEM")
@Entity
public class InventoryItem {
	@TableGenerator(allocationSize = 1,initialValue = 0,name = "inventory_item_details_sequence")
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE,generator ="inventory_item_details_sequence" )
	private Long inventoryItemId;
	@Column(length = 50,nullable = false)
	private String inventoryItemName;
	@Column(length = 50,nullable = false)
	private String inventoryItemCategory;
	@Column(nullable = false)
	private int inventoryItemQuantity;
	@Column(length = 20,nullable = false)
	private String inventoryItemUnit;
	@Column(nullable = false)
	private double inventoryItemPrice;
	@Column( columnDefinition="Decimal(20,2)")
	private double inventoryItemTotalAmount;
	
	
}


