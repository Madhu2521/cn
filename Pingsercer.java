import java.io.*;
import java.net.*;

public class PingServer {
    public static void main(String[] args) {
        try {
            ServerSocket ServerSoc = new ServerSocket(2156);
            System.out.println("[INFO]: Server Started and listening for connections");
            Socket Soc = ServerSoc.accept();
            if (Soc.isConnected())
                System.out.println("[INFO]: Connected to client at" + Soc.getInetAddress());
            System.out.println("[INFO]: Waiting for inputs");
            DataInputStream SocIn = new DataInputStream(Soc.getInputStream());
            DataOutputStream SocOut = new DataOutputStream(Soc.getOutputStream());
            String Packets = "";
            String Address = "";
            if ((SocIn.readUTF()).equals("P")) {
                Packets = new StringBuilder().append(SocIn.readInt()).toString();
                System.out.println("[INFO]: Requested number of packets to ping: " + Packets);
            }
            if ((SocIn.readUTF()).equals("A")) {
                Address = SocIn.readUTF();
                System.out.println("[INFO]: Requested address to be ping: " + Address);
            }
            System.out.println("[INFO]: Executing ping -n " + Packets + " " + Address);
            Process PingProc = new ProcessBuilder("ping", "-n", Packets, Address).start();
            BufferedReader BufReader = new BufferedReader(new InputStreamReader(PingProc.getInputStream()));
            String PingOut = BufReader.readLine();
            while (PingOut != null) {
                SocOut.writeUTF(PingOut);
                PingOut = BufReader.readLine();
            }
            BufReader.close();
            SocIn.close();
            SocOut.close();
            Soc.close();
            ServerSoc.close();
            System.out.println("[INFO]: Server Socket Closed and exitting");
        } catch (Exception x) {
            x.printStackTrace();
        }
    }

}
