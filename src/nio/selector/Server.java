package nio.selector;


import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class Server {

    public static void main(String[] args) throws Exception {
        System.out.println("Server start...");
        // 1.获取通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        // 2. 切换非阻塞模式
        serverSocketChannel.configureBlocking(false);
        // 3. 绑定连接到端口
        serverSocketChannel.bind(new InetSocketAddress(9999));
        // 4. 获取选择器
        Selector selector = Selector.open();
        // 5. 将服务端通道注册到选择器，并开始指定监听事件
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        // 6. 使用selector轮询已经就绪好的事件
        while(selector.select() > 0) {
            System.out.println("开始一轮事件处理...");
            // 7. 获取选择器中的所有注册通道中已经就绪好的事件
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey sk = iterator.next();
                // 8. 判断事件类型
                if(sk.isAcceptable()) {
                    // 9. 获取当前接入的客户端通道
                    System.out.println("accept事件...");
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector,SelectionKey.OP_READ);
                }else if(sk.isReadable()){
                    System.out.println("read事件...");
                    SocketChannel socketChannel = (SocketChannel) sk.channel();
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    int len = 0;
                    while((len = socketChannel.read(byteBuffer) )> 0) {
                        byteBuffer.flip();
                        System.out.println(new String(byteBuffer.array(),0,len));
                    }

                }
                iterator.remove();

            }


        }





    }

}
