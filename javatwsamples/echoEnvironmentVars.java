/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javatwsamples;

/**
 *
 * @author dthurston
 */
public class echoEnvironmentVars {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("+++ Java Environment Variables.");
        System.out.println("+ Account ACCOUNT_SID:       " + System.getenv("ACCOUNT_SID"));
        System.out.println("+ Account AUTH_TOKEN:        " + System.getenv("AUTH_TOKEN"));
        System.out.println("+ ------------------");
        System.out.println("+ Messaging Service SID:     " + System.getenv("MESSAGING_SERVICE_SID"));        
        System.out.println("+ Notify Service SID:        " + System.getenv("NOTIFY_SERVICE_SID"));        
        System.out.println("+ ------------------");
        System.out.println("+ TaskRouter, WORKSPACE_SID: " + System.getenv("WORKSPACE_SID"));        
        System.out.println("+ ------------------");
        System.out.println("+ PHONE_NUMBER1:              " + System.getenv("PHONE_NUMBER1"));
        System.out.println("+ PHONE_NUMBER2:              " + System.getenv("PHONE_NUMBER2"));
        System.out.println("+ PHONE_NUMBER3:              " + System.getenv("PHONE_NUMBER3"));
        System.out.println("+ PHONE_NUMBER4:              " + System.getenv("PHONE_NUMBER4"));
        System.out.println("+ ------------------");
        System.out.println("+ AUTHY_ID:                   " + System.getenv("AUTHY_ID"));
        System.out.println("+ AUTHY_ID_EMAIL:             " + System.getenv("AUTHY_ID_EMAIL"));
        System.out.println("+ AUTHY_API_KEY:              " + System.getenv("AUTHY_API_KEY"));
        System.out.println("+ AUTHY_PHONE_COUNTRYCODE:    " + System.getenv("AUTHY_PHONE_COUNTRYCODE"));
        System.out.println("+ AUTHY_PHONE_NUMBER1:        " + System.getenv("AUTHY_PHONE_NUMBER1"));
        System.out.println("++ Exit.");
    }
    
}
