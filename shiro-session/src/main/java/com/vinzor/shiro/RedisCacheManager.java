package com.vinzor.shiro;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

public class RedisCacheManager implements CacheManager {

	 private static final Logger logger = LoggerFactory.getLogger(RedisCacheManager.class);


	 	@Autowired
	    private RedisTemplate<String, Object> redisTemplate;

	    //返回cache容器
	    @Override
	    public <K, V> Cache<K, V> getCache(String name) throws CacheException {
	        return new RedisCache<K, V>(name, redisTemplate);
	    }

	    public RedisTemplate<String, Object> getRedisTemplate() {
	        return redisTemplate;
	    }

	    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
	        this.redisTemplate = redisTemplate;
	    }

}
