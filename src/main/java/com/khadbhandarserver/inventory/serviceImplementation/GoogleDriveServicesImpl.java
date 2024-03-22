package com.khadbhandarserver.inventory.serviceImplementation;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.http.InputStreamContent;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.khadbhandarserver.inventory.dto.googledrive.GoogleDriveFilesDto;
import com.khadbhandarserver.inventory.exception.InternalServerError;
import com.khadbhandarserver.inventory.helper.AppConstant;
import com.khadbhandarserver.inventory.service.GoogleDriveServices;
import com.khadbhandarserver.inventory.util.GoogleDriveUtils;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class GoogleDriveServicesImpl implements GoogleDriveServices {

	@Autowired
	private GoogleDriveUtils googleDriveUtils;
	private ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public Map<Object, Object> getAllFilesPresentInGdrive() {
		Map<Object, Object> googDriveFilesMap = new HashMap<>();

		System.out.println("---------is successful-------- "+this.retriveFileFromGdrive());
		
		try {
			Drive googleService = this.googleDriveUtils.getGoogleDriveInstance();
			if (googleService.files().list().execute().get("files") instanceof List) {
				List<GoogleDriveFilesDto> googleDriveFiles = this.objectMapper
						.readerForListOf(GoogleDriveFilesDto.class).readValue(googleService.files().list().execute().get("files").toString());
				googDriveFilesMap.put(AppConstant.statusCode, AppConstant.ok);
				googDriveFilesMap.put(AppConstant.status, AppConstant.success);
				googDriveFilesMap.put(AppConstant.statusMessage, AppConstant.dataFetchedSuccesfully);
				googDriveFilesMap.put(AppConstant.response,
						googleDriveFiles != null ? googleDriveFiles : Collections.EMPTY_LIST);
			}
		} catch (IOException | GeneralSecurityException e) {
			log.info("-------error occured while fetching data from  gdrive---- " + e.getLocalizedMessage());
			throw new InternalServerError("error occured while fetching data from gdrive");

		}

		return googDriveFilesMap;
	}

	@Override
	public Map<Object, Object> uploadFileToGdrive(MultipartFile multipartFile) {
		
	
		String query = "mimeType='application/vnd.google-apps.folder'" + " and " + "name='khad-server-backup'";
		Map<Object, Object> googDriveFilesMap = new HashMap<>();
		try {
			Drive googleService = this.googleDriveUtils.getGoogleDriveInstance();
			List<GoogleDriveFilesDto> googleDriveFilesviaQuery = objectMapper.readerForListOf(GoogleDriveFilesDto.class)
					.readValue(googleService.files().list().setQ(query).execute()
							.get("files").toString());
			String folderId = googleDriveFilesviaQuery.size() > 0 ? googleDriveFilesviaQuery.get(0).getId()
					: this.googleDriveUtils.createFolderInGdrive("khad-server-backup");
			File fileMetadata = new File();
			fileMetadata.setParents(Collections.singletonList(folderId));
			fileMetadata.setName(multipartFile.getOriginalFilename()+"-"+LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy-HH-mm-ss")));
			File uploadFile = googleService.files()
					.create(fileMetadata, new InputStreamContent(multipartFile.getContentType(),
							new ByteArrayInputStream(multipartFile.getBytes())))
					.setFields("id").execute();
			
			googDriveFilesMap.put(AppConstant.statusCode, AppConstant.ok);
			googDriveFilesMap.put(AppConstant.status, AppConstant.success);
			googDriveFilesMap.put(AppConstant.statusMessage, AppConstant.dataSubmitedsuccessfully);
			googDriveFilesMap.put(AppConstant.submitedFileId, uploadFile.getId());

		} catch (Exception e) {
			throw new InternalServerError("sorry something went wrong while inserting file into  gdrive");

		}
		return googDriveFilesMap;
	}
	
	
	public boolean retriveFileFromGdrive() {
		
		String query = "mimeType='application/x-sql'" + " and " + "name='22-03-2024-16-35-07-khad-server.sql'";
		
			try {
				Drive googleService = this.googleDriveUtils.getGoogleDriveInstance();
				
				List<GoogleDriveFilesDto> googleDriveFilesviaQuery = objectMapper.readerForListOf(GoogleDriveFilesDto.class)
						.readValue(googleService.files().list().setQ(query).execute()
								.get("files").toString());		
				
				
	byte[] is=googleService.files().get(googleDriveFilesviaQuery.size()>0?googleDriveFilesviaQuery.get(0).getId():"").executeMediaAsInputStream().readAllBytes();
	FileOutputStream fos=new FileOutputStream(new java.io.File("C:\\backup\\ppanda.sql"));
	BufferedOutputStream bos=new BufferedOutputStream(fos);
	bos.write(is);
	bos.flush();
	bos.close();
	fos.close();

			return true;	
				
			} catch (GeneralSecurityException | IOException e) {
				
				log.info("--something went wrong--- "+e.getLocalizedMessage());
			}
		
		return false;
	}
	
	
	
	

}
