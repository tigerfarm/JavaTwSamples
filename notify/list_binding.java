package notify;

import com.twilio.Twilio;
import com.twilio.base.ResourceSet;
import com.twilio.rest.notify.v1.service.Binding;

public class list_binding {

    private static final String ACCOUNT_SID = System.getenv("MASTER_ACCOUNT_SID");
    private static final String AUTH_TOKEN = System.getenv("MASTER_AUTH_TOKEN");
    private static String notifySid = System.getenv("NOTIFY_SERVICE_SID");  // not "final" because I reset it when testing.

    public static void main(String[] args) {

        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        notifySid = "IS5f8c400423a209420fe4b159fd28fffb";
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
