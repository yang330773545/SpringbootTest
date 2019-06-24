package com.vinzor.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vinzor.mysql.entity.Device;
import com.vinzor.mysql.service.DeviceService;

@RestController
public class DeviceController {

	@Autowired
	DeviceService deviceService;
	@GetMapping(value="/getmeetingroomdevice")
	public List<Device> findDeviceByRoomId(int roomId){
		return deviceService.selectDeviceByRoomId(roomId);
	}
	@PostMapping(value="/adddevice")
	public boolean addDevice(@RequestBody String json) {
		return deviceService.insertDevice(json);
	}
	@GetMapping(value="delete/{id}/device")
	public boolean deleteDeviceById(@PathVariable int id) {
		return deviceService.deleteDeviceById(id);
	}
	@GetMapping(value="update/{id}/device")
	public boolean updateDeviceNameById(@PathVariable int id,String deviceName) {
		return deviceService.updateDeviceNameById(id, deviceName);
	}
	
}
