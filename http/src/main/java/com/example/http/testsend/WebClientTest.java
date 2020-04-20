package com.example.http.testsend;

import com.example.http.entity.TestEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

/*
 https://docs.spring.io/spring/docs/5.2.5.RELEASE/spring-framework-reference/web-reactive.html#webflux-client-body-form
 */
@Component
public class WebClientTest {
    Logger logger = LoggerFactory.getLogger(WebClientTest.class);
    private WebClient webClient = WebClient.create();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public void testInfo(){
        String uri = "http://127.0.0.1:8080/info";
        Mono<String> mono = webClient.get().uri(uri).retrieve().bodyToMono(String.class); //.block();同步使用
        logger.info("返回值为"+mono.block());
    }

    public void testEntity(int id){
        String uri="http://127.0.0.1:8080/entity/{?}";
        Mono<TestEntity> entity = webClient.get().uri(uri,1).retrieve().bodyToMono(TestEntity.class);
        logger.info("entity的name为:"+entity.block().getName());
    }

    public void testEntities(int id){
        String uri="http://127.0.0.1:8080/entity/{?}";
        Flux<TestEntity> fEntity = webClient.get().uri(uri,1).retrieve().bodyToFlux(TestEntity.class);
        List<TestEntity> entities = fEntity.collectList().block();
        logger.info("entity的name为:"+entities.get(0).getName());
    }

    public void postTestEntity(){
        String uri="http://127.0.0.1:8080/entity";
        TestEntity tEntity = new TestEntity(2,"B");
        Mono<TestEntity> entity=webClient.post().uri(uri).contentType(MediaType.APPLICATION_JSON).bodyValue(tEntity)
                .retrieve().bodyToMono(TestEntity.class);
        logger.info("entity的name为:"+entity.block().getName());
    }

    public void patchJson(){
        String uri="http://127.0.0.1:8080/entity/{id}";
        ObjectNode testEntityJsonObject = objectMapper.createObjectNode();
        testEntityJsonObject.put("id", 1);
        testEntityJsonObject.put("name", "John");
        Mono<String> mono = webClient.patch().uri(uri,1).contentType(MediaType.APPLICATION_JSON)
                .bodyValue(testEntityJsonObject).retrieve().bodyToMono(String.class);
        logger.info("json:"+mono.block());
    }

}
