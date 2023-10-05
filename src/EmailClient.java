import javax.mail.internet.AddressException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class EmailClient {
    public static void main(String[] args) throws IOException {
        while (true){
            LocalDate myDateObj = LocalDate.now();
            Administer administer = new Administer();
            try{
                // We need to get back all the saved client details.
                administer.deSerialize();
                // Send mails to recipients who have birthdays on the current date.
                ArrayList<Recipient> totalRecipient = LoadRecipients.load();
                Greetings.sendGreetings(totalRecipient, administer);
                Scanner scanner = new Scanner(System.in);
                // Create text file to store records.
                System.out.println("Enter option type: \n"
                        + "1 - Adding a new recipient\n"
                        + "2 - Sending an email\n"
                        + "3 - Printing out all the recipients who have birthdays\n"
                        + "4 - Printing out details of all the emails sent\n"
                        + "5 - Printing out the number of recipient objects in the application\n"
                        + "-1 - exit\n"
                        + "===========================================================================");

                int option = Integer.parseInt(scanner.nextLine());
                if (option == -1){
                    // Store the current date to sent birthday wishes once per day.
                    FileWriter myWriter = new FileWriter("E:\\OOP Assignment\\birthdayChecker.txt");
                    myWriter.write(String.valueOf(myDateObj));
                    myWriter.close();
                    break;
                }
                switch(option){
                    case 1:
                        System.out.println("Enter recipient details to add");
                        String record = scanner.nextLine();
                        if (administer.isValidRecord(record)){
                            Inbox contacts = new Inbox(record);
                            // Add a new recipient.
                            contacts.addRecipient(record);
                        }
                        else{
                            System.out.println("Please enter a valid record!");
                        }
                        break;
                    case 2:
                        System.out.println("Enter details");
                        String emailDetails = scanner.nextLine();
                        String[] separator = emailDetails.split(",");
                        if (administer.isValidEmail(separator[0])){
                            SendEmail mail = new SendEmail(separator[0], separator[1], separator[2]);
                            // Send the mail.
                            mail.sendMail();
                            // Store the details of the sent mail.
                            administer.storeMails(mail);
                        }
                        else {
                            System.out.println("Please enter a valid email address!");
                        }

                        break;
                    case 3:
                        System.out.println("Enter the date to clarify who has birthday on the given date(Format yyyy/MM/dd)");
                        String birthdate = scanner.nextLine();
                        CheckBirthday.isBirthday(birthdate);
                        break;
                    case 4:
                        // We need to get back all the saved client details.
                        System.out.println("Enter the date which you want to get details(Format yyyy/MM/dd): ");
                        String inputDate = scanner.nextLine();
                        administer.getEmailInfo(inputDate);
                        break;
                    case 5:
                        // code to print the number of recipient objects in the application
                        System.out.println("Total number of recipients is: " + LoadRecipients.recipientCount);
                        break;
                    default:
                        System.out.println("Enter a valid key!");
                        break;
                }
                FileWriter myWriter = new FileWriter("E:\\OOP Assignment\\birthdayChecker.txt");
                myWriter.write(String.valueOf(myDateObj));
                myWriter.close();

            }catch (IOException ex){
                System.out.println("Creating a file....");
                // Initially we create the recipient list.
                File file = new File("E:\\OOP Assignment\\clientList.txt");
                file.createNewFile();
            }catch (StringIndexOutOfBoundsException | NumberFormatException e) {
                System.out.println("Enter valid input!");
            }catch(ArrayIndexOutOfBoundsException e){
                System.out.println("Please enter all the details of the recipient!");
            }finally {
                // Initialize the recipient count.
                LoadRecipients.recipientCount = 0;
            }
        }
    }
}
