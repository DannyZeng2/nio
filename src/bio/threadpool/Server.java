package bio.threadpool;

import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try {
            System.out.println("listening at port 9999...");
            ServerSocket serverSocket = new ServerSocket(9999);
            SocketServerPoolHandler socketServerPoolHandler = new SocketServerPoolHandler(6, 120, 10);

            while (true) {
                Socket socket = serverSocket.accept();
                socketServerPoolHandler.execute(new ServerRunnableTarget(socket));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
