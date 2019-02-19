package proxy;

import com.twilio.Twilio;
import com.twilio.rest.proxy.v1.service.PhoneNumber;

public class add_phone_number {

    private static final String ACCOUNT_SID = System.getenv("ACCOUNT_SID");
    private static final String AUTH_TOKEN = System.getenv("AUTH_TOKEN");

    public static void main(String[] args) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        PhoneNumber phoneNumber = 
            PhoneNumber.creator(System.getenv("PROXY_SERVICE_SID"))
            .setSid("PN826ba59b78e4bc53d0ca6237ebe15e6a")
            .create();
        System.out.println("+ Into the Proxy service, added phone number: " + phoneNumber.getSid());
    }
}