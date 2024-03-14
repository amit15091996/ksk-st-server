package com.khadbhandarserver.inventory.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import org.springframework.stereotype.Component;



@Component
public class InvoiceGenerator {
	
	
	private final  String invoicePrefix="INVENTORY";
	
	public  String generateInvoice(LocalDate dateOfInvoice) {
	String [] number= {"0","1","2","3","4","5","6","7","8","9"};
	Random random=new Random();
	
	String inVoiced=invoicePrefix+"-"+dateOfInvoice.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))+"-";
	
    for(int i=0;i<10;i++) {
    	
    inVoiced=inVoiced+number[random.nextInt(number.length)] ;
    	
    }
		return inVoiced;
	}
	
	public String InvoiceNumber(LocalDateTime localDatetime) {
		
		return localDatetime.format(DateTimeFormatter.ofPattern("ddMMyyyyHHmmssSSS"));
	}
	

}
