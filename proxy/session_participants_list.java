package proxy;

import com.twilio.Twilio;
import com.twilio.base.ResourceSet;
import com.twilio.rest.proxy.v1.service.session.Participant;

public class session_participants_list {

    private static final String ACCOUNT_SID = System.getenv("ACCOUNT_SID");
    private static final String AUTH_TOKEN = System.getenv("AUTH_TOKEN");

    public static void main(String[] args) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        String serviceSid = System.getenv("PROXY_SERVICE_SID");
        String sessionSid = System.getenv("PROXY_SESSION_SID");
        System.out.println("+ List sessions for Proxy service: " + serviceSid);
        System.out.println("++ Sessions SID: " + sessionSid);
        ResourceSet<Participant> Participants =
                Participant.reader(serviceSid, sessionSid).read();
        for (Participant itemParticipant : Participants) {
            System.out.println(
                    "+++ " + itemParticipant.getSid() + ", " + itemParticipant.getFriendlyName() + ", " + itemParticipant.getProxyIdentifier()
            );
        }
    }
}
