/*
 * Twilio Phone Verification
 * Author: Stacy David
 */
package authyjavasamples.NoHelperLibrary;

import java.io.DataInputStream;

public class AuthyPhoneVerificationCheck {

    private static final String THECLASSNAME = "AuthyPhoneVerification";

    // -------------------------------------------------------------------------
    private static final String PARAM_VIA = "sms";    // <sms|call>
    private static final String PARAM_COUNTRYCODE = System.getenv("AUTHY_PHONE_COUNTRYCODE");
    private static final String PARAM_PHONENUMBER = System.getenv("AUTHY_PHONE_NUMBER1");
    // -------------------------------------------------------------------------

    public static void main(String[] args) throws Exception {

        String theResponse;
        PrintJson doPrintJson = new PrintJson();
        AuthyCalls doSend = new AuthyCalls();
        
        System.out.println("+++ Start class: " + THECLASSNAME);

        // ---------------------------------------------------------------------------
        System.out.println("");
        System.out.println("++ Request: Phone Verification...");
        System.out.println("+ Parameters:");
        System.out.println("+ Via:                    " + PARAM_VIA);
        System.out.println("+ Phone's Country Code:   " + PARAM_COUNTRYCODE);
        System.out.println("+ Phone Number:           " + PARAM_PHONENUMBER);
        theResponse = doSend.reqPhoneVerifySend(PARAM_VIA, PARAM_COUNTRYCODE, PARAM_PHONENUMBER);
        System.out.println("+ Response:");
        doPrintJson.pretty(theResponse);

        System.out.println("");
        System.out.println("----------------------------------");
        String thePrompt = "++ Enter Phone Verification code (or exit)> ";
        String paramVerificationCode = "";
        try (DataInputStream console = new DataInputStream(System.in)) {
            while (paramVerificationCode.length() != 4) {
                System.out.print(thePrompt);
                paramVerificationCode = console.readLine();
                if (paramVerificationCode.length() != 4) {
                    System.out.println("+ Must be 4 digits.");
                } else if (paramVerificationCode.equalsIgnoreCase("exit")) {
                    System.out.println("++ Exit.");
                    return;
                }
            }
        }
        System.out.println("");
        System.out.println("++ Request: Verify Phone Verification code...");
        System.out.println("+ Verification Code:      " + paramVerificationCode);
        System.out.println("+ Phone's Country Code:   " + PARAM_COUNTRYCODE);
        System.out.println("+ Phone Number:           " + PARAM_PHONENUMBER);
        theResponse = doSend.reqPhoneVerifyCheck(paramVerificationCode, PARAM_COUNTRYCODE, PARAM_PHONENUMBER);
        System.out.println("+ Response:");
        doPrintJson.pretty(theResponse);

        System.out.println("");
        System.out.println("+++ Exit class: " + THECLASSNAME);
    }
}
