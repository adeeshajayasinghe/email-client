import java.util.ArrayList;

public class EmailInbox {
//    ArrayList<String> records;
//    ArrayList<String> mailIds;
//    ArrayList<String> recipientNames;
//    public EmailInbox(){
//        records = new ArrayList<String>();
//    }
//    public Recipient makeRecipient(String type){
//        Recipient recipient = null;
//        String[] separator = type.split(",");
//
//        if (type.startsWith("Official:")){
//            OfficialRecipient receiver = new OfficialRecipient();
//            records.add(type);
//            mailIds.add(separator[1]);
//            recipientNames.add(separator[0].substring(10)); // Check this!!!!!!!!!!
//            recipient = receiver;
//        }
//        else if(type.startsWith("Official_friend:")){
//            OfficialRecipientFriend receiver = new OfficialRecipientFriend();
//            records.add(type);
//            mailIds.add(separator[1]);
//            recipientNames.add(separator[0].substring(16));
//            recipient = receiver;
//            if (CheckBirthday.isBirthdayToday(separator[3].substring(separator[3].length() - 5))){
//                Greetings greet = new Greetings(separator[1], recipient);
//                greet.sendGreetings();
//            }
//        }
//        else {
//            PersonalRecipient receiver = new PersonalRecipient();
//            records.add(type);
//            mailIds.add(separator[2]);
//            recipientNames.add(separator[0].substring(11));
//            recipient = receiver;
//            if (CheckBirthday.isBirthdayToday(separator[3].substring(separator[3].length() - 5))){
//                Greetings greet = new Greetings(separator[2], recipient);
//                greet.sendGreetings();
//            }
//        }
//        return recipient;
//    }
//    public int getRecipientCount(){
//        return records.size();
//    }
}
