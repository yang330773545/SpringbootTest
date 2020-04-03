## 改为springboot2.1.6 之后Eclipse中pom第一行报错
```
	<properties>
		<java.version>1.8</java.version>
		<maven-jar-plugin.version>3.1.1</maven-jar-plugin.version>
	</properties>
```
### 404test
设置404页面<br>
### Actuator
使用Actuator监控springboot项目<br>
### SMSloginbackground
短信发送例子使用了网易云，榛子云和阿里云
### admin
使用spring-boot-admin单个springboot项目（也可监控Eureka集群）<br>
### aspect
使用aspect实现AOP
### boot-banner
将项目启动的时候springboot变成自己公司或个人的名称.txt或者动图.gif（txt会先执行）<br> 
### boot-swagger
使用swagger自动生成接口文档
### boot-useragentutil
使用UserAgentUtils来获得访问的浏览器信息
### command-line-runner
SpringApplication.run方法执行前执行一些初始化操作（@Order实现）
### devtools
使用Devtools来实现热部署
### docker-test
使用maven插件完成docker镜像使用mvn package docker:build命令打包
### elasticsearch
ElasticSearch的使用
### boot-security
spring security在springmvc和springwebflux中使用
### boot-swagger
自动生成api集合网页主要是注解使用详见项目注释
### boot-useragentutil
使用useragentutil来获取用户使用的浏览器等信息
### command-line-runner
实现CommandLineRunner接口完成项目启动前需要干的事
### devtools
实现热部署
### examplemybatis
MyBatis注解方式的使用（里边也使用了shiro）
### file
使用MultipartFile来完成文件上传下载
### githuboauth2
github的oauth2.0授权示例
### jpa
SpringBootJpa示例
### jwt
使用HTTPS和JWT示例
### kafka
kafka使用示例
### memcached
memcached使用示例
### mongodb
mongodb使用示例
### mq-customer mq-producer
rabbitmq ACK等机制使用
### mybatisXML
Mybatis使用XML的示例
### no-paper-test
随便写写玩的没啥子卵用
### qiniu-file
七牛云存储文件示例（前端没实现）
### rabbitmq
rabbitmq使用（不同的Exchange）
### redis
redis使用例子
### send-mail
发送邮件的例子（qq的话需要该账号要开启smtp）
### shiro-jwt
shiro整合jwt进行鉴权
### shiro-session
整合shiro将session放入redis中
### spring-hoteoas 
REST成熟度模型第四个层次（具体可参考Hoteoas）
### we-not-chat
WebSocker实现双向消息
### web-flux
springwebflux的使用
*** 
Markdown示例

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
