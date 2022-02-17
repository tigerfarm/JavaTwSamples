package TwilioProxyService;

import com.twilio.Twilio;
import com.twilio.exception.ApiException;
import com.twilio.rest.proxy.v1.service.Session;

public class session_create {

    private static final String ACCOUNT_SID = System.getenv("ACCOUNT_SID");
    private static final String AUTH_TOKEN = System.getenv("AUTH_TOKEN");

    public static void main(String[] args) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        // https://www.twilio.com/docs/proxy/api/session
        try {
            Session session = Session.creator(System.getenv("PROXY_SERVICE_SID"))
                    .setUniqueName("MyFirstSession").create();
            System.out.println("+ New Proxy service session SID: " + session.getSid());
        } catch (ApiException e) {
            System.out.println("- Error Code: " + e.getCode() + ": " + e.getMessage());
            if ( e.getCode() == 80603) {
                System.out.println("- Session name already exists.");
            }
        }
    }
}
