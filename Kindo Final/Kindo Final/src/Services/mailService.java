package Services;

import java.util.Properties;
import javax.mail.Session;
import javax.mail.Message;
import javax.mail.Transport;
import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.PasswordAuthentication;
import javax.mail.internet.AddressException;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

public class mailService{

    public mailService() {
    }
    
    
    public static void sendMail(String recepient) throws Exception{
        System.out.println("preparing to send emaill!!!!!!!!!!!");
    Properties properties = new Properties();
    properties.put("mail.smtp.auth", "true");
    properties.put("mail.smtp.host", "smtp.googlemail.com");
    properties.put("mail.smtp.port", "587");
    properties.put("mail.smtp.starttls.enable", "true");
    String myAccountEmail = "kindogarten2020@gmail.com";
    String password = "2020kindo";
    Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password);
            }
            });
        
    
    
  
    Message message = prepareMessage(session,myAccountEmail,recepient);
        Transport.send(message);
        System.out.println("message sent successfully!!!!!!!!!!!");
    }
    private static Message prepareMessage(Session session,String myAccountEmail,String recepient) throws AddressException, MessagingException
    {
    
        
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("newsssss");
            message.setText("Check our new updates now ! ");
            
            return message;
      
   
    }
    
              
}


//import com.mysql.jdbc.interceptors.SessionAssociationInterceptor;
//import java.util.Properties;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javax.mail.Authenticator;
//import javax.mail.Message;
//import javax.mail.MessagingException;
//import javax.mail.PasswordAuthentication;
//import javax.mail.Session;
//import javax.mail.Transport;
//import javax.mail.internet.AddressException;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;
//import javax.mail.Authenticator;
//import javax.mail.Multipart;
//import javax.mail.internet.MimeBodyPart;
//import javax.mail.internet.MimeMultipart;
//        /**
// *
// * @author khaoula
// */
//public class mailService{
//         private String host ;
//         private String  port = "587";
//         
//    public mailService() {
//    }
//         
//
//    public mailService(String mailFrom, String password, String mailTo, String subject, String message) throws Exception
//    {
//       
//            this.host = "smtp.gmail.com";
//       
//
//        sendEmail(host, port, mailFrom, password, mailTo, subject, message, null);
//    }
//
//    private void sendEmail(String host, String port, final String userName, final String password, String toAddress, String subject, String message, String[] attachFiles) throws Exception
//    {
//        Properties properties = new Properties();
//        properties.put("mail.smtp.host", host);
//        properties.put("mail.smtp.port", port);
//        properties.put("mail.smtp.auth", "true");
//        properties.put("mail.smtp.starttls.enable", "true");
//        properties.put("mail.user", userName);
//        properties.put("mail.password", password);
//
//        Authenticator auth = new Authenticator()
//        {
//            public PasswordAuthentication getPasswordAuthentication()
//            {
//                return new PasswordAuthentication(userName, password);
//            }
//        };
//        Session session = Session.getInstance(properties, auth);
//
//        Message msg = new MimeMessage(session);
//
//        msg.setFrom(new InternetAddress(userName));
//        InternetAddress[] toAddresses = {new InternetAddress(toAddress)};
//        msg.setRecipients(Message.RecipientType.TO, toAddresses);
//        msg.setSubject(subject);
//        //msg.setSentDate(new Date());
//        
//        MimeBodyPart messageBodyPart = new MimeBodyPart();
//        messageBodyPart.setContent(message, "text/html");
//
//        Multipart multipart = new MimeMultipart();
//        multipart.addBodyPart(messageBodyPart);
//
//        if (attachFiles != null && attachFiles.length > 0)
//        {
//            for (String filePath : attachFiles)
//            {
//                MimeBodyPart attachPart = new MimeBodyPart();
//
//                try
//                {
//                    attachPart.attachFile(filePath);
//                }
//                finally
//                {
//                    multipart.addBodyPart(attachPart);
//                }
//            }
//        }
//        msg.setContent(multipart);
//
//        Transport.send(msg);
//    }
//    }
//        System.out.println("preparing to send emaill!!!!!!!!!!!");
//    Properties properties = new Properties();
//    properties.put("mail.smtp.auth", "true");
//    properties.put("mail.smtp.host", "smtp.googlemail.com");
//    properties.put("mail.smtp.port", "587");
//    properties.put("mail.smtp.starttls.enable", "true");
//    //properties.put("mail.smtp.ssl.trust", "smtp.gmail.com"); 
//    
//    String myAccountEmail = "maissabelfekih@gmail.com";
//    String password = "qsdf7fdsq";
//    Session session = Session.getInstance(properties, new Authenticator() {
//            @Override
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication(myAccountEmail, password);
//            }
//            });
//        
//    
//    
//  
//    Message message = prepareMessage(session,myAccountEmail,recepient);
//        Transport.send(message);
//        System.out.println("message sent successfully!!!!!!!!!!!");
//    }
//    private static Message prepareMessage(Session session,String myAccountEmail,String recepient) throws AddressException, MessagingException
//    {
//    
//        
//            Message message = new MimeMessage(session);
//            message.setFrom(new InternetAddress(myAccountEmail));
//            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
//            message.setSubject("event jdid hbatt , jawikk behiiiiiiii ");
//            message.setText("This is the new ...");
//            return message;
//      
//   
//    }
    
              
    
