package com.khadbhandarserver.inventory.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
	
	@Value("${mysqlFileLocation}")
	private  String mysqlFileLocation;
	
	 public  boolean backupSqlData(){
		 String backupCommand=this.dumpFileLocation+" "+"-u"+this.databaseUserName+" "+"-p"+this.databasePassword+" "+"--add-drop-table --databases "+this.databaseName+" -r "+this.location;
	       log.info(backupCommand);
	       int processComplete=0;
	       try {
	    	   Process process = Runtime.getRuntime().exec(backupCommand);
		         processComplete = process.waitFor();
	       }
	       catch (IOException | InterruptedException e) {
			log.info(e.getLocalizedMessage());
		}
	      
	        return processComplete == 0;
	    }
	 
	 public boolean retriveBackupSqlData(){
		
		 
		 int exitCode = 0;
		 
		 try {
	            ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "mysql", "--login-path=client", "<", this.location);
	            pb.redirectErrorStream(true);
	            Process process = pb.start();
	            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
	            String line;
	            while ((line = reader.readLine()) != null) {
	               log.info(line);
	            }
	            exitCode = process.waitFor();
	            log.info("MySQL command executed with exit code:-"+exitCode);
	        } catch (IOException | InterruptedException e) {
	           log.info(e.getLocalizedMessage());
	        }	        
	        return exitCode==0;
	 }
	 
	

}
