package messaging_service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class send_sms {

    private static final String ACCOUNT_SID = System.getenv("ACCOUNT_SID");
    private static final String AUTH_TOKEN = System.getenv("AUTH_TOKEN");

    public static void main(String[] args) {

        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        
        // The fromPhoneNumber is selected from the Messaging Service pool of numbers.
        // String fromPhoneNumber = "MG507899be5f0b346466b088f148b94104";
        // Or, the fromPhoneNumber can be a Twilio phone number numbers.
        String fromPhoneNumber = System.getenv("PHONE_NUMBER3");
        String toPhoneNumber = System.getenv("MY_PHONE_NUMBER");
        String theMsg = "This is the ship that made the Kessel Run in twelve parsecs?";
        Message message
                = Message.creator(
                        new PhoneNumber(toPhoneNumber),
                        new PhoneNumber(fromPhoneNumber),
                        theMsg
                ).create();
        // Immediately returned values:
        System.out.println("+ Message SID: " + message.getSid());
        System.out.println("+ from:   " + fromPhoneNumber);
        System.out.println("+ to:     " + message.getTo());
        System.out.println("+ Status: " + message.getStatus());
        System.out.println("+ Text:   " + message.getBody());
        System.out.println("");
    }
}
