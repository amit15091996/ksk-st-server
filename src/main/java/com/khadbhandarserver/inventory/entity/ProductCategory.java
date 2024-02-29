package com.khadbhandarserver.inventory.entity;

import javax.swing.plaf.synth.SynthListUI;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.TableGenerator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "CATEGORY_DETAILS")
@Entity
public class ProductCategory {
	
	@TableGenerator(allocationSize = 1,initialValue = 0,name = "category_details_sequence")
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE,generator ="category_details_sequence" )
	private long categoryId;
	@Column(length = 50,nullable = false)
	private String CategoryName;
	@Column(length = 50,nullable = false)
	private String categoryDesc;
	
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval = true)
	@JoinColumn(name = "inventoryItemGroup",referencedColumnName = "CategoryName")
	private List<InventoryItem> inventoryItem;

}
