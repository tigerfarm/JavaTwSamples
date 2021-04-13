package javatwsamples;

/*
    https://www.twilio.com/docs/usage/security
 */
import com.twilio.Twilio;
import com.twilio.base.ResourceSet;
import com.twilio.rest.monitor.v1.Alert;
import com.twilio.security.RequestValidator;
import java.util.HashMap;
import java.util.Map;

public class SecurityValidation {

    private static final String ACCOUNT_SID = System.getenv("MASTER_ACCOUNT_SID");
    private static final String AUTH_TOKEN = System.getenv("MASTER_AUTH_TOKEN");
    String authToken = System.getenv("MASTER_ACCOUNT_SID");
    // Initialize the validator
    RequestValidator validator = new RequestValidator(authToken);

    public static void main(String[] args) {

        // The Twilio request URL
        String url = "https://mycompany.com/myapp.php?foo=1&bar=2";
        // The post variables in Twilio's request
        Map<String, String> params = new HashMap<>();
        params.put("CallSid", "CA1234567890ABCDE");
        params.put("Caller", "+12349013030");

        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        ResourceSet<Alert> alerts = Alert.reader()
                .limit(20)
                .read();
        for (Alert record : alerts) {
            Alert alert = Alert.fetcher(record.getSid()).fetch();
            System.out.println("+ SID: " + record.getSid()
                    + " " + alert.getErrorCode()
                    + " " + alert.getDateGenerated()
            );
        }
    }
}
