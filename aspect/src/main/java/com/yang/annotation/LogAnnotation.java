package com.yang.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;

import org.springframework.stereotype.Component;

/**
 * 
 * @author yangyang
 * ElementType	可选值及解释
 * ANNOTATION_TYPE 注释类型声明
 * CONSTRUCTOR 构造函数声明
 * FIELD 字段声明（包括枚举常量）
 * LOCAL_VARIABLE 局部变量声明
 * METHOD 方法声明
 * PACKAGE 包
 * PARAMETER 正式参数声明
 * TYPE 类，接口（包括注释类型）或枚举声明
 * TYPE_PARAMETER 输入参数声明
 * TYPE_USE 使用一种类型
 *
 */
@Target({ ElementType.METHOD })
/**
 * 
 * @author yangyang
 * RetentionPolicy可选值
 * SOURCE 注解仅存在于源码中，在class字节码文件中不包含
 * CLASS 默认的保留策略，注解会在class字节码文件中存在，但运行时无法获得
 * RUNTIME 注解会在class字节码文件中存在，在运行时可以通过反射获取到
 */
@Retention(RetentionPolicy.RUNTIME)
// 说明该注解将被包含在javadoc中
@Documented
@Component
public @interface LogAnnotation {

	String value() default "";
}
