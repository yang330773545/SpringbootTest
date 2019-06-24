package com.vinzor.mysql.service;

import java.util.List;

import com.vinzor.mysql.entity.Device;

public interface DeviceService {

	List<Device> selectDeviceByRoomId(int meetingRoomId);
	boolean insertDevice(String json);
	boolean deleteDeviceById(int id);
	boolean updateDeviceNameById(int id, String deviceName);
}
