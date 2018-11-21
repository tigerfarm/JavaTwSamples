# Sample Twilio Account Security Java Programs

Java programs to send requests to the Twilio Account Security APIs: 2FA and Phone Verification.

## Quick Test

Download this project's zip into a working directory and unzip it.
Edit authVars.sh to have your values.
Run the following commands.
The first command (source authVars.sh) sets your environment variables that the Java programs will use.
The next command (PhoneVerification) sends an OTP to your mobile phone and then prompts you for the code. For test purposes, enter a incorrect value.
The third command (PhoneVerificationVerify) prompts for the OTP. Enter the correct code to see the correct result.
````
$ source authVars.sh
+++ Authy Java Samples.++ Echo environment variables:
+ PhoneVerificationService/main, SID:                     your_account_SID
+ PhoneVerificationService/main, AUTH_TOKEN:              your_account_auth_token
+ PhoneVerificationService/main, AUTHY_ID:                your_sample_authy_id(example: 430012345)
+ PhoneVerificationService/main, AUTHY_ID_EMAIL:          your_sample_authy_email_address
+ PhoneVerificationService/main, PHONE_NUMBER1:           your_account_phone_number1(example: +16501231111)
+ PhoneVerificationService/main, PHONE_NUMBER2:           your_account_phone_number2(example: +16501232222)
+ PhoneVerificationService/main, AUTHY_API_KEY:           your_account_authy_API_key
+ PhoneVerificationService/main, AUTHY_PHONE_COUNTRYCODE: your_country_code(example: +1)
+ PhoneVerificationService/main, AUTHY_PHONE_NUMBER1:     your_mobile_phone_number(example: 6501231234)
++ Exit.

$ java -cp AuthyJavaSamples.jar authyjavasamples.PhoneVerification
+++ Start.
++ Send the one time passcode (OTP) to: 6501231234
+ sendOTP, success: Text message sent to +1 650-123-1234.
-------------------------
++ Enter the received OTP (or exit)> 3215
- logAndThrow, verifyOTP, Error: Verification code is incorrect

+++ Exit.

$ java -cp AuthyJavaSamples.jar authyjavasamples.PhoneVerificationVerify
+++ Start.
-------------------------
++ Enter the received OTP (or exit)> 3214
+ verifyOTP, success: Verification code is correct.

+++ Exit.
````

Note, authVars.sh runs the AuthyJavaSamples.java program which echos the environment values in authVars.sh.

## Reports

https://www.twilio.com/blog/2018/02/new-webhooks-and-reporting-for-twilio-authy-2fa-and-verify-phone-verification-apis.html

Samples, SMS events:
````
curl -g "https://api.authy.com/protected/json/reporting/events?query[objects.phone_verification.s_via][eq]=sms&query[time][gte]=2018-07-20T00:00:00.000Z" \
-H "X-Authy-API-Key: $REPORTING_AUTHY_API_KEY"
````
Started:
````
curl -g "https://api.authy.com/protected/json/reporting/date_histogram?report[phone_verification_started][event][eq]=phone_verification_started&interval=hour&page=1" \
-H "X-Authy-API-Key: $REPORTING_AUTHY_API_KEY"
````
Validated:
````
curl -g "https://api.authy.com/protected/json/reporting/date_histogram?report[phone_verification_code_is_valid][event][eq]=phone_verification_code_is_valid&interval=hour&page=$REPORTING_PAGE" \
-H "X-Authy-API-Key: $REPORTING_AUTHY_API_KEY"
````
Samples:

https://github.com/AuthySE/Authy-Reporting-Samples

## Getting Started with Development

To get started, start with Phone Verification:

    Create a Twilio account, a free Trial works fine (https://twilio.com/console).
    In the Authy section of the Twilio Console, follow the Get Started steps to make your account 2FA enabled, and enter an application (Friendly name) to create an API key.
    In your environment, set your Authy required variables.
    In your IDE, create a project.
    Include the Libraries:
       Java Helper Library: twilio-x.x.x-jar-with-dependencies.jar
          Download link from: https://www.twilio.com/docs/libraries/java
       Authy and JSON libraries: authy-java-1.2.0.jar and json-20150729.jar
          Download from: https://jar-download.com/explore-java-source-code.php?a=authy-java&g=com.authy&v=1.2.0&downloadable=1
    Include and run: AuthyJavaSamples.java. It will echo the environment variables that are used by Authy Java sample programs.
    Include and run: PhoneVerification.java. It will send an OTP and prompt you for the code to be verified.

When using NetBeans, use shell script that sets the variables then starts the IDE.
This insures the the environment variables are available for the programs that are run in the IDE.

````
$ cat ~/nb.sh 
export ACCOUNT_SID=your_account_sid
export AUTH_TOKEN=your_account_auth_token
export AUTHY_ID=sample_user_authy_id (example: 30012357)
export AUTHY_ID_EMAIL=sample_user_email_address
export AUTHY_PHONE_COUNTRYCODE=+1
export AUTHY_PHONE_NUMBER1=2223331234
export PHONE_NUMBER1=+12223331111
export PHONE_NUMBER2=+12223332222
"/Applications/NetBeans/NetBeans 8.2.app/Contents/Resources/NetBeans/bin/netbeans" &
````

You can check the program before running it, to know which variables are required, for which program.

## Authy Java Programs That Use the Authy Helper Library
````
PhoneVerification.java:
When it is run, it sends a phone verification OTP to: AUTHY_PHONE_COUNTRYCODE + AUTHY_PHONE_NUMBER1.
The phone receives the OTP.
In PhoneVerification.java, you are prompted to enter the passcode.
Enter the OTP. A request is sent to verify the entered passcode.
PhoneVerification.java receives and displays the result: valid or not.
````
````
PhoneVerificationVerify.java:
When it is run, you are prompted to enter a one time passcode (OTP) that was sent to: AUTHY_PHONE_COUNTRYCODE + AUTHY_PHONE_NUMBER1.
Enter the OTP. A request is sent to verify the entered passcode.
PhoneVerificationVerify.java receives and displays the result: valid or not.
````
SendMsg1.java is a sample program to send an SMS message for testing.

## Authy Java Programs Without Using the Authy Helper Library

Authy.java:
Set API_KEY to your Twilio Console Authy Application API key.
This is a class of methods to make requests to the Twilio Account Security API.

AuthyPhoneVerification.java:
Set PARAM_COUNTRYCODE and PARAM_PHONENUMBER to your mobile phone number.
Sends a request to the Phone Verification API.
Receives and displays the response.
Prompt and wait for the user to enter the passcode that was sent by the Twilio service to the person's phone.
The Twilio service receives the request, generates a passcode and sends it to the person's phone. 
After the passcode is entered into this program's prompt, a request is sent to verify the entered passcode.
Receives and displays the response. The response confirms the validity of the passcode.

AuthyPhoneVerificationCheck.java:
(Second part of AuthyPhoneVerification.java)
Prompt and wait for the user to enter the passcode that was sent by the Twilio service to the person's phone.
After the passcode is entered into this program's prompt, a request is sent to verify the entered passcode.
Receives and displays the response. The response confirms the validity of the passcode.

AuthyUserAdd.java:
Send a request to the Twilio Account Security API, to have a 2FA user added.

AuthyUserRemove.java:
Send a request to the Twilio Account Security API, to have a 2FA user removed.

AuthyUserStatus.java:
Send a request to the Twilio Account Security API, to get the status of a 2FA user.

AuthyPushAuth.java:
Send a Push Authentication notification request to a 2FA user, using the Twilio Account Security API.

AuthyPushCheck.java:
Send a request to check the status of a Push Authentication notification.

AuthyToken.java:
Send a request to the Twilio Account Security API, to have a token sent to a 2FA user's phone number.
Prompt for the token that was sent.
The Twilio Account Security service generates and sends a token to a 2FA user's phone.
After the token is entered into the prompt, this program send a request to verify the entered token.
The response confirms the validity of the token.

AuthyTokenCheck.java:
(Second part of AuthyToken.java)
Prompt for the token that was sent.
After the token is entered into the prompt, this program send a request to verify the entered token.
The response confirms the validity of the token.

TwLookup.java:
Send a request to get information about a phone number, any phone number in the world.

TwSendSmsMsg.java:
Send an SMS to a phone number, any phone number in the world.

TwCalls.java:
Set ACCOUNT_SID and AUTH_TOKEN to your Twilio Console SID and token.
This is a class of methods to make requests to Twilio Lookup and other APIs.

HttpCalls.java:
To send HTTP POST and GET requests.
Return the response.

PrintJson.java:
To pretty print JSON responses from the Twilio services.

PrintXml.java:
To pretty print XML responses from the Twilio services.
