package com.yang.gzcloud.view;

import com.yang.gzcloud.entity.MyComputerUsage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import oshi.SystemInfo;
import oshi.hardware.*;
import oshi.hardware.CentralProcessor.TickType;
import oshi.software.os.*;
import oshi.software.os.OperatingSystem.ProcessSort;
import oshi.util.FormatUtil;
import oshi.util.Util;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class TestController {
    private Logger logger = LoggerFactory.getLogger(TestController.class);

    private List<String> oshi = new ArrayList<>();

    @RequestMapping(value = "/computer", method = RequestMethod.POST)
    public String test(MyComputerUsage myComputerUsage){
        logger.info("__获得发送过来的信息__");
        return "123";
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public List<String> testO(){
        oshi.clear();
        SystemInfo si = new SystemInfo();
        HardwareAbstractionLayer hal = si.getHardware();
        OperatingSystem os = si.getOperatingSystem();

        FileSystem fileSystem = os.getFileSystem();

        NetworkParams networkParams = os.getNetworkParams();

        ComputerSystem system = hal.getComputerSystem();

        CentralProcessor cpu = hal.getProcessor();

        GlobalMemory memory = hal.getMemory();

        Sensors sensors = hal.getSensors();

        PowerSource[] powerSources = hal.getPowerSources();

        HWDiskStore[] disks = hal.getDiskStores();

        NetworkIF[] networkIFs = hal.getNetworkIFs();

        Display[] displays = hal.getDisplays();

        UsbDevice[] usbDevices = hal.getUsbDevices(true);

        SoundCard[] soundCards = hal.getSoundCards();

        logger.info("————获得操作系统名称，开机时间等。————");
       // printOperatingSystem(os);

        logger.info("————获取主机型号，固件等信息。————");
        //printComputerSystem(system);

        logger.info("————获取CPU频率，和使用情况。————");
        //printCpu(cpu);

        logger.info("————获取磁盘，分区等信息。————");
        printDisks(disks);

        logger.info("————获取显示器相关信息。————");
        //printDisplays(displays);

        logger.info("————获取系统中磁盘使用情况。————");
        printFileSystem(fileSystem);

        logger.info("————获取内存信息，使用情况。————");
        printMemory(memory);

        logger.info("————获取网络接口信息。————");
        //printNetworkInterfaces(networkIFs);

        logger.info("————获取网络参数，主机名，ip地址等。————");
        //printNetworkParameters(networkParams);

        logger.info("————获取电源信息。————");
        //printPowerSources(powerSources);

        logger.info("————获取当前进程的信息和资源使用。————");
        //printProcesses(os,memory);

        logger.info("————获取CPU相关信息（型号等）。————");
        //printProcessor(cpu);

        logger.info("————获取系统服务相关信息。————");
        //printServices(os);

        logger.info("————获取声卡相关信息。————");
        //printSoundCards(soundCards);

        logger.info("————获取USB驱动相关信息。————");
        //printUsbDevices(usbDevices);

        logger.info("————获取传感器相关信息（CPU温度等）。————");
        //printSensors(sensors);

        for(String s : oshi){
            System.out.println(s);
        }

        return oshi;
    }

    private void printOperatingSystem(final OperatingSystem os) {
        oshi.add(String.valueOf(os));
        oshi.add("Booted: " + Instant.ofEpochSecond(os.getSystemBootTime()));
        oshi.add("Uptime: " + FormatUtil.formatElapsedSecs(os.getSystemUptime()));
        oshi.add("Running with" + (os.isElevated() ? "" : "out") + " elevated permissions.");
    }

    private void printComputerSystem(final ComputerSystem computerSystem) {
        oshi.add("system: " + computerSystem.toString());
        oshi.add(" firmware: " + computerSystem.getFirmware().toString());
        oshi.add(" baseboard: " + computerSystem.getBaseboard().toString());
    }

    private void printProcessor(CentralProcessor processor) {
        oshi.add(processor.toString());
    }

    private void printMemory(GlobalMemory memory) {
        oshi.add("Memory: \n " + memory.toString());
        VirtualMemory vm = memory.getVirtualMemory();
        oshi.add("Swap: \n " + vm.toString());
        PhysicalMemory[] pmArray = memory.getPhysicalMemory();
        if (pmArray.length > 0) {
            oshi.add("Physical Memory: ");
            for (PhysicalMemory pm : pmArray) {
                oshi.add(" " + pm.toString());
            }
        }
    }

    private void printCpu(CentralProcessor processor) {
        oshi.add("Context Switches/Interrupts: " + processor.getContextSwitches() + " / " + processor.getInterrupts());

        long[] prevTicks = processor.getSystemCpuLoadTicks();
        long[][] prevProcTicks = processor.getProcessorCpuLoadTicks();
        oshi.add("CPU, IOWait, and IRQ ticks @ 0 sec:" + Arrays.toString(prevTicks));
        // Wait a second...
        Util.sleep(1000);
        long[] ticks = processor.getSystemCpuLoadTicks();
        oshi.add("CPU, IOWait, and IRQ ticks @ 1 sec:" + Arrays.toString(ticks));
        long user = ticks[TickType.USER.getIndex()] - prevTicks[TickType.USER.getIndex()];
        long nice = ticks[TickType.NICE.getIndex()] - prevTicks[TickType.NICE.getIndex()];
        long sys = ticks[TickType.SYSTEM.getIndex()] - prevTicks[TickType.SYSTEM.getIndex()];
        long idle = ticks[TickType.IDLE.getIndex()] - prevTicks[TickType.IDLE.getIndex()];
        long iowait = ticks[TickType.IOWAIT.getIndex()] - prevTicks[TickType.IOWAIT.getIndex()];
        long irq = ticks[TickType.IRQ.getIndex()] - prevTicks[TickType.IRQ.getIndex()];
        long softirq = ticks[TickType.SOFTIRQ.getIndex()] - prevTicks[TickType.SOFTIRQ.getIndex()];
        long steal = ticks[TickType.STEAL.getIndex()] - prevTicks[TickType.STEAL.getIndex()];
        long totalCpu = user + nice + sys + idle + iowait + irq + softirq + steal;

        oshi.add(String.format(
                "User: %.1f%% Nice: %.1f%% System: %.1f%% Idle: %.1f%% IOwait: %.1f%% IRQ: %.1f%% SoftIRQ: %.1f%% Steal: %.1f%%",
                100d * user / totalCpu, 100d * nice / totalCpu, 100d * sys / totalCpu, 100d * idle / totalCpu,
                100d * iowait / totalCpu, 100d * irq / totalCpu, 100d * softirq / totalCpu, 100d * steal / totalCpu));
        oshi.add(String.format("CPU load: %.1f%%", processor.getSystemCpuLoadBetweenTicks(prevTicks) * 100));
        double[] loadAverage = processor.getSystemLoadAverage(3);
        oshi.add("CPU load averages:" + (loadAverage[0] < 0 ? " N/A" : String.format(" %.2f", loadAverage[0]))
                + (loadAverage[1] < 0 ? " N/A" : String.format(" %.2f", loadAverage[1]))
                + (loadAverage[2] < 0 ? " N/A" : String.format(" %.2f", loadAverage[2])));
        // per core CPU
        StringBuilder procCpu = new StringBuilder("CPU load per processor:");
        double[] load = processor.getProcessorCpuLoadBetweenTicks(prevProcTicks);
        for (double avg : load) {
            procCpu.append(String.format(" %.1f%%", avg * 100));
        }
        oshi.add(procCpu.toString());
        long freq = processor.getProcessorIdentifier().getVendorFreq();
        if (freq > 0) {
            oshi.add("Vendor Frequency: " + FormatUtil.formatHertz(freq));
        }
        freq = processor.getMaxFreq();
        if (freq > 0) {
            oshi.add("Max Frequency: " + FormatUtil.formatHertz(freq));
        }
        long[] freqs = processor.getCurrentFreq();
        if (freqs[0] > 0) {
            StringBuilder sb = new StringBuilder("Current Frequencies: ");
            for (int i = 0; i < freqs.length; i++) {
                if (i > 0) {
                    sb.append(", ");
                }
                sb.append(FormatUtil.formatHertz(freqs[i]));
            }
            oshi.add(sb.toString());
        }
    }

    private void printProcesses(OperatingSystem os, GlobalMemory memory) {
        oshi.add("My PID: " + os.getProcessId() + " with affinity "
                + Long.toBinaryString(os.getProcessAffinityMask(os.getProcessId())));
        oshi.add("Processes: " + os.getProcessCount() + ", Threads: " + os.getThreadCount());
        // Sort by highest CPU
        List<OSProcess> procs = Arrays.asList(os.getProcesses(5, ProcessSort.CPU));

        oshi.add("   PID  %CPU %MEM       VSZ       RSS Name");
        for (int i = 0; i < procs.size() && i < 5; i++) {
            OSProcess p = procs.get(i);
            oshi.add(String.format(" %5d %5.1f %4.1f %9s %9s %s", p.getProcessID(),
                    100d * (p.getKernelTime() + p.getUserTime()) / p.getUpTime(),
                    100d * p.getResidentSetSize() / memory.getTotal(), FormatUtil.formatBytes(p.getVirtualSize()),
                    FormatUtil.formatBytes(p.getResidentSetSize()), p.getName()));
        }
    }

    private void printServices(OperatingSystem os) {
        oshi.add("Services: ");
        oshi.add("   PID   State   Name");
        // DO 5 each of running and stopped
        int i = 0;
        for (OSService s : os.getServices()) {
            if (s.getState().equals(OSService.State.RUNNING) && i++ < 5) {
                oshi.add(String.format(" %5d  %7s  %s", s.getProcessID(), s.getState(), s.getName()));
            }
        }
        i = 0;
        for (OSService s : os.getServices()) {
            if (s.getState().equals(OSService.State.STOPPED) && i++ < 5) {
                oshi.add(String.format(" %5d  %7s  %s", s.getProcessID(), s.getState(), s.getName()));
            }
        }
    }

    private void printSensors(Sensors sensors) {
        oshi.add("Sensors: " + sensors.toString());
    }

    private void printPowerSources(PowerSource[] powerSources) {
        StringBuilder sb = new StringBuilder("Power Sources: ");
        if (powerSources.length == 0) {
            sb.append("Unknown");
        }
        for (PowerSource powerSource : powerSources) {
            sb.append("\n ").append(powerSource.toString());
        }
        oshi.add(sb.toString());
    }

    private void printDisks(HWDiskStore[] diskStores) {
        oshi.add("Disks:");
        for (HWDiskStore disk : diskStores) {
            oshi.add(" " + disk.toString());

            HWPartition[] partitions = disk.getPartitions();
            for (HWPartition part : partitions) {
                oshi.add(" |-- " + part.toString());
            }
        }

    }

    private void printFileSystem(FileSystem fileSystem) {
        oshi.add("File System:");

        oshi.add(String.format(" File Descriptors: %d/%d", fileSystem.getOpenFileDescriptors(),
                fileSystem.getMaxFileDescriptors()));

        OSFileStore[] fsArray = fileSystem.getFileStores();
        for (OSFileStore fs : fsArray) {
            long usable = fs.getUsableSpace();
            long total = fs.getTotalSpace();
            oshi.add(String.format(
                    " %s (%s) [%s] %s of %s free (%.1f%%), %s of %s files free (%.1f%%) is %s "
                            + (fs.getLogicalVolume() != null && fs.getLogicalVolume().length() > 0 ? "[%s]" : "%s")
                            + " and is mounted at %s",
                    fs.getName(), fs.getDescription().isEmpty() ? "file system" : fs.getDescription(), fs.getType(),
                    FormatUtil.formatBytes(usable), FormatUtil.formatBytes(fs.getTotalSpace()), 100d * usable / total,
                    FormatUtil.formatValue(fs.getFreeInodes(), ""), FormatUtil.formatValue(fs.getTotalInodes(), ""),
                    100d * fs.getFreeInodes() / fs.getTotalInodes(), fs.getVolume(), fs.getLogicalVolume(),
                    fs.getMount()));
        }
    }

    private void printNetworkInterfaces(NetworkIF[] networkIFs) {
        StringBuilder sb = new StringBuilder("Network Interfaces:");
        if (networkIFs.length == 0) {
            sb.append(" Unknown");
        }
        for (NetworkIF net : networkIFs) {
            sb.append("\n ").append(net.toString());
        }
        oshi.add(sb.toString());
    }

    private void printNetworkParameters(NetworkParams networkParams) {
        oshi.add("Network parameters:\n " + networkParams.toString());
    }

    private void printDisplays(Display[] displays) {
        oshi.add("Displays:");
        int i = 0;
        for (Display display : displays) {
            oshi.add(" Display " + i + ":");
            oshi.add(String.valueOf(display));
            i++;
        }
    }

    private void printUsbDevices(UsbDevice[] usbDevices) {
        oshi.add("USB Devices:");
        for (UsbDevice usbDevice : usbDevices) {
            oshi.add(String.valueOf(usbDevice));
        }
    }

    private void printSoundCards(SoundCard[] cards) {
        oshi.add("Sound Cards:");
        for (SoundCard card : cards) {
            oshi.add(" " + String.valueOf(card));
        }
    }
}
