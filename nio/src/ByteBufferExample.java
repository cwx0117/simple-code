import java.nio.ByteBuffer;

public class ByteBufferExample {
    public static void main(String[] args) {
        String message = "Hello, World!";

        // 写入数据到ByteBuffer中
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.put(message.getBytes());
        buffer.flip(); // 切换为读模式

        // 从ByteBuffer中读取数据
        byte[] bytes = new byte[buffer.remaining()];
        buffer.get(bytes);
        String response = new String(bytes);
        System.out.println("Received response: " + response);
    }
}
