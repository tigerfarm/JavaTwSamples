package javatwsamples;

import com.twilio.Twilio;
import com.twilio.base.ResourceSet;
import com.twilio.rest.api.v2010.account.IncomingPhoneNumber;

public class phoneNumbersList {

    private static final String ACCOUNT_SID = System.getenv("MAIN_ACCOUNT_SID");
    private static final String AUTH_TOKEN = System.getenv("MAIN_AUTH_TOKEN");

    public static void main(String[] args) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        ResourceSet<IncomingPhoneNumber> incomingPhoneNumbers
                = IncomingPhoneNumber.reader()
                        .limit(20)
                        .read();
        System.out.println("+ Twilio Account phone number list: ");
        //                  ++ PN4fd260f72cde1f16ed96a9580373b171  +15878063883
        System.out.println("++ SID                                 FriendlyName");
        for (IncomingPhoneNumber record : incomingPhoneNumbers) {
            System.out.print("++ " + record.getSid());
            System.out.print("  " + record.getFriendlyName());
            System.out.println("");
        }
    }
}
