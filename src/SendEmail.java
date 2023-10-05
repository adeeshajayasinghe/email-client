
import java.io.Serializable;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
// To save mail objects in the hard disk we make this class serializable.
public class SendEmail implements Serializable {
    private String mailId;
    private String username;
    private String subject;
    private String content;
    private transient boolean isGreeting;
    // This constructor for normal mails.
    public SendEmail(String mailId, String subject, String content){
        this.mailId = mailId;
        this.subject = subject;
        this.content = content;
        this.isGreeting = false;
    }
    // This constructor for Birthday wishes.
    public SendEmail(String mailId, String username, String subject, String content){
        this.mailId = mailId;
        this.username = username;
        this.subject = subject;
        this.content = content;
        this.isGreeting = true;
    }
    public String getSubject(){
        return subject;
    }
    public String getMailId(){
        return mailId;
    }
    public void sendMail(){
        final String username = properties.getProperty("username");
        final String password = properties.getProperty("password");
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(properties.getProperty("username")));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(mailId)
            );
            message.setSubject(subject);
            message.setText(content);

            Transport.send(message);
            // Check type of the mail - A birthday wish or a normal mail.
            if (isGreeting){
                System.out.println("Birthday wish successfully sent to " + this.username);
            }
            else{
                System.out.println("Email sent successfully.");
            }
        // We also need to check whether device is connected to the internet or not.
        } catch (MessagingException e) {
            System.out.println("Please connect your device to the internet!");
        }
    }

}

