package com.yang.gzcloud.service.impl;

import com.yang.gzcloud.entity.MyComputerUsage;
import com.yang.gzcloud.service.MyComputerUsageService;
import org.springframework.stereotype.Service;
import oshi.SystemInfo;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.os.OperatingSystem;
import oshi.util.FormatUtil;


@Service
public class MyComputerUsageServiceImpl implements MyComputerUsageService {
    private SystemInfo si = new SystemInfo();
    private HardwareAbstractionLayer hal = si.getHardware();
    private OperatingSystem os = si.getOperatingSystem();

    @Override
    public MyComputerUsage getComputerUsage() {
        long[] prevTicks = hal.getProcessor().getSystemCpuLoadTicks();
        MyComputerUsage myComputerUsage = new MyComputerUsage();
        myComputerUsage.setCpuUse(hal.getProcessor().getSystemCpuLoadBetweenTicks(prevTicks) * 100);
        myComputerUsage.setHostName(os.getNetworkParams().getHostName());
        myComputerUsage.setIpv4DefaultGateway(os.getNetworkParams().getIpv4DefaultGateway());
       // myComputerUsage.setIoWait(hal.getProcessor().getSystemCpuLoadTicks()[]);
        myComputerUsage.setFreeMemory(hal.getMemory().toString());
        myComputerUsage.setOperatingSystem(String.valueOf(os));
        myComputerUsage.setUptime(FormatUtil.formatElapsedSecs(os.getSystemUptime()));
        return myComputerUsage;
    }
}
