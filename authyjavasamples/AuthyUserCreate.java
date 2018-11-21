/*
To run:
java -cp AuthyJavaSamples.jar authyjavasamples.PhoneVerification

Based on:
    https://www.twilio.com/docs/authy/api/users
    https://github.com/twilio/authy-java
Libraries:
    Java Helper Library, download link from: https://www.twilio.com/docs/libraries/java
    Authy and JSON libraries: authy-java-1.2.0.jar and json-20150729.jar
    Download from: https://jar-download.com/explore-java-source-code.php?a=authy-java&g=com.authy&v=1.2.0&downloadable=1
 */
package authyjavasamples;

import com.authy.AuthyApiClient;
import com.authy.api.Tokens;
import com.authy.api.User;
import com.authy.api.Users;

public class AuthyUserCreate {
    private static final String AUTHY_API_KEY = System.getenv("AUTHY_API_KEY");
    private static final String AUTHY_ID_EMAIL = System.getenv("AUTHY_ID_EMAIL");
    private static final String PARAM_COUNTRYCODE = System.getenv("AUTHY_PHONE_COUNTRYCODE");
    // private static final String PARAM_PHONENUMBER = System.getenv("AUTHY_PHONE_NUMBER1");
    private static final String PARAM_PHONENUMBER = "6505551111";
    // -------------------------------------------------------------------------
    public static void main(String[] args) {
        System.out.println("+++ AuthyUserCreate.");
        AuthyApiClient client = new AuthyApiClient(AUTHY_API_KEY);
        System.out.println("++ Create user whose phone number is: (" + PARAM_COUNTRYCODE + ")" + PARAM_PHONENUMBER);
        Users users = client.getUsers();
        Tokens tokens = client.getTokens();
        User user = users.createUser(AUTHY_ID_EMAIL, PARAM_PHONENUMBER, PARAM_COUNTRYCODE);
        if (user.isOk()) {
            System.out.println("+ New User id: " + user.getId() + " Note, should be stored in your DB.");
        }
    }

}
