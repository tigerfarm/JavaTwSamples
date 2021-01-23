package javatwsamples;
/*
Sample output lines:
+ SID: NOcf10f836ed1874b3bd98dc291510b52f 20107 2021-01-05T23:34:06.000Z
+ SID: NO32a4ae12f0d8faa0c3cd3bf52c1bc45b 20107 2021-01-05T23:34:06.000Z
+ SID: NO731a511bf48d4e5d0c4c3a9ee4f081e6 82002 2021-01-05T23:26:56.000Z
+ SID: NO2d433f2cb523a2ca50340c748e2281ac 82002 2021-01-05T23:25:49.000Z
+ SID: NOadb482247bbc7717ea67b58c66383ba7 82002 2021-01-05T23:25:45.000Z
+ SID: NO602b43472a41b85bb3a0b68ddb28cf1c 82002 2021-01-05T23:24:10.000Z
+ SID: NOd83cede60f53e292d9dfe567bf5be2a6 81016 2021-01-05T02:01:18.000Z
*/
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
            System.out.println("+ SID: " + record.getSid()
                    + " " + alert.getErrorCode()
                    + " " + alert.getDateGenerated()
            );
        }
    }
}
