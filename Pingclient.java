import java.io.*;
import java.net.*;
import java.util.*;

public class Pingclient {
    public static void main(String[] args) throws IOException {
        Socket Soc = new Socket("localhost", 2156);
        BufferedReader BufReader = new BufferedReader(new InputStreamReader(System.in));
        if (Soc.isConnected())
            System.out.println("[INFO]: Connected to Server at " + Soc.getInetAddress());
        Scanner in = new Scanner(System.in);
        DataInputStream SocIn = new DataInputStream(Soc.getInputStream());
        DataOutputStream SocOut = new DataOutputStream(Soc.getOutputStream());
        System.out.print("[SYSTEM]> Enter Number of packets to send: ");
        int Packets = in.nextInt();
        System.out.print("[SYSTEM]> Enter Address to ping: ");
        String Addresss = BufReader.readLine();
        SocOut.writeUTF("P");
        SocOut.writeInt(Packets);
        SocOut.writeUTF("A");
        SocOut.writeUTF(Addresss);
        String PingProcOut = SocIn.readUTF();
        try {
            do {
                System.out.println("[OUTPUT]: " + PingProcOut);
            } while ((PingProcOut = SocIn.readUTF()) != null);
        } catch (EOFException x) {
        }
        System.out.println("[INFO]: Completed ping process");
        System.out.println("[INFO]: Closing Socket and streams and exitting");
        SocOut.flush();
        SocOut.close();
        SocIn.close();
        Soc.close();
    }
}
