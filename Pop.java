package pop3email;
import java.io.IOException;
import java.util.Properties;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;
public class POP3Email {
public static void displaymail(String host,String storeType,String user,String password)
throws MessagingException, IOException {
//create properties field
Properties prop = new Properties();
prop.put("mail.pop3.host", host);
prop.put("mail.pop3.port", "995");
prop.put("mail.pop3.starttls.enable", "true");
Session emailSession = Session.getDefaultInstance(prop);
//POP3 store object and connection with POP server
Store store = emailSession.getStore("pop3s");
store.connect(host,user,password);
//folder object for open inbox
Folder emailFolder = store.getFolder("INBOX");
emailFolder.open(Folder.READ_ONLY);
//get the messages
Message messages[] = emailFolder.getMessages();
int latestmessage = (messages.length-1);
Message recentMessage = messages[latestmessage];
recentMessage.writeTo(System.out);
//close the store and folder
emailFolder.close(true);
store.close();
}
public static void main(String args[]) throws MessagingException, IOException {
//declaring all the function parameters 
String host = "pop.gmail.com";
String mailStoreType = "pop3";
String username = "dragodark223@gmail.com";
String password = "Darkdrago@46";
displaymail(host,mailStoreType,username,password);
}
}
