package com.khadbhandarserver.inventory.util;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class DatabseUtil {
	
	@Value("${sqlBackUpLocation}")
	private  String location;
	
	@Value("${spring.datasource.username}")
	private  String databaseUserName;
	
	@Value("${spring.datasource.password}")
	private  String databasePassword;
	
	@Value("${spring.datasource.name}")
	private  String databaseName;
	
	@Value("${dumpFilelocation}")
	private  String dumpFileLocation;
	
	 public  boolean backupSqlData()
	            throws IOException, InterruptedException {
		 String backupCommand=this.dumpFileLocation+" "+"-u"+this.databaseUserName+" "+"-p"+this.databasePassword+" "+"--add-drop-table --databases "+this.databaseName+" -r "+this.location;
		 
	
		 
	       log.info(backupCommand);
	       
	        Process process = Runtime.getRuntime().exec(backupCommand);
	        log.info(process.toString());
	        int processComplete = process.waitFor();
	        return processComplete == 0;
	    }
	 
	

}
