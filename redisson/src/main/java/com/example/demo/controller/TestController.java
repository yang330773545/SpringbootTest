package com.example.demo.controller;

import org.redisson.RedissonMultiLock;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

// https://github.com/redisson/redisson-examples/tree/master/locks-synchronizers-examples/src/main/java/org/redisson/example/locks
@RestController
public class TestController {

    private  Logger log = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private RedissonClient redissonClient;

    private final String KEY_PREFIX = "my_test_key_";

    @GetMapping(value = "/lock/{id}")
    public String unfairLock(@PathVariable String id) {
        String lockKey = KEY_PREFIX + id;
        // lock 默认为非公平锁
        RLock lock = redissonClient.getLock(lockKey);
        // 获取锁并在10s后进行自动释放 未解锁前使用
        lock.lock(10, TimeUnit.SECONDS);
        // 异步锁
        //lock.lockAsync(10, TimeUnit.SECONDS);

        try {
            // 等待100秒 并在10秒后解锁
            boolean res = lock.tryLock(100, 10, TimeUnit.SECONDS);
            // Future<Boolean> res = lock.tryLockAsync(100, 10, TimeUnit.SECONDS);
            if(res){
                // do somethings
            }
        } catch (InterruptedException e){
            log.error("尝试加锁时出现异常");
        } finally {
            lock.unlock();
        }

        return null;

//        // 公平锁
//        RLock lock_f = redissonClient.getFairLock("test");
//        // MultiLock，可以把一组锁当作一个锁来加锁和释放。
//        RLock lock1 = redissonClient.getLock("lock1");
//        RLock lock2 = redissonClient.getLock("lock2");
//        RLock lock3 = redissonClient.getLock("lock3");
//        RedissonMultiLock lock_m = new RedissonMultiLock(lock1, lock2, lock3);
//        lock_m.lock();
//        lock_m.unlock();
//        // 红锁 RedLock 是在多个 Redis 集群上部署的一种分布式锁的实现方式，他有效避免了单点问题 如果从 N/2+1 个 redis 服务中都获取锁成功，那么，本次分布式锁的获取被视为成功，否则视为获取锁失败
//        RedissonClient client1 = Redisson.create();
//        RedissonClient client2 = Redisson.create();
//
//        RLock lock1 = client1.getLock("lock1");
//        RLock lock2 = client1.getLock("lock2");
//        RLock lock3 = client2.getLock("lock3");
//        RedissonMultiLock lock_m = new RedissonMultiLock(lock1, lock2, lock3);
    }

}
