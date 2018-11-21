/*
 * Author: Stacy David
 */
package authyjavasamples.NoHelperLibrary;

import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.message.BasicNameValuePair;

public class TwCalls {

    private static final String ACCOUNT_SID = System.getenv("ACCOUNT_SID");
    private static final String AUTH_TOKEN = System.getenv("AUTH_TOKEN");
    
    private static final HttpCalls HTTPS_REQUEST = new HttpCalls();
    private static final String HTTP_LOOKUP_HOST = "lookups.twilio.com";
    private static final String HTTP_API_HOST = "api.twilio.com";
    private static final int HTTPS_PORT = 443;

    public String reqLookupCarrier(String paramPhoneNumber) throws Exception {
        // ---------------------------------------------------------------------------
        //    Type=carrier : Carrier information costs $0.005 
        // or Type=caller-name : $0.01 per phone number looked up (only in US)
        // https://your_account_sid:your_account_token@lookups.twilio.com/v1/PhoneNumbers/18182103863?Type=carrier
        String theRequest = "https://" + HTTP_LOOKUP_HOST + "/v1/PhoneNumbers/+" + paramPhoneNumber + "?Type=carrier";
        // System.out.println("+ Request: '" + theRequest);
        CredentialsProvider credsProvider = new BasicCredentialsProvider();
        credsProvider.setCredentials(
                new AuthScope(HTTP_LOOKUP_HOST, HTTPS_PORT),
                new UsernamePasswordCredentials(ACCOUNT_SID, AUTH_TOKEN));
        return HTTPS_REQUEST.doGet(theRequest, credsProvider);
    }

     public String reqSendSmsMsg(String fromPhoneNumber, String toPhoneNumber, String paramMessage, String theType) throws Exception {
        String theRequest = "https://" + HTTP_API_HOST + "/2010-04-01/Accounts/" + ACCOUNT_SID + "/Messages." + theType;
        // System.out.println("+ Request: '" + theRequest);
        
        List<NameValuePair> params = new ArrayList<>();
        // https://www.twilio.com/docs/api/rest/making-calls
        params.add(new BasicNameValuePair("From", fromPhoneNumber));
        params.add(new BasicNameValuePair("To", toPhoneNumber));
        params.add(new BasicNameValuePair("Body", paramMessage));
        
        CredentialsProvider credsProvider = new BasicCredentialsProvider();
        credsProvider.setCredentials(
                new AuthScope(HTTP_API_HOST, HTTPS_PORT),
                new UsernamePasswordCredentials(ACCOUNT_SID, AUTH_TOKEN));

        return HTTPS_REQUEST.doPost(theRequest, params, credsProvider);
    }

     public String reqCallHttpClient(String fromPhoneNumber, String toPhoneNumber, String paramUrl) throws Exception {
        String theRequest = "https://" + HTTP_API_HOST + "/2010-04-01/Accounts/" + ACCOUNT_SID + "/Calls/";
        // System.out.println("+ Request: '" + theRequest);
        
        List<NameValuePair> params = new ArrayList<>();
        // https://www.twilio.com/docs/api/rest/making-calls
        params.add(new BasicNameValuePair("From", fromPhoneNumber));
        params.add(new BasicNameValuePair("To", toPhoneNumber));
        params.add(new BasicNameValuePair("Url", paramUrl));        // Example: "http://tigerfarmpress.com/twilio/ReadKeatsPoem.xml"
        // Option: params.add(new BasicNameValuePair("StatusCallback", "http://www.tigerfarmpress.com/python/twEchoReq"));
        // Option: params.add(new BasicNameValuePair("Record", "true"));
        // Option: params.add(new BasicNameValuePair("RecordingStatusCallback", "http://www.tigerfarmpress.com/python/twEchoReq"));

        CredentialsProvider credsProvider = new BasicCredentialsProvider();
        credsProvider.setCredentials(
                new AuthScope(HTTP_API_HOST, HTTPS_PORT),
                new UsernamePasswordCredentials(ACCOUNT_SID, AUTH_TOKEN));

        return HTTPS_REQUEST.doPost(theRequest, params, credsProvider);
    }

}
