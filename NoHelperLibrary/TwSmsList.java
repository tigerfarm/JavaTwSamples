/*
 * Twilio: SMS list messages, to and from, a phone number.
 * Author: Stacy David
 */
package NoHelperLibrary;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TwSmsList {

    private static final String PARAM_PHONENUMBER = System.getenv("PHONE_NUMBER3");
    private static final String PARAM_RESPONSE_TYPE = "json";    // <json|xml>

    public static void main(String[] args) throws Exception {

        twilioRequests doSend = new twilioRequests();
        PrintJson doPrint;
        String theResponse;

        System.out.println("+++ Start class.");
        System.out.println("");

        System.out.println("++ Request: SMS get messages");
        System.out.println("+ Parameters:");
        System.out.println("+ Phone Number :" + PARAM_PHONENUMBER + ":");
        System.out.println("");
        System.out.println("-------------------------------------------------");
        System.out.println("+ Sent From:");
        theResponse = doSend.reqSmsList("From", PARAM_PHONENUMBER, PARAM_RESPONSE_TYPE);
        // doPrint = new PrintJson();
        // doPrint.pretty(theResponse);
        printMessageLog(theResponse);

        System.out.println("");
        System.out.println("-------------------------------------------------");
        System.out.println("+ Sent To:");
        theResponse = doSend.reqSmsList("To", PARAM_PHONENUMBER, PARAM_RESPONSE_TYPE);
        doPrint = new PrintJson();
        doPrint.pretty(theResponse);
        printMessageLog(theResponse);

        System.out.println("");
        System.out.println("+++ Exit class.");
    }

    private static void printMessageLog(String theResponse) throws Exception {
        JSONObject responseJson;
        JSONArray theJsonArray;
        try {
            responseJson = new JSONObject(theResponse);
            theJsonArray = responseJson.getJSONArray("messages");
        } catch (JSONException e) {
            System.out.println("-- Failed to parse JSON response.");
            return;
        }
        System.out.println("++ Message list: date, status, to, from, message");
        try {
            for (int i = 0; i < theJsonArray.length(); i++) {
                theJsonArray.getJSONObject(i).getString("from");
                System.out.println("+ "
                        + theJsonArray.getJSONObject(i).getString("status")
                        + ", " + theJsonArray.getJSONObject(i).getString("date_updated")
                        + ", " + theJsonArray.getJSONObject(i).getString("to")
                        + ", " + theJsonArray.getJSONObject(i).getString("from")
                        + ", " + theJsonArray.getJSONObject(i).getString("body")
                );
            }
        } catch (JSONException e) {
            System.out.println("-- Failed to parse JSON response.");
        }
                /* Sample message
{
   "first_page_uri": "/2010-04-01/Accounts/ACxxxxxxx/Messages.json?From=+15555551111&PageSize=50&Page=0",
   "end": 18,
   "previous_page_uri": null,
   "messages": [
      {
         "sid": "SM46eaxxxxxxb",
         "date_created": "Mon,
         02 Oct 2017 22:26:04 +0000",
         "date_updated": "Mon,
         02 Oct 2017 22:26:04 +0000",
         "date_sent": "Mon,
         02 Oct 2017 22:26:04 +0000",
         "account_sid": "ACxxxx",
         "to": "+15555552222",
         "from": "+15555551111",
         "messaging_service_sid": null,
         "body": "Hello There 9",
         "status": "received",
         "num_segments": "1",
         "num_media": "0",
         "direction": "inbound",
         "api_version": "2010-04-01",
         "price": "-0.00750",
         "price_unit": "USD",
         "error_code": null,
         "error_message": null,
         "uri": "/2010-04-01/Accounts/ACxxxxxxx/Messages/SM46eaxxxxxxb.json",
         "subresource_uris": 
            {
               "media": "/2010-04-01/Accounts/ACxxxxxxx/Messages/SM46eaxxxxxxb/Media.json"
            }
      },
         */

    }

}
