package com.vinzor.mysql.entity;

import lombok.Data;

@Data
public class Announcement {

	private int id;
	private int meetingId;
	private String annoTitle;
	private String annoContent;
	private int logoFileId;
	private String logoFilePath;
	private String logoFileName;
	private int backgroundFileId;
	private String backgroundFilePath;
	private String backgroundFileName;
	
}
