package notify;

import com.twilio.Twilio;
import com.twilio.converter.Promoter;
import com.twilio.rest.notify.v1.service.Notification;
import java.util.ArrayList;
import java.util.List;

public class create_notification_binding1 {

    private static final String ACCOUNT_SID = System.getenv("ACCOUNT_SID");
    private static final String AUTH_TOKEN = System.getenv("AUTH_TOKEN");

    public static void main(String[] args) {

        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        List<String> listSendTo = new ArrayList<>();
        listSendTo.add("bob");
        listSendTo.add("bill");
        Notification notification =
                // Notification.creator("ISXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX")
                Notification.creator("IS3a46cc3e6ca7a1b8bd7aea51c875d33a")
                        .setBody("Hello Bob")
                        // .setIdentity(Promoter.listOfOne("00000001"))
                        .setIdentity(listSendTo
                    ).create();

        // Immediately returned values:
        System.out.print("+ Notification SID: " + notification.getSid());
        System.out.print(", Text: " + notification.getBody());
        System.out.print(", Date: " + notification.getDateCreated());
        System.out.println("");
    }
}
