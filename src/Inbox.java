
public class Inbox {
    private String record;
    public Inbox(String record){
        this.record = record;
    }
    /*This is factory pattern for adding recipients to the client list.
    * It creates specific recipient according to the given recipient details
    * and call to add that recipient */
    public void addRecipient(String log) throws ArrayIndexOutOfBoundsException{
        if (log.startsWith("Official:")){
            OfficialRecipient officialRecipient = new OfficialRecipient(this.record);
            officialRecipient.addRecipient();
        }
        else if(log.startsWith("Official_friend:")){
            OfficialRecipientFriend officeFriend = new OfficialRecipientFriend(this.record);
            officeFriend.addRecipient();
        }
        else {
            PersonalRecipient personalRecipient = new PersonalRecipient(this.record);
            personalRecipient.addRecipient();
        }
    }
}
