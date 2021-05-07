package javatwsamples;

import java.util.Base64;

public class base64sample {

    public static void main(String[] args) {
        System.out.println("+++ Start: Base64 encoding.");
        String aString = "test input";
        System.out.println("+ String: "
                + "+ Encoded: "
                + Base64.getEncoder().encodeToString(aString.getBytes())
        );
        System.out.println("+ How to generate a an Authorization Basic header:");
        System.out.println("++ Authorization: Basic "
                + Base64.getEncoder().encodeToString(
                        String.format("%s:%s", System.getenv("MASTER_ACCOUNT_SID"), System.getenv("MASTER_AUTH_TOKEN")).getBytes()
                )
        );
        System.out.println("+++ Exit.");
    }
}
