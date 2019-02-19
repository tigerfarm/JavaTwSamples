/*
 * Author: Stacy David
 * Program uses the Turing machine approach to parsing by:
 *    moving a pointer through the characters and looking at a number.
 */
package NoHelperLibrary;

public class PrintJson {

    public void pretty(String jsonData) {
        int si;
        int ei;
        int startBracket;
        int endBracket;
        int aComma;
        int aBlank;
        String tabString = "   ";
        int tabStringLength = tabString.length();
        String indentString = "";

        // System.out.println("++ Print: " + jsonData); 
        si = jsonData.indexOf("{", 0);
        ei = jsonData.indexOf("}", 0);
        if (si < 0 || ei < 0) {
            System.out.println("-- Error: response is Not JSON data.");
            System.out.println("+ Response text: " + jsonData + "...");
            return;
        }
        int jsonDataLength = jsonData.length();
        while (si < jsonDataLength) {
            // {"caller_name": null, "country_code": "US", "phone_number": "+15555551234", "national_format": "(555) 555-1234", "carrier": {"mobile_country_code": null, "mobile_network_code": null, "name": "Twilio", "type": "voip", "error_code": null}, "add_ons": null, "url": "https://lookups.twilio.com/v1/PhoneNumbers/+15555551234?Type=carrier"}
            startBracket = jsonData.indexOf("{", si);
            endBracket = jsonData.indexOf("}", si);
            aComma = jsonData.indexOf(",", si);
            aBlank = jsonData.indexOf(" ", si);
            // System.out.println(indentString + "si="+si+", startBracket="+startBracket+", endBracket="+endBracket+", aComma="+aComma); 
            if (si == aBlank) {
                // Case: first char is a blank " ".
                // Skips by.
                si = si + 1;
            } else if (si == aComma) {
                // Case: }, "add_ons": null,
                // Skips by.
                si = si + 1;
            } else if (si == startBracket) {
                // Case: {"caller_name": null, "country_code": "US"}
                // Prints: {
                System.out.println(indentString + "{");
                indentString = indentString + tabString;
                si = si + 1;
            } else if (si == endBracket) {
                // Need a case to handle: "current_price": "0.090"}, {"prefixes": ["1808"],
                // Case: }, "add_ons": null,
                // Prints: },
                // Case: }
                // Prints: }
                // Stacy: Need a case to handle: {\"sub\": {\"media\": \"ab.json\"}} --- last printed :"sub": {"media": "ab.json":
                indentString = indentString.substring(0, indentString.length() - tabStringLength);
                if (aComma == si + 1) {
                    System.out.println(indentString + "},");
                    si = aComma + 1;
                } else {
                    System.out.println(indentString + "}");
                    si = endBracket + 1;
                }
                if (indentString.length() > tabStringLength) {
                    indentString = indentString.substring(0, indentString.length() - tabStringLength);
                }
            } else if ((startBracket > 0) && (startBracket < endBracket) && (startBracket < aComma)) {
                // Case: "carrier": {"mobile_country_code": null
                // si=112, startBracket=124, endBracket=181, aComma=152
                // Prints: "carrier":
                System.out.println(indentString + jsonData.substring(si, startBracket));
                si = startBracket;
                indentString = indentString + tabString;
            } else if ((aComma < 0) || (endBracket < aComma)) {
                // Case: "country_code": "US"}
                // Case: "country_code": "US"}, "add_ons": null,
                // Prints: "country_code": "US"
                System.out.println(indentString + jsonData.substring(si, endBracket));
                si = endBracket;
            } else if (endBracket > aComma) {
                // Case: "caller_name": null, "country_code": "US"}
                // Prints: "caller_name": null,
                System.out.println(indentString + jsonData.substring(si, aComma) + ",");
                si = aComma + 1;
            } else {
                System.out.println(indentString + "- error, case not accounted for.");
            }
        }
    }

    public static void main(String[] args) throws Exception {

        final String THECLASSNAME = "PrintJson";

        System.out.println("+++ Start class: " + THECLASSNAME);
        System.out.println("");

        String jsonRequest;
        jsonRequest = "{\"authy_id\":12345678,\"device_uuid\":54737394,\"callback_action\":\"approval_request_status\",\"uuid\":\"a181d690-7adb-0135-0b5f-0a980ce4a6d4\",\"status\":\"approved\",\"approval_request\":{\"transaction\":{\"details\":null,\"device_details\":{},\"device_geolocation\":\"\",\"device_signing_time\":1505325758,\"encrypted\":false,\"flagged\":false,\"hidden_details\":null,\"message\":\"Tiger Farm Press approval requested.\",\"reason\":\"\",\"requester_details\":null,\"status\":\"approved\",\"uuid\":\"a181d690-7adb-0135-0b5f-0a980ce4a6d4\",\"created_at_time\":1505325749,\"customer_uuid\":123123},\"expiration_timestamp\":1505412149,\"logos\":null,\"app_id\":\"59xxxxxxc\"},\"signature\":\"xxxxxx==\",\"device\":{\"city\":\"Hollywood\",\"country\":\"United States\",\"ip\":\"123.12.12.195\",\"region\":\"California\",\"registration_city\":\"Hollywood\",\"registration_country\":\"United States\",\"registration_ip\":\"107.210.221.195\",\"registration_method\":\"sms\",\"registration_region\":\"California\",\"os_type\":\"android\",\"last_account_recovery_at\":1498678607,\"id\":54737394,\"registration_date\":1504999725,\"last_sync_date\":1505325642}}";
        // jsonRequest = "{\"sub\": {\"media\": \"ab.json\"}}";
        PrintJson doPrintJson = new PrintJson();
        doPrintJson.pretty(jsonRequest);

        System.out.println("");
        System.out.println("+++ Exit class: " + THECLASSNAME);
    }

}
