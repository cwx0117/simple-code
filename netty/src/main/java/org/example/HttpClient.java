package org.example;


import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.DefaultFullHttpRequest;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpResponseDecoder;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.handler.codec.http.HttpContent;
import io.netty.handler.codec.http.LastHttpContent;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.util.CharsetUtil;

import java.net.URI;

import static io.netty.handler.codec.http.HttpHeaderNames.CONNECTION;
import static io.netty.handler.codec.http.HttpHeaderNames.CONTENT_TYPE;
import static io.netty.handler.codec.http.HttpHeaderValues.KEEP_ALIVE;

/**
 * 在上述代码中，`HttpServerHandler`类实现了`channelRead()`方法来处理HTTP请求。
 * 在这个方法中，它构造了一个HTTP响应，并将其写回客户端。在`channelReadComplete()`方法中，
 * 它将响应刷新到客户端。在`exceptionCaught()`方法中，它打印异常信息并关闭连接。
 */
public class HttpClient {

    private final String host;
    private final int port;

    public HttpClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void run() throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) {
                            ChannelPipeline p = ch.pipeline();
                            p.addLast(new HttpClientCodec());
                            p.addLast(new HttpObjectAggregator(65536));
                            p.addLast(new HttpResponseDecoder());
                            p.addLast(new HttpClientHandler());
                        }
                    });
            ChannelFuture f = b.connect(host, port).sync();
            URI uri = new URI("/");
            HttpRequest request = new DefaultFullHttpRequest(HttpVersion.HTTP_1_1, HttpMethod.GET, uri.toASCIIString(),
                    Unpooled.EMPTY_BUFFER);
            request.headers().set(CONNECTION, KEEP_ALIVE);
            request.headers().set(CONTENT_TYPE, "text/plain; charset=UTF-8");

            f.channel().write(request);
            f.channel().flush();
            f.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        String host = args[0];
        int port = Integer.parseInt(args[1]);
        new HttpClient(host, port).run();
    }

    private static class HttpClientHandler extends SimpleChannelInboundHandler<Object> {

        private HttpResponse response;
        private ByteBuf buf = Unpooled.buffer();

        @Override
        public void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
            if (msg instanceof HttpResponse) {
                response = (HttpResponse) msg;
                System.out.println("Response Status: " + response.status());
                System.out.println("Response Headers: " + response.headers());
            }

            if (msg instanceof HttpContent) {
                HttpContent content = (HttpContent) msg;
                buf.writeBytes(content.content());
                if (content instanceof LastHttpContent) {
                    System.out.println("Response Content: " + buf.toString(CharsetUtil.UTF_8));
                    buf.clear();
                    ctx.close();
                }
            }
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            cause.printStackTrace();
            ctx.close();
        }
    }
}


