package com.vinzor.mysql.service.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vinzor.mysql.entity.Device;
import com.vinzor.mysql.mapper.DeviceMapper;
import com.vinzor.mysql.service.DeviceService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DeviceServiceImpl implements DeviceService{

	@Autowired
	private DeviceMapper deviceMapper;
	private ObjectMapper mapper=new ObjectMapper();
	@Override
	public List<Device> selectDeviceByRoomId(int meetingRoomId) {
		// TODO Auto-generated method stub
		return deviceMapper.selectDeviceByRoomId(meetingRoomId);
	}
	@Override
	public boolean insertDevice(String json) {
		// TODO Auto-generated method stub
		try {
			Device device=mapper.readValue(json, Device.class);
			if(deviceMapper.insertDevice(device.getType(),device.getDeviceName(), device.getDeviceIp(),
					device.getMeetingRoomId())==1) return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.error("添加设备json解析出错 json为"+json);
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public boolean deleteDeviceById(int id) {
		// TODO Auto-generated method stub
		if(deviceMapper.deleteDeviceById(id)==1) return true;
		return false;
	}
	@Override
	public boolean updateDeviceNameById(int id, String deviceName) {
		// TODO Auto-generated method stub
		if(deviceMapper.updateDeviceNameById(id, deviceName)==1) return true;
		return false;
	}

}
