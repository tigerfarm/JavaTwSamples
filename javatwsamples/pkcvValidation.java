package javatwsamples;

/*
    https://www.twilio.com/docs/iam/pkcv/quickstart#client-validation-java-example
 */
import com.twilio.http.TwilioRestClient;
import com.twilio.http.ValidationClient;
import com.twilio.rest.accounts.v1.credential.PublicKey;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.NewKey;
import com.twilio.type.PhoneNumber;
// import org.apache.commons.codec.binary.Base64;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class pkcvValidation {

    private static final String ACCOUNT_SID = System.getenv("MASTER_ACCOUNT_SID");
    private static final String AUTH_TOKEN = System.getenv("MASTER_AUTH_TOKEN");

    public static void main(String[] args) throws NoSuchAlgorithmException {
// Generate public/private key pair
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);
        KeyPair pair = keyGen.generateKeyPair();
        java.security.PublicKey pk = pair.getPublic();

        // Use the default rest client
        TwilioRestClient client
                = new TwilioRestClient.Builder(ACCOUNT_SID, AUTH_TOKEN)
                        .build();

        // Create a public key and api key using the default client
        PublicKey key = PublicKey.creator(
                // Original line: Base64.encodeBase64String(pk.getEncoded())
                // Line that compiled for me:
                Base64.getEncoder().encodeToString(pk.getEncoded())
        ).setFriendlyName("Public Key").create(client);

        NewKey apiKey = NewKey.creator().create(client);

        // Switch to validation client as the default client
        TwilioRestClient validationClient = new TwilioRestClient.Builder(apiKey.getSid(), apiKey.getSecret())
                .accountSid(ACCOUNT_SID)
                .httpClient(new ValidationClient(ACCOUNT_SID, key.getSid(), apiKey.getSid(), pair.getPrivate()))
                .build();

        // Make REST API requests with Client Validation enabled
        Iterable<Message> messages = Message.reader().read(validationClient);
        for (Message m : messages) {
            System.out.println(m.getBody());
        }

        Message m = Message.creator(
                new PhoneNumber("+1XXXXXXXXXX"),
                new PhoneNumber("+1XXXXXXXXXX"),
                "Client Validation Test"
        ).create(validationClient);
        System.out.println(m.getSid());

    }
}
