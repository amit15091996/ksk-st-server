package com.khadbhandarserver.inventory.service;

import java.util.Map;
import org.springframework.web.multipart.MultipartFile;

public interface GoogleDriveServices {

	Map<Object, Object> getAllFilesPresentInGdrive();

	Map<Object, Object> uploadFileToGdrive(MultipartFile multipartFile);

}
