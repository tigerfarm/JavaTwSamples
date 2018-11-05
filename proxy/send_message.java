package proxy;

import com.twilio.Twilio;
import com.twilio.rest.proxy.v1.service.session.participant.MessageInteraction;

public class send_message {

    private static final String ACCOUNT_SID = System.getenv("ACCOUNT_SID");
    private static final String AUTH_TOKEN = System.getenv("AUTH_TOKEN");

    public static void main(String[] args) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        MessageInteraction messageInteraction = MessageInteraction.creator(
                System.getenv("PROXY_SERVICE_SID"),
                "KC5a322db63efc5c01c42a90b91d2bac9c",
                "KPf371f33997341c7e12130ab80eed5407",
                "Reply to this message to chat!")
            .create();

        System.out.println("+ Proxy service session, message interaction SID: " + messageInteraction.getSid());
    }
}
