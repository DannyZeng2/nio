package nio.buffer;

import java.nio.ByteBuffer;

public class BufferTest01 {

    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(10);

        System.out.println(buffer.position()); // 0
        System.out.println(buffer.limit()); // 10
        System.out.println(buffer.capacity()); // 10

        System.out.println("===================");
        // 往缓冲区添加数据
        String data = "hi danny";
        buffer.put(data.getBytes());
        System.out.println(buffer.position()); // 8
        System.out.println(buffer.limit()); // 10
        System.out.println(buffer.capacity()); // 10

        System.out.println("===================");
        // flip方法将Buffer从写模式切换到读模式。调用flip()方法会将position设回0，并将limit设置成之前position的值
        buffer.flip();
        System.out.println(buffer.position()); // 0
        System.out.println(buffer.limit()); // 8
        System.out.println(buffer.capacity()); // 10

        // 读取数据
        System.out.println("===================");
        char c = (char) buffer.get();
        System.out.println(c);
        System.out.println(buffer.position()); // 1
        System.out.println(buffer.limit()); // 8
        System.out.println(buffer.capacity()); // 10

        // 清除数据,并不会真的清除缓冲区的数据，而是当下一次新数据插入时覆盖
        System.out.println("===================");
        buffer.clear();
        System.out.println(buffer.position()); // 0
        System.out.println(buffer.limit()); // 10
        System.out.println(buffer.capacity()); // 10
    }

}
