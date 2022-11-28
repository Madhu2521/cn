iimport java.net.*;
import java.io.*;
public class UDPEchoServer
{
public static void main(String args[]) throws Exception
{
int port = 8000;
DatagramSocket serverDatagramSocket = null;
serverDatagramSocket = new DatagramSocket(port);
System.out.println("Created UDP Echo Server on port "+port);
byte buffer[] = new byte[1024];
DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length);
String input;
while(true)
{
serverDatagramSocket.receive(datagramPacket);
input = new String(datagramPacket.getData(), 0,datagramPacket.getLength());
if( input.equals("bye") ){
serverDatagramSocket.send(datagramPacket);
break;
}
System.out.println("Received from client: "+input);
System.out.println( "Server Echoing " );
serverDatagramSocket.send(datagramPacket);
}
System.out.println( "\nTerminated...... " );
}
}
