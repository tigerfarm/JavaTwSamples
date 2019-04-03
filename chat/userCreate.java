//
// Doc link:
//  https://www.twilio.com/docs/chat/rest/messages?code-sample=code-list-all-messages&code-language=Java&code-sdk-version=7.x
//
package chat;

import com.twilio.Twilio;
import com.twilio.rest.chat.v1.service.User;

public class userCreate {

    private static final String ACCOUNT_SID = System.getenv("ACCOUNT_SID");
    private static final String AUTH_TOKEN = System.getenv("AUTH_TOKEN");
    public static final String SERVICE_SID = System.getenv("CHAT_SERVICE_SID");

    public static void main(String[] args) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        System.out.println("+++ Create user.");

        User user = User.creator( SERVICE_SID,
                "test6"
                ).create();
        System.out.println("+ " + user.getSid() + " " + user.getIdentity() + " " + user.getFriendlyName());
        user = User.updater( SERVICE_SID,user.getSid())
                .setFriendlyName("test me 6").update();
        System.out.println("+ " + user.getSid() + " " + user.getIdentity() + " " + user.getFriendlyName());
                
        System.out.println("+ End of list.");
        System.out.println("");
    }
}
