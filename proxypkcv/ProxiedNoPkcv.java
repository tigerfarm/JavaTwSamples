package proxypkcv;

import com.twilio.Twilio;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.api.v2010.account.Message;

public class ProxiedNoPkcv {

    public static final String ACCOUNT_SID = System.getenv("MASTER_ACCOUNT_SID");
    public static final String AUTH_TOKEN = System.getenv("MASTER_AUTH_TOKEN");

    public static void main(String[] args) {

        System.out.println("+++ Start.");
        
        ProxiedNoPkcvTwilioClientCreator clientCreator = new ProxiedNoPkcvTwilioClientCreator(
                ACCOUNT_SID, AUTH_TOKEN, "InternetProxy.us.corp", 8080);

        TwilioRestClient twilioRestClient = clientCreator.getClient();
        Twilio.setRestClient(twilioRestClient);

        Iterable<Message> messages = Message.reader().read(twilioRestClient);
        for (Message m : messages) {
            System.out.println(m.getBody());
        }
        
        System.out.println("+++ Exit.");
    }
}
