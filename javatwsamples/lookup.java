package javatwsamples;
/*
    https://www.twilio.com/docs/lookup/api
 */
import com.twilio.Twilio;
import com.twilio.rest.lookups.v1.PhoneNumber;
import java.util.Arrays;
public class lookup {

    private static final String ACCOUNT_SID = System.getenv("MASTER_ACCOUNT_SID");
    private static final String AUTH_TOKEN = System.getenv("MASTER_AUTH_TOKEN");

    public static void main(String[] args) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        PhoneNumber phoneNumber = PhoneNumber.fetcher(
                new com.twilio.type.PhoneNumber("+15108675310"))
                .setType(Arrays.asList("caller-name")).fetch();

        System.out.println(phoneNumber.getCountryCode());

    }
}
