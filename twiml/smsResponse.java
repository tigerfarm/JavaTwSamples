package twiml;
// Documentation: https://www.twilio.com/docs/voice/twiml/say
// https://docs.aws.amazon.com/polly/latest/dg/voicelist.html

import com.twilio.twiml.messaging.Body;
import com.twilio.twiml.messaging.Message;
import com.twilio.twiml.TwiMLException;

public class smsResponse {
    public static void main(String[] args) {
        Body body = new Body.Builder("Hello World!").build();
        Message message = new Message.Builder().body(body).build();
        try {
            System.out.println( message.toXml() );
        } catch (TwiMLException e) {
            e.printStackTrace();
        }
    }
}
