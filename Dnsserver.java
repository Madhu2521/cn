import java.io.*; 
import java.net.*; 
import java.util.*; 

class DNSServer
{
    public static void main(String args[])throws Exception{
        DatagramSocket server=new DatagramSocket(1309); 
        while(true){ 
            int flag=0;
            byte[]sendbyte=new byte[1024]; 
            byte[]receivebyte=new byte[1024]; 
            DatagramPacket receiver=new DatagramPacket(receivebyte,receivebyte.length); 
            server.receive(receiver);
            String str=new String(receiver.getData()); 
            String s=str.trim();      
            InetAddress addr=receiver.getAddress(); 
            int port=receiver.getPort();
            String ip[]={"192.165.1.1","192.165.1.5","192.168.1.1","192.165.1.6","192.165.1.2"};
            String name[]={"www.google.com","www.amazon.in","localhost","www.gmail.com","www.youtube.com"}; 
            
            
            /*sendbyte = InetAddress.getByName(s).toString().getBytes();
            DatagramPacket sdp = new DatagramPacket(sendbyte, sendbyte.length, addr, port);
            ds.send(sdp); */
            for(int i=0;i<ip.length;i++)
            {
                if(s.equals(ip[i]))
                {
                    flag=1;
                    sendbyte=name[i].getBytes();
                    DatagramPacket sender=new DatagramPacket(sendbyte,sendbyte.length,addr, port);
                    server.send(sender); 
                    break;
                }
                else if(s.equals(name[i]))
                {
                    flag=1;
                    sendbyte=ip[i].getBytes();
                    DatagramPacket sender=new DatagramPacket(sendbyte,sendbyte.length,addr, port);
                    server.send(sender); 
                    break;
                }
            }
            if(flag==0){
                String send="The specified IP or Domain Name is not logged in the DNS Server";
                sendbyte=send.getBytes();
                DatagramPacket sender=new DatagramPacket(sendbyte,sendbyte.length,addr, port);
                server.send(sender); 
            }
            break;
        }
        server.close();
    }
}
