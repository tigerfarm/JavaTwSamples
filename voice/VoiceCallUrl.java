package voice;



import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Call;
import com.twilio.type.PhoneNumber;
import java.net.URI;
import java.net.URISyntaxException;

public class VoiceCallUrl {

    private static final String ACCOUNT_SID = System.getenv("MASTER_ACCOUNT_SID");
    private static final String AUTH_TOKEN = System.getenv("MASTER_AUTH_TOKEN");

    public static void main(String[] args) throws URISyntaxException {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        String fromPhoneNumber = System.getenv("MASTER_PHONE_NUMBER_1");
        String toPhoneNumber = System.getenv("MASTER_PHONE_NUMBER_2");
        //  TwiML Bin, SayLongMessage:
        //   https://handler.twilio.com/twiml/EHf52349676ed04afb49edd776b8233a0d
        Call call = Call.creator(
                new PhoneNumber(toPhoneNumber),
                new PhoneNumber(fromPhoneNumber),
                new URI("https://handler.twilio.com/twiml/EHf52349676ed04afb49edd776b8233a0d")
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
