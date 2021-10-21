package bio.multithread;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class ServerThreadReader extends Thread {
    private final Socket socket;

    public ServerThreadReader(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            // 从socket管道得到一个字节输入流对象
            InputStream is = socket.getInputStream();
            // 把字节输入流对象包装成缓冲字符输入流对象
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String msg;
            while ((msg = br.readLine()) != null) {
                System.out.println("服务端收到消息：" + msg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
