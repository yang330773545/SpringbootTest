package htttps.server;

import java.io.FileInputStream;
import java.net.InetSocketAddress;
import java.security.KeyStore;

import javax.net.ssl.KeyManagerFactory;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;

public class NettyDemoHttpsServer {
	
	private final int port;
	
	public NettyDemoHttpsServer(final int port) {
		this.port = port;
	}
	
	public void start() throws Exception{
        ServerBootstrap bootstrap = new ServerBootstrap();
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup work = new NioEventLoopGroup();
        
        // Ssl
        KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance("SunX509");
		KeyStore keyStore = KeyStore.getInstance("JKS");
		keyStore.load(new FileInputStream("e:/work/test2/keystore.jks"), "8a5500n".toCharArray());
		keyManagerFactory.init(keyStore, "8a5500n".toCharArray());
		final SslContext sslContext = SslContextBuilder.forServer(keyManagerFactory).build();
		
        bootstrap.group(boss,work)
                .handler(new LoggingHandler(LogLevel.DEBUG))
                .channel(NioServerSocketChannel.class)
                .childHandler(new HttpsCodecInitializer(sslContext, false));

        ChannelFuture f = bootstrap.bind(new InetSocketAddress(port)).sync();
        System.out.println("server start up on port: " + port);
        f.channel().closeFuture().sync();

    }
}
