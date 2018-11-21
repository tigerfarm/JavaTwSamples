/*
 * Twilio: Send an SMS.
 * Author: Stacy David
 */
package authyjavasamples.NoHelperLibrary;

public class TwSendSmsMsg {

    private static final String THECLASSNAME = "TwSendSmsMsg";

    private static final String PARAM_FROM_PHONENUMBER = System.getenv("PHONE_NUMBER1");
    private static final String PARAM_TO_PHONENUMBER = System.getenv("PHONE_NUMBER2");
    private static final String PARAM_MESSAGE = "Hello from " + THECLASSNAME;
    private static final String PARAM_RESPONSE_TYPE = "xml";    // <json|xml>

    public static void main(String[] args) throws Exception {

        TwCalls doSend = new TwCalls();

        System.out.println("+++ Start class: " + THECLASSNAME);
        System.out.println("");

        System.out.println("++ Request: Send SMS");
        System.out.println("+ Parameters:");
        System.out.println("+ Phone Number, FROM: " + PARAM_FROM_PHONENUMBER);
        System.out.println("+ Phone Number, TO:   " + PARAM_TO_PHONENUMBER);
        System.out.println("+ Message:            " + PARAM_MESSAGE);
        String theResponse = doSend.reqSendSmsMsg(PARAM_FROM_PHONENUMBER, PARAM_TO_PHONENUMBER, PARAM_MESSAGE, PARAM_RESPONSE_TYPE);
        System.out.println("+ Response:");
        PrintXml doPrint = new PrintXml();
        doPrint.pretty(theResponse);

        System.out.println("");
        System.out.println("+++ Exit class: " + THECLASSNAME);
    }
}
