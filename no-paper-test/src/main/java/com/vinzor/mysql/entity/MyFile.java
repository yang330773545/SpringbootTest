package com.vinzor.mysql.entity;

import lombok.Data;

//批注文件还没有想好怎么存 对应
@Data
public class MyFile {

	private int id;
	private String name;
	private String path;
	private String type;
	private int meetingId;
	private int participantId;
}
