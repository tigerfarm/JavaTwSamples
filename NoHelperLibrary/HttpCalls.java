package NoHelperLibrary;

import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class HttpCalls {

    private static final String THECLASSNAME = "HttpCalls";

    public String doGet(String theUrl) throws Exception {
        String theResponse;
        try (CloseableHttpClient httpclient = HttpClients.custom()
                .build()) {
            HttpGet httpcall = new HttpGet(theUrl);
            try (CloseableHttpResponse response = httpclient.execute(httpcall)) {
                theResponse = response.getStatusLine().toString();
                System.out.println("+ Response status: " + theResponse);
                theResponse = EntityUtils.toString(response.getEntity());
            }
        }
        return theResponse;
    }

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

    /* Save Binary
File myFile = new File("mystuff.bin");
CloseableHttpClient client = HttpClients.createDefault();
try (CloseableHttpResponse response = client.execute(new HttpGet("http://host/stuff"))) {
    HttpEntity entity = response.getEntity();
    if (entity != null) {
        try (FileOutputStream outstream = new FileOutputStream(myFile)) {
            entity.writeTo(outstream);
        }
    }
}
    */
    public String doPost(String theUrl, List<NameValuePair> params) throws Exception {
        String theResponse;
        try (CloseableHttpClient httpclient = HttpClients.custom()
                .build()) {
            HttpPost httpcall = new HttpPost(theUrl);
            httpcall.setEntity(new UrlEncodedFormEntity(params));
            //System.out.println("+ The request:     " + httpcall.getRequestLine());
            UrlEncodedFormEntity aString = new UrlEncodedFormEntity(params);
            // System.out.println("+ Headings:" + aString.toString());
            // System.out.println("+ Params:" + params.toString());
            try (CloseableHttpResponse response = httpclient.execute(httpcall)) {
                theResponse = response.getStatusLine().toString();
                System.out.println("+ Response status: " + theResponse);
                theResponse = EntityUtils.toString(response.getEntity());
            }
        }
        return theResponse;
    }
 
    public String doPost(String theUrl, List<NameValuePair> params, CredentialsProvider credsProvider) throws Exception {
        String theResponse;
        try (CloseableHttpClient httpclient = HttpClients.custom()
                .setDefaultCredentialsProvider(credsProvider)
                .build()) {
            HttpPost httpcall = new HttpPost(theUrl);
            httpcall.setEntity(new UrlEncodedFormEntity(params));
            //System.out.println("+ The request:     " + httpcall.getRequestLine());
            UrlEncodedFormEntity aString = new UrlEncodedFormEntity(params);
            // System.out.println("+ Headings:" + aString.toString());
            // System.out.println("+ Params:" + params.toString());
            try (CloseableHttpResponse response = httpclient.execute(httpcall)) {
                theResponse = response.getStatusLine().toString();
                System.out.println("+ Response status: " + theResponse);
                theResponse = EntityUtils.toString(response.getEntity());
            }
        }
        return theResponse;
    }

    public static void main(String[] args) {
        System.out.println("+++ Start class: " + THECLASSNAME);

        String theRequest;
        HttpCalls doHttpCalls = new HttpCalls();
        
        // GET a text file
        System.out.println("----------------------------------------");
        theRequest = System.getenv("HELLO_REQUEST_URL");
        System.out.println("+ GET Request: '" + theRequest);
        try {
            System.out.println("+ Response:\n" + doHttpCalls.doGet(theRequest) + "\n:end.");
        } catch (Exception ex) {
            System.out.println("- Error:" + ex);
        }

        // Echo POST values
        System.out.println("----------------------------------------");
        theRequest = System.getenv("ECHO_REQUEST_URL");
        System.out.println("++ Parameters:");
        String param1 = "hello";
        String param2 = "there";
        System.out.println("+ param1: " + param1);
        System.out.println("+ param2: " + param2);
        List<NameValuePair> paramsHello = new ArrayList<>();
        // https://www.twilio.com/docs/api/rest/making-calls
        paramsHello.add(new BasicNameValuePair("param1", param1));
        paramsHello.add(new BasicNameValuePair("param2", param2));
        System.out.println("+ POST Request: '" + theRequest);
        try {
            System.out.println("+ Response:\n" + doHttpCalls.doPost(theRequest, paramsHello) + "\n:end.");
        } catch (Exception ex) {
            System.out.println("- Error:" + ex);
        }

        // Authy POST
        System.out.println("----------------------------------------");
        theRequest = System.getenv("ECHO_REQUEST_URL");
        String paramCountryCode = System.getenv("AUTHY_PHONE_COUNTRYCODE");
        String paramPhonenumber = System.getenv("AUTHY_PHONE_NUMBER1");
        String paramEmailAddress = System.getenv("AUTHY_ID_EMAIL");
        System.out.println("++ Parameters:");
        System.out.println("+ Phone's Country Code:   " + paramCountryCode);
        System.out.println("+ Phone Number:           " + paramPhonenumber);
        List<NameValuePair> params = new ArrayList<>();
        // https://www.twilio.com/docs/api/rest/making-calls
        params.add(new BasicNameValuePair("user[country_code]", paramCountryCode));
        params.add(new BasicNameValuePair("user[cellphone]", paramPhonenumber));
        params.add(new BasicNameValuePair("user[email]", paramEmailAddress));
        String API_KEY = System.getenv("AUTHY_API_KEY");
        theRequest = theRequest + "?api_key=" + API_KEY;
        System.out.println("+ POST Request: '" + theRequest);
        try {
            System.out.println("+ Response:\n" + doHttpCalls.doPost(theRequest, params) + "\n:end.");
        } catch (Exception ex) {
            System.out.println("- Error:" + ex);
        }

        System.out.println("+++ Exit class: " + THECLASSNAME);
    }
}
