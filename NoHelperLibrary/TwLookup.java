/*
 * Twilio: Get information on a phone number
 * https://www.twilio.com/docs/api/lookups
 *
 * Phone number Lookup example URL:
 * https://account_SID:account_auth_token@lookups.twilio.com/v1/PhoneNumbers/15555551234?Type=carrier
 */
package NoHelperLibrary;

import org.json.JSONException;
import org.json.JSONObject;

public class TwLookup {

    private static final String THECLASSNAME = "TwLookup";

    private static final String PARAM_PHONENUMBER = System.getenv("PHONE_NUMBER1");

    public static void main(String[] args) throws Exception {

        twilioRequests doSend = new twilioRequests();
        PrintJson doPrint = new PrintJson();
        String theResponse;

        System.out.println("+++ Start class: " + THECLASSNAME);
        System.out.println("");
        System.out.println("++ Request: Phone Lookup");
        System.out.println("+ Parameters:");
        System.out.println("+ Phone Number: " + PARAM_PHONENUMBER);
        
        System.out.println("--------------------------------------------");
        theResponse = doSend.reqLookupCarrierFree(PARAM_PHONENUMBER);
        System.out.println("+ Response cost: free:");
        doPrint.pretty(theResponse);
        
        System.out.println("--------------------------------------------");
        theResponse = doSend.reqLookupCarrier(PARAM_PHONENUMBER);
        System.out.println("+ Response cost: 1/2 cent:");
        doPrint.pretty(theResponse);
        System.out.println("--------------------------------------------");
        // String theResponse = "{\"caller_name\": null, \"country_code\": \"US\", \"phone_number\": \"+15555551234\", \"national_format\": \"(555) 555-1234\", \"carrier\": {\"mobile_country_code\": null, \"mobile_network_code\": null, \"name\": \"Twilio - Broadvox-CLEC - SMS-Sybase365/MMS-SVR\", \"type\": \"voip\", \"error_code\": null}, \"add_ons\": null, \"url\": \"https://lookups.twilio.com/v1/PhoneNumbers/+15555551234?Type=carrier\"}";
        JSONObject responseJson;
        try {
            responseJson = new JSONObject(theResponse);
        } catch (JSONException e) {
            System.out.println("-- Failed to parse JSON response.");
            return;
        }
        try {
            // "country_code": "US"
            // "national_format": "(555) 555-1234"
            // "type": "voip"
            // "name": "Twilio - Broadvox-CLEC - SMS-Sybase365/MMS-SVR"
            System.out.println("+ country_code:    " + responseJson.getString("country_code"));
            System.out.println("+ national_format: " + responseJson.getString("national_format"));
            System.out.println("+ carrier.type :   " + responseJson.getJSONObject("carrier").getString("type"));
            System.out.println("+ carrier.name :   " + responseJson.getJSONObject("carrier").getString("name"));
        } catch (JSONException e) {
            System.out.println("-- Failed to parse JSON response.");
        }

        System.out.println("");
        System.out.println("+++ Exit class: " + THECLASSNAME);
    }
}
