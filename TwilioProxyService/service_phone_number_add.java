package TwilioProxyService;

import com.twilio.Twilio;
import com.twilio.exception.ApiException;
import com.twilio.rest.proxy.v1.service.PhoneNumber;

public class service_phone_number_add {
    private static final String ACCOUNT_SID = System.getenv("ACCOUNT_SID");
    private static final String AUTH_TOKEN = System.getenv("AUTH_TOKEN");
    public static void main(String[] args) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        System.out.println("+ Into the Proxy service, add the phone number: " + System.getenv("PHONE_NUMBER_SID"));
        try {
            PhoneNumber phoneNumber
                    = PhoneNumber.creator(System.getenv("PROXY_SERVICE_SID"))
                            .setSid(System.getenv("PHONE_NUMBER_SID"))
                            .create();
            System.out.println("++ Added: " + phoneNumber.getSid());
        } catch (ApiException e) {
            System.out.println("- Error Code: " + e.getCode() + ": " + e.getMessage());
        }
    }
}
