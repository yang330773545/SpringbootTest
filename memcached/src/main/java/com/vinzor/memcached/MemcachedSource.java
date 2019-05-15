package com.vinzor.memcached;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

//@ConfigurationProperties(prefix = "memcache") 的意思会以 memcache.* 为开通将对应的配置文件加载到属性中
@Component
@ConfigurationProperties(prefix="memcache")
public class MemcachedSource {
    private String ip;

    private int port;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
