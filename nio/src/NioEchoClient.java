import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class NioEchoClient {
    public static void main(String[] args) throws IOException {
        String host = "localhost"; // 服务器主机名或IP地址
        int port = 9999; // 服务器监听的端口号
        SocketChannel socketChannel = SocketChannel.open(); // 创建客户端Socket通道
        socketChannel.connect(new InetSocketAddress(host, port)); // 连接服务器
        System.out.println("NioEchoClient connected to " + host + ":" + port);

        ByteBuffer buffer = ByteBuffer.allocate(1024); // 创建缓冲区

        // 向服务器发送数据
        String message = "Hello, World!";
        buffer.put(message.getBytes());
        buffer.flip(); // 切换为读模式
        socketChannel.write(buffer);
        buffer.clear(); // 清空缓冲区

        // 从服务器接收数据
        int bytesRead = socketChannel.read(buffer);
        if (bytesRead > 0) {
            buffer.flip(); // 切换为读模式
            String response = new String(buffer.array(), 0, bytesRead);
            System.out.println("Received response: " + response);
            buffer.clear(); // 清空缓冲区
        }

        socketChannel.close(); // 关闭通道
    }
}
