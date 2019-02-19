package voice;

import com.twilio.twiml.voice.Dial;
import com.twilio.twiml.VoiceResponse;
import com.twilio.twiml.TwiMLException;
import com.twilio.twiml.voice.Conference;

public class conference {

    public static void main(String[] args) {
        
        // https://www.twilio.com/docs/voice/twiml/conference
        Conference conference = new Conference.Builder("Room 1234").build();
        Dial dial = new Dial.Builder().conference(conference).build();
        VoiceResponse response = new VoiceResponse.Builder().dial(dial).build();

        try {
            System.out.println(response.toXml());
        } catch (TwiMLException e) {
            e.printStackTrace();
        }
    }
}