import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MailBuilder {
    public static void sendMail(List<String> reception) throws Exception {
        System.out.println("Preparing to send emails...");

        //key, value store
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.stmp.port", "587");

        //email login and password
        DataProvider dataProvider = new DataProvider();
        final String myAccountEmail = dataProvider.loginProvider();
        final String myPassword = dataProvider.passwordProvider();

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                //return super.getPasswordAuthentication();
                return new PasswordAuthentication(myAccountEmail, myPassword);
            }
        });

        for(int i=0; i<reception.size(); i++) {
            Message message = prepareMessage(session, myAccountEmail, reception.get(i));
            System.out.println("Sent to: " + reception.get(i));
            Transport.send(message);
        }
    }

    //separate message
    private static Message prepareMessage(Session session, String myAccountEmail, String receptient){
        DataProvider dataProvider = new DataProvider();
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(receptient));
            message.setSubject(dataProvider.titleProvider());
            message.setText(dataProvider.textProvider());
            return message;
        }catch (Exception ex){
            Logger.getLogger(MailBuilder.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
