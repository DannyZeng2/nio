package bio.multiThread;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args)  {
        try {
            System.out.println("listening at port 9999...");
            ServerSocket serverSocket = new ServerSocket(9999);
            while (true) {
                Socket socket = serverSocket.accept();
                new ServerThreadReader(socket).start();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
