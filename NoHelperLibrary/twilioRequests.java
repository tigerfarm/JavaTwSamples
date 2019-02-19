/*
 * Author: Stacy David
 */
package NoHelperLibrary;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.message.BasicNameValuePair;

public class twilioRequests {

    private static final String ACCOUNT_SID = System.getenv("ACCOUNT_SID");
    private static final String AUTH_TOKEN =  System.getenv("AUTH_TOKEN");
    
    private static final String SMS_REQUEST_URL = String.format("https://api.twilio.com/2010-04-01/Accounts/%s/Messages.", ACCOUNT_SID);
    private static final HttpCalls HTTPS_REQUEST = new HttpCalls();
    private static final String HTTP_LOOKUP_HOST = "lookups.twilio.com";
    private static final String HTTP_API_HOST = "api.twilio.com";
    private static final int HTTPS_PORT = 443;

    public String reqRecList(String theType) throws Exception {
        // https://api.twilio.com/2010-04-01/Accounts/ACxxxxxx/Recordings.json
        String theRequest = "https://" + HTTP_API_HOST + "/2010-04-01/Accounts/" + ACCOUNT_SID + "/Recordings." + theType;
        // System.out.println("+ Request: '" + theRequest);
        CredentialsProvider credsProvider = new BasicCredentialsProvider();
        credsProvider.setCredentials(
                new AuthScope(HTTP_API_HOST, HTTPS_PORT),
                new UsernamePasswordCredentials(ACCOUNT_SID, AUTH_TOKEN));
        return HTTPS_REQUEST.doGet(theRequest, credsProvider);
    }

    public String reqAppList(String theType) throws Exception {
        // https://api.twilio.com/2010-04-01/Accounts/ACxxxxxx/Applications.json
        String theRequest = "https://" + HTTP_API_HOST + "/2010-04-01/Accounts/" + ACCOUNT_SID + "/Applications." + theType;
        // System.out.println("+ Request: '" + theRequest);
        CredentialsProvider credsProvider = new BasicCredentialsProvider();
        credsProvider.setCredentials(
                new AuthScope(HTTP_API_HOST, HTTPS_PORT),
                new UsernamePasswordCredentials(ACCOUNT_SID, AUTH_TOKEN));
        return HTTPS_REQUEST.doGet(theRequest, credsProvider);
    }

    public String reqCallView(String paramSid, String theType) throws Exception {
        // https://api.twilio.com/2010-04-01/Accounts/ACxxxxxx/Calls/CAxxxxxx.json
        String theRequest = "https://" + HTTP_API_HOST + "/2010-04-01/Accounts/" + ACCOUNT_SID + "/Calls/" + paramSid + "." + theType;
        // System.out.println("+ Request: '" + theRequest);
        CredentialsProvider credsProvider = new BasicCredentialsProvider();
        credsProvider.setCredentials(
                new AuthScope(HTTP_API_HOST, HTTPS_PORT),
                new UsernamePasswordCredentials(ACCOUNT_SID, AUTH_TOKEN));
        return HTTPS_REQUEST.doGet(theRequest, credsProvider);
    }

    public String reqQueueList(String theType) throws Exception {
        String theRequest = "https://" + HTTP_API_HOST + "/2010-04-01/Accounts/" + ACCOUNT_SID + "/Queues." + theType;
        // System.out.println("+ Request: '" + theRequest);
        CredentialsProvider credsProvider = new BasicCredentialsProvider();
        credsProvider.setCredentials(
                new AuthScope(HTTP_API_HOST, HTTPS_PORT),
                new UsernamePasswordCredentials(ACCOUNT_SID, AUTH_TOKEN));
        return HTTPS_REQUEST.doGet(theRequest, credsProvider);
    }

    public String reqQueueCreate(String paramName, String theType) throws Exception {
        String theRequest = "https://" + HTTP_API_HOST + "/2010-04-01/Accounts/" + ACCOUNT_SID + "/Queues." + theType;
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("FriendlyName", paramName));
        CredentialsProvider credsProvider = new BasicCredentialsProvider();
        credsProvider.setCredentials(
                new AuthScope(HTTP_API_HOST, HTTPS_PORT),
                new UsernamePasswordCredentials(ACCOUNT_SID, AUTH_TOKEN));
        return HTTPS_REQUEST.doPost(theRequest, params, credsProvider);
    }
    
    public String reqLookupCarrier(String paramPhoneNumber) throws Exception {
        // ---------------------------------------------------------------------------
        //    Type=carrier : Carrier information costs $0.005 
        // or Type=caller-name : $0.01 per phone number looked up (only in US)
        // https://ACxxxxxx:xxxxxx@lookups.twilio.com/v1/PhoneNumbers/15555551234?Type=carrier
        String theRequest = "https://" + HTTP_LOOKUP_HOST + "/v1/PhoneNumbers/+" + URLEncoder.encode(paramPhoneNumber) + "?Type=carrier";
        // System.out.println("+ Request: '" + theRequest);
        CredentialsProvider credsProvider = new BasicCredentialsProvider();
        credsProvider.setCredentials(
                new AuthScope(HTTP_LOOKUP_HOST, HTTPS_PORT),
                new UsernamePasswordCredentials(ACCOUNT_SID, AUTH_TOKEN));
        return HTTPS_REQUEST.doGet(theRequest, credsProvider);
    }
    public String reqLookupCarrierFree(String paramPhoneNumber) throws Exception {
        // ---------------------------------------------------------------------------
        //    Type=carrier : Carrier information costs $0.005 
        // or Type=caller-name : $0.01 per phone number looked up (only in US)
        // https://ACxxxxxx:xxxxxx@lookups.twilio.com/v1/PhoneNumbers/15555551234?Type=carrier
        String theRequest = "https://" + HTTP_LOOKUP_HOST + "/v1/PhoneNumbers/+" + URLEncoder.encode(paramPhoneNumber);
        // System.out.println("+ Request: '" + theRequest);
        CredentialsProvider credsProvider = new BasicCredentialsProvider();
        credsProvider.setCredentials(
                new AuthScope(HTTP_LOOKUP_HOST, HTTPS_PORT),
                new UsernamePasswordCredentials(ACCOUNT_SID, AUTH_TOKEN));
        return HTTPS_REQUEST.doGet(theRequest, credsProvider);
    }

     public String reqAccPhoneList(String theType) throws Exception {
        // https://api.twilio.com/2010-04-01/Accounts/your_account_SID/IncomingPhoneNumbers.json
        String theRequest = "https://" + HTTP_API_HOST + "/2010-04-01/Accounts/" + ACCOUNT_SID + "/IncomingPhoneNumbers." + theType;
        // System.out.println("+ Request: '" + theRequest);
        CredentialsProvider credsProvider = new BasicCredentialsProvider();
        credsProvider.setCredentials(
                new AuthScope(HTTP_API_HOST, HTTPS_PORT),
                new UsernamePasswordCredentials(ACCOUNT_SID, AUTH_TOKEN));
        return HTTPS_REQUEST.doGet(theRequest, credsProvider);
    }

     public String reqSmsMediaList(String theSid) throws Exception {
        String theRequest = "https://" + HTTP_API_HOST + "/2010-04-01/Accounts/" + ACCOUNT_SID + "/Messages/" + theSid + "/Media.json";
        // System.out.println("+ Request: '" + theRequest);
        CredentialsProvider credsProvider = new BasicCredentialsProvider();
        credsProvider.setCredentials(
                new AuthScope(HTTP_API_HOST, HTTPS_PORT),
                new UsernamePasswordCredentials(ACCOUNT_SID, AUTH_TOKEN));
        return HTTPS_REQUEST.doGet(theRequest, credsProvider);
    }
     public String reqSmsList(String FromTo, String fromPhoneNumber, String theType) throws Exception {
        String theRequest = "https://" + HTTP_API_HOST + "/2010-04-01/Accounts/" + ACCOUNT_SID + "/Messages." + theType;
        if ( !(fromPhoneNumber.equalsIgnoreCase("+all")) ) {
            theRequest = theRequest + "?" + URLEncoder.encode(FromTo) +"=" + URLEncoder.encode(fromPhoneNumber);
        }
        // &DateSent=2017-09-09T17%3A00%3A00-07%3A00'
        // &DateSent=2017-09-09T17:00:00-07:00' --- or --- DateTime.parse("2017-02-22")
        System.out.println("+ Request: '" + theRequest);
        CredentialsProvider credsProvider = new BasicCredentialsProvider();
        credsProvider.setCredentials(
                new AuthScope(HTTP_API_HOST, HTTPS_PORT),
                new UsernamePasswordCredentials(ACCOUNT_SID, AUTH_TOKEN));
        return HTTPS_REQUEST.doGet(theRequest, credsProvider);
    }
     public String reqSmsListFromTo(String fromPhoneNumber, String toPhoneNumber, String theType) throws Exception {
        String requestUrl = SMS_REQUEST_URL  + theType + "?From="+URLEncoder.encode(fromPhoneNumber) + "&To="+URLEncoder.encode(toPhoneNumber);
        System.out.println("+ Request: '" + requestUrl);
        CredentialsProvider credsProvider = new BasicCredentialsProvider();
        credsProvider.setCredentials(
                new AuthScope(HTTP_API_HOST, HTTPS_PORT),
                new UsernamePasswordCredentials(ACCOUNT_SID, AUTH_TOKEN));
        return HTTPS_REQUEST.doGet(requestUrl, credsProvider);
    }

     public String reqSmsSend(String fromPhoneNumber, String toPhoneNumber, String paramMessage, String theType) throws Exception {
        String theRequest = "https://" + HTTP_API_HOST + "/2010-04-01/Accounts/" + ACCOUNT_SID + "/Messages." + theType;
        List<NameValuePair> params = new ArrayList<>();
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
        String theRequest = "https://" + HTTP_API_HOST + "/2010-04-01/Accounts/" + ACCOUNT_SID + "/Calls.json";
        // System.out.println("+ Request: '" + theRequest);
        
        List<NameValuePair> params = new ArrayList<>();
        // https://www.twilio.com/docs/api/rest/making-calls
        params.add(new BasicNameValuePair("From", fromPhoneNumber));
        params.add(new BasicNameValuePair("To", toPhoneNumber));
        params.add(new BasicNameValuePair("Url", paramUrl));        // Example: "http://example.com/twilio/z-ReadMyKeatsPoem.xml"
        params.add(new BasicNameValuePair("answerOnBridge", "true"));
        // Option: params.add(new BasicNameValuePair("StatusCallback", "http://www.example.com/python/twEchoReq"));
        // Option: params.add(new BasicNameValuePair("Record", "true"));
        // Option: params.add(new BasicNameValuePair("RecordingStatusCallback", "http://www.example.com/python/twEchoReq"));

        CredentialsProvider credsProvider = new BasicCredentialsProvider();
        credsProvider.setCredentials(
                new AuthScope(HTTP_API_HOST, HTTPS_PORT),
                new UsernamePasswordCredentials(ACCOUNT_SID, AUTH_TOKEN));

        return HTTPS_REQUEST.doPost(theRequest, params, credsProvider);
    }

    public static void main(String[] args) {
        System.out.println("+++ Start.");
        
        PrintJson doPrint = new PrintJson();
        twilioRequests twilioRequest = new twilioRequests();
        System.out.println("--------------------------------------------");
        String theResponse;
        try {
            theResponse = twilioRequest.reqLookupCarrierFree(System.getenv("PHONE_NUMBER1"));
            System.out.println("+ Response:\n" + theResponse + "\n:end.");
            System.out.println("+ Response cost: free:");
            doPrint.pretty(theResponse);
        } catch (Exception ex) {
            System.out.println("- Error:" + ex);
        }
        
        System.out.println("+++ Completed.");
    }

}
