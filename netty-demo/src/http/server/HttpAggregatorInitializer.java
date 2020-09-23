package http.server;


import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;

public class HttpAggregatorInitializer extends ChannelInitializer<Channel> {
	
	private boolean isClient;
	
	public HttpAggregatorInitializer(boolean isClient) {
		this.isClient = isClient;
	}

	@Override
	protected void initChannel(Channel arg0) throws Exception {
		// TODO Auto-generated method stub
		ChannelPipeline pipeline = arg0.pipeline();
		if(isClient) {
			pipeline.addLast("codoc", new HttpClientCodec());
		}else {
			pipeline.addLast("codoc", new HttpServerCodec());
		}
		pipeline.addLast("aggegator", new HttpObjectAggregator(512 * 1024));
		pipeline.addLast(new ChannelHandler());
	}
}
