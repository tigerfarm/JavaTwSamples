package authyjavasamples;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class SendMsg1 {
    public static final String ACCOUNT_SID = System.getenv("ACCOUNT_SID");
    public static final String AUTH_TOKEN = System.getenv("AUTH_TOKEN");
    public static void main(String[] args) {
        System.out.print("++ Send SMS message.");
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        String fromPhoneNumber = System.getenv("PHONE_NUMBER1");
        String toPhoneNumber = System.getenv("PHONE_NUMBER2");
        String theMsg = "From Twilio Support, Stacy";
        Message message
                = Message.creator(
                        new PhoneNumber(toPhoneNumber),
                        new PhoneNumber(fromPhoneNumber),
                        theMsg
                )
                        // Echo Status Callback parameters:
                        // .setStatusCallback("https://www.twilio.com/console/runtime/functions/manage/ZH83657ec3e3d10856d22df622e53c0071")
                        .create();
        // Immediately returned values:
        System.out.print("+ from: " + message.getFrom());
        System.out.print(", to: " + toPhoneNumber);
        System.out.print(", Status: " + message.getStatus());
        System.out.print(", " + message.getSid());
        System.out.print(", " + message.getBody());
        System.out.println("");
    }
}
