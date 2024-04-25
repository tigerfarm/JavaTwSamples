/*
 * Twilio: Send an SMS without using Twilio helper library
 * Author: Stacy David
 */
package NoHelperLibrary;

import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class SmsSendPost {

    private static final String ACCOUNT_SID = System.getenv("ACCOUNT_SID");
    private static final String AUTH_TOKEN = System.getenv("AUTH_TOKEN");
    private static final String HTTP_API_HOST = "api.twilio.com";
    private static final int HTTPS_PORT = 443;

    private static final String THECLASSNAME = "SmsSendPost";
    private static final String PARAM_FROM_PHONENUMBER = System.getenv("PHONE_NUMBER1");
    private static final String PARAM_TO_PHONENUMBER = System.getenv("PHONE_NUMBER3");
    private static final String PARAM_MESSAGE = "Test 3, from Stacy";
    private static final String PARAM_RESPONSE_TYPE = "json";    // HTTP response type: <json|xml>

    private static String doPost(String theUrl, List<NameValuePair> params, CredentialsProvider credsProvider) throws Exception {
        String theResponse;
        try ( CloseableHttpClient httpclient = HttpClients.custom()
                .setDefaultCredentialsProvider(credsProvider)
                .build()) {
            HttpPost httpcall = new HttpPost(theUrl);
            httpcall.setEntity(new UrlEncodedFormEntity(params));
            System.out.println("+ theUrl request:     " + theUrl);
            System.out.println("+ The request:   " + httpcall.getRequestLine());
            UrlEncodedFormEntity aString = new UrlEncodedFormEntity(params);
            // System.out.println("+ Headings:" + aString.toString());
            System.out.println("+ Params:" + params.toString());
            try ( CloseableHttpResponse response = httpclient.execute(httpcall)) {
                theResponse = response.getStatusLine().toString();
                System.out.println("+ Response status: " + theResponse);
                theResponse = EntityUtils.toString(response.getEntity());
            }
        }
        return theResponse;
    }

    private static String reqSmsSend(String fromPhoneNumber, String toPhoneNumber, String paramMessage, String theType) throws Exception {
        String theRequest = "https://" + HTTP_API_HOST + "/2010-04-01/Accounts/" + ACCOUNT_SID + "/Messages." + theType;
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("From", fromPhoneNumber));
        params.add(new BasicNameValuePair("To", toPhoneNumber));
        params.add(new BasicNameValuePair("Body", paramMessage));
        System.out.println("++ params: " + params);
        CredentialsProvider credsProvider = new BasicCredentialsProvider();
        credsProvider.setCredentials(
                new AuthScope(HTTP_API_HOST, HTTPS_PORT),
                new UsernamePasswordCredentials(ACCOUNT_SID, AUTH_TOKEN));
        //
        // Override the URL for testing:
        // theRequest = "https://echo.herokuapp.com/echo/";
        //
        return doPost(theRequest, params, credsProvider);
    }

    private static void printMessageLog(String theResponse) throws Exception {
        JSONObject theJsonResponse;
        try {
            theJsonResponse = new JSONObject(theResponse);
        } catch (JSONException e) {
            System.out.println("-- Failed to parse JSON response.");
            return;
        }
        System.out.println("++ List: from, to, date, status, message");
        System.out.println("+ "
                + theJsonResponse.getString("from")
                + ", " + theJsonResponse.getString("to")
                + ", " + theJsonResponse.getString("date_updated")
                + ", " + theJsonResponse.getString("status")
                + ", " + theJsonResponse.getString("body")
        );
    }

    public static void main(String[] args) throws Exception {

        System.out.println("+++ Start class: " + THECLASSNAME);
        System.out.println("");

        System.out.println("++ Request: Send SMS");
        System.out.println("+ Parameters:");
        System.out.println("+ Phone Number, FROM: " + PARAM_FROM_PHONENUMBER);
        System.out.println("+ Phone Number, TO:   " + PARAM_TO_PHONENUMBER);
        System.out.println("+ Message:            " + PARAM_MESSAGE);
        String theResponse = reqSmsSend(PARAM_FROM_PHONENUMBER, PARAM_TO_PHONENUMBER, PARAM_MESSAGE, PARAM_RESPONSE_TYPE);
        // System.out.println("+ Response:");
        // PrintJson doPrint = new PrintJson();
        // doPrint.pretty(theResponse);
        printMessageLog(theResponse);

        System.out.println("");
        System.out.println("+++ Exit class: " + THECLASSNAME);
    }

}
