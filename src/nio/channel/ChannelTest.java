package nio.channel;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ChannelTest {

    @Test
    public void write() {
        try {
            FileOutputStream fos = new FileOutputStream("src/nio/channel/data01.md");
            FileChannel channel = fos.getChannel();
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            buffer.put("hello,world...".getBytes());
            buffer.flip(); // 切换读模式
            channel.write(buffer); // 写出数据，对于缓冲区而言，是读数据
            channel.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void read() {
        try {
            FileInputStream fis = new FileInputStream("src/nio/channel/data01.md");
            FileChannel channel = fis.getChannel();
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            channel.read(buffer);
            //buffer.flip();
            System.out.println(buffer.position());
            String s = new String(buffer.array(),0,buffer.position());
            //new String(buffer.array(),0,buffer.remaining())
            System.out.println(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 复制数据
     */
    @Test
    public void copy() {
        try {
            ByteBuffer buffer = ByteBuffer.allocate(1024);

            FileInputStream fis = new FileInputStream("src/nio/channel/input/IMG_001.JPG");
            FileChannel inputChannel = fis.getChannel();


            FileOutputStream fos = new FileOutputStream("src/nio/channel/output/IMG_002.JPG");
            FileChannel outputChannel = fos.getChannel();

            while (true){
                buffer.clear();
                int flag = inputChannel.read(buffer);
                if(flag == -1) {
                    break;
                }
                buffer.flip(); // 切换读模式
                outputChannel.write(buffer);

            }
            inputChannel.close();// 写出数据，对于缓冲区而言，是读数据
            outputChannel.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 分散与聚集
     */
    @Test
    public void scatter_and_gather() {
        try {
            FileInputStream fis = new FileInputStream("src/nio/channel/input/IMG_001.JPG");
            FileOutputStream fos = new FileOutputStream("src/nio/channel/output/IMG_002.JPG");

            FileChannel outputChannel = fos.getChannel();
            FileChannel inputChannel = fis.getChannel();

            ByteBuffer buffer1 = ByteBuffer.allocate(5020);
            ByteBuffer buffer2 = ByteBuffer.allocate(32424);
            ByteBuffer buffer3 = ByteBuffer.allocate(100000);

            ByteBuffer[] buffers = {buffer1,buffer2,buffer3};

            // 从一个channel读取数据到多个缓冲区
            inputChannel.read(buffers);
            for (ByteBuffer buffer : buffers) {
                buffer.flip();
            }
            outputChannel.write(buffers);
            inputChannel.close();
            outputChannel.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
