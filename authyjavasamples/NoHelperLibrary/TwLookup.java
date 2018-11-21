/*
 * Twilio: Get information on a phone number
 * https://www.twilio.com/docs/api/lookups
 */
package authyjavasamples.NoHelperLibrary;

public class TwLookup {

    private static final String PARAM_PHONENUMBER = System.getenv("AUTHY_PHONE_COUNTRYCODE") + System.getenv("AUTHY_PHONE_NUMBER1");

    public static void main(String[] args) throws Exception {

        TwCalls doSend = new TwCalls();

        System.out.println("+++ Start.");
        System.out.println("");

        System.out.println("++ Request: Phone Lookup");
        System.out.println("+ Parameters:");
        System.out.println("+ Phone Number: " + PARAM_PHONENUMBER);
        String theResponse = doSend.reqLookupCarrier(PARAM_PHONENUMBER);
        System.out.println("+ Response:");
        PrintJson doPrint = new PrintJson();
        doPrint.pretty(theResponse);

        System.out.println("");
        System.out.println("+++ Exit.");
    }
}
