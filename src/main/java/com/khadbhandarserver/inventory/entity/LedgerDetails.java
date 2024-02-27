package com.khadbhandarserver.inventory.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long ledgerId;
	@Column(length = 50,nullable = false)
	private String customerName;
	@Column(length = 50,nullable = false)
	private String fatherName;
	@Column(length = 500,nullable = false)
	private String address;
	@Column(length = 10,nullable = false)
	private String mobileNumber;

}
