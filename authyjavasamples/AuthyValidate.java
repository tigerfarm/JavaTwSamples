// To run:
// java -cp AuthyJavaSamples.jar authyjavasamples.AuthyJavaSamples
//
// Documentation: https://www.twilio.com/docs/authy/validation#verify-a-twilio-authy-callback
//
package authyjavasamples;

public class AuthyValidate {

    public static void main(String[] args) {
        System.out.print("+++ Authy Java Samples.");
        System.out.println("++ Validate Authy-Signature.");
        /*
        var nonce = _request.Headers["X-Authy-Signature-Nonce"];
        var url = string.Format(
                "{0}://{1}{2}", 
                _request.Url.Scheme, 
                _request.Headers["X-Original-Host"], 
                _request.Url.AbsolutePath
        );
        var data = string.Format("{0}|{1}|{2}|{3}", nonce, _request.HttpMethod, url, serialized);
        var digest = ComputeDigest(data, _apiKey);
        
        var authySignature = _request.Headers["X-Authy-Signature"];
        
        return digest == authySignature;
        */
        System.out.println("++ Exit.");
    }
}

