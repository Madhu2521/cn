package cnproject;

import java.io.*;
import java.io.InputStream;
import java.net.Socket;
public class ftpclient {
    public static void main(String[] args) throws Exception{
       try{
        Socket s=new Socket("localhost",1999);
        InputStream is = s.getInputStream();
        String name="C:\\Users\\Madhu\\Downloads\\bts.txt";
        FileOutputStream fos = new FileOutputStream(name);
        
        byte b[]=new byte[1024];
        is.read(b, 0, b.length);
        fos.write(b, 0, b.length);
        
        System.out.println("File saved successfully!");
    
    }
       catch(Exception e){System.out.println(e);}
    }
    
}
