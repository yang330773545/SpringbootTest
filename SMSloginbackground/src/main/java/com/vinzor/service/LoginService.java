package com.vinzor.service;

public interface LoginService {
	public boolean sendSMS(String json);
	public boolean sendZhenziyunSMS(String json);
	public boolean sendAliyunSMS(String json);
	public boolean checkSMS(String json);
}
