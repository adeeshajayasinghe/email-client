import java.io.IOException;

public abstract class Recipient {
    private String emailId;
    public Recipient(String emailId){
        this.emailId = emailId;
    }
    // These methods should implement the personal, official and official friend recipients.
    public abstract void addRecipient() throws IOException;
    public abstract String getMailAddress();
}
