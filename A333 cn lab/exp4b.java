
import java.net.*;
import java.io.*;
import java.util.Scanner;

public class ChatClient {

    public static void main(String[] args) {
        Socket clientSocket = new Socket();

        try {
            clientSocket.connect(new InetSocketAddress("localhost", 55555));

            System.out.println("Connected to chat server.");

            // Thread to receive messages
            Thread receiveThread = new Thread(() -> {
                try {
                    BufferedReader in = new BufferedReader(
                        new InputStreamReader(clientSocket.getInputStream())
                    );

                    String message;

                    while ((message = in.readLine()) != null) {
                        System.out.println(message);
                    }

                    System.out.println("Disconnected from server.");

                } catch (IOException e) {
                    System.out.println("Error receiving message: " + e);
                }
            });

            receiveThread.setDaemon(true);
            receiveThread.start();

            // Send messages
            Scanner sc = new Scanner(System.in);
            PrintWriter out = new PrintWriter(
                clientSocket.getOutputStream(), true
            );

            while (true) {
                String message = sc.nextLine();

                if (message.equalsIgnoreCase("exit")) {
                    break;
                }

                out.println(message);
            }

            clientSocket.close();
            sc.close();

        } catch (Exception e) {
            System.out.println("Unable to connect or communicate: " + e);
        }
    }
}
