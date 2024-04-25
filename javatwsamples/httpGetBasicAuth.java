/*
 * Author: Stacy David
 */
package javatwsamples;

import NoHelperLibrary.*;
import java.net.URLEncoder;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class httpGetBasicAuth {

    private static final String ACCOUNT_SID = System.getenv("MAIN_ACCOUNT_SID");
    private static final String AUTH_TOKEN = System.getenv("MAIN_AUTH_TOKEN");

    // private static final String SMS_REQUEST_URL = String.format("https://localhost:8000/2010-04-01/Accounts/%s/Messages.", ACCOUNT_SID);
    // private static final String SMS_REQUEST_URL = String.format("https://api.twilio.com/2010-04-01/Accounts/%s/Messages.", ACCOUNT_SID);
    private static final String HTTP_LOOKUP_HOST = "lookups.twilio.com";
    private static final String HTTP_API_HOST = "api.twilio.com";

    public String doGet(String theUrl, CredentialsProvider credsProvider) throws Exception {
        String theResponse;
        try (CloseableHttpClient httpclient = HttpClients.custom()
                .setDefaultCredentialsProvider(credsProvider)
                .build()) {
            HttpGet httpcall = new HttpGet(theUrl);
            try (CloseableHttpResponse response = httpclient.execute(httpcall)) {
                theResponse = response.getStatusLine().toString();
                if (theResponse.compareTo("HTTP/1.1 200 OK") != 0) {
                    System.out.println("-- Error Response status: " + theResponse);
                }
                theResponse = EntityUtils.toString(response.getEntity());
            }
        }
        return theResponse;
    }

    public static void main(String[] args) {
        String theResponse;
        String theRequest;
        System.out.println("+++ Start.");

        PrintJson doPrintJson = new PrintJson();
        httpGetBasicAuth twilioRequest = new httpGetBasicAuth();
        CredentialsProvider credsProvider = new BasicCredentialsProvider();
        CredentialsProvider credsProvider1 = new BasicCredentialsProvider();
        System.out.println("+ ACCOUNT_SID:AUTH_TOKEN '" + ACCOUNT_SID + ":" + AUTH_TOKEN + "'");
        System.out.println("--------------------------------------------");
        System.out.println("+ Lookup request, cost: free:");
        //    Type=carrier : Carrier information costs $0.005 
        // or Type=caller-name : $0.01 per phone number looked up (only in US)
        // https://ACxxxxxx:xxxxxx@lookups.twilio.com/v1/PhoneNumbers/15555551234?Type=carrier
        // String theRequest = "https://" + HTTP_LOOKUP_HOST + "/v1/PhoneNumbers/" + URLEncoder.encode(paramPhoneNumber);
        //
        theRequest = "https://" + HTTP_LOOKUP_HOST + "/v1/PhoneNumbers/" + URLEncoder.encode(System.getenv("PHONE_NUMBER1"));
        System.out.println("+ Request: '" + theRequest);
        credsProvider.setCredentials(
                new AuthScope(HTTP_LOOKUP_HOST, 443),
                new UsernamePasswordCredentials(ACCOUNT_SID, AUTH_TOKEN)
        );
        try {
            theResponse = twilioRequest.doGet(theRequest, credsProvider);
            // System.out.println("+ Response:\n" + theResponse + "\n:end.");
            doPrintJson.pretty(theResponse);
        } catch (Exception ex) {
            System.out.println("- Error:" + ex);
        }

        System.out.println("--------------------------------------------");
        System.out.println("+ Account phone numbers:");
        // https://api.twilio.com/2010-04-01/Accounts/your_account_SID/IncomingPhoneNumbers.json
        theRequest = "https://" + HTTP_API_HOST + "/2010-04-01/Accounts/" + ACCOUNT_SID + "/IncomingPhoneNumbers." + "json";
        credsProvider1.setCredentials(
                new AuthScope(HTTP_API_HOST, 443),
                new UsernamePasswordCredentials(ACCOUNT_SID, AUTH_TOKEN)
        );
        try {
            theResponse = twilioRequest.doGet(theRequest, credsProvider1);
            doPrintJson.pretty(theResponse);
        } catch (Exception ex) {
            System.out.println("- Error:" + ex);
        }

        System.out.println("+++ Completed.");
    }

}
