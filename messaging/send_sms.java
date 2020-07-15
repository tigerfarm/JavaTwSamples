package messaging;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class send_sms {

    private static final String ACCOUNT_SID = System.getenv("MASTER_ACCOUNT_SID");
    private static final String AUTH_TOKEN = System.getenv("MASTER_AUTH_TOKEN");

    public static void main(String[] args) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        String fromPhoneNumber = System.getenv("MASTER_PHONE_NUMBER_1");
        String toPhoneNumber = System.getenv("MY_PHONE_NUMBER");
        // String theMsg = "This is the ship that made the ¡Kessel Run in \nfourteen parsecs?";
        String theMsg = "¡Hola Dave!\n\nHerr Spider\u00e1csMan.";
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

