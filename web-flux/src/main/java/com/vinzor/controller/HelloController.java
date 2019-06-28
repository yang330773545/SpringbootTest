package com.vinzor.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

/**
 * 
 * @author 50000008
 * just() 方法可以指定序列中包含的全部元素
 *	响应式编程的返回值必须是 Flux 或者 Mono ，两者之间可以相互转换
 */
@RestController
public class HelloController {

    @GetMapping("/hello")
    public Mono<String> hello() {
        return Mono.just("Welcome to reactive world ~");
    }
}
