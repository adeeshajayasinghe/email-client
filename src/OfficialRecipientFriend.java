import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class OfficialRecipientFriend extends Recipient implements Wishable{
    private String record;
    private String name;
    private String birthday;
    private String mailAddress;

    public OfficialRecipientFriend(String record){
        super(record);
        this.record = record;
        String[] separator = record.split(",");
        this.name = separator[0].substring(15);
        this.birthday = separator[3];
        this.mailAddress = separator[1];
    }
    public String getName(){
        return this.name;
    }

    @Override
    public String getBirthday() {
        return this.birthday;
    }

    @Override
    public String getMailAddress() {
        return this.mailAddress;
    }

    @Override
    public void addRecipient() {
        try{
            FileWriter fileWriter = new FileWriter("E:\\OOP Assignment\\clientList.txt", true);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            writer.write(this.record + "\n");
            writer.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred while writing the recipient file.");
        }

    }

}
