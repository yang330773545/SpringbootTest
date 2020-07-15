package com.example.demo.web;

import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@RestController
public class ExecutorTestController {

    // 单线程的线程池
    public String testExecutor(){
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        /**
         * public static ExecutorService newSingleThreadExecutor() {
         *         return new FinalizableDelegatedExecutorService
         *             (new ThreadPoolExecutor(1, 1,
         *                                     0L, TimeUnit.MILLISECONDS,
         *                                     new LinkedBlockingQueue<Runnable>()));
         *     }
         */
        Runtime.getRuntime().addShutdownHook(new Thread(){
            @Override
            public void run() {
                executorService.shutdown();
                try {
                    executorService.awaitTermination(30, TimeUnit.SECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        return null;
    }

    // 固定数量的线程池
    public String fixedNumberExecutor(){
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        /**
         *     public static ExecutorService newFixedThreadPool(int nThreads) {
         *         return new ThreadPoolExecutor(nThreads, nThreads,
         *                                       0L, TimeUnit.MILLISECONDS,
         *                                       new LinkedBlockingQueue<Runnable>());
         *     }
         */
        return null;
    }

    // 可缓存的线程池 初始大小0最大为Integer.MAX_VALUE 使用SynchronousQueue队列 无数据缓冲put操作必须等待task反之亦然
    public void cachedExecutor(){
        ExecutorService executorService = Executors.newCachedThreadPool();
        /**
         *     public static ExecutorService newCachedThreadPool() {
         *         return new ThreadPoolExecutor(0, Integer.MAX_VALUE,
         *                                       60L, TimeUnit.SECONDS,
         *                                       new SynchronousQueue<Runnable>());
         *     }
         */
    }

    // 可延迟的线程池
    public void delayedExecutor(){
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);
        /**
         *     public static ScheduledExecutorService newScheduledThreadPool(int corePoolSize) {
         *         return new ScheduledThreadPoolExecutor(corePoolSize);
         *     }
         */
    }

    // work-stealing任务窃取算法
    public void workStealingExecutor(){
        ExecutorService executorService = Executors.newWorkStealingPool(15);
        /**
         *     public static ExecutorService newWorkStealingPool(int parallelism) {
         *         return new ForkJoinPool
         *             (parallelism,
         *              ForkJoinPool.defaultForkJoinWorkerThreadFactory,
         *              null, true);
         *     }
         */
    }
}
