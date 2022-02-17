package TwilioProxyService;

import com.twilio.Twilio;
import com.twilio.exception.ApiException;
import com.twilio.rest.proxy.v1.Service;

public class service_create {

    private static final String ACCOUNT_SID = System.getenv("ACCOUNT_SID");
    private static final String AUTH_TOKEN = System.getenv("AUTH_TOKEN");

    public static void main(String[] args) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        //
        String sessionName = "myProxyName";
        System.out.println("+ Create a service with the name: " + sessionName);
        try {
            Service service = Service.creator(sessionName).create();
            String serviceSid = service.getSid();
            // Retrieve a service.
            // https://www.twilio.com/docs/proxy/api/service
            service = Service.fetcher(serviceSid).fetch();
            System.out.print("+ Created Proxy service, SID: " + serviceSid);
            System.out.print(", " + service.getUniqueName());
            System.out.println("");
        } catch (ApiException e) {
            System.out.println("- Error Code: " + e.getCode() + ": " + e.getMessage());
            if (e.getCode() == 80602) {
                System.out.println("- Service with that name already exists.");
            }
        }
    }
}
