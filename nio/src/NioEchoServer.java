import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class NioEchoServer {
    public static void main(String[] args) throws IOException {
        int port = 9999; // 服务器监听的端口号
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open(); // 创建服务器Socket通道
        serverSocketChannel.socket().bind(new InetSocketAddress(port)); // 绑定端口
        serverSocketChannel.configureBlocking(false); // 配置为非阻塞模式
        System.out.println("NioEchoServer started on port " + port);

        while (true) {
            SocketChannel clientSocketChannel = serverSocketChannel.accept(); // 等待客户端连接
            if (clientSocketChannel != null) {
                System.out.println("Client connected from " + clientSocketChannel.getRemoteAddress());

                ByteBuffer buffer = ByteBuffer.allocate(1024); // 创建缓冲区

                while (clientSocketChannel.read(buffer) > 0) { // 从客户端读取数据
                    buffer.flip(); // 切换为读模式
                    clientSocketChannel.write(buffer); // 将数据写回客户端
                    buffer.clear(); // 清空缓冲区
                }

                clientSocketChannel.close(); // 关闭通道
            }
        }
    }
}

