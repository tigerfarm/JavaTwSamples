package notify;

import com.twilio.Twilio;
import com.twilio.converter.Promoter;
import com.twilio.rest.notify.v1.service.Notification;

public class create_notification_number_doc {

    private static final String ACCOUNT_SID = System.getenv("ACCOUNT_SID");
    private static final String AUTH_TOKEN = System.getenv("AUTH_TOKEN");

    public static void main(String[] args) {

        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Notification notification
                = // Notification.creator("ISXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX")
                Notification
                        .creator(System.getenv("NOTIFY_SERVICE_SID"))
                        // .setBody("Knok-Knok! This is a Notify SMS")
                        .setBody("message 1.1") // .setToBinding(Promoter.listOfOne("{\"binding_type\":\"sms\", \"address\":\"+1651000000000\"}"))
                        .setToBinding(Promoter.listOfOne(
                                "{\"binding_type\":\"sms\", \"address\":\"" + System.getenv("PHONE_NUMBER4") + "\"}"
                        )).create();

        System.out.print("+ Notification SID: " + notification.getSid());
        System.out.print(", Text: " + notification.getBody());
        System.out.print(", Date: " + notification.getDateCreated());
        System.out.println("");
    }
}
