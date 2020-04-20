package com.example.http.testsend;

import com.example.http.entity.TestEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;


@Component
public class RestTemplateTest {
    Logger logger = LoggerFactory.getLogger(RestTemplate.class);
    // new HttpComponentsClientHttpRequestFactory()解决PatchForObject报错 使用httpclient
    private RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
    // 使用Jackson
    private final ObjectMapper objectMapper = new ObjectMapper();

    // getForEntity返回的ResponseEntity可处理response原生数组
    public void testInfo(){
        String uri="http://127.0.0.1:8080/info";
        String info = restTemplate.getForObject(uri,String.class);
        logger.info("返回值为"+info);
    }

    public void testEntity(int id){
        String uri="http://127.0.0.1:8080/entity/{?}";
        TestEntity entity = restTemplate.getForObject(uri, TestEntity.class, id);
        logger.info("entity的name为:"+entity.getName());
    }

    // {}中可使用1,2,3
    public void testEntity1(int id){
        String uri="http://127.0.0.1:8080/entity?id={?}";
        TestEntity entity = restTemplate.getForObject(uri, TestEntity.class,id);
        logger.info("entity的name为:"+entity.getName());
    }

    public void postTestEntity(){
        String uri="http://127.0.0.1:8080/entity";
        //设置request可以用HttpEntity
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        //参数
        MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
        map.add("name","B");
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
        ResponseEntity<TestEntity> response = restTemplate.postForEntity( uri, request , TestEntity.class );
        logger.info("entity的name为:"+response.getBody().getName());

        // 直接传类过去收不到参数
        TestEntity entity = new TestEntity(2,"B");
        System.out.println(entity.getName());
        TestEntity entity1 = restTemplate.postForObject(uri,entity,TestEntity.class);
        logger.info("entity的name为:"+entity1.getName());
    }

    public void patchJson(){
        String uri="http://127.0.0.1:8080/entity/{id}";
        ObjectNode testEntityJsonObject = objectMapper.createObjectNode();
        testEntityJsonObject.put("id", 1);
        testEntityJsonObject.put("name", "John");
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        HttpEntity<String> formEntity = new HttpEntity<>(testEntityJsonObject.toString(), headers);
        String str = restTemplate.patchForObject(uri,formEntity,String.class,1);
        logger.info("json:"+str);
    }
}
