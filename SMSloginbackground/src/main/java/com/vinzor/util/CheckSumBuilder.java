package com.vinzor.util;

import org.apache.commons.codec.digest.DigestUtils;

public class CheckSumBuilder {

	public static String getCheckSum(String APP_SECRET,String NONCE,String curTime) {
		StringBuilder stringBuilder=new StringBuilder();
		stringBuilder.append(APP_SECRET);
		stringBuilder.append(NONCE);
		stringBuilder.append(curTime);
		String string=stringBuilder.toString();
		return DigestUtils.sha1Hex(string);
	}
}
