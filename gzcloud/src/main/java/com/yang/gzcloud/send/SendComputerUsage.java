package com.yang.gzcloud.send;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yang.gzcloud.entity.MyComputerUsage;
import com.yang.gzcloud.service.MyComputerUsageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class SendComputerUsage {
    @Value("${send.ip}")
    private String sendIp;
    private RestTemplate restTemplate = new RestTemplate();
    private ObjectMapper mapper=new ObjectMapper();
    @Autowired
    private MyComputerUsageService myService;
    public void send(){
        String uri = "http://"+sendIp+":8080"+"/computer";
        log.info(uri);
        restTemplate.postForObject(uri, myService.getComputerUsage(), Object.class);
        /*
        try {
            restTemplate.postForObject(uri, myService.getComputerUsage(), MyComputerUsage.class);
        }catch (Exception e){
            log.info("__发送消息失败请检查网络__");
        }
         */
    }
}
