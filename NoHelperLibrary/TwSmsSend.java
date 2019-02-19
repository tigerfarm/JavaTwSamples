/*
 * Twilio: Send an SMS.
 * Author: Stacy David
 */
package NoHelperLibrary;

import org.json.JSONException;
import org.json.JSONObject;

public class TwSmsSend {

    private static final String PARAM_FROM_PHONENUMBER = System.getenv("PHONE_NUMBER3"); 
    private static final String PARAM_TO_PHONENUMBER = System.getenv("PHONE_NUMBER4");
    private static final String PARAM_MESSAGE = "Test message from Twilio technical support";
    private static final String PARAM_RESPONSE_TYPE = "json";    // <json|xml>

    public static void main(String[] args) throws Exception {

        twilioRequests doSend = new twilioRequests();

        System.out.println("+++ Start.");
        System.out.println("");

        System.out.println("++ Request: Send SMS");
        System.out.println("+ Parameters:");
        System.out.println("+ Phone Number, FROM: " + PARAM_FROM_PHONENUMBER);
        System.out.println("+ Phone Number, TO:   " + PARAM_TO_PHONENUMBER);
        System.out.println("+ Message:            " + PARAM_MESSAGE);
        String theResponse = doSend.reqSmsSend(PARAM_FROM_PHONENUMBER, PARAM_TO_PHONENUMBER, PARAM_MESSAGE, PARAM_RESPONSE_TYPE);
        // System.out.println("+ Response:");
        // PrintJson doPrint = new PrintJson();
        // doPrint.pretty(theResponse);
        printMessageLog(theResponse);

        System.out.println("");
        System.out.println("+++ Exit.");
    }

    private static void printMessageLog(String theResponse) throws Exception {
        JSONObject theJsonResponse;
        try {
            theJsonResponse = new JSONObject(theResponse);
        } catch (JSONException e) {
            System.out.println("-- Failed to parse JSON response.");
            return;
        }
        System.out.println("++ List: from, to, date, status, message");
        System.out.println("+ "
                + theJsonResponse.getString("from")
                + ", " + theJsonResponse.getString("to")
                + ", " + theJsonResponse.getString("date_updated")
                + ", " + theJsonResponse.getString("status")
                + ", " + theJsonResponse.getString("body")
        );
    }
}
