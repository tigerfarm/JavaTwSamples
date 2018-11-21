 /*
 * Methods to make requests to the Twilio Account Security API.
 * Docs: https://www.twilio.com/docs/api/authy
 * Author: Stacy David
 */
package authyjavasamples.NoHelperLibrary;

import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

public class AuthyCalls {

    // -------------------------------------------------------------------------
    private static final String AUTHY_API_KEY = System.getenv("AUTHY_API_KEY");
    // -------------------------------------------------------------------------

    private static final HttpCalls HTTPS_REQUEST = new HttpCalls();

    public String reqTokenSend(String param_AuthyId) throws Exception {
        String theResponse = "";
        String theRequest = "https://api.authy.com/protected/json/sms/"+param_AuthyId+"?api_key=" + AUTHY_API_KEY + "&force=true";
        // System.out.println("+ Request: '" + theRequest);
        try {
            theResponse = HTTPS_REQUEST.doGet(theRequest);
        } catch (Exception ex) {
            System.out.println("- Error:" + ex);
            return theResponse;
        }
        return theResponse;
    }

    public String reqTokenVerification(String param_AuthyId, String param_Token) throws Exception {
        String theResponse = "";
        String theRequest = "https://api.authy.com/protected/json/verify/"+param_Token+"/"+param_AuthyId+"?api_key=" + AUTHY_API_KEY;
        // System.out.println("+ Request: '" + theRequest);
        try {
            theResponse = HTTPS_REQUEST.doGet(theRequest);
        } catch (Exception ex) {
            System.out.println("- Error:" + ex);
            return theResponse;
        }
        return theResponse;
    }

    public String reqPhoneVerifySend(String paramVia, String paramCountryCode, String paramPhoneNumber) throws Exception {
        String theResponse = "";
        String theRequest = "https://api.authy.com/protected/json/phones/verification/start" + "?api_key=" + AUTHY_API_KEY;
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("via", paramVia));
        params.add(new BasicNameValuePair("country_code", paramCountryCode));
        params.add(new BasicNameValuePair("phone_number", paramPhoneNumber));
        System.out.println("+ Request: '" + theRequest);
        try {
            theResponse = HTTPS_REQUEST.doPost(theRequest, params);
        } catch (Exception ex) {
            System.out.println("- Error:" + ex);
            return theResponse;
        }
        return theResponse;
    }

    public String reqPhoneVerifyCheck(String paramVerificationCode, String PARAM_COUNTRYCODE, String PARAM_PHONENUMBER) throws Exception {
        String theResponse = "";
        String theRequest = "https://api.authy.com/protected/json/phones/verification/check?api_key=" + AUTHY_API_KEY
                + "&country_code=" + PARAM_COUNTRYCODE
                + "&phone_number=" + PARAM_PHONENUMBER
                + "&verification_code=" + paramVerificationCode;
        // String paramLocale = "es"; --> locale=es
        // System.out.println("+ Request: '" + theRequest);
        try {
            theResponse = HTTPS_REQUEST.doGet(theRequest);
        } catch (Exception ex) {
            System.out.println("- Error:" + ex);
            return theResponse;
        }
        return theResponse;
    }

    public String reqPushAuth(String param_AuthyId, String paramMessage, String paramSeconds) throws Exception {
        String theResponse = "";
        String theRequest = "https://api.authy.com/onetouch/json/users/"+param_AuthyId+"/approval_requests?api_key=" + AUTHY_API_KEY;
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("message", paramMessage));
        params.add(new BasicNameValuePair("seconds_to_expire", paramSeconds));
        // System.out.println("+ Request: '" + theRequest);
        try {
            theResponse = HTTPS_REQUEST.doPost(theRequest, params);
        } catch (Exception ex) {
            System.out.println("- Error:" + ex);
            return theResponse;
        }
        return theResponse;
    }

    public String reqPushCheck(String paramUuid) throws Exception {
        String theResponse = "";
        String theRequest = "https://api.authy.com/onetouch/json/approval_requests/"+paramUuid+"?api_key=" + AUTHY_API_KEY;
        // System.out.println("+ Request: '" + theRequest);
        try {
            theResponse = HTTPS_REQUEST.doGet(theRequest);
        } catch (Exception ex) {
            System.out.println("- Error:" + ex);
            return theResponse;
        }
        return theResponse;
    }

    public String reqUserAdd(String paramCountryCode, String paramPhoneNumber, String paramEmail) throws Exception {
        String theResponse = "";
        String theRequest = "https://api.authy.com/protected/json/users/new?api_key=" + AUTHY_API_KEY;
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("user[country_code]", paramCountryCode));
        params.add(new BasicNameValuePair("user[cellphone]", paramPhoneNumber));
        params.add(new BasicNameValuePair("user[email]", paramEmail));
        // System.out.println("+ Request: '" + theRequest);
        try {
            theResponse = HTTPS_REQUEST.doPost(theRequest, params);
        } catch (Exception ex) {
            System.out.println("- Error:" + ex);
            return theResponse;
        }
        return theResponse;
    }

    public String reqUserStatus(String paramAuthyId) throws Exception {
        String theResponse = "";
        String theRequest = "https://api.authy.com/protected/json/users/" + paramAuthyId + "/status?api_key=" + AUTHY_API_KEY;
        // System.out.println("+ Request: '" + theRequest);
        try {
            theResponse = HTTPS_REQUEST.doGet(theRequest);
        } catch (Exception ex) {
            System.out.println("- Error:" + ex);
            return theResponse;
        }
        return theResponse;
    }

    public String reqUserRemove(String paramAuthyId) throws Exception {
        String theResponse = "";
        String theRequest = "https://api.authy.com/protected/json/users/"+paramAuthyId+"/remove?api_key=" + AUTHY_API_KEY;
        List<NameValuePair> params = new ArrayList<>();
        // System.out.println("+ Request: '" + theRequest);
        try {
            theResponse = HTTPS_REQUEST.doPost(theRequest, params);
        } catch (Exception ex) {
            System.out.println("- Error:" + ex);
            return theResponse;
        }
        return theResponse;
    }

}
