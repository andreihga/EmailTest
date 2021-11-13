import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import static javax.mail.internet.InternetAddress.*;

public class Config {

    public static void main(String[] args) {
        Properties prop = new Properties();
        prop.put("mail.smtp.auth", true);
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", "smtp.mailtrap.io");
        prop.put("mail.smtp.port", "2525");
        prop.put("mail.smtp.ssl.trust", "smtp.mailtrap.com");

        Session session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("500972059439cc", "48107c52ce8801");
            }
        });


        Message message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress("andrei@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, parse("hogik00@gmail.com"));
            message.setSubject("Test Subject");
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        String msg = "Test message";

        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        Multipart multipart = new MimeMultipart();
        try {
            mimeBodyPart.setContent(msg, "text/html");
            multipart.addBodyPart(mimeBodyPart);
            message.setContent(multipart);
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }

}
