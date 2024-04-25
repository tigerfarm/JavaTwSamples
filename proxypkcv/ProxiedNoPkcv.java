package proxypkcv;

import com.twilio.Twilio;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class ProxiedNoPkcv {

    public static final String ACCOUNT_SID = System.getenv("MAIN_ACCOUNT_SID");
    public static final String AUTH_TOKEN = System.getenv("MAIN_AUTH_TOKEN");

    public static void main(String[] args) {

        System.out.println("+++ Start.");

        ProxiedNoPkcvTwilioClientCreator clientCreator = new ProxiedNoPkcvTwilioClientCreator(
                ACCOUNT_SID, AUTH_TOKEN, "192.168.1.76", 8080);

        TwilioRestClient twilioRestClient = clientCreator.getClient();
        Twilio.setRestClient(twilioRestClient);

        // To use customer client: twilioRestClient, add into the function call:
        //  .read(twilioRestClient);
        //  .create(twilioRestClient);
        
        /*
        Iterable<Message> messages = Message.reader().read(twilioRestClient);
        for (Message m : messages) {
            System.out.println(m.getBody());
        }
        */

        String theMsg = "¡Hola Dave!\n\nHerr SpideráMan.";   // works
        Message message
                = Message.creator(
                        new PhoneNumber(System.getenv("MY_PHONE_NUMBER")),
                        new PhoneNumber(System.getenv("MASTER_PHONE_NUMBER_1")),
                        theMsg
                ).create(twilioRestClient);
        // Immediately returned values:
        System.out.println("+ Message SID: " + message.getSid());
        System.out.println("+ Status:      " + message.getStatus());
        System.out.println("+ from:        " + message.getFrom());
        System.out.println("+ to:          " + message.getTo());
        System.out.println("+ Message text: " + message.getBody());

        System.out.println("+++ Exit.");
    }
}
