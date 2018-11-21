 /*
Author: Stacy David
 */
package authyjavasamples.NoHelperLibrary;

public class AuthyPushAuth {

    private static final String THECLASSNAME = "AuthyPushAuth";
    
    // -------------------------------------------------------------------------
    private static final String PARAM_AUTHYID = System.getenv("AUTHY_ID");
    private static final String PARAM_MESSAGE = "Owl Publishing transaction authentication requested.";
    private static final String PARAM_SECONDS = "600";
    // -------------------------------------------------------------------------

    public static void main(String[] args) throws Exception {

        AuthyCalls doSend = new AuthyCalls();
        String theResponse;

        System.out.println("+++ Start class: " + THECLASSNAME);
        System.out.println("");
        System.out.println("++ Parameters:");
        System.out.println("+ Authy ID:          " + PARAM_AUTHYID);
        System.out.println("+ Message:           " + PARAM_MESSAGE);
        System.out.println("+ Seconds To Expire: " + PARAM_SECONDS);
        theResponse = doSend.reqPushAuth(PARAM_AUTHYID, PARAM_MESSAGE, PARAM_SECONDS);
        System.out.println("+ Response:");
        PrintJson doPrintJson = new PrintJson();
        doPrintJson.pretty(theResponse);

        System.out.println("");
        System.out.println("+++ Exit class: " + THECLASSNAME);
    }
}
