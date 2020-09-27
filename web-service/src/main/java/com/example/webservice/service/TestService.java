package com.example.webservice.service;

import com.example.webservice.entity.Person;

import javax.jws.WebService;


@WebService(name = "DemoService", // 暴露服务名称
        targetNamespace = "http://service.webservice.example.com"// 命名空间,一般是接口的包名倒序
)
public interface TestService {
    Person insertPersonInfo(String person);
}
