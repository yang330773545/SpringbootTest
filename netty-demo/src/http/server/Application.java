package http.server;

public class Application {

	 public static void main(String[] args) throws Exception{
		 NettyDemoHttpServer server = new NettyDemoHttpServer(8081);// 8081为启动端口
	     server.start();
    }
}
