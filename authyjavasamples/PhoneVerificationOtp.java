/*
To run:
java -cp AuthyJavaSamples.jar authyjavasamples.PhoneVerificationOtp

Based on:
    https://www.twilio.com/docs/verify
    https://www.twilio.com/docs/verify/quickstart/java-servlets
    https://github.com/TwilioDevEd/account-security-quickstart-servlets/blob/master/src/main/java/com/twilio/accountsecurity/services/PhoneVerificationService.java
Libraries:
    Java Helper Library, download link from: https://www.twilio.com/docs/libraries/java
    Authy and JSON libraries: authy-java-1.2.0.jar and json-20150729.jar
    Download from: https://jar-download.com/explore-java-source-code.php?a=authy-java&g=com.authy&v=1.2.0&downloadable=1
 */
package authyjavasamples;

import com.authy.AuthyApiClient;
import com.authy.api.Params;
import com.authy.api.Verification;
import java.io.DataInputStream;

public class PhoneVerificationOtp {

    // -------------------------------------------------------------------------
    private static final String AUTHY_API_KEY = System.getenv("AUTHY_API_KEY");
    private static final String PARAM_VIA = "sms";    // <sms|call>
    private static final String PARAM_COUNTRYCODE = System.getenv("AUTHY_PHONE_COUNTRYCODE");
    private static final String PARAM_PHONENUMBER = System.getenv("AUTHY_PHONE_NUMBER1");
    // -------------------------------------------------------------------------
    private AuthyApiClient authyApiClient = new AuthyApiClient(AUTHY_API_KEY);

    public boolean sendOTP(String countryCode, String phoneNumber, String via) throws Exception {
        Params params = new Params();
        params.setAttribute("code_length", "4");
        Verification verification = authyApiClient.getPhoneVerification().start(
                phoneNumber, countryCode, via, params
        );
        if (!verification.isOk()) {
            logAndThrow("sendOTP, Error: " + verification.getMessage());
            return false;
        }
        System.out.println("+ sendOTP, success: " + verification.getMessage());
        return true;
    }

    public void verifyOTP(String countryCode, String phoneNumber, String token) throws Exception {
        Verification verification = authyApiClient.getPhoneVerification().check(
                phoneNumber, countryCode, token
        );
        if (!verification.isOk()) {
            logAndThrow("verifyOTP, Error: " + verification.getMessage());
            return;
        }
        System.out.println("+ verifyOTP, success: " + verification.getMessage());
    }

    public PhoneVerificationOtp(AuthyApiClient authyApiClient) {
        this.authyApiClient = authyApiClient;
    }

    private void logAndThrow(String message) {
        System.out.println("- logAndThrow, " + message);
    }

    public static void main(String[] args) {
        System.out.println("+++ Start.");
        PhoneVerificationOtp phonVeriService = new PhoneVerificationOtp(new AuthyApiClient(AUTHY_API_KEY));
        try {
            System.out.println("++ Send the one time passcode (OTP) to: " + PARAM_PHONENUMBER);
            if (!phonVeriService.sendOTP(PARAM_COUNTRYCODE, PARAM_PHONENUMBER, PARAM_VIA)) {
                System.out.println("- Token not sent, exit this program.");
                return;
            }
            System.out.println("-------------------------");
            String thePrompt = "++ Enter the received OTP (or exit)> ";
            String paramToken = "";
            try (DataInputStream console = new DataInputStream(System.in)) {
                while (paramToken.length() != 4) {
                    System.out.print(thePrompt);
                    paramToken = console.readLine();
                    if (paramToken.equalsIgnoreCase("exit")) {
                        System.out.println("++ Exit.");
                        return;
                    } else if (paramToken.length() != 4) {
                        System.out.println("+ Must be 4 digits.");
                    }
                }
            }
            phonVeriService.verifyOTP(PARAM_COUNTRYCODE, PARAM_PHONENUMBER, paramToken);
        } catch (Exception e) {
            System.out.println("- Exception caught.");
        }
    }

}
