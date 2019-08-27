package com.vinzor.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vinzor.entity.User;
import com.vinzor.redis.StringRedisService;

@RestController
public class TestController {

	private static final Logger log = LoggerFactory.getLogger(TestController.class);

	
	@Autowired
	private StringRedisService stringRedisService;
		
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	
	//V为String
	@RequestMapping(value="/test")
	public User demo() {		
		stringRedisService.set("haha", "haha", 30*60L);
		redisTemplate.opsForValue().set("user1",new User(1,"yj","123","111"));
		return (User)redisTemplate.opsForValue().get("user1");
	}
	//V为List
	public boolean demoList() {
		ListOperations<String, Object> listOperations=redisTemplate.opsForList();
		
		listOperations.leftPush("test", "woshishui");
		listOperations.rightPush("test", "gouzi");
		
		String aString=(String) listOperations.leftPop("test");
		String bString=(String) listOperations.rightPop("test");
		
		System.out.println(aString);
		System.out.println(bString);
		
		return true;
	}
	
	//V为Hash
	public boolean demoHash() {
		HashOperations<String, Object, Object> hashOperations=redisTemplate.opsForHash();
		
		Map<String, User> aMap=new LinkedHashMap<String, User>();
		aMap.put("zhangsan", new User(1,"yj","123","111"));
		aMap.put("lisi", new User(1,"yj","123","111"));
		aMap.put("wangwu", new User(1,"yj","123","111"));
		
		hashOperations.put("keys", "hashkey", "value");
		hashOperations.putAll("keya", aMap);
		
		String aString=(String) hashOperations.get("keys", "hashkey");
		System.out.println(aString);
		
		hashOperations.delete("keys", "hashkey");
		
		
		System.out.println(hashOperations.size("keya"));
		
		
		return true;
	}
	//V为Set https://blog.csdn.net/pengdandezhi/article/details/78909041
	public boolean demoSet() {
		SetOperations<String, Object> setOperations=redisTemplate.opsForSet();
		
		setOperations.add("key", "values1","values2","values3","values4","values5");
		
		setOperations.remove("key", "values1");
		
		//随机删除并返回
		String aString=(String) setOperations.pop("key");
		System.out.println(aString);
		//move(K key, V value, K destKey) 把源集合中的一个元素移动到目标集合。成功返回true
		
		// 检查集合中是否包含某个元素
		boolean b=setOperations.isMember("key", "value5");
		System.out.println(b);
		
		// 随机返回集合中指定数量的元素。随机的元素可能重复
		setOperations.randomMembers("key", 3);
		return true;
	}
	
	//V为ZSet https://blog.csdn.net/pengdandezhi/article/details/78911049
	public boolean demoZSet() {
		ZSetOperations<String, Object> zSetOperations=redisTemplate.opsForZSet();
		//添加元素到变量中同时指定元素的分值
		zSetOperations.add("keys", "wanger", 2);
		//获取指定区间的元素
		zSetOperations.range("keys",2,5);
		
		//计算并返回成员在有序集合中从低到高的排名 0为最低
		zSetOperations.rank("keys","wanger");
		//计算并返回成员在有序集合中从高到低的排名
		zSetOperations.reverseRank("keys","wanger");
		
		//从有序集合中获取指定范围内从高到低的成员集合
		zSetOperations.reverseRange("keys", 0, 5);
		
		//给有序集合中的指定成员的分数增加 delta
		zSetOperations.incrementScore("keys", "wanger", 10);
		
		
		zSetOperations.remove("keys", "wanger");
		
		return true;
		
	}

}
