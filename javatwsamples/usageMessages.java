package javatwsamples;

import com.twilio.Twilio;
import com.twilio.base.ResourceSet;
import com.twilio.rest.api.v2010.account.usage.Record;

public class usageMessages {

    private static final String ACCOUNT_SID = System.getenv("MAIN_ACCOUNT_SID");
    private static final String AUTH_TOKEN = System.getenv("MAIN_AUTH_TOKEN");

    public static void main(String[] args) {
        System.out.println("+++ Start.");
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        ResourceSet<Record> records = Record.reader()
                // Tested and works: CALLS_INBOUND
                // Does not work: CHANNELS_WHATSAPP_OUTBOUND CHANNELS-WHATSAPP-OUTBOUND and other other 3
                .setCategory(Record.Category.CALLS_INBOUND)
                .limit(20)
                .read();
        System.out.println("+++ Exit.");
    }
}
