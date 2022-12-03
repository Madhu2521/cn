import java.io.*;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ftpserver{
    public static void main(String[] args) throws Exception {
        
        ServerSocket ss = new ServerSocket(1999);
        Socket s = ss.accept();
        String fname="C:\\Users\\Madhu\\Downloads\\ssourl.txt";
        //FileInputStream fis = new FileInputStream(fname);
        File obj=new File(fname);
        OutputStream os = s.getOutputStream();
        
        
        
        if(obj.exists()!=true){
            System.out.println("nnooooo");
            
            System.exit(0);
        }
        FileInputStream fis = new FileInputStream(fname);
            byte b[]=new byte[1024];
        fis.read(b, 0, b.length);
        
        os.write(b, 0, b.length);
        System.out.println();
        System.out.println("File sent succesfully!");
        }
        
        
        
    }
