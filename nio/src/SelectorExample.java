import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

public class SelectorExample {

    public static void main(String[] args) throws IOException {
        // 创建一个Selector对象
        Selector selector = Selector.open();

// 将ServerSocketChannel注册到Selector中，并指定监听连接事件
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        serverChannel.socket().bind(new InetSocketAddress("localhost", 9999));
        serverChannel.configureBlocking(false);
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);

// 轮询Selector
        while (true) {
            // 阻塞直到有事件发生
            selector.select();

            // 获取到就绪状态的Channel集合
            Set<SelectionKey> selectedKeys = selector.selectedKeys();
            Iterator<SelectionKey> keyIterator = selectedKeys.iterator();

            while (keyIterator.hasNext()) {
                SelectionKey key = keyIterator.next();

                // 处理连接事件
                if (key.isAcceptable()) {
                    // 接受连接
                    SocketChannel socketChannel = serverChannel.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ);
                }

                // 处理读事件
                if (key.isReadable()) {
                    // 读取数据
                    SocketChannel socketChannel = (SocketChannel) key.channel();
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    socketChannel.read(buffer);
                    buffer.flip();
                    String message = StandardCharsets.UTF_8.decode(buffer).toString();
                    System.out.println(message);
                }

                // 将已处理的事件从集合中移除
                keyIterator.remove();
            }
        }

    }
}
