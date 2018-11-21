<?php

class HTTPRequester {

    public static function HTTPPost($AccountSid, $url, array $params) {
        $query = http_build_query($params);
        $ch = curl_init();
        curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
        curl_setopt($ch, CURLOPT_HEADER, 1);
        curl_setopt($ch, CURLOPT_USERPWD, getenv('ACCOUNT_SID') . ":" . getenv('AUTH_TOKEN'));
        curl_setopt($ch, CURLOPT_URL, $url);
        curl_setopt($ch, CURLOPT_POST, true);
        curl_setopt($ch, CURLOPT_POSTFIELDS, $query);
        $response = curl_exec($ch);
        curl_close($ch);
        return $response;
    }

}

echo '+++ Start.';

$via = "sms";
$authyApiKey = getenv("AUTHY_API_KEY");
$authyPhoneNumber = getenv("AUTHY_PHONE_NUMBER1");
$authyCountryCode = getenv('AUTHY_PHONE_COUNTRYCODE');
//
$url = "https://api.authy.com/protected/json/phones/verification/start?api_key={$authyApiKey}";
$data = array('via' => $via, 'country_code' => $authyCountryCode, 'phone_number' => $authyPhoneNumber);
echo "\xA++ URL Request: ", $url;
$http = new HTTPRequester();
$response = $http->HTTPPost($AccountSid, $url, $data);
echo "\xA+ Response: {$response}";

echo "\xA+++ Exit.\xA";
?>