package proxy;

import com.twilio.Twilio;
import com.twilio.base.ResourceSet;
import com.twilio.rest.proxy.v1.service.PhoneNumber;

public class service_phone_number_list {

    private static final String ACCOUNT_SID = System.getenv("ACCOUNT_SID");
    private static final String AUTH_TOKEN = System.getenv("AUTH_TOKEN");

    public static void main(String[] args) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        // https://www.twilio.com/docs/proxy/api/phone-number
        String serviceSid = System.getenv("PROXY_SERVICE_SID");
        ResourceSet<PhoneNumber> items = PhoneNumber.reader(serviceSid)
                .read();
        System.out.println("+ List phone numbers for Proxy service: " + serviceSid);
        for (PhoneNumber item : items) {
            System.out.println(
                    "++ " + item.getSid()
            );
        }

    }
}
