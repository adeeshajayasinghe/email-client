import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class OfficialRecipient extends Recipient{
    private String record;
    private String mailAddress;
    private String post;
    public OfficialRecipient(String record) throws ArrayIndexOutOfBoundsException{
        super(record);
        this.record = record;
        String[] separator = record.split(",");
        this.mailAddress = separator[1];
        this.post = separator[2];
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

    @Override
    public String getMailAddress() {
        return this.mailAddress;
    }
}
