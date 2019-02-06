/*
    SMS Pricing

    Documentation: https://www.twilio.com/docs/sms/api/pricing
    NumberType: mobile, local, shortcode, or toll free
*/
package messaging;

import com.twilio.Twilio;
import com.twilio.rest.pricing.v1.messaging.Country;
import com.twilio.type.InboundSmsPrice;
import com.twilio.type.OutboundSmsPrice;

public class msg_pricing {

    private static final String ACCOUNT_SID = System.getenv("ACCOUNT_SID");
    private static final String AUTH_TOKEN = System.getenv("AUTH_TOKEN");

    public static void main(String[] args) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        String countryCode = "GB";
        System.out.println("+ Get Twilio Messaging pricing for country code: " + countryCode);
        Country messagingCountry = Country.fetcher(countryCode).fetch();
        for (InboundSmsPrice price : messagingCountry.getInboundSmsPrices()) {
            // For each message pricing category,
            // print number type and current (reflecting any discounts your account has) price.
            System.out.println("++ from: " + price.getType()
                    + " " + price.getCurrentPrice()
                    + " " + price.getBasePrice()
            );
            
        }
        System.out.println("+ End of list.");
        System.out.println("");
        System.out.println("+ List the country's carriers: MCC MNC Name");
        for (OutboundSmsPrice price : messagingCountry.getOutboundSmsPrices()) {
            // For each message pricing category,
            // print number type and current (reflecting any discounts your account has) price.
            System.out.println("+ " + price.getMcc()
                    + " " + price.getMnc()
                    + " " + price.getCarrier()
                    // + " " + price.getPrices()
            );
            
        }
        System.out.println("+ End of list.");

        /*
        System.out.println("+ list of countries where Twilio Messaging is available.");
        ResourceSet<Country> messagingCountries = Country.reader().read();
        for (Country country : messagingCountries) {
            System.out.println("+ " + country.getIsoCountry());
        }
        System.out.println("+ End of list.");
        */
    }
}
