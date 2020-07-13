package com.example.demo.controller;

import com.example.demo.dto.Demo;
import com.example.demo.error.MyException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class TestController {
    @GetMapping("/demo")
    public String demo(@Valid Demo demo, BindingResult bindingResult) throws MyException {
        if(bindingResult.hasErrors()){
            for(ObjectError error : bindingResult.getAllErrors()){
                return error.getDefaultMessage();
            }
        }
        throw new MyException("发生错误");
        //return "hello";
    }
}
