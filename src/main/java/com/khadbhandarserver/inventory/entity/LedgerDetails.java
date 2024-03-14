package com.khadbhandarserver.inventory.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
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
@Entity
@Table(name = "LEDGER_DETAILS")
public class LedgerDetails {
	
	@TableGenerator(allocationSize = 1,initialValue = 0,name = "ledger_details_sequence")
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE,generator ="ledger_details_sequence" )
	private long ledgerId;
	@Column(length = 50,nullable = false,unique = true)
	private String customerName;
	@Column(length = 50,nullable = false)
	private String fatherName;
	@Column(length = 500,nullable = false)
	private String address;
	@Column(length = 10,nullable = false)
	private String mobileNumber;
	@Column(length = 50,nullable = false)
	private String customerArea;

}
