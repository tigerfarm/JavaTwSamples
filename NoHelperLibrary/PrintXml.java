/*
 * Author: Stacy David Thurston
 */
package NoHelperLibrary;

public class PrintXml {

    public void pretty(String xmlData) {
        int si;
        int ei;
        int noDataItem;
        int endDataItem;
        int nextDataItem;
        int responseItem;
        String tabString = "  ";
        int tabStringLength = tabString.length();
        String indentString = "";

        // System.out.println("++ Print: " + xmlData); 
        si = xmlData.indexOf("<", 0);
        ei = xmlData.indexOf(">", 0);
        if (si < 0 || ei < 0) {
            System.out.println("-- Error: response is Not XML data.");
            System.out.println("+ Response text: " + xmlData + "...");
            return;
        }
        while (ei > 0) {
            noDataItem = xmlData.indexOf("/>", si);
            responseItem = xmlData.indexOf("<?", si);
            endDataItem = xmlData.indexOf("</", si);
            nextDataItem = xmlData.indexOf("<", ei + 1);
            // System.out.println("si="+si+", ei="+ei+", endDataItem="+endDataItem + ", nextDataItem="+nextDataItem); 
            if (ei == noDataItem + 1) {
                // Case, no data in the item: <soapenv:Header/> 
                System.out.println(indentString + xmlData.substring(si, ei + 1));
            } else if (responseItem == 0) {
                // Case, response heading: <?xml version='1.0' encoding='UTF-8'?> 
                System.out.println(indentString + xmlData.substring(si, ei + 1));
            } else if (si == endDataItem) {
                // Case, data item: ></v2:values> 
                indentString = indentString.substring(0, indentString.length() - tabStringLength);
                System.out.println(indentString + xmlData.substring(si, ei + 1));
            } else if (ei == nextDataItem - 1) {
                // Case, data item: <soapenv:Body><v2:runReport> 
                System.out.println(indentString + xmlData.substring(si, ei + 1));
                indentString += tabString;
            } else {
                // Case, data item: <v2:attributeFormat>csv</v2:attributeFormat> 
                ei = xmlData.indexOf(">", nextDataItem);
                System.out.println(indentString + xmlData.substring(si, ei + 1));
            }
            si = ei + 1;
            ei = xmlData.indexOf(">", si);
        }
    }

    public static void main(String[] args) throws Exception {

        final String THECLASSNAME = "PrintXml";

        System.out.println("+++ Start class: " + THECLASSNAME);
        System.out.println("");

        System.out.println("++ Print the SOAP XML request string.");
        String soapEnvBody = ""
                + "<soap:findFlight>"
                + "<destination>LAX</destination>"
                + "</soap:findFlight>";
        String namespacePrefix = "soap";
        String xmlRequest = ""
                + "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:" + namespacePrefix + "=\"http://soap.training.example.com/" + "\">"
                + "<soapenv:Header/>"
                + "<soapenv:Body>"
                + soapEnvBody
                + "</soapenv:Body>"
                + "</soapenv:Envelope>";
        PrintXml doPrintXml = new PrintXml();
        // doPrintXml.pretty(xmlRequest);
        doPrintXml.pretty("<?xml version='1.0' encoding='UTF-8'?>\n" +
"<TwilioResponse><IncomingPhoneNumbers end=\"4\" firstpageuri=\"/2010-04-01/Accounts/ACxxxxxx/IncomingPhoneNumbers?PageSize=50&amp;Page=0\" nextpageuri=\"\" page=\"0\" pagesize=\"50\" previouspageuri=\"\" start=\"0\" uri=\"/2010-04-01/Accounts/ACxxxxxx/IncomingPhoneNumbers?PageSize=50&amp;Page=0\"><IncomingPhoneNumber><Sid>PNxxxxx</Sid><AccountSid>ACxxxxxx</AccountSid><FriendlyName>555555123</FriendlyName><PhoneNumber>+11231231234</PhoneNumber><VoiceUrl></VoiceUrl><VoiceMethod>POST</VoiceMethod><VoiceFallbackUrl/><VoiceFallbackMethod>POST</VoiceFallbackMethod><VoiceCallerIdLookup>false</VoiceCallerIdLookup><DateCreated>Tue, 02 May 2017 01:35:22 +0000</DateCreated><DateUpdated>Mon, 15 May 2017 20:33:26 +0000</DateUpdated><SmsUrl>https://demo.twilio.com/welcome/sms/reply/</SmsUrl><SmsMethod>POST</SmsMethod><SmsFallbackUrl></SmsFallbackUrl><SmsFallbackMethod>POST</SmsFallbackMethod><AddressRequirements>none</AddressRequirements><Beta>false</Beta><Capabilities><Voice>false</Voice><SMS>true</SMS><MMS>false</MMS><Fax>false</Fax></Capabilities><VoiceReceiveMode>voice</VoiceReceiveMode><StatusCallback></StatusCallback><StatusCallbackMethod>POST</StatusCallbackMethod><ApiVersion>2010-04-01</ApiVersion><VoiceApplicationSid/><SmsApplicationSid></SmsApplicationSid><Origin>twilio</Origin><TrunkSid/><EmergencyStatus>Inactive</EmergencyStatus><EmergencyAddressSid/><Uri>/2010-04-01/Accounts/ACxxxxxx/IncomingPhoneNumbers/PNxxxxxx</Uri></IncomingPhoneNumber><IncomingPhoneNumber><Sid>PNxxxxxx</Sid><AccountSid>ACxxxxxx</AccountSid><FriendlyName>441303720099</FriendlyName><PhoneNumber>+15555550099</PhoneNumber><VoiceUrl>https://handler.twilio.com/twiml/EH4cb8bc326xxxxx20d</VoiceUrl><VoiceMethod>POST</VoiceMethod><VoiceFallbackUrl></VoiceFallbackUrl><VoiceFallbackMethod>POST</VoiceFallbackMethod><VoiceCallerIdLookup>false</VoiceCallerIdLookup><DateCreated>Tue, 02 May 2017 01:31:55 +0000</DateCreated><DateUpdated>Mon, 12 Jun 2017 17:26:02 +0000</DateUpdated><SmsUrl>http://www.example.com/python/twEchoFwd2Me</SmsUrl><SmsMethod>POST</SmsMethod><SmsFallbackUrl></SmsFallbackUrl><SmsFallbackMethod>POST</SmsFallbackMethod><AddressRequirements>none</AddressRequirements><Beta>false</Beta><Capabilities><Voice>true</Voice><SMS>true</SMS><MMS>false</MMS><Fax>true</Fax></Capabilities><VoiceReceiveMode>voice</VoiceReceiveMode><StatusCallback></StatusCallback><StatusCallbackMethod>POST</StatusCallbackMethod><ApiVersion>2010-04-01</ApiVersion><VoiceApplicationSid></VoiceApplicationSid><SmsApplicationSid></SmsApplicationSid><Origin>twilio</Origin><TrunkSid/><EmergencyStatus>Inactive</EmergencyStatus><EmergencyAddressSid/><Uri>/2010-04-01/Accounts/ACxxxxxx/IncomingPhoneNumbers/PNxxxxxx</Uri></IncomingPhoneNumber><IncomingPhoneNumber><Sid>PNxxxxxx</Sid><AccountSid>ACxxxxxx</AccountSid><FriendlyName>(818) 210-3863</FriendlyName><PhoneNumber>+18182103863</PhoneNumber><VoiceUrl>https://handler.twilio.com/twiml/EH8614fdce0367889cf57482e6a11259c0</VoiceUrl><VoiceMethod>POST</VoiceMethod><VoiceFallbackUrl>http://www.example.com/twilio/voice-PlayMP3-GatherDigits.xml</VoiceFallbackUrl><VoiceFallbackMethod>POST</VoiceFallbackMethod><VoiceCallerIdLookup>false</VoiceCallerIdLookup><DateCreated>Tue, 02 May 2017 01:05:45 +0000</DateCreated><DateUpdated>Mon, 12 Jun 2017 17:11:32 +0000</DateUpdated><SmsUrl>https://about-time-2357.twil.io/smsHelloWorld</SmsUrl><SmsMethod>POST</SmsMethod><SmsFallbackUrl>http://www.example.com/abc/def</SmsFallbackUrl><SmsFallbackMethod>POST</SmsFallbackMethod><AddressRequirements>none</AddressRequirements><Beta>false</Beta><Capabilities><Voice>true</Voice><SMS>true</SMS><MMS>true</MMS><Fax>true</Fax></Capabilities><VoiceReceiveMode>voice</VoiceReceiveMode><StatusCallback></StatusCallback><StatusCallbackMethod>POST</StatusCallbackMethod><ApiVersion>2010-04-01</ApiVersion><VoiceApplicationSid></VoiceApplicationSid><SmsApplicationSid></SmsApplicationSid><Origin>twilio</Origin><TrunkSid/><EmergencyStatus>Inactive</EmergencyStatus><EmergencyAddressSid/><Uri>/2010-04-01/Accounts/ACxxxxxx/IncomingPhoneNumbers/PNxxxxxx</Uri></IncomingPhoneNumber><IncomingPhoneNumber><Sid>PNxxxxxxx</Sid><AccountSid>ACxxxxxx</AccountSid><FriendlyName>(555) 555-6006</FriendlyName><PhoneNumber>+14555556006</PhoneNumber><VoiceUrl>https://handler.twilio.com/twiml/EH8ee35fbxxxxxx8</VoiceUrl><VoiceMethod>POST</VoiceMethod><VoiceFallbackUrl></VoiceFallbackUrl><VoiceFallbackMethod>POST</VoiceFallbackMethod><VoiceCallerIdLookup>false</VoiceCallerIdLookup><DateCreated>Fri, 02 Jun 2017 23:04:49 +0000</DateCreated><DateUpdated>Mon, 12 Jun 2017 19:48:34 +0000</DateUpdated><SmsUrl>https://handler.twilio.com/twiml/EH063ffxxxxxxxd</SmsUrl><SmsMethod>POST</SmsMethod><SmsFallbackUrl></SmsFallbackUrl><SmsFallbackMethod>POST</SmsFallbackMethod><AddressRequirements>none</AddressRequirements><Beta>false</Beta><Capabilities><Voice>true</Voice><SMS>true</SMS><MMS>true</MMS><Fax>true</Fax></Capabilities><VoiceReceiveMode>voice</VoiceReceiveMode><StatusCallback></StatusCallback><StatusCallbackMethod>POST</StatusCallbackMethod><ApiVersion>2010-04-01</ApiVersion><VoiceApplicationSid></VoiceApplicationSid><SmsApplicationSid></SmsApplicationSid><Origin>twilio</Origin><TrunkSid/><EmergencyStatus>Inactive</EmergencyStatus><EmergencyAddressSid/><Uri>/2010-04-01/Accounts/ACxxxxxx/IncomingPhoneNumbers/PNxxxxxx</Uri></IncomingPhoneNumber><IncomingPhoneNumber><Sid>PNxxxxxx</Sid><AccountSid>ACxxxxxx</AccountSid><FriendlyName>(555) 555-3911</FriendlyName><PhoneNumber>+11112221234</PhoneNumber><VoiceUrl>http://example.com/twilio/hello.xml</VoiceUrl><VoiceMethod>GET</VoiceMethod><VoiceFallbackUrl></VoiceFallbackUrl><VoiceFallbackMethod>POST</VoiceFallbackMethod><VoiceCallerIdLookup>false</VoiceCallerIdLookup><DateCreated>Tue, 02 May 2017 22:48:27 +0000</DateCreated><DateUpdated>Fri, 05 May 2017 21:56:19 +0000</DateUpdated><SmsUrl>https://handler.twilio.com/twiml/EHf1c5xxxxxx</SmsUrl><SmsMethod>POST</SmsMethod><SmsFallbackUrl></SmsFallbackUrl><SmsFallbackMethod>POST</SmsFallbackMethod><AddressRequirements>none</AddressRequirements><Beta>false</Beta><Capabilities><Voice>true</Voice><SMS>true</SMS><MMS>true</MMS><Fax>true</Fax></Capabilities><VoiceReceiveMode>voice</VoiceReceiveMode><StatusCallback></StatusCallback><StatusCallbackMethod>POST</StatusCallbackMethod><ApiVersion>2010-04-01</ApiVersion><VoiceApplicationSid></VoiceApplicationSid><SmsApplicationSid></SmsApplicationSid><Origin>twilio</Origin><TrunkSid/><EmergencyStatus>Inactive</EmergencyStatus><EmergencyAddressSid/><Uri>/2010-04-01/Accounts/ACxxxxxx/IncomingPhoneNumbers/PNxxxxxx</Uri></IncomingPhoneNumber></IncomingPhoneNumbers></TwilioResponse>");

        System.out.println("");
        System.out.println("+++ Exit class: " + THECLASSNAME);
    }

}
