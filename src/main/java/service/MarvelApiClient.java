package main.java.service;

import main.java.model.ApiClient;

import java.io.IOException;
import java.math.BigInteger;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Duration;

public class MarvelApiClient implements ApiClient {

    private static final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_1_1)
            .connectTimeout(Duration.ofSeconds(10))
            .build();

    @Override
    public String getBody() throws IOException, InterruptedException, NoSuchAlgorithmException {
        String timestamp = String.valueOf(System.currentTimeMillis());
        String kingKey = timestamp + System.getenv("MARVEL_P_KEY") + System.getenv("MARVEL_KEY");
        String hash = makeHash(kingKey);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://gateway.marvel.com:443/v1/public/series?ts=" + timestamp
                        + "&hash=" + hash
                        + "&apikey=" + System.getenv("MARVEL_KEY")))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        HttpHeaders headers = response.headers();
        headers.map().forEach((k, v) -> System.out.println(k + ":" + v));

        return response.body();
    }

    private String makeHash(String kingKey) throws NoSuchAlgorithmException {
            MessageDigest md = MessageDigest.getInstance("MD5");
            BigInteger hash = new BigInteger(1, md.digest(kingKey.getBytes()));
            return hash.toString(16);
    }
}
