/*
 * Twilio: get message list.
 * https://www.twilio.com/docs/api/rest/message
 */
package messaging;

import com.twilio.Twilio;
import com.twilio.base.ResourceSet;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class SmsMsgListFromTo {

    private static final String ACCOUNT_SID = System.getenv("ACCOUNT_SID");
    private static final String AUTH_TOKEN = System.getenv("AUTH_TOKEN");

    public static void main(String[] args) {
        String theClassName = "SmsMsgListFromTo";
        System.out.println("+++ Start class: " + theClassName);

        ResourceSet<Message> messages;
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        String fromPhoneNumber = System.getenv("PHONE_NUMBER2");
        String toPhoneNumber = System.getenv("PHONE_NUMBER3");

        System.out.println("++ List messages: " + fromPhoneNumber + " to " + toPhoneNumber);
        messages = Message.reader()
                .setFrom(new PhoneNumber(fromPhoneNumber))
                .setTo(new PhoneNumber(toPhoneNumber))
                .read();
        for (Message message : messages) {
            System.out.print("+ from:" + message.getFrom());
            System.out.print(", to:" + message.getTo());
            System.out.print(", " + message.getDateSent());
            System.out.print(", " + message.getStatus());
            System.out.print(", " + message.getPrice());
            System.out.print(", " + message.getSid());
            System.out.print(", " + message.getBody());
            System.out.print(", " + message.getNumMedia());
            System.out.println("");
        }
        System.out.println("");
        System.out.println("++ List messages: " + toPhoneNumber + " to " + fromPhoneNumber);
        messages = Message.reader()
                .setFrom(new PhoneNumber(toPhoneNumber))
                .setTo(new PhoneNumber(fromPhoneNumber))
                .read();
        for (Message message : messages) {
            System.out.print("+ from:" + message.getFrom());
            System.out.print(", to:" + message.getTo());
            System.out.print(", " + message.getDateSent());
            System.out.print(", " + message.getStatus());
            System.out.print(", " + message.getPrice());
            System.out.print(", " + message.getSid());
            System.out.print(", " + message.getBody());
            System.out.println("");
        }

        System.out.println("\n+++ Exit class: " + theClassName + ".");
    }
}
