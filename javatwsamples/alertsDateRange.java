package javatwsamples;

import com.twilio.Twilio;
import com.twilio.base.ResourceSet;
import com.twilio.rest.monitor.v1.Alert;
import org.joda.time.DateTime;

public class alertsDateRange {

    private static final String ACCOUNT_SID = System.getenv("MASTER_ACCOUNT_SID");
    private static final String AUTH_TOKEN = System.getenv("MASTER_AUTH_TOKEN");

    public static void main(String[] args) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        ResourceSet<Alert> alerts = Alert.reader()
                .setStartDate(DateTime.parse("2021-01-01T00:00:00.000Z"))
                .setEndDate(DateTime.parse("2021-01-22T00:00:00.000Z"))
                // .setLogLevel("warning")
                .limit(20)
                .read();
        for (Alert record : alerts) {
            Alert alert = Alert.fetcher(record.getSid()).fetch();
            System.out.println("+ SID: " + record.getSid() + " " + alert.getErrorCode());
        }
    }
}
