package com.vinzor.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import java.lang.reflect.Method;

import com.vinzor.annotation.LogAnnotation;

@Component
@Aspect
public class LogAspect {

	/**
	 * @author 爆山大爷
	 * i定义切点表达式
	 * https://blog.csdn.net/Wetsion/article/details/80192202
	 */
	@Pointcut("@annotation(com.vinzor.annotation.LogAnnotation)")
	public void pointcut() { }
	/**
	 * i环绕通知
	 * @author yangyang
	 * @param joinPoint
	 * @version 0.0.1 19/03/25
	 * 
	 */
	@Around("pountcut()")
	public void around(ProceedingJoinPoint joinPoint) {
		try {
            joinPoint.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        LogAnnotation log = method.getAnnotation(LogAnnotation.class);
        String value = log.value();
        System.out.println(value);
        Object[] args = joinPoint.getArgs();
        System.out.println(args);
        System.out.println("结束执行");
	}
    /**
     * 前置通知
     */
    @Before("pointcut()")
    public void doBeforeController(JoinPoint joinPoint) {
        System.out.println("action名称 "); 
    }

    /**
     * 后置通知
     */
    @AfterReturning(pointcut = "pointcut()", returning = "retValue")
    public void doAfterController(JoinPoint joinPoint, Object retValue) {
        System.out.println("retValue is:" + retValue);
    }
}
