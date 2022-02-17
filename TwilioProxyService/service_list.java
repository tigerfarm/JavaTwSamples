package TwilioProxyService;

import com.twilio.Twilio;
import com.twilio.base.ResourceSet;
import com.twilio.rest.proxy.v1.Service;

public class service_list {

    private static final String ACCOUNT_SID = System.getenv("ACCOUNT_SID");
    private static final String AUTH_TOKEN = System.getenv("AUTH_TOKEN");

    public static void main(String[] args) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        //
        System.out.println("+ List services (SID, name).");
        ResourceSet<Service> services = Service.reader().read();
        for (Service record : services) {
            System.out.println(record.getSid() + " " + record.getUniqueName());
        }
    }
}
