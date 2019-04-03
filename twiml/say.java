// Documentation: https://www.twilio.com/docs/voice/twiml/say
import com.twilio.twiml.VoiceResponse;
import com.twilio.twiml.voice.Say;
import com.twilio.twiml.TwiMLException;
public class say {
    public static void main(String[] args) {
        String toSay = "Bonjour";
        Say say = new Say.Builder(toSay)
                .voice(Say.Voice.WOMAN)
                .language(Say.Language.FR_FR).build();
        VoiceResponse response = new VoiceResponse.Builder().say(say).build();
        try {
            System.out.println(response.toXml());
        } catch (TwiMLException e) {
            e.printStackTrace();
        }
    }
}
