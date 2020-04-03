package com.yang.gzcloud.schedule;

import com.yang.gzcloud.send.SendComputerUsage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SendSche {
    @Autowired
    private SendComputerUsage sendComputerUsage;

    @Scheduled(fixedDelay = 5000)
    public void sendTask(){
        log.info("__定时任务发送消息__");
        sendComputerUsage.send();
    }
}
