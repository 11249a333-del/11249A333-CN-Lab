
import java.net.*;
import java.io.*;

public class HTTPServer {
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(8080);

            System.out.println("HTTP Server running on http://localhost:8080 ...");

            while (true) {
                Socket conn = server.accept();

                BufferedReader in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream())
                );

                String request;
                System.out.println("--- Received HTTP Request ---");

                while ((request = in.readLine()) != null && !request.isEmpty()) {
                    System.out.println(request);
                }

                String response =
                    "HTTP/1.1 200 OK\r\n" +
                    "Content-Type: text/html; charset=utf-8\r\n" +
                    "Connection: close\r\n" +
                    "\r\n" +
                    "<html><body><h1>welcome to Computer networks Lab!</h1></body></html>";

                OutputStream out = conn.getOutputStream();
                out.write(response.getBytes("UTF-8"));
                out.flush();

                conn.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
