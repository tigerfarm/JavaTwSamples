package javatwsamples;

import java.util.Base64;

public class base64sample {

    public static void main(String[] args) {
        System.out.println("+++ Start: Base64 encoding.");
        String aString = "abc";
        System.out.println("+ String:  " + aString );
        String aStringEncoded = Base64.getEncoder().encodeToString(aString.getBytes());
        System.out.println("+ Encoded: " + aStringEncoded);
        System.out.println("+ Decoded: " + new String(Base64.getDecoder().decode(aStringEncoded)));
        
        System.out.println("-------------------");
        System.out.println("+ How to decode a string.");
        String bString = "abcdef";
        String bStringEncoded = Base64.getEncoder().encodeToString(bString.getBytes());
        System.out.println(
                "++ Original string: "
                + bString
                + ", Encoded: "
                + bStringEncoded
                + ", Decoded: "
                + new String(Base64.getDecoder().decode(
                        bStringEncoded
                ))
        );

        System.out.println("-------------------");
        System.out.println("+ How to generate a an Authorization Basic header:");
        String theEncode = Base64.getEncoder().encodeToString(
                String.format("%s:%s", System.getenv("MAIN_ACCOUNT_SID"), System.getenv("MAIN_AUTH_TOKEN")).getBytes()
        );
        System.out.println("++ Encoded, Authorization: Basic " + theEncode );
        String theDecode = new String(Base64.getDecoder().decode(theEncode));
        System.out.println("++ Decoded, Authorization: Basic " + theDecode );

        System.out.println("-------------------");
        String accountSid =       "ACjr0e389ccba35d23d8d4f6f8465274j3";
        String accountAuthToken = "asljeij024039qt440w5jeisgrje4g34gj";        
        String accountString = accountSid + ":" + accountAuthToken;
        System.out.println("++ Not encoded, Authorization: Basic " + accountString);
        String accountEncoded = Base64.getEncoder().encodeToString(accountString.getBytes());
        System.out.println("++ Encoded, Authorization: Basic     " + accountEncoded );
        String accountDecode = new String(Base64.getDecoder().decode(accountEncoded));
        System.out.println("++ Decoded, Authorization: Basic     " + accountDecode );

        System.out.println("-------------------");
        System.out.println("+++ Exit.");
    }
}
