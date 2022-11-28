import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;
import javax.activation.*;
public class SimpleMailTransferProtocol
{public static void main(String[] args){
Scanner in = new Scanner(System.in);
String SenderUser = "ap.monishkumar";
String SenderMail = "ap.monishkumar@gmail.com";
System.out.print("Enter Password for Authentication:");
String SenderPassword = in.nextLine();
System.out.print("Enter Destination Address:");
String ToMail = in.nextLine();
String ToHost = "smtp.gmail.com";
Properties SessionProperties = new Properties();
SessionProperties.put("mail.smtp.auth","true");

SessionProperties.put("mail.smtp.starttls.enable","true");
SessionProperties.put("mail.smtp.host",ToHost);
SessionProperties.put("mail.smtp.port",587);
Session CurrentSession = Session.getInstance(SessionProperties, new javax.mail.Authenticator(){
protected PasswordAuthentication
getPasswordAuthentication(){ return new
PasswordAuthentication(SenderMail, SenderPassword);
}
});
try{
Message ThisMessage = new MimeMessage(CurrentSession);
ThisMessage.setFrom(new InternetAddress(SenderMail));
ThisMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(ToMail));
System.out.print("Enter Subject for mail:");
String Subject = in.nextLine();
System.out.println("Enter Body of the mail:");
String Body = in.nextLine();
System.out.print("Do you want to add attachment?(y/n):");
String c = in.nextLine();
if(c.equalsIgnoreCase("y")){ System.out.print("E
nter FileName to Attach:");String FileName =
in.nextLine();
DataSource FileSource = new FileDataSource(FileName);
MimeBodyPart PartOne = new MimeBodyPart();
PartOne.setText(Body);
MimeBodyPart PartTwo = new MimeBodyPart();
PartTwo.setDataHandler(new DataHandler(FileSource));
PartTwo.setFileName(FileName);
Multipart MessageBody = new MimeMultipart();
MessageBody.addBodyPart(PartOne);
MessageBody.addBodyPart(PartTwo);
ThisMessage.setContent(MessageBody);
}
else ThisMessage.setContent(Body,"text/html");
ThisMessage.setSubject(Subject);
Transport.send(ThisMessage);
System.out.println("The Message was sent successfully...");
}
catch(Exception
e){ e.printStackTra
ce();
}
in.close();
}
}
