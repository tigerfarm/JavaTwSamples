// To run:
// java -cp AuthyJavaSamples.jar authyjavasamples.AuthyJavaSamples
//
package authyjavasamples;

public class AuthyJavaSamples {

    public static void main(String[] args) {
        System.out.print("+++ Authy Java Samples.");
        System.out.println("++ Echo environment variables:");
        System.out.println("+ PhoneVerificationService/main, SID:                     " + System.getenv("ACCOUNT_SID"));
        System.out.println("+ PhoneVerificationService/main, AUTH_TOKEN:              " + System.getenv("AUTH_TOKEN"));
        System.out.println("+ PhoneVerificationService/main, AUTHY_ID:                " + System.getenv("AUTHY_ID"));
        System.out.println("+ PhoneVerificationService/main, AUTHY_ID_EMAIL:          " + System.getenv("AUTHY_ID_EMAIL"));
        System.out.println("+ PhoneVerificationService/main, PHONE_NUMBER1:           " + System.getenv("PHONE_NUMBER1"));
        System.out.println("+ PhoneVerificationService/main, PHONE_NUMBER2:           " + System.getenv("PHONE_NUMBER2"));
        System.out.println("+ PhoneVerificationService/main, PHONE_NUMBER3:           " + System.getenv("PHONE_NUMBER3"));
        System.out.println("+ PhoneVerificationService/main, PHONE_NUMBER4:           " + System.getenv("PHONE_NUMBER4"));
        System.out.println("+ PhoneVerificationService/main, AUTHY_API_KEY:           " + System.getenv("AUTHY_API_KEY"));
        System.out.println("+ PhoneVerificationService/main, AUTHY_PHONE_COUNTRYCODE: " + System.getenv("AUTHY_PHONE_COUNTRYCODE"));
        System.out.println("+ PhoneVerificationService/main, AUTHY_PHONE_NUMBER1:     " + System.getenv("AUTHY_PHONE_NUMBER1"));
        System.out.println("++ Exit.");
    }
}
