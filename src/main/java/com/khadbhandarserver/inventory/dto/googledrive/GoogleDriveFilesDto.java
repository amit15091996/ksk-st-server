package com.khadbhandarserver.inventory.dto.googledrive;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class GoogleDriveFilesDto {
	
	private String id;
	private String kind;
	private String mimeType;
	private String name;
}
