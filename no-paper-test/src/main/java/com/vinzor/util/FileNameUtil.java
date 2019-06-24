package com.vinzor.util;

import java.util.List;

public class FileNameUtil {


	void rename(String name,List<String> aList) {
		
		for(String list:aList) {
			if(list.equals(name)) {
				name=name+1;
				rename(name, aList);
			}
		}
	}
}
