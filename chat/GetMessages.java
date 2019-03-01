//
// Doc link:
//  https://www.twilio.com/docs/chat/rest/messages?code-sample=code-list-all-messages&code-language=Java&code-sdk-version=7.x
//
package chat;

import com.twilio.Twilio;
import com.twilio.base.ResourceSet;
import com.twilio.rest.chat.v2.service.channel.Message;

public class GetMessages {

    private static final String ACCOUNT_SID = System.getenv("ACCOUNT_SID");
    private static final String AUTH_TOKEN = System.getenv("AUTH_TOKEN");
    public static final String SERVICE_SID = System.getenv("CHAT_SERVICE_SID");
    public static final String CHANNEL_SID = "CHcca2f11dcf0b430eb75444ce0d95542f";   // mychannel1

    public static void main(String[] args) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        System.out.println("+++ Get chat channel messages.");
        ResourceSet<Message> messages = Message.reader(SERVICE_SID,CHANNEL_SID)
                .setOrder(Message.OrderType.DESC) // DESC | ASC
                .read();
        for (Message record : messages) {
            System.out.println("+ " + record.getDateCreated() + " " + record.getFrom() + " " + record.getBody());
        }
        System.out.println("+ End of list.");
        System.out.println("");
    }
}
