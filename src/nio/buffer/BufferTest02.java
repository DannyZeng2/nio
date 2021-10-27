package nio.buffer;

import java.nio.ByteBuffer;

public class BufferTest02 {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(10);

        String data = "hi!danny";
        buffer.put(data.getBytes());
        buffer.flip();
        System.out.println(buffer.position()); // 0
        System.out.println(buffer.limit()); // 8
        System.out.println(buffer.capacity()); // 10

        byte[] dst = new byte[3];

        buffer.get(dst);
        System.out.println(new String(dst));
        System.out.println(buffer.position()); // 3
        System.out.println(buffer.limit()); // 8
        System.out.println(buffer.capacity()); // 10

        buffer.mark(); // 标记当前position下标位置
        buffer.get(dst);
        System.out.println(new String(dst));
        System.out.println(buffer.remaining()); // 2

        buffer.reset(); // 重置下标到标记处

        System.out.println(buffer.remaining()); // 5


    }
}
