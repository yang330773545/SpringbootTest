一级标题
==
二级标题
--
# 一级标题
##  二级标题
### 三级标题
。。。
###### 六级标题

![](https://7n.w3cschool.cn/attachments/cover/cover_javavm.jpeg?imageView2/1/w/150/h/84)
![123](https://7n.w3cschool.cn/attachments/cover/cover_javavm.jpeg?imageView2/1/w/150/h/84 "不要点啊")

| Left-aligned | Center-aligned | Right-aligned |
| :---         |     :---:      |          ---: |
| git status   | git status     | git status    |
| git diff     | git diff       | git diff      |

*斜体字* 
**粗体字** 
***加粗斜体字***

***

这是一段普通的文本，  
直接回车不能换行，<br>  
要使用\<br>  

    我是谁
    爆山大爷
    
Thank `You` . Please `Call` Me `Coder`
[爆山大爷](https://baike.baidu.com/item/%E7%88%86%E5%B1%B1/22225262?fromtitle=%E7%88%86%E5%B1%B1%E5%A4%A7%E7%88%B7&fromid=23534342)  

* 编程语言  
    * 脚本语言  
        * Python  
        
>数据结构  
>>树  
>>>二叉树  
>>>>平衡二叉树  
>>>>>满二叉树  

```Java
public class Demo extend Thread{
  String baoshan="爆山大爷";
  public void run(){
    System.out.println("这个是什么{}",baoshan)
  }
```
***
## 改为springboot2.1.6 pom第一行报错解决方法
```
	<properties>
		<java.version>1.8</java.version>
		<maven-jar-plugin.version>3.1.1</maven-jar-plugin.version>
	</properties>
```
## 简单写一下实现（名称排序）
### Actuator
监控springboot项目<br>
只需在导入后在配置文件中加入
```
info.app.name=spring-boot-actuator
info.app.version= 1.0.0
info.app.test=test

#打开所有的监控点
management.endpoints.web.exposure.include=*
#健康检查 健康信息包含磁盘空间、redis、DB，启用监控的这个spring boot应用确实是连接了redis和oracle DB，actuator就自动给监控起来了除了always之外还有when-authorized、never，默认值是never
management.endpoint.health.show-details=always
#代表启用单独的url地址来监控 Spring Boot 应用，为了安全一般都启用独立的端口来访问后端的监控信息
#management.endpoints.web.base-path=/monitor

#启用接口关闭 Spring Boot
management.endpoint.shutdown.enabled=true
```
使用详情在ActuatorApplication注释中
### SMSloginbackground
自己写的一个短信发送例子使用了网易云，榛子云和阿里云
### admin
可以监控单个springboot项目或者Eureka集群<br>
@Configuration<br>
@EnableAutoConfiguration<br>
@EnableAdminServer<br>
将@ComponentScan变为了EnableAdminServer
使用详情见AdminApplication
### boot-banner
在springboot启动的时候springboot变成自己公司或个人的名称.txt或者动图.gif<br>
只需要在resources目录下放入banner.txt或banner.gif<br>
txt会先执行[在线生成txt](http://www.network-science.de/ascii/)  
### boot-security
spring的安全管理框架
### boot-swagger
自动生成api集合网页主要是注解使用详见项目注释
### boot-useragentutil
使用useragentutil来获取用户使用的浏览器等信息
### command-line-runner
实现CommandLineRunner接口完成项目启动前需要干的事
### devtools
实现热部署
### examplemybatis
mybatis注解方式使用和shiro简单使用
### file
使用MultipartFile来上传文件
### githuboauth2
使用github的oauth2.0授权
### jpa
jpa简单使用
### jwt
jwt的构建和使用
### memcached
memcached示例
### mongodb
mongodb使用例子
### mq-customer mq-producer
rabbitmq 使用
### no-paper-test
没事写这玩的项目未完成
### qiniu-file
七牛云文件
### rabbitmq
也是rabbitmq使用 上边的注解更加详情
### redis
redis使用例子
### send-mail
邮件服务器
### shiro-session
将session放入redis
### spring-hoteoas
Richardson 提出的 REST 成熟度模型第四个层次在资源的表达中包含了链接信息。客户端可以根据链接来发现可以执行的动作。
### we-not-chat
webSocket使用
### web-flux
springflux简单使用

