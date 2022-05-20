package javatwsamples;
/*
    https://www.twilio.com/docs/usage/security
    javac -cp twilio-8.18.0-jar-with-dependencies.jar jSignatureValidationPost.java
    java -cp .:twilio-8.18.0-jar-with-dependencies.jar jSignatureValidationPost
 */
import com.twilio.security.RequestValidator;
import java.util.HashMap;
import java.util.Map;

public class jSignatureValidationPostList {

    public static void main(String[] args) {
        // Initialize the validator
        String AUTH_TOKEN = System.getenv("MASTER_AUTH_TOKEN");
        RequestValidator validator = new RequestValidator(AUTH_TOKEN);
        // The actual Twilio request URL
        String url = "https://example.com/myapp";
        //
        // The POST body in the Twilio request.
        // Note, the parameters need to URL decoded.
        Map<String, String> params = new HashMap<>();
        params.put("CallSid", "CA1234567890ABCDE");
        params.put("Caller", "+16505551111");
        // ... all the parameters ...
        System.out.println("+ params: " + params);
        //
        String twilioSignature = "0/KCTR6DLpKmkAf8muzZqo1nDgQ=";
        System.out.println("+ Signature validation is: " + validator.validate(url, params, twilioSignature));
    }
}
