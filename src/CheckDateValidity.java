import java.text.ParseException;
import java.text.SimpleDateFormat;

public class CheckDateValidity {
    /* This method checks if given date valid or not
    * Returns boolean value*/
    public static boolean checkValidity(String date){
        try{
            // Pass valid date format.
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            dateFormat.setLenient(false);
            dateFormat.parse(date);
            return true;
        }catch (ParseException ex){
            return false;
        }
    }
}
