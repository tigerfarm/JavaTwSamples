//
// For setting my home phone caller id.
//
package zmine;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.sip.Domain;
public class SetPhNum {
    private static final String ACCOUNT_SID = System.getenv("ACCOUNT_SID_TF");
    private static final String AUTH_TOKEN = System.getenv("AUTH_TOKEN_TF");
    public static void main(String[] args) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        System.out.println("+++ Update the SIP Domain Voice URL to set the caller id.");
        Domain domain = Domain.updater(System.getenv("SIP_DOMAIN_SID_TF"))
                .setVoiceUrl("https://" + System.getenv("FUNCTIONS_HOST_TF")
//                       + "/sipoutbound?callerId=" + System.getenv("PHONE_NUMBER_WORK")
                      + "/sipoutbound?callerId=" + System.getenv("PHONE_NUMBER_HOME")
                ).update();
        System.out.println("+ SIP Domain: " + domain.getDomainName());
        System.out.println("+ Updated URL: " + domain.getVoiceUrl());
        System.out.println("");
    }
}
