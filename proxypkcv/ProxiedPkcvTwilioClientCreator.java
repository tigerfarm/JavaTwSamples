package proxypkcv;

import com.twilio.http.HttpClient;
import com.twilio.http.NetworkHttpClient;
import com.twilio.http.TwilioRestClient;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

import com.twilio.http.ValidationClient;
import com.twilio.rest.accounts.v1.credential.PublicKey;
import com.twilio.rest.api.v2010.account.NewKey;

// Download and include JAR file: 
//  https://mvnrepository.com/artifact/commons-codec/commons-codec/1.15
import org.apache.commons.codec.binary.Base64;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;

public class ProxiedPkcvTwilioClientCreator {

    public static final String ACCOUNT_SID = System.getenv("MASTER_ACCOUNT_SID");
    public static final String AUTH_TOKEN = System.getenv("MASTER_AUTH_TOKEN");

    private String username;
    private String password;
    private String proxyHost;
    private int proxyPort;
    private HttpClient httpClient;

    /**
     * Constructor for ProxiedTwilioClientCreator
     *
     * @param username
     * @param password
     * @param proxyHost
     * @param proxyPort
     */
    public ProxiedPkcvTwilioClientCreator(String username, String password, String proxyHost, int proxyPort) {
        this.username = username;
        this.password = password;
        this.proxyHost = proxyHost;
        this.proxyPort = proxyPort;
    }

    /**
     * Creates a custom HttpClient based on default config as seen on:
     * {@link com.twilio.http.NetworkHttpClient#NetworkHttpClient() constructor}
     */
    private void createHttpClient() {
        RequestConfig config = RequestConfig.custom()
                .setConnectTimeout(10000)
                .setSocketTimeout(30500)
                .build();

        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setDefaultMaxPerRoute(10);
        connectionManager.setMaxTotal(10 * 2);

        HttpHost proxy = new HttpHost(proxyHost, proxyPort);

        HttpClientBuilder clientBuilder = HttpClientBuilder.create();
        clientBuilder
                .setConnectionManager(connectionManager)
                .setProxy(proxy)
                .setDefaultRequestConfig(config);

        // Inclusion of Twilio headers and build() is executed under this constructor
        this.httpClient = new NetworkHttpClient(clientBuilder);
    }

    /**
     * Get the custom client or builds a new one
     *
     * @return a TwilioRestClient object
     */
    public TwilioRestClient getClient() throws Exception {

        if (this.httpClient == null) {
            this.createHttpClient();
        }

        // Generate public/private key pair
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);
        KeyPair pair = keyGen.generateKeyPair();
        java.security.PublicKey pk = pair.getPublic();

        // Use the default rest client
        TwilioRestClient client = new TwilioRestClient.Builder(ACCOUNT_SID, AUTH_TOKEN).build();

        // Create a public key and api key using the default client
        PublicKey key = PublicKey.creator(
                Base64.encodeBase64String(pk.getEncoded())
        ).setFriendlyName("Public Key").create(client);
        NewKey apiKey = NewKey.creator().create(client);
        
        /* Alternative
        PrivateKey pk = null;
        try {
            pk = readFromPem();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
         */
        
        // Switch to validation client as the default client
        TwilioRestClient validationClient = new TwilioRestClient.Builder(apiKey.getSid(), apiKey.getSecret())
                .accountSid(ACCOUNT_SID)
                .httpClient(new ValidationClient(ACCOUNT_SID, key.getSid(), apiKey.getSid(), pair.getPrivate()))
                .build();
        return validationClient;
        // TwilioRestClient.Builder builder = new TwilioRestClient.Builder(username, password);
        // return builder.httpClient(this.httpClient).build();

    }

}
