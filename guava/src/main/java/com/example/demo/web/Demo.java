package com.example.demo.web;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Lists;
import com.google.common.io.Files;
import com.google.common.util.concurrent.RateLimiter;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class Demo {
    Logger logger = LoggerFactory.getLogger(Demo.class);

    /**
     * @throws ExecutionException
     * 使用guava的cache做计数器
     */
    @GetMapping("/cache")
    public void useLoadingCache() throws ExecutionException {
        LoadingCache<Long, AtomicLong> counter = CacheBuilder.newBuilder()
                // 过期时间两秒 对一秒内访问量计数
                .expireAfterAccess(2, TimeUnit.SECONDS)
                .build(new CacheLoader<Long, AtomicLong>() {
                    @Override
                    public AtomicLong load(Long seconds) throws Exception {
                        return new AtomicLong(0);
                    }
                });
        long limit = 1000;
        while(true){
            // 当前秒
            long currentSeconds = System.currentTimeMillis()/1000;
            if(counter.get(currentSeconds).incrementAndGet() > limit){
                logger.info("限流："+currentSeconds);
                continue;
            }
            // 业务处理
        }
    }

    /**
     * 使用RateLimiter提供的令牌桶算法
     */
    @GetMapping("/limiter")
    public void useRateLimiter() throws InterruptedException {
        // 桶容量为2且每秒增加2个令牌（500ms增加一个令牌）
        RateLimiter limiter = RateLimiter.create(2.0);
        // acquire 表示消费多少个令牌 其允许一定量的突发（输出0为成功）
        logger.info(String.valueOf(limiter.acquire(4)));
        // 上边消费了4个令牌 这里输出的等待时间差不多为2s
        logger.info(String.valueOf(limiter.acquire()));
        Thread.sleep(5000L);
        // 允许将未消费的令牌暂存令牌桶 这里存了2个旧的
        logger.info(String.valueOf(limiter.acquire()));
        logger.info(String.valueOf(limiter.acquire()));
        logger.info(String.valueOf(limiter.acquire()));
        logger.info(String.valueOf(limiter.acquire()));
    }

    @GetMapping("/limiter2")
    public void useRateLimiter2() throws InterruptedException {
        // 第二个参数表示 从冷启动速率过渡到平均速度的时间间隔
        RateLimiter limiter = RateLimiter.create(5.0,1000,TimeUnit.MILLISECONDS);

        //这里输出的等待时间更长
        for(int i = 0; i < 5; i++){
            logger.info(String.valueOf(limiter.acquire()));
        }
        Thread.sleep(1000L);
        //这里输出的等待时间稍微变短
        for(int i = 0; i < 5; i++){
            logger.info(String.valueOf(limiter.acquire()));
        }
    }

    public boolean acquire() throws IOException {
        String luaScript = Files.toString(new File("/lua/limit.lua"), Charset.defaultCharset());
        Jedis jedis = new Jedis("172.0.0.1", 6379);
        String key = "ip:" + System.currentTimeMillis()/1000;
        String limit = "3";
        return (Long)jedis.eval(luaScript, Lists.newArrayList(key), Lists.newArrayList(limit)) == 1;
    }

    @GetMapping("/lists")
    public void useLists(){
        List<String> lists = Lists.newArrayList("A","B","C",null,"E","F");
        String str = "A,B,C,,E,F";
        String str1 = "   A,B,C,D,E,F   ";
       // logger.info(Joiner.on(",").join(lists));
        // 去空
        logger.info(Joiner.on(",").skipNulls().join(lists));
        // 替换空
        logger.info(Joiner.on(",").useForNull("D").join(lists));

        List<String> aList = Splitter.on(",").splitToList(str);
        logger.info(String.valueOf(aList));
        // 去空格
        List<String> bList = Splitter.on(",").trimResults().splitToList(str1);
        logger.info(String.valueOf(bList));
        // 去空
        List<String> cList = Splitter.on(",").omitEmptyStrings().splitToList(str);
        logger.info(String.valueOf(cList));
        // 按长度分割
        List<String> dList = Splitter.on(",").fixedLength(3).splitToList(str);
        logger.info(String.valueOf(dList));
    }

}
