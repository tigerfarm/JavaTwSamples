/*
 * Twilio: get message list.
 * https://www.twilio.com/docs/api/rest/message
 */
package messaging;

import com.twilio.Twilio;
import com.twilio.base.ResourceSet;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class SmsMsgListDate1 {

    private static final String ACCOUNT_SID = System.getenv("MASTER_ACCOUNT_SID");
    private static final String AUTH_TOKEN = System.getenv("MASTER_AUTH_TOKEN");

    public static void main(String[] args) {

        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        ZonedDateTime theZonedDateTime = ZonedDateTime.of(2022, 12, 1, 0, 0, 0, 0, ZoneId.of("UTC"));
        ResourceSet<Message> messages = Message.reader()
                .setTo(new PhoneNumber(System.getenv("MASTER_PHONE_NUMBER_2")))
                .setDateSent(theZonedDateTime)
                .read();
        // System.out.println("++ List inbound messages: " + DateTime.now());
        // messages = Message.reader().read();
        for (Message message : messages) {
            System.out.print("+ from:" + message.getFrom());
            System.out.print(", to:" + message.getTo());
            System.out.print(", dataSent: " + message.getDateSent());
            System.out.print(", status:" + message.getStatus());
            System.out.print(", price: " + message.getPrice());
            System.out.print(", sid:" + message.getSid());
            System.out.print(", text:" + message.getBody());
            System.out.println("");
        }
        System.out.println( "+ End list.");
    }
}
