/*
 * Twilio Authy: Add 2FA user
 * Docs: https://www.twilio.com/docs/api/authy/authy-totp#enabling-two-factor
 * Author: Stacy David
 */
package authyjavasamples.NoHelperLibrary;

public class AuthyUserAdd {

    private static final String THECLASSNAME = "AuthyUserAdd";

    // -------------------------------------------------------------------------
    private static final String PARAM_COUNTRYCODE = System.getenv("AUTHY_PHONE_COUNTRYCODE");
    private static final String PARAM_PHONENUMBER = System.getenv("AUTHY_PHONE_NUMBER1");
    private static final String PARAM_EMAIL = System.getenv("AUTHY_ID_EMAIL");
    // -------------------------------------------------------------------------

    public static void main(String[] args) throws Exception {

        String theResponse;
        PrintJson doPrintJson = new PrintJson();
        AuthyCalls doSend = new AuthyCalls();
        
        System.out.println("+++ Start class: " + THECLASSNAME);
        System.out.println("");
        System.out.println("++ Parameters:");
        System.out.println("+ Phone's Country Code:   " + PARAM_COUNTRYCODE);
        System.out.println("+ Phone Number:           " + PARAM_PHONENUMBER);
        System.out.println("+ Email Address:          " + PARAM_EMAIL);
        theResponse = doSend.reqUserAdd(PARAM_COUNTRYCODE, PARAM_PHONENUMBER, PARAM_EMAIL);
        System.out.println("+ Response:");
        doPrintJson.pretty(theResponse);
        
        System.out.println("");
        System.out.println("+++ Exit class: " + THECLASSNAME);
    }
}
