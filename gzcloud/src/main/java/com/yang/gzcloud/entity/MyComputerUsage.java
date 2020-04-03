package com.yang.gzcloud.entity;

import lombok.Data;

import java.util.List;

@Data
public class MyComputerUsage {
    private String operatingSystem;

    private String uptime;

    private Double cpuUse;

    private String ioWait;

    private List<Disk> disks;

    private String hostName;

    private String ipv4DefaultGateway;

    private String freeMemory;

    @Data
    class Disk{
        private String diskName;
        private String capacity;
        private String freeSpace;
    }
}
