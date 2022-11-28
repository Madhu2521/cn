import java.net.*;
import java.io.*;
public class UDPEchoClient
{
public static void main(String[] args) throws Exception
{
InetAddress address = null;
int port = 8000;
DatagramSocket datagramSocket = null;
BufferedReader keyboardReader = null;
address = InetAddress.getLocalHost();
datagramSocket = new DatagramSocket();
keyboardReader = new BufferedReader(new InputStreamReader(System.in));
System.out.println("Ready to send your messages...(Press \'bye\' to end)");
String input,msg;
byte b1[]= new byte[1024];
while (true)
{
System.out.print( "Client : " );
input = keyboardReader.readLine();
DatagramPacket datagramPacket = new DatagramPacket (input.getBytes(), input.length(), address, port);
datagramSocket.send(datagramPacket);
DatagramPacket recievepacket = new DatagramPacket(b1, b1.length) ;
datagramSocket.receive(recievepacket);
msg= new String(recievepacket.getData(),0,recievepacket.getLength());
System.out.println( "Server : " + msg );
if(msg.equals("bye"))break;
}
}
}
