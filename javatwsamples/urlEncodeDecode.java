package javatwsamples;

import java.net.URLDecoder;
import java.net.URLEncoder;

public class urlEncodeDecode {
    
    public static void main(String[] args) {

        String aString = "ToCountry=US&ToState=CA&SmsMessageSid=SM60566ee207e123ee2765abcc55c9b9c9&NumMedia=0&ToCity=SAN+BRUNO&FromZip=94030&SmsSid=SM60566ee207e123ee2765abcc55c9b9c9&FromState=CA&SmsStatus=received&FromCity=SAN+BRUNO&Body=hi+there%2C+2%2B3%3D4&FromCountry=US&To=%2B16505552222&ToZip=94030&NumSegments=1&ReferralNumMedia=0&MessageSid=SM60566ee207e123ee2765abcc55c9b9c9&AccountSid=ACa48237447837494873949a3&From=%2B16505551111&ApiVersion=2010-04-01";
        String bString = URLEncoder.encode(aString);
        String cString = URLDecoder.decode(bString);
        System.out.println("+++ Start.");
        System.out.println("+ aString :" + aString + ":");
        System.out.println("+ bString :" + bString + ":");
        System.out.println("+ cString :" + cString + ":");
        System.out.println("+++ Exit.");
    }

}
