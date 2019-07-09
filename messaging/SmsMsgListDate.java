/*
 * Twilio: get message list.
 * https://www.twilio.com/docs/api/rest/message
 */
package messaging;

import com.twilio.Twilio;
import com.twilio.base.ResourceSet;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.joda.time.DateTime;
import com.google.common.collect.Range;

public class SmsMsgListDate {

    private static final String ACCOUNT_SID = System.getenv("ACCOUNT_SID");
    private static final String AUTH_TOKEN = System.getenv("AUTH_TOKEN");

    public static void main(String[] args) {
        String theClassName = "GetMsgListSms";
        System.out.println("+++ Start class: " + theClassName);

        ResourceSet<Message> messages;
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        String thisPhoneNumber = System.getenv("PHONE_NUMBER2");
        Range<DateTime> rangeDateSent;
        rangeDateSent = null;
        rangeDateSent.lowerEndpoint();

        System.out.println("++ List inbound messages: " + thisPhoneNumber);
        messages = Message.reader()
                .setTo(new PhoneNumber(thisPhoneNumber))
                .setDateSent(DateTime.parse("2019-02-06T00:00:00.000Z"))
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
        System.out.println("+ End list.");
        
        System.out.println("++ List outbound messages: " + thisPhoneNumber);
        messages = Message.reader()
                .setFrom(new PhoneNumber(thisPhoneNumber))
                .setDateSent(DateTime.parse("2019-02-06"))
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
