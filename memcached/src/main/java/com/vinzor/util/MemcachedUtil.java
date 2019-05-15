package com.vinzor.util;

import java.util.concurrent.ExecutionException;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.vinzor.memcached.MemcachedRunner;

import net.spy.memcached.MemcachedClient;
import net.spy.memcached.internal.OperationFuture;

@Component
public class MemcachedUtil {
	protected Logger logger =  LoggerFactory.getLogger(this.getClass());
	@Resource
	MemcachedRunner memcachedRunner;
	private MemcachedClient getMemcachedClient() {		
		return memcachedRunner.getClient();
	}
	public boolean add(String key,int exp,Object object) throws InterruptedException, ExecutionException {
		OperationFuture<Boolean> oFuture=this.getMemcachedClient().add(key, exp, object);
		return oFuture.get();
	}
	public Object get(String key) {
		return this.getMemcachedClient().get(key);
	}
	public boolean delete(String key) throws InterruptedException, ExecutionException {
		OperationFuture<Boolean> oFuture=this.getMemcachedClient().delete(key);		
		return oFuture.get();
	}
}
