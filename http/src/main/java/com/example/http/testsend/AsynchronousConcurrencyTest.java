package com.example.http.testsend;

import com.example.http.utils.JSONUtils;
import com.google.common.collect.Lists;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.nio.client.HttpAsyncClient;
import org.apache.http.nio.client.methods.HttpAsyncMethods;
import org.apache.http.nio.protocol.BasicAsyncRequestConsumer;
import org.apache.http.nio.protocol.BasicAsyncResponseConsumer;
import org.apache.http.nio.protocol.HttpAsyncRequestProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.AsyncContext;
import javax.servlet.AsyncListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

public class AsynchronousConcurrencyTest {

    private final Logger logger = LoggerFactory.getLogger(AsynchronousConcurrencyTest.class);
    final static ExecutorService executorService = Executors.newFixedThreadPool(2);
    public static HttpAsyncClient client;
    public Integer asyncTimeoutInSeconds = 10;

    // 同步阻塞调用即串行调用 响应时间为总和
    public void synchronousBlockingCall() throws Exception {
        RpcService rpcService = new RpcService();
        HttpService httpService = new HttpService();
        // 耗时10ms
        Map<String, String> result1 = rpcService.getRpcResult();
        // 耗时20ms
        Integer result2 = httpService.getHttpResult();
        // 总耗时为30ms
    }

    // 线程池配合Future阻塞主线程 响应时间为最慢的请求返回时间
    public void asynchronousFutureCall(){
        RpcService rpcService = new RpcService();
        HttpService httpService = new HttpService();
        Future<Map<String, String>> future1 = null;
        Future<Integer> future2 = null;

        future1 = executorService.submit(()-> rpcService.getRpcResult());
        future2 = executorService.submit(()-> httpService.getHttpResult());
        try {
            // 耗时10ms
            Map<String, String> result1 = future1.get(300, TimeUnit.MILLISECONDS);
            // 耗时20ms
            Integer result2 = future2.get(300, TimeUnit.MILLISECONDS);
            // 总耗时20ms
        }catch (Exception e){
            if(future1 != null){
                future1.cancel(true);
            }
            if(future2 != null){
                future2.cancel(true);
            }
            throw new RuntimeException(e);
        }

    }

    // 通过回调机制实现 首先发出网络请求当网络返回时回调方法 不能提高性能支持大量并发连接或提高吞吐量
    public String asynchronousCallBack(String url) throws ExecutionException, InterruptedException {
        CompletableFuture<String> asyncFuture = new CompletableFuture<>();
        HttpAsyncRequestProducer producer = HttpAsyncMethods.create(new HttpPost(url));
        BasicAsyncResponseConsumer consumer = new BasicAsyncResponseConsumer();
        FutureCallback callback = new FutureCallback<HttpResponse>() {
            @Override
            public void completed(HttpResponse response) {
                asyncFuture.complete(response.toString());
            }

            @Override
            public void failed(Exception e) {
                asyncFuture.completeExceptionally(e);
            }

            @Override
            public void cancelled() {
                asyncFuture.cancel(true);
            }
        };
        client.execute(producer, consumer, callback);
        return asyncFuture.get();
    }

    // 异步编排场景一 三个服务异步并发调用 合并结果
    public void asynchronousOrchestration1() throws Exception {
        MyService service = new MyService();
        CompletableFuture<String> future1 = service.getHttpData("");
        CompletableFuture<String> future2 = service.getHttpData("");
        CompletableFuture<String> future3 = service.getHttpData("");
        // 不阻塞主线程
        CompletableFuture.allOf(future1, future2, future3).thenApplyAsync((Void)->{
            // 异步处理结果
            return null;
        }).exceptionally(e -> {
            // 处理异常
            return null;
        });
        // 阻塞
        CompletableFuture future4 = CompletableFuture.allOf(future1, future2, future3).thenApplyAsync((Void)->{
            try {
                return Lists.newArrayList(future1.get(), future2.get(), future3.get());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }).exceptionally(e -> {
            // 处理异常
            return null;
        });
    }

    // 异步编排场景二 两个服务并发调用然后消费结果 不阻塞主线程
    public void asynchronousOrchestration2() throws Exception {
        MyService service = new MyService();
        CompletableFuture<String> future1 = service.getHttpData("");
        CompletableFuture<String> future2 = service.getHttpData("");
        future1.thenAcceptBothAsync(future2, (future1Result, future2Result)->{
            // 异步处理结果
        }).exceptionally(e -> {
            // 处理异常
            return null;
        });
    }

    // 异步编排场景三 1执行完毕后并发执行2和3然后消费相关结果 不阻塞主线程
    public void asynchronousOrchestration3() throws Exception {
        MyService service = new MyService();
        CompletableFuture<String> future1 = service.getHttpData("");
        CompletableFuture<String> future2 = future1.thenApplyAsync(v -> {
            return "result from service2";
        });
        CompletableFuture<String> future3 = service.getHttpData("");

        future2.thenCombineAsync(future3, (future2Result, future3Result) -> {
            // 异步处理结果
            return null;
        }).exceptionally(e -> {
            // 处理异常
            return null;
        });

    }

    private AsyncListener asyncListener = new MyAsyncListener();

    // Servlet容器接受请求后 tomcat解析请求体 异步Servlet请求交给异步线程池完成 tomcat释放容器
    public void submitFuture(final HttpServletRequest req, final Callable<CompletableFuture> task) throws Exception {
        final String uri = req.getRequestURI();
        final Map<String, String[]> params = req.getParameterMap();
        final AsyncContext asyncContext = req.startAsync();
        asyncContext.getRequest().setAttribute("uri", uri);
        asyncContext.getRequest().setAttribute("params", params);
        asyncContext.setTimeout(asyncTimeoutInSeconds * 1000);
        if(asyncListener != null){
            asyncContext.addListener(asyncListener);
        }
        CompletableFuture future = task.call();
        future.thenAccept(result -> {
            HttpServletResponse resp = (HttpServletResponse) asyncContext.getResponse();
            try {
                if(result instanceof String){
                    byte[] bytes = ((String) result).getBytes("GBK");
                    resp.setContentType("text/html;charset=gbk");
                    resp.setContentLength(bytes.length);
                    resp.getOutputStream().write(bytes);
                } else {
                    write(resp, JSONUtils.toJSON(result));
                }
            } catch (Throwable e){
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                // 程序内部错误
                try{
                    logger.error("get info error,uri:{}, params:{}", uri, JSONUtils.toJSON(result), e);
                }catch (Exception ex){

                }finally {
                    asyncContext.complete();
                }
            }
        }).exceptionally(e -> {
            asyncContext.complete();
            return null;
        });
    }

    private void write(HttpServletResponse resp, String json){

    }

    static class RpcService{
        Map<String, String> getRpcResult() throws Exception {
            // 调用远程方法这里使用Thread.sleep模拟
            Thread.sleep(100);
            return new HashMap<String, String>();
        }
    }

    static class HttpService{
        Integer getHttpResult() throws Exception {
            Thread.sleep(20);
            return 0;
        }
    }

    static class MyService{
        CompletableFuture<String> getHttpData(String url) throws Exception {
            Thread.sleep(100);
            return null;
        }
    }
}
