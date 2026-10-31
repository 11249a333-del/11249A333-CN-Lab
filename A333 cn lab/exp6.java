
import java.util.HashMap;

public class ARP_RARP_Simulator {

    HashMap<String, String> arpTable = new HashMap<>();
    HashMap<String, String> rarpTable = new HashMap<>();

    // Constructor
    ARP_RARP_Simulator() {

        arpTable.put("192.168.1.1", "00:0a:95:9d:68:16");
        arpTable.put("192.168.1.2", "00:0a:95:9d:68:17");
        arpTable.put("192.168.1.3", "00:0a:95:9d:68:18");

        // Create RARP table
        for (String ip : arpTable.keySet()) {
            rarpTable.put(arpTable.get(ip), ip);
        }
    }

    // ARP Request
    void arpRequest(String ipAddress) {

        System.out.println("ARP Request: Who has " + ipAddress +
                           "? Tell me your MAC address.");

        String macAddress = arpTable.get(ipAddress);

        if (macAddress != null) {
            System.out.println("ARP Reply: " + ipAddress +
                               " is at " + macAddress + ".");
        } else {
            System.out.println("ARP Reply: " + ipAddress +
                               " is not found.");
        }
    }

    // RARP Request
    void rarpRequest(String macAddress) {

        System.out.println("RARP Request: Who has MAC address " +
                           macAddress + "? Tell me your IP address.");

        String ipAddress = rarpTable.get(macAddress);

        if (ipAddress != null) {
            System.out.println("RARP Reply: " + macAddress +
                               " is associated with IP " + ipAddress + ".");
        } else {
            System.out.println("RARP Reply: " + macAddress +
                               " is not found.");
        }
    }

    // Main Method
    public static void main(String[] args) {

        ARP_RARP_Simulator simulator = new ARP_RARP_Simulator();

        simulator.arpRequest("192.168.1.1");
        simulator.arpRequest("192.168.1.100");

        simulator.rarpRequest("00:0a:95:9d:68:16");
        simulator.rarpRequest("00:0a:95:9d:68:99");
    }
}
