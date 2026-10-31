
import java.net.*;
import java.io.*;
import java.util.*;

public class ChatServer {

    static ArrayList<Socket> clients = new ArrayList<>();

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(55555);

            System.out.println("Server listening on port 55555...");

            while (true) {
                Socket clientSocket = serverSocket.accept();

                synchronized (clients) {
                    clients.add(clientSocket);
                }

                Thread thread = new Thread(() -> handleClient(clientSocket));
                thread.start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void handleClient(Socket clientSocket) {
        String clientAddress = clientSocket.getInetAddress().toString();

        System.out.println("New connection from " + clientAddress);

        try {
            BufferedReader in = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream())
            );

            String message;

            while ((message = in.readLine()) != null) {
                System.out.println("Received from " + clientAddress + ": " + message);

                synchronized (clients) {
                    for (Socket client : clients) {
                        if (client != clientSocket) {
                            PrintWriter out = new PrintWriter(
                                client.getOutputStream(), true
                            );
                            out.println(message);
                        }
                    }
                }
            }

        } catch (Exception e) {
            System.out.println("Error with " + clientAddress + ": " + e);
        } finally {
            synchronized (clients) {
                clients.remove(clientSocket);
            }

            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println(clientAddress + " disconnected");
        }
    }
}
