/*
 * Twilio service: Get a 2FA user's status
 * Docs: https://www.twilio.com/docs/api/authy/authy-totp#user-status
 * Author: Stacy David
 */
package authyjavasamples.NoHelperLibrary;

public class AuthyUserStatus {

    private static final String THECLASSNAME = "AuthyUserStatus";

    // -------------------------------------------------------------------------
    private static final String PARAM_AUTHYID = System.getenv("AUTHY_ID");
    // -------------------------------------------------------------------------

    public static void main(String[] args) throws Exception {

        String theResponse;
        PrintJson doPrintJson = new PrintJson();
        AuthyCalls doSend = new AuthyCalls();
        
        System.out.println("+++ Start class: " + THECLASSNAME);
        System.out.println("");
        System.out.println("++ Parameters:");
        System.out.println("+ Authy ID: " + PARAM_AUTHYID);
        theResponse = doSend.reqUserStatus(PARAM_AUTHYID);
        System.out.println("+ Response:");
        doPrintJson.pretty(theResponse);
        
        System.out.println("");
        System.out.println("+++ Exit class: " + THECLASSNAME);
    }
}
