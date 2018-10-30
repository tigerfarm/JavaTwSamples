package messaging_service;

// This version seem old and out of date.

import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
public class send_sms1 {
  // Find your Account Sid and Token at twilio.com/user/account
  public static final String ACCOUNT_SID = "ACXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";
  public static final String AUTH_TOKEN = "your_auth_token";
//  public static void main(String[]args) throws TwilioRestException {
//    TwilioRestClient client = new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN);
    // Build the parameters
    List<NameValuePair> params = new ArrayList<NameValuePair>();
//    params.add(new BasicNameValuePair("To", "+15558675310"));
//    params.add(new BasicNameValuePair("MessagingServiceSid", "MGXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX"));
//    params.add(new BasicNameValuePair("Body",
//     "Phantom Menace was clearly the best of the prequel trilogy."));
//    MessageFactory messageFactory = client.getAccount().getMessageFactory();
//    Message message = messageFactory.create(params);
//    System.out.println(message.getSid());
//  }
}