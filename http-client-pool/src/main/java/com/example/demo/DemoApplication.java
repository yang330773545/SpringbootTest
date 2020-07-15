package com.example.demo;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @RestController
    class TestController{
        @GetMapping("/demo")
        public String demo() throws IOException {
            HttpResponse response = null;
            try {
                HttpGet get = new HttpGet("https://www.baidu.com/");
                response = HttpClientPool.getHttpClient().execute(get);
                if(response.getStatusLine().getStatusCode() != HttpStatus.SC_OK){
                    EntityUtils.consume(response.getEntity());
                    // error
                }else {
                    String result = EntityUtils.toString(response.getEntity());
                    // ok
                }
            }catch (Exception e){
                if(response != null){
                    EntityUtils.consume(response.getEntity());
                }
            }
            return "thank you";
        }
    }
}
