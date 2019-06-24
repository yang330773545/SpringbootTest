package com.vinzor.mysql.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Meeting implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7119765440964906399L;

	private int id;
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	private String appointmentPersion;
	private int signBackId;
	private int projectionBackId;
	private int tableBackId;
	private int signLogoId;
	private int projectionLogoId;
	private int meetingRoomId;
	private int wayOfSign;
	private int confidentiality;
	private int theme;
	private String schemaString;
	private String urlString;
	//这里是文件路径暂时还没有使用查询
	/*
	private String signBackFilePath;
	private String projectionBackFilePath;
	private String tableBackFilePath;
	private String signLogoFilePath;
	private String projectionLogoFilePath;
	private String signBackFileName;
	private String projectionBackFileName;
	private String tableBackFileName;
	private String signLogoFileName;
	private String projectionLogoFileName;
	*/
}
