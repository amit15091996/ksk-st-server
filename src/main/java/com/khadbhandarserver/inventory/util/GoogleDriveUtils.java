package com.khadbhandarserver.inventory.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.time.Instant;
import java.util.Set;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.File;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class GoogleDriveUtils {

	 private static final String APPLICATION_NAME = "Web client 1";
	  private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
	  private static final String TOKENS_DIRECTORY_PATH = "tokens";
	  private static final Set<String> SCOPES =DriveScopes.all();
	  private static final Resource CREDENTIALS_FILE_PATH=new ClassPathResource("khad-server-gdrive-credentials.json");

			  
	  private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT)throws IOException {
	    InputStream in = new FileInputStream(CREDENTIALS_FILE_PATH.getFile());
	    GoogleClientSecrets clientSecrets =GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));
	    GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
	        HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
	        .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
	        .setAccessType("offline")
	        .setApprovalPrompt("force")
	        .build();
	    LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(9098).setCallbackPath("/login/oauth2/code/google").build();
	    Credential credential = new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
	    return credential;
	  }

	  public Drive getGoogleDriveInstance() throws GeneralSecurityException, IOException {
	      final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
	      Drive service = new Drive.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
	            .setApplicationName(APPLICATION_NAME)
	            .build();
	      log.info("-------access token expiration-------- "+ Instant.now().plusMillis(getCredentials(HTTP_TRANSPORT).getExpirationTimeMilliseconds()));
	      return service;
	   }

	  public String createFolderInGdrive(String folderName) {
		  String folderId="";
		    File fileMetadata = new File();
		    fileMetadata.setName(folderName);
		    fileMetadata.setMimeType("application/vnd.google-apps.folder");
		    fileMetadata.setFolderColorRgb("rgb(0,0,128)");
		    try {
		      File file = this.getGoogleDriveInstance().files().create(fileMetadata).setFields("id").execute();
		      folderId=file.getId();
		      } catch (IOException | GeneralSecurityException e) {
		       log.info("Unable to create folder:- " + e.getLocalizedMessage());
		      }
		    return folderId ;
	}
	
	
}
