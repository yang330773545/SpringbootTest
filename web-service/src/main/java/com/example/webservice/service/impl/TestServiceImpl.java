package com.example.webservice.service.impl;

import com.example.webservice.entity.Person;
import com.example.webservice.service.TestService;

import javax.jws.WebService;


@WebService(serviceName = "DemoService", // 与接口中指定的name一致
        targetNamespace = "http://service.webservice.example.com", // 与接口中的命名空间一致,一般是接口的包名倒
        endpointInterface = "com.example.webservice.service.TestService"// 接口地址
)
public class TestServiceImpl implements TestService {
    @Override
    public Person insertPersonInfo(String person) {
        return null;
    }
}
