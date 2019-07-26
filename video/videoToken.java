// Document link: https://www.twilio.com/docs/iam/access-tokens?code-sample=code-creating-an-access-token-video-2&code-language=Java&code-sdk-version=7.x
// Twilio Console link with the following code: https://www.twilio.com/console/video/runtime/testing-tools
// Requires the inclusion of the library file: slf4j-simple-1.x.x.jar

package video;

import com.twilio.jwt.accesstoken.AccessToken;
import com.twilio.jwt.accesstoken.VideoGrant;

public class videoToken {
    // Substitute your Twilio AccountSid and ApiKey details
    private static final String ACCOUNT_SID = System.getenv("ACCOUNT_SID");
    public static final String API_KEY_SID = System.getenv("API_KEY");
    public static final String API_KEY_SECRET = System.getenv("API_KEY_SECRET");
    public static void main(String[] args) throws Exception {
        // Create a VideoGrant
        final VideoGrant grant = new VideoGrant();
        grant.setRoom("cool room");
        // Create an Access Token
        final AccessToken token = new AccessToken.Builder(ACCOUNT_SID, API_KEY_SID, API_KEY_SECRET)
                .identity("example-user") // Set the Identity of this token
                .grant(grant) // Grant access to Video
                .build();
        // Serialize the token as a JWT
        final String jwt = token.toJwt();
        System.out.println(jwt);
    }
}
