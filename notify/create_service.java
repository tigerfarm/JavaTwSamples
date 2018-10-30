package notify;

import com.twilio.Twilio;
import com.twilio.rest.notify.v1.Service;

public class create_service {

    private static final String ACCOUNT_SID = System.getenv("ACCOUNT_SID");
    private static final String AUTH_TOKEN = System.getenv("AUTH_TOKEN");

    public static void main(String[] args) {

        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Service service = Service.creator()
                // .setMessagingServiceSid("MGXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX")
                .setMessagingServiceSid("MG507899be5f0b346466b088f148b94104")
                .setFriendlyName("My Notify Service")
                .create();

        // Immediately returned values:
        System.out.print("+ Notify Service SID: " + service.getSid());
        System.out.print(", Messaging Service SID: " + service.getMessagingServiceSid());
        System.out.print(", Service SID: " + service.getFriendlyName());
        System.out.println("");
    }
}
