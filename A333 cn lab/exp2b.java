
import java.net.*;
import java.io.*;

public class HTTPClient {
    public static void main(String[] args) {
        try {
            Socket client = new Socket("localhost", 8080);

            String httpRequest =
                "GET / HTTP/1.1\r\n" +
                "Host: localhost\r\n" +
                "Connection: close\r\n" +
                "\r\n";

            OutputStream out = client.getOutputStream();
            out.write(httpRequest.getBytes("UTF-8"));
            out.flush();

            InputStream in = client.getInputStream();

            ByteArrayOutputStream response = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int bytesRead;

            while ((bytesRead = in.read(buffer)) != -1) {
                response.write(buffer, 0, bytesRead);
            }

            System.out.println("--- Received HTTP Response ---");
            System.out.println(response.toString("UTF-8"));

            client.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
