import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;  // Import the LocalDateTime class
import java.time.format.DateTimeFormatter;  // Import the DateTimeFormatter class
import java.util.Objects;
import java.util.Scanner;

public class CheckBirthday {


    /* This method for check given birthdate is today
    * If birthdate matches with today return boolean value as true*/
    public static boolean isBirthdayToday(String date) {
        LocalDate myDateObj = LocalDate.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String formattedDate = myDateObj.format(myFormatObj);
        return Objects.equals(date.substring(date.length() - 5), formattedDate.substring(formattedDate.length() - 5));
    }
    /* This method for printing all the recipients who have birthdays
    *  Method accepts a date and checks any birthdays exist in recipients list */
    public static void isBirthday(String date){
        if (CheckDateValidity.checkValidity(date)){
            try{
                boolean noMailsToday = true;
                String line;
                File file = new File("E:\\OOP Assignment\\clientList.txt");
                Scanner scan = new Scanner(file);
                // If recipient list is empty.
                if (!scan.hasNextLine()){
                    System.out.println("No entries in the recipient list!");
                    noMailsToday = false;
                }else{
                    while(scan.hasNextLine()){
                        line = scan.nextLine();
                        if (date.substring(date.length() - 5).equals(line.substring(line.length() - 5))){
                            if (line.startsWith("Office_friend:")){
                                String newRecord = line.substring(15);
                                String[] rec = newRecord.split(",");
                                System.out.println(rec[0]);
                            }
                            else {
                                String newRecord = line.substring(10);
                                String[] rec = newRecord.split(",");
                                System.out.println(rec[0]);
                            }
                            noMailsToday = false;
                        }
                    }
                }
                if(noMailsToday){
                    System.out.println("No mails on the given date!");
                }
            }catch(IOException e){
                System.out.println("Recipient file not found!");
            }
        }else{
            System.out.println("Please enter a valid date!");
        }

    }
}
