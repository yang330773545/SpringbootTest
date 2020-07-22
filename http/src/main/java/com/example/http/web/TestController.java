package com.example.http.web;

import com.example.http.entity.TestEntity;
import com.example.http.testsend.RestTemplateTest;
import com.example.http.testsend.WebClientTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class TestController {

    @Autowired
    private RestTemplateTest templateTest;
    @Autowired
    private WebClientTest webClientTest;

    @GetMapping("/info")
    public String getInfo(){
        return "hello";
    }
    @GetMapping("/entity/{id}")
    public TestEntity getEntity(@PathVariable String id){
        TestEntity entity = new TestEntity(1,"A");
        return entity;
    }
    @GetMapping("/entity")
    public TestEntity getEntityById(String id){
        TestEntity entity = new TestEntity(1,"A");
        return entity;
    }
    @PostMapping("/entity")
    public TestEntity postEntity(TestEntity entity){
        return entity;
    }
    @PatchMapping(value = "/entity/{id}")
    public String postEntity(@PathVariable String id, @RequestBody String json){
        return json;
    }
    @GetMapping("/test")
    public void haha(){
        templateTest.testInfo();
        templateTest.testEntity(1);
        templateTest.testEntity1(2);
        templateTest.postTestEntity();
        templateTest.patchJson();
        webClientTest.testInfo();
        webClientTest.testEntity(1);
        webClientTest.postTestEntity();
        webClientTest.patchJson();
        String s = "asdadsadwwasd";
        Map<Character,Integer> map = new LinkedHashMap<>();
        char[] chars = s.toCharArray();

        }
    }
}
