import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class Administer {
    // Make hash map to store mail object according to its send date.
    private HashMap<LocalDate, ArrayList<SendEmail>> storage;
    public Administer(){
        storage = new HashMap<>();
    }

    public void storeMails(SendEmail mail){
        // Get the email sent date.
        LocalDate currentDate = LocalDate.now();
        // If no emails sent on the given date we create an array list for that date.
        // We store contents in hashmap as, key -> given date, value -> [mail objects on given date]
         if (!(storage.containsKey(currentDate))){
             storage.put(currentDate, new ArrayList<>());
         }
         storage.get(currentDate).add(mail);
        // after all the modifications we need to save changes in hash map.
         serialize();
    }
    public void getEmailInfo(String date){
        if (CheckDateValidity.checkValidity(date)){
            // Convert given input format to appropriate format.
            DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            LocalDate localDate = LocalDate.parse(date, myFormatObj);
            // Check whether any emails exist on the given date.
            if (storage.containsKey(localDate)){
                ArrayList<SendEmail> mails = storage.get(localDate);
                for (SendEmail mail : mails){
                    System.out.println("Recipient mail address: " + mail.getMailId() + ", " + "Subject: " + mail.getSubject());
                }
            }
            else{
                System.out.println("No emails sent on the given date");
            }
        }else{
            System.out.println("Please enter a valid date!");
        }

    }


    public void serialize(){
        try{
            FileOutputStream fileStream = new FileOutputStream("E:\\OOP Assignment\\mails.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileStream);
            // Since Hashmap implements the serializable interface.
            // In order to serialize hash map we need to serialize the content in it.
            // Though LocalDate implements serializable by default we need to explicitly serialize email objects.
            out.writeObject(this.storage);
            out.close();
            fileStream.close();
        }
        catch (IOException e) {
            System.out.println("Serialized file not found!");
        }
    }
    // Get the saved objects into the application and store them in the hashmap.
    public void deSerialize() throws IOException {
        try{
            HashMap<LocalDate, ArrayList<SendEmail>> newHashMap = null;
            FileInputStream filesStream = new FileInputStream("E:\\OOP Assignment\\mails.ser");
            ObjectInputStream in = new ObjectInputStream(filesStream);
            storage = (HashMap) in.readObject();
            in.close();;
            filesStream.close();
        }
        catch(IOException | ClassNotFoundException e){
            System.out.println("Creating file....");
        }
    }
    // Check for email address validity.
    public boolean isValidEmail(String email){
        boolean isValid;
        try{
            InternetAddress internetAddress = new InternetAddress(email);
            internetAddress.validate();
            isValid = true;
        }catch (AddressException e){
            isValid = false;
        }
        return isValid;
    }
    // Check if given record contains valid email address.
    public boolean isValidRecord(String record){
        String clientDetails = record.split(":")[1];
        String clientType = record.split(":")[0];
        String emailAddress;
        if (Objects.equals(clientType, "Official")){
            emailAddress = clientDetails.split(",")[1];
        }
        else if (Objects.equals(clientType, "Office_friend")){
            emailAddress = clientDetails.split(",")[1];
        }
        else{
            emailAddress = clientDetails.split(",")[2];
        }
        return isValidEmail(emailAddress);
    }

}
