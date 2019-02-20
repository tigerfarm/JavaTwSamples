//
// For setting my home phone caller id.
//
package authyjavasamples;

import NoHelperLibrary.PrintJson;
import com.twilio.Twilio;
import com.twilio.converter.Promoter;
import com.twilio.rest.lookups.v1.PhoneNumber;

public class lookup {

    private static final String ACCOUNT_SID = System.getenv("ACCOUNT_SID_TF");
    private static final String AUTH_TOKEN = System.getenv("AUTH_TOKEN_TF");

    public static void main(String[] args) {
        
        PrintJson doPrint = new PrintJson();
        
        PhoneNumber phoneNumber;
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        
        String thePhoneNumber = System.getenv("PHONE_NUMBER_4");
        System.out.println("+++ Twilio phone number lookup: " + thePhoneNumber);

        phoneNumber = PhoneNumber.fetcher(
                new com.twilio.type.PhoneNumber(thePhoneNumber))
            .setType(Promoter.listOfOne("carrier")).fetch();
        System.out.println("+ Phone Number Carrier Name information: ");
        doPrint.pretty(phoneNumber.getCarrier().toString());

        phoneNumber = PhoneNumber.fetcher(
                new com.twilio.type.PhoneNumber(thePhoneNumber))
                .setType(Promoter.listOfOne("caller-name")).fetch();
        System.out.println("+ Phone Number CallerName information: ");
        doPrint.pretty(phoneNumber.getCallerName().toString());

        System.out.println("");
    }
}
