import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class Greetings {
    /* Method for send birthday wishes
    * If birthday wishes are already sent on a specific day
    * we shouldn't send birthday wishes again */

    public static void sendGreetings(ArrayList<Recipient> recipient, Administer admin) throws IOException {
        LocalDate myDateObj = LocalDate.now();
        File file = new File("E:\\OOP Assignment\\birthdayChecker.txt");
        file.createNewFile();
        for (Recipient res:recipient){
            if (res instanceof Wishable){
                String birthdate = ((Wishable) res).getBirthday();
                if (CheckBirthday.isBirthdayToday(birthdate)){
                    try {
                        BufferedReader br = new BufferedReader(new FileReader(file));
                        String line = br.readLine();
                        // At first run of the email client, we save the current date to a text file.
                        // If file already contains the current date we don't send birthday wishes
                        // because we have already sent those mails.
                        if (line == null || !(String.valueOf(myDateObj).equals(line))) {
                            SendEmail email;
                            String username = null;
                            String emailAddress = null;
                            String content = null;
                            String subject = "Birthday Greetings";
                            if (res instanceof PersonalRecipient){
                                emailAddress = res.getMailAddress();
                                username = ((PersonalRecipient) res).getName();
                                content = "Hugs and love on your birthday. <Adeesha Jayasinghe>";
                            }
                            else if ((res instanceof OfficialRecipientFriend)){
                                emailAddress = res.getMailAddress();
                                username = ((OfficialRecipientFriend) res).getName();
                                content = "Wish you a Happy Birthday. <Adeesha Jayasinghe>";
                            }
                            email = new SendEmail(emailAddress, username, subject, content);
                            email.sendMail();
                            admin.storeMails(email);
                        }
                    } catch (IOException e) {
                        System.out.println("An error occurred while reading the birthday checker file!");
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                }
            }
        }
    }
}
