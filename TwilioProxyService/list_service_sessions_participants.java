package TwilioProxyService;

import com.twilio.Twilio;
import com.twilio.base.ResourceSet;
import com.twilio.rest.proxy.v1.service.Session;
import com.twilio.rest.proxy.v1.service.session.Participant;

public class list_service_sessions_participants {

    private static final String ACCOUNT_SID = System.getenv("ACCOUNT_SID");
    private static final String AUTH_TOKEN = System.getenv("AUTH_TOKEN");

    public static void main(String[] args) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        // https://www.twilio.com/docs/proxy/api/phone-number
        String serviceSid = System.getenv("PROXY_SERVICE_SID");
        ResourceSet<Session> items = Session.reader(serviceSid)
                .read();
        System.out.println("+ List sessions for Proxy service: " + serviceSid);
        for (Session item : items) {
            System.out.println(
                    "++ Sessions SID: " + item.getSid() + ", " + item.getUniqueName()
            );
            ResourceSet<Participant> Participants = Participant.reader(serviceSid, item.getSid())
                    .read();
            for (Participant itemParticipant : Participants) {
                System.out.println(
                        "+++ " + itemParticipant.getSid() + ", " + itemParticipant.getFriendlyName() + ", " + itemParticipant.getProxyIdentifier()
                );
            }
        }

    }
}
