import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;
public class PingProgram {
 
 static void commands(ArrayList<String> commandList) throws Exception
 {
 // creating the sub process, execute system command
 ProcessBuilder build = new ProcessBuilder(commandList);
 Process process = build.start();
 
 // to read the output
 BufferedReader input = new BufferedReader(new 
InputStreamReader(process.getInputStream()));
 BufferedReader Error = new BufferedReader(new 
InputStreamReader(process.getErrorStream()));
 String s = null;
 
 while((s = input.readLine()) != null)
 {
 System.out.println(s);
 }
 System.out.println("Error (if any): ");
 while((s = Error.readLine()) != null)
 {
 System.out.println(s);
 }
 }
 
 public static void sendPingRequest(String ipAddress) throws UnknownHostException, 
IOException
 {
 InetAddress ip = InetAddress.getByName(ipAddress);
 System.out.println("Sending Ping Request to " + ipAddress);
 if (ip.isReachable(5000))
 System.out.println("Host is reachable");
 else
 System.out.println("Sorry ! The host is not reachable");
 }
// Driver code
 public static void main(String[] args) throws UnknownHostException, IOException, Exception
 {
 
 ArrayList<String> commandList = new ArrayList<String>();
 commandList.add("ping");
 
 System.out.println("\n\t\tPing and Trace program\n");
 Scanner obj = new Scanner(System.in);
 System.out.println("Enter the IP Address/Domain Name:");
 String ipAddress = obj.nextLine();
 sendPingRequest(ipAddress);
 commandList.add(ipAddress);
 PingProgram.commands(commandList);
 
 System.out.println("\nTracing route to IP Address/Domain Name " + ipAddress );
 commandList.clear();
 commandList.add("tracert");
 commandList.add(ipAddress);
 
 PingProgram.commands(commandList);
 }
}
