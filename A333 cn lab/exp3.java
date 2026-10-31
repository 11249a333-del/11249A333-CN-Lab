
import java.net.*;
import java.io.*;

public class EchoServer {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(65432);

            System.out.println("Server listening on port 65432...");

            Socket connection = serverSocket.accept();

            System.out.println("Connected by " + connection.getInetAddress());

            InputStream in = connection.getInputStream();
            OutputStream out = connection.getOutputStream();

            byte[] buffer = new byte[1024];
            int bytesRead;

            while ((bytesRead = in.read(buffer)) != -1) {
                String data = new String(buffer, 0, bytesRead);

                System.out.println("Received: " + data);

                out.write(buffer, 0, bytesRead);
                out.flush();
            }

            connection.close();
            serverSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
