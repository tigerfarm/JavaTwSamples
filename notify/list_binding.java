package notify;

import com.twilio.Twilio;
import com.twilio.base.ResourceSet;
import com.twilio.rest.notify.v1.service.Binding;

public class list_binding {

    private static final String ACCOUNT_SID = System.getenv("ACCOUNT_SID");
    private static final String AUTH_TOKEN = System.getenv("AUTH_TOKEN");
    private static String notifySid = System.getenv("NOTIFY_SERVICE_SID");  // not "final" because I reset it when testing.

    public static void main(String[] args) {

        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        notifySid = "ISa62b58313e737b6e9d41d19eeef9c32e";
        ResourceSet<Binding> bindings
                = Binding.reader(notifySid)
                        .read();
        System.out.println("+ List bindings for NOTIFY_SERVICE_SID: " + notifySid);
        for (Binding binding : bindings) {
            System.out.print("++ Binding SID: " + binding.getSid());
            System.out.print(", Identity: " + binding.getIdentity());
            System.out.print(", Binding type: " + binding.getBindingType());
            System.out.print(", Address: " + binding.getAddress());
            System.out.println("");
        }
        System.out.println("");
    }
}
