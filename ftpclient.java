/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cnproject;

import java.io.*;
import java.net.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;;
public class ftpc {
Socket soc;
DataInputStream din;
DataOutputStream dout;
String fileName;

public ftpc() throws Exception{
soc = new Socket("127.0.0.1",6060);
System.out.println("[INFO] : Socket connected at "+soc.getInetAddress());
din = new DataInputStream(soc.getInputStream());
dout = new DataOutputStream(soc.getOutputStream());
}

public void getFile() throws Exception{
    
String fileIn;
Scanner in = new Scanner(System.in);
System.out.print("[SYSTEM] > Enter File name to download: ");
fileName = in.nextLine();
dout.writeUTF(fileName);
Path file = Paths.get(fileName);
fileName = file.getFileName().toString();

if(din.readUTF().equals("1")) {
    System.out.println("[INFO] : The Requested file is available at "+soc.getInetAddress());
    File fileObj = new File(fileName);
    //if(fileObj.exists()){
        System.out.println("[INFO] : The Requested file already existing in current folder");
        System.out.print("[SYSTEM] > Do you want to replace the file[Y/N] :");
        String ch = in.nextLine();
        if(ch.equalsIgnoreCase("n")){
            System.out.print("[SYSTEM] > Do you want to rename the recieved file[Y/N] :");
            ch = in.nextLine();
            if(ch.equalsIgnoreCase("n")){
                System.out.println("[INFO] : File already exists. No further operations done");
                in.close();
                return;
            }
            System.out.print("[SYSTEM] > Enter new file name :");
            fileName = in.nextLine();
        }
    //}
    System.out.println("[INFO] : File transfer starting from "+soc.getInetAddress());
    FileOutputStream fout = new FileOutputStream(fileName);
    while(!( fileIn = din.readUTF()).equals("-1")) 
        fout.write(Integer.parseInt(fileIn));
    System.out.println("[INFO] : File transfer completed from "+soc.getInetAddress());
    fout.close();
}
else{
System.out.println("[ERROR] : The requested file was not found on the server");
System.out.println("[ERROR] : Check file name or try again");
}
in.close();
}

public static void main(String[] args) throws Exception{
ftpc instance = new ftpc();
instance.getFile();
instance.soc.close();
System.out.println("[INFO] : Socket closed");
}
}
