import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class LoadRecipients {
    // To get the number of recipient objects in the application
    public static int recipientCount;

    public static ArrayList<Recipient> load() throws IOException {
        // Read the recipient list and load each record to an array list.
        ArrayList<Recipient> recipients = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader("E:\\OOP Assignment\\clientList.txt"));
        String line;
        while((line = br.readLine()) != null){
            recipientCount += 1;
            Recipient recipient = null;
            if (line.startsWith("Personal:")){
                recipient = new PersonalRecipient(line);
            }
            else if (line.startsWith("Official:")){
                recipient = new OfficialRecipient(line);
            }
            else {
                recipient = new OfficialRecipientFriend(line);
            }
            recipients.add(recipient);
        }
        br.close();
        return recipients;
    }
}
