 /*
 * Author: Stacy David
 */
package authyjavasamples.NoHelperLibrary;

import java.io.DataInputStream;

public class AuthyToken {

    private static final String THECLASSNAME = "AuthyToken";

    // -------------------------------------------------------------------------
    private static final String PARAM_AUTHYID = System.getenv("AUTHY_ID");
    // -------------------------------------------------------------------------

    public static void main(String[] args) throws Exception {

        AuthyCalls doCall = new AuthyCalls();
        String theResponse;

        System.out.println("+++ Start class: " + THECLASSNAME);
        System.out.println("");

        // ---------------------------------------------------------------------------
        System.out.println("++ Parameters:");
        System.out.println("+ Authy ID: " + PARAM_AUTHYID);
        theResponse = doCall.reqTokenSend(PARAM_AUTHYID);
        System.out.println("+ Response:");
        PrintJson doPrintJson = new PrintJson();
        doPrintJson.pretty(theResponse);

        // ---------------------------------------------------------------------------
        System.out.println("");
        System.out.println("-------------------------");
        String thePrompt = "++ Enter token (or exit)> ";
        String paramToken = "";
        try (DataInputStream console = new DataInputStream(System.in)) {
            while (paramToken.length() != 7) {
                System.out.print(thePrompt);
                paramToken = console.readLine();
                if (paramToken.length() != 7) {
                    System.out.println("+ Must be 7 digits.");
                } else if (paramToken.equalsIgnoreCase("exit")) {
                    System.out.println("++ Exit.");
                    return;
                }
            }
        }
        System.out.println("++ Parameters:");
        System.out.println("+ Token: " + paramToken);
        theResponse = doCall.reqTokenVerification(PARAM_AUTHYID, paramToken);

        System.out.println("-------------------------------");
        // Print the status from: "token":"is valid",
        // "message":"Token is valid.",
        // "success":"true",
        // "message":"Token is invalid. Token was used recently",
        // "success":false,
        String theFindString = "\"message\":";
        int si = theResponse.indexOf(theFindString);
        int ei = theResponse.indexOf("\"", (si+theFindString.length()+2));
        String theStatus = theResponse.substring((si+theFindString.length()+1), ei);
        System.out.println("+ Token validation message: " + theStatus);

        System.out.println("");
        System.out.println("+++ Exit class: " + THECLASSNAME);
    }
}
