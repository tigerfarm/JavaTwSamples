package javatwsamples;

import com.twilio.Twilio;
import com.twilio.rest.serverless.v1.service.Function;

public class functionsFetch {

    private static final String ACCOUNT_SID = System.getenv("MASTER_ACCOUNT_SID");
    private static final String AUTH_TOKEN = System.getenv("MASTER_AUTH_TOKEN");

    public static void main(String[] args) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Function function = Function.fetcher(
                "ZSb2201277346da03f6ca1ea804ce3aaba",
                "ZH1100b8ce0150592646eaf625cee01e5a")
                .fetch();
        System.out.println("+ Friendly name: " + function.getFriendlyName());
        Function.deleter(
                "ZSb2201277346da03f6ca1ea804ce3aaba",
                "ZH1100b8ce0150592646eaf625cee01e5a")
                .delete();
        System.out.println("+ Deleted.");
    }
}
