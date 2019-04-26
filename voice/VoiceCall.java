package voice;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Call;
import com.twilio.type.PhoneNumber;
import java.net.URI;
import java.net.URISyntaxException;

public class VoiceCall {

    private static final String ACCOUNT_SID = System.getenv("ACCOUNT_SID");
    private static final String AUTH_TOKEN = System.getenv("AUTH_TOKEN");

    public static void main(String[] args) throws URISyntaxException {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        String fromPhoneNumber = System.getenv("PHONE_NUMBER3");
        String toPhoneNumber = System.getenv("PHONE_NUMBER6");
        //  TwiML Bin, Say a poem:
        //   https://handler.twilio.com/twiml/EH83382136170f8b9fa841074a99eab808
        Call call = Call.creator(
                new PhoneNumber(toPhoneNumber),
                new PhoneNumber(fromPhoneNumber),
                new URI("https://handler.twilio.com/twiml/EH83382136170f8b9fa841074a99eab808")
        )
                .setStatusCallback(System.getenv("ECHO_REQUEST_URL"))
                .create();
        
        System.out.print("+ from:" + call.getFrom());
        System.out.print(", to:" + toPhoneNumber);
        System.out.print(", " + call.getSid());
        System.out.print(", " + call.getStatus());
        System.out.println("");
    }
}
