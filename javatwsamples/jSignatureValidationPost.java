package javatwsamples;
/*
    https://www.twilio.com/docs/usage/security
    javac -cp twilio-8.18.0-jar-with-dependencies.jar jSignatureValidationPost.java
    java -cp .:twilio-8.18.0-jar-with-dependencies.jar jSignatureValidationPost
 */
import com.twilio.security.RequestValidator;
import java.util.HashMap;
import java.util.Map;

public class jSignatureValidationPost {

    public static void main(String[] args) {
        // Initialize the validator
        String AUTH_TOKEN = System.getenv("MASTER_AUTH_TOKEN");
        RequestValidator validator = new RequestValidator(AUTH_TOKEN);
        // The Twilio request URL
        String url = "https://example.com/myapp";
        // The post variables in Twilio's request
        Map<String, String> params = new HashMap<>();
        params.put("CallSid", "CA1234567890ABCDE");
        params.put("Caller", "+12349013030");
        System.out.println("+ params: " + params);
        String twilioSignature = "0/KCTR6DLpKmkAf8muzZqo1nDgQ=";
        System.out.println(validator.validate(url, params, twilioSignature));
        System.out.println("+ Signature validation is: " + validator.validate(url, params, twilioSignature));
    }
}
