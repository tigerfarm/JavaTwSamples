package notify;

import com.twilio.Twilio;
import com.twilio.rest.notify.v1.service.Binding;

public class create_binding {

    private static final String ACCOUNT_SID = System.getenv("ACCOUNT_SID");
    private static final String AUTH_TOKEN = System.getenv("AUTH_TOKEN");

    public static void main(String[] args) {

        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Binding binding = Binding.creator(
                // "ISXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX",
                "IS3a46cc3e6ca7a1b8bd7aea51c875d33a",
                // "00000001",
                "bill",
                Binding.BindingType.SMS,
                // "+1651000000000"
                System.getenv("PHONE_NUMBER4")
        ).create();

        // Immediately returned values:
        System.out.print("+ Binding SID: " + binding.getSid());
        System.out.print(", Identity: " + binding.getIdentity());
        System.out.print(", Binding type: " + binding.getBindingType());
        System.out.print(", Address: " + binding.getAddress());
        System.out.println("");
    }
}
