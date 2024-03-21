package com.khadbhandarserver.inventory.serviceImplementation;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.InputStreamContent;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class GoogleDriveServicesImpl {
	
	  private static final String APPLICATION_NAME = "Web client 1";
	  private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
	  private static final String TOKENS_DIRECTORY_PATH = "tokens";
	  private static final Set<String> SCOPES =DriveScopes.all();
	  private static final String CREDENTIALS_FILE_PATH = "C:\\Khad-Bhandar-Server\\ksk-st-server\\src\\main\\resources\\khad-server-gdrive-credentials.json";

	  private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT)throws IOException {
	    InputStream in = new FileInputStream(new java.io.File(CREDENTIALS_FILE_PATH));
	    
	    GoogleClientSecrets clientSecrets =GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));
	    
	   
	    GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
	        HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
	        .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
	        .setAccessType("offline")
	        .build();
	    LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(9098).setCallbackPath("/login/oauth2/code/google").build();
	    Credential credential = new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
	    return credential;
	  }

	  public Drive getInstance() throws GeneralSecurityException, IOException {
	      final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
	      Drive service = new Drive.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
	            .setApplicationName(APPLICATION_NAME)
	            .build();
	      return service;
	   }

	  public  String getfiles() throws IOException, GeneralSecurityException {
		  final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
		  
		 
		  
		    Drive service = getInstance();
		    
		  List<Object> ll=(List<Object>)service.files().list().execute().get("files");
		  
		  ll.forEach(j->{
			  System.out.println(j);
		  });
		  
		    FileList result = service.files().list()
		        .setPageSize(10)
		        .execute();
		    List<File> files = result.getFiles();
		    if (files == null || files.isEmpty()) {
		      System.out.println("No files found.");
		      return "No files found.";
		    } else {
		      return files.toString();
		    }
		  }


		public String uploadFile(MultipartFile file) {
		  try {
		    System.out.println(file.getOriginalFilename());

		     String folderId = "your_folder_id";
		     if (null != file) {
		        File fileMetadata = new File();
		        fileMetadata.setParents(Collections.singletonList(folderId));
		        fileMetadata.setName(file.getOriginalFilename());
		        File uploadFile = getInstance()
		              .files()
		              .create(fileMetadata, new InputStreamContent(
		                    file.getContentType(),
		                    new ByteArrayInputStream(file.getBytes()))
		              )
		              .setFields("id").execute();
		              System.out.println(uploadFile);
		        return uploadFile.getId();
		     }
		  } catch (Exception e) {
		     System.out.printf("Error: "+ e);
		  }
		  return null;
		}
	
	

}
