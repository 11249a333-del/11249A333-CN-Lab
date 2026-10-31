
import java.net.*;
import java.io.*;
import java.util.Scanner;

public class EchoClient {
    public static void main(String[] args) {
        try {
            Socket clientSocket = new Socket("localhost", 65432);

            Scanner sc = new Scanner(System.in);

            OutputStream out = clientSocket.getOutputStream();
            InputStream in = clientSocket.getInputStream();

            byte[] buffer = new byte[1024];

            while (true) {
                System.out.print("Enter a message: ");
                String message = sc.nextLine();

                out.write(message.getBytes());
                out.flush();

                int bytesRead = in.read(buffer);

                if (bytesRead == -1) {
                    break;
                }

                String data = new String(buffer, 0, bytesRead);

                System.out.println("Echoed from server: " + data);

                if (message.equalsIgnoreCase("exit")) {
                    break;
                }
            }

            clientSocket.close();
            sc.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
