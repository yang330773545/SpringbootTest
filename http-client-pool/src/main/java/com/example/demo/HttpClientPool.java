package com.example.demo;

import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.DnsResolver;
import org.apache.http.conn.HttpConnectionFactory;
import org.apache.http.conn.ManagedHttpClientConnection;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.DefaultConnectionReuseStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.ManagedHttpClientConnectionFactory;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.impl.conn.SystemDefaultDnsResolver;
import org.apache.http.impl.io.DefaultHttpRequestWriterFactory;
import org.apache.http.impl.io.DefaultHttpResponseParserFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;


public class HttpClientPool {
    static PoolingHttpClientConnectionManager manager = null;
    static CloseableHttpClient httpClient = null;

    public static synchronized CloseableHttpClient getHttpClient(){
        if(httpClient == null){
            // 注册访问协议相关工厂
            Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                    .register("http", PlainConnectionSocketFactory.INSTANCE)
                    .register("https", SSLConnectionSocketFactory.getSystemSocketFactory())
                    .build();
            // 写请求，配置相应处理器
            HttpConnectionFactory<HttpRoute, ManagedHttpClientConnection> connectionFactory = new ManagedHttpClientConnectionFactory(
                    DefaultHttpRequestWriterFactory.INSTANCE,
                    DefaultHttpResponseParserFactory.INSTANCE
            );
            // DNS解析器
            DnsResolver dnsResolver = SystemDefaultDnsResolver.INSTANCE;
            //池化连接管理器
            manager = new PoolingHttpClientConnectionManager(socketFactoryRegistry, connectionFactory, dnsResolver);
            // 默认socket配置
            SocketConfig defaultSocketConfig = SocketConfig.custom().setTcpNoDelay(true).build();
            manager.setDefaultSocketConfig(defaultSocketConfig);

            manager.setMaxTotal(300);// 整个线程池最大连接数
            manager.setDefaultMaxPerRoute(200);// 每个路由的最大连接
            //manager.setMaxPerRoute(new HttpRoute(new HttpHost("www.baidu.com", 80)), 100); //为某个路由单独设置最大连接
            manager.setValidateAfterInactivity(5 * 1000);// 连接多长时间不活跃进行验证默认2s

            // 请求配置
            RequestConfig defaultRequestConfig = RequestConfig.custom()
                    .setConnectTimeout(2 * 1000)// 超时连接超时时间
                    .setSocketTimeout(5 * 1000)// 设置等待超时时间
                    .setConnectionRequestTimeout(2 * 1000)// 连接池获取连接的等待时间
                    .build();
            // 创建HttpClient
            httpClient = HttpClients.custom().setConnectionManager(manager).setConnectionManagerShared(false)// 不共享连接池
                    .evictIdleConnections(60, TimeUnit.SECONDS)// 定期回收空闲连接
                    .evictExpiredConnections()// 定期回收过期连接
                    .setConnectionTimeToLive(60, TimeUnit.SECONDS)//设置存活时间不设置根据长连接信息决定
                    .setDefaultRequestConfig(defaultRequestConfig)
                    .setConnectionReuseStrategy(DefaultConnectionReuseStrategy.INSTANCE)// 连接重用策略（是否能KeepAlive）
                    .setKeepAliveStrategy(DefaultConnectionKeepAliveStrategy.INSTANCE)// 长连接配置（长连接生产多长时间）
                    .setRetryHandler(new DefaultHttpRequestRetryHandler(0, false)) // 重试次数
                    .build();
            // jvm停止或重启释放资源
            Runtime.getRuntime().addShutdownHook(new Thread(){
                @Override
                public void run() {
                    try {
                        httpClient.close();
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                }
            });
            return httpClient;
        }
        return httpClient;
    }
}
