package conversations;

import com.twilio.Twilio;
import com.twilio.rest.conversations.v1.Conversation;

public class fetchConversation {

    private static final String ACCOUNT_SID = System.getenv("MASTER_ACCOUNT_SID");
    private static final String AUTH_TOKEN = System.getenv("MASTER_AUTH_TOKEN");

    public static void main(String[] args) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        System.out.println("++ Fetch a conversation using either the SID or unique name.");
        Conversation conversation
                = Conversation.fetcher("studio1u")
                        .fetch();
        System.out.println("+ Conversation SID:          " + conversation.getSid());
        System.out.println("+ Conversation FriendlyName: " + conversation.getFriendlyName());
        System.out.println("+ Conversation UniqueName:   " + conversation.getUniqueName());
    }
}
