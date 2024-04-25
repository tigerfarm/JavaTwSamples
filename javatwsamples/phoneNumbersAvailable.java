package javatwsamples;

import com.twilio.Twilio;
import com.twilio.base.ResourceSet;
import com.twilio.rest.api.v2010.account.availablephonenumbercountry.Local;
// For buying phone numbers:
// import com.twilio.rest.api.v2010.account.IncomingPhoneNumber;

public class phoneNumbersAvailable {

    private static final String ACCOUNT_SID = System.getenv("MAIN_ACCOUNT_SID");
    private static final String AUTH_TOKEN = System.getenv("MAIN_AUTH_TOKEN");

    public static void main(String[] args) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        ResourceSet<Local> phoneNumbers = Local.reader("US")
                .setAreaCode(213).limit(20).read();

        System.out.println("+ Twilio available phone numbers: ");
        //                  ++ +15109482272  (510) 948-2272  CA
        System.out.println("++ PhoneNumber   FriendlyName    Region");
        for (Local record : phoneNumbers) {
            System.out.print("++ " + record.getPhoneNumber());
            System.out.print("  " + record.getFriendlyName());
            System.out.print("  " + record.getRegion());
            System.out.println("");
            //
            // Add your create here using the above phone number.
            /*
            String phoneNumberToPurchase =  String.valueOf(record.getPhoneNumber());
            IncomingPhoneNumber incomingPhoneNumber = IncomingPhoneNumber.creator(
                new com.twilio.type.PhoneNumber(phoneNumberToPurchase))
            .create();
             */
        }
        System.out.println("+ End of list.");
    }
}
