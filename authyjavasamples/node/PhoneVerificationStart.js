// https://www.npmjs.com/package/request

console.log("+++ Send SMS phone verification request to Twilio.");

console.log("+ api_key: " + process.env.AUTHY_API_KEY
        + ", country_code: " + process.env.AUTHY_PHONE_COUNTRYCODE,
        + ", phone_number: " + process.env.AUTHY_PHONE_NUMBER1);

var request = require('request');
API_KEY = "Vye28fVRU1Bo85BISTENwp1klE5a7tir";
param_AuthyId = "40023285";
theRequest = "https://api.authy.com/onetouch/json/users/" + param_AuthyId + "/approval_requests?api_key=" + API_KEY;
var theParameters = 'message=Tiger Farm Press approval requested.';
console.log('+ URL request: ' + theRequest);

request({
    url: theRequest + "&" + theParameters,
    method: "POST"
}, function (error, response, body) {
    console.log(body);
});