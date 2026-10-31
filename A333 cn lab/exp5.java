
import java.net.*;
import java.io.*;
import java.util.Scanner;

public class FileTransferClient {
    public static void main(String[] args) {
        try {
            Socket clientSocket = new Socket("localhost", 12345);

            Scanner sc = new Scanner(System.in);

            System.out.print("Enter the filename to send: ");
            String filename = sc.nextLine();

            // Send filename to server
            OutputStream out = clientSocket.getOutputStream();
            out.write((filename + "\n").getBytes());
            out.flush();

            try {
                FileInputStream file = new FileInputStream(filename);

                byte[] buffer = new byte[1024];
                int bytesRead;

                while ((bytesRead = file.read(buffer)) != -1) {
                    out.write(buffer, 0, bytesRead);
                }

                file.close();
                out.flush();

                System.out.println("File " + filename + " sent successfully.");

            } catch (FileNotFoundException e) {
                System.out.println("File " + filename + " not found.");
            }

            clientSocket.close();
            sc.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
