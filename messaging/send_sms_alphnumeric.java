package messaging;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class send_sms_alphnumeric {

    private static final String ACCOUNT_SID = System.getenv("ACCOUNT_SID");
    private static final String AUTH_TOKEN = System.getenv("AUTH_TOKEN");

    public static void main(String[] args) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        String fromPhoneNumber = "alph";        // Alphanumeric sender id
        String toPhoneNumber = System.getenv("PHONE_NUMBER_UK");
        String theMsg = "This is the ship that made the Kessel Run in fourteen parsecs?";
        Message message
                = Message.creator(
                        new PhoneNumber(toPhoneNumber),
                        new PhoneNumber(fromPhoneNumber),
                        theMsg
                ).create();
        // Immediately returned values:
        System.out.println("+ Message SID: " + message.getSid());
        System.out.println("+ Status:      " + message.getStatus());
        System.out.println("+ from:        " + message.getFrom());
        System.out.println("+ to:          " + message.getTo());
        System.out.println("+ Message text: " + message.getBody());
    }
}

