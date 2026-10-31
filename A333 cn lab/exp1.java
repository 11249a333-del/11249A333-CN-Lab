import java.net.InetAddress;
import java.net.UnknownHostException;

public class HostDetails {
    public static void main(String[] args) {
        try {
            InetAddress inet = InetAddress.getLocalHost();

            String hostname = inet.getHostName();
            String ipAddress = inet.getHostAddress();

            System.out.println("Hostname: " + hostname);
            System.out.println("IP Address: " + ipAddress);

        } catch (UnknownHostException e) {
            System.out.println("Unable to get host details.");
            e.printStackTrace();
        }
    }
}
