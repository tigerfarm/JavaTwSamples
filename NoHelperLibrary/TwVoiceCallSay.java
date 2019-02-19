/*
 * Twilio: Make a voice call.
 * https://www.twilio.com/docs/api/rest/making-calls
 * Author: Stacy David
 */
package NoHelperLibrary;

public class TwVoiceCallSay {

    private static final String PARAM_FROM_PHONENUMBER = System.getenv("PHONE_NUMBER3");
    private static final String PARAM_TO_PHONENUMBER = System.getenv("PHONE_NUMBER4");
    private static final String PARAM_URL = System.getenv("ECHO_REQUEST_URL");

    public static void main(String[] args) throws Exception {

        twilioRequests doSend = new twilioRequests();

        System.out.println("+++ Start class.");
        System.out.println("");

        System.out.println("++ Request: Phone Lookup");
        System.out.println("+ Parameters:");
        System.out.println("+ Phone Number, FROM: " + PARAM_FROM_PHONENUMBER);
        System.out.println("+ Phone Number, TO:   " + PARAM_TO_PHONENUMBER);
        System.out.println("+ URL: " + PARAM_URL);
        String theResponse = doSend.reqCallHttpClient(PARAM_FROM_PHONENUMBER, PARAM_TO_PHONENUMBER, PARAM_URL);
        System.out.println("+ Response:");
        PrintJson doPrint = new PrintJson();
        doPrint.pretty(theResponse);

        System.out.println("");
        System.out.println("+++ Exit class.");
    }
}
