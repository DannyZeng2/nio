package bio.singleThread;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args)  {
        try {
            System.out.println("listening at port 9999...");
            ServerSocket serverSocket = new ServerSocket(9999);
            Socket socket = serverSocket.accept();
            // 从socket管道得到一个字节输入流对象
            InputStream is = socket.getInputStream();
            // 把字节输入流对象包装成缓冲字符输入流对象
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String msg ;

            while ((msg = br.readLine()) != null) {
                System.out.println("服务端收到消息：" + msg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
