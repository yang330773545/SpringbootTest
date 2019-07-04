package com.yang.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.yang.annotation.LogAnnotation;

@Aspect
@Component

public class LogAspect {
    final static Logger log = LoggerFactory.getLogger(LogAspect.class);

    ThreadLocal<Long> beginTime = new ThreadLocal<>();

    @Pointcut("@annotation(logAnnotation)")
    public void serviceStatistics(LogAnnotation logAnnotation) {
    }

    @Before("serviceStatistics(logAnnotation)")
    public void doBefore(JoinPoint joinPoint, LogAnnotation logAnnotation) {
        // 记录请求到达时间
        beginTime.set(System.currentTimeMillis());
        log.info("cy666 note:{}", logAnnotation.value());
    }

    @After("serviceStatistics(logAnnotation)")
    public void doAfter(LogAnnotation logAnnotation) {
        log.info("cy666 statistic time:{}, note:{}", System.currentTimeMillis() - beginTime.get(), logAnnotation.value());
    }
}
