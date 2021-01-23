/*
 * Twilio: Send an SMS.
 * Author: Stacy David
 */
package messaging;

import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.message.BasicNameValuePair;

public class SmsSendHttp {

    private static final String THECLASSNAME = "TwSendSmsMsg";

    private static final String PARAM_FROM_PHONENUMBER = System.getenv("PHONE_NUMBER1");
    private static final String PARAM_TO_PHONENUMBER = System.getenv("PHONE_NUMBER2");
    private static final String PARAM_MESSAGE = "Hello from " + THECLASSNAME;
    private static final String PARAM_RESPONSE_TYPE = "xml";    // <json|xml>

    private static final String ACCOUNT_SID = System.getenv("ACCOUNT_SID");
    private static final String AUTH_TOKEN = System.getenv("AUTH_TOKEN");

    private static final HttpCalls HTTPS_REQUEST = new HttpCalls();
    private static final String HTTP_API_HOST = "api.twilio.com";
    private static final int HTTPS_PORT = 443;

    public static String reqSendSmsMsg(String fromPhoneNumber, String toPhoneNumber, String paramMessage, String theType) throws Exception {
        String theRequest = "https://" + HTTP_API_HOST + "/2010-04-01/Accounts/" + ACCOUNT_SID + "/Messages." + theType;
        System.out.println("+ Request: '" + theRequest);
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

    public static void main(String[] args) throws Exception {

        System.out.println("+++ Start class: " + THECLASSNAME);
        System.out.println("");

        System.out.println("++ Request: Send SMS");
        System.out.println("+ Parameters:");
        System.out.println("+ Phone Number, FROM: " + PARAM_FROM_PHONENUMBER);
        System.out.println("+ Phone Number, TO:   " + PARAM_TO_PHONENUMBER);
        System.out.println("+ Message:            " + PARAM_MESSAGE);
        String theResponse = reqSendSmsMsg(PARAM_FROM_PHONENUMBER, PARAM_TO_PHONENUMBER, PARAM_MESSAGE, PARAM_RESPONSE_TYPE);
        System.out.println("+ Response:");
        // PrintXml doPrint = new PrintXml();
        // doPrint.pretty(theResponse);
        System.out.println(theResponse);

        System.out.println("");
        System.out.println("+++ Exit class: " + THECLASSNAME);
    }
}
