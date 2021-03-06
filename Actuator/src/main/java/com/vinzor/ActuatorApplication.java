package com.vinzor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 
 * 
 * HTTP方法	          路径	                      描述
 * GET	/auditevents	显示应用暴露的审计事件 (比如认证进入、订单失败)
 * GET	/beans	描述应用程序上下文里全部的 Bean，以及它们的关系
 * GET	/conditions	就是 1.0 的 /autoconfig ，提供一份自动配置生效的条件情况，记录哪些自动配置条件通过了，哪些没通过
 * GET	/configprops	描述配置属性(包含默认值)如何注入Bean
 * GET	/env	获取全部环境属性
 * GET	/env/{name}	根据名称获取特定的环境属性值
 * GET	/flyway	提供一份 Flyway 数据库迁移信息
 * GET	/liquidbase	显示Liquibase 数据库迁移的纤细信息
 * GET	/health	报告应用程序的健康指标，这些值由 HealthIndicator 的实现类提供
 * GET	/heapdump	dump 一份应用的 JVM 堆信息
 * GET	/httptrace	显示HTTP足迹，最近100个HTTP request/repsponse
 * GET	/info	获取应用程序的定制信息，这些信息由info打头的属性提供
 * GET	/logfile	返回log file中的内容(如果 logging.file 或者 logging.path 被设置)
 * GET	/loggers	显示和修改配置的loggers
 * GET	/metrics	报告各种应用程序度量信息，比如内存用量和HTTP请求计数
 * GET	/metrics/{name}	报告指定名称的应用程序度量值
 * GET	/scheduledtasks	展示应用中的定时任务信息
 * GET	/sessions	如果我们使用了 Spring Session 展示应用中的 HTTP sessions 信息
 * POST	/shutdown	关闭应用程序，要求endpoints.shutdown.enabled设置为true
 * GET	/mappings	描述全部的 URI路径，以及它们和控制器(包含Actuator端点)的映射关系
 * GET	/threaddump	获取线程活动的快照
 * 
 * 
 * 
 * @author 我是谁
 *
 */
@SpringBootApplication
public class ActuatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(ActuatorApplication.class, args);
	}

}
