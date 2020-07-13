package com.example.demo;

import com.example.demo.error.ErrorInfo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @ControllerAdvice
    class GlobalExceptionHandler{
        public static final String DEFAULT_ERROR_VIEW = "error";

        @ExceptionHandler(value = Exception.class)
        @ResponseBody
        public ErrorInfo<String> defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
            ErrorInfo<String> info = new ErrorInfo<>();
            info.setMessage(e.getMessage());
            info.setCode(ErrorInfo.ERROR);
            info.setData("Some Data");
            info.setUrl(req.getRequestURL().toString());
            return info;
        }
    }
}
