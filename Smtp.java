import java.util.Properties;
import java.util.*;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
public class Mail {
Session newSession = null;
MimeMessage mimeMessage;
public static void main(String args[]) throws AddressException,MessagingException{
System.out.println("\t\tEmail using Google SMTP Server\n");
Scanner sc = new Scanner(System.in);
System.out.println("Enter your Email Address:");
String myemail = sc.nextLine();
System.out.println("Enter your Email Password:");
String mypassword = sc.nextLine();
System.out.println("Enter Recipient's Email Address:");
String toaddress = sc.nextLine();
Mail mail = new Mail(); 
mail.setupServerProperties();
mail.draftEmail(toaddress);
mail.sendEmail(myemail,mypassword);
}
private void sendEmail(String fromUser, String fromUserPassword) throws 
MessagingException {
String emailHost = "smtp.gmail.com";
Transport transport = newSession.getTransport("smtp");
transport.connect(emailHost,fromUser,fromUserPassword);
transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
transport.close();
System.out.println("Email successfully sent");
}
private MimeMessage draftEmail(String recipient) throws MessagingException {
String emailSubject = "Test Mail";
mimeMessage = new MimeMessage(newSession);
mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
mimeMessage.setSubject(emailSubject);
mimeMessage.setText("Hello, This is my first mail using google smtp server\n This is exersice 10 
of CN lab");
return mimeMessage;
}
private void setupServerProperties() {
System.out.println("\nPreparing to send an Email");
Properties properties = System.getProperties();
properties.put("mail.smtp.port", "587");
properties.put("mail.smtp.auth", "true");
properties.put("mail.smtp.starttls.enable", "true");
newSession = Session.getDefaultInstance(properties,null);
}
}
