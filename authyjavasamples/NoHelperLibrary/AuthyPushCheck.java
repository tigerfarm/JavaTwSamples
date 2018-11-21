 /*
 * Author: Stacy David
 */
package authyjavasamples.NoHelperLibrary;

public class AuthyPushCheck {

    private static final String THECLASSNAME = "AuthyPushCheck";

    // -------------------------------------------------------------------------
    private static final String PARAM_UUID = "9dd87d80-77e4-0135-8a68-1226b57fac04";
    // -------------------------------------------------------------------------

    public static void main(String[] args) throws Exception {

        AuthyCalls doSend = new AuthyCalls();
        String theResponse;

        System.out.println("+++ Start class: " + THECLASSNAME);
        System.out.println("");
        System.out.println("++ Parameters:");
        System.out.println("+ UUID: " + PARAM_UUID);
        theResponse = doSend.reqPushCheck(PARAM_UUID);
        System.out.println("+ JSON Response:");
        PrintJson doPrintJson = new PrintJson();
        doPrintJson.pretty(theResponse);

        System.out.println("-------------------------------");
        // Print the status from: "status":"approved",
        String theFindString = "\"status\":";
        int si = theResponse.indexOf(theFindString);
        int ei = theResponse.indexOf("\"", (si+theFindString.length()+2));
        String theStatus = theResponse.substring((si+theFindString.length()+1), ei);
        System.out.println("+ Authentication Status: " + theStatus);

        System.out.println("");
        System.out.println("+++ Exit class: " + THECLASSNAME);
    }
}
