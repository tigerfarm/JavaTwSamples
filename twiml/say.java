package twiml;
// Documentation: https://www.twilio.com/docs/voice/twiml/say
// https://docs.aws.amazon.com/polly/latest/dg/voicelist.html
import com.twilio.twiml.VoiceResponse;
import com.twilio.twiml.voice.Say;
import com.twilio.twiml.TwiMLException;
public class say {
    public static void main(String[] args) {
        // String toSay = "Bonjour";
        String toSay = "Ciao, come stai?";
        Say say = new Say.Builder(toSay)
                .voice(Say.Voice.POLLY_CARLA)
                // .voice(Say.Voice.WOMAN)
                // .language(Say.Language.FR_FR)
                .build();
        VoiceResponse response = new VoiceResponse.Builder().say(say).build();
        try {
            System.out.println(response.toXml());
        } catch (TwiMLException e) {
            e.printStackTrace();
        }
    }
}
