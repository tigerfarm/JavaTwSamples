/*
 * Twilio: get message list.
 * https://www.twilio.com/docs/api/rest/message
 */
package messaging;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

public class SmsMsgFetchFromSid {

    private static final String ACCOUNT_SID = System.getenv("ACCOUNT_SID");
    private static final String AUTH_TOKEN = System.getenv("AUTH_TOKEN");

    public static void main(String[] args) {
        System.out.println("+++ Start.");
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        // String messageSid = "SM5747ee62ac5265a11e3bd26e5f75e5df"; // Success
        String messageSid = "SM4a5c38ed7fd86aae9a350c61561708de"; // Has an error message
        System.out.println("++ Fetch log information for message SID: " + messageSid);
        Message message = Message.fetcher(messageSid).fetch();
        System.out.println("From, to, date sent, status, price, SID, text");
        System.out.print("+ from:" + message.getFrom());
        System.out.print(", to:" + message.getTo());
        System.out.print(", " + message.getDateSent());
        System.out.print(", " + message.getStatus());
        System.out.print(", " + message.getPrice());
        System.out.print(", " + message.getSid());
        System.out.print(", " + message.getBody());
        System.out.println("\n+++ Exit.");
    }
}
