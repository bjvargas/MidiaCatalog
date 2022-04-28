package main.java;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CallApi {
    private static final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_1_1)
            .connectTimeout(Duration.ofSeconds(10))
            .build();

    public static void main (String[] args) throws IOException, InterruptedException {

        String[] moviesArray = parseJsonMovies(getJsonAsStringForIMDB());
        String[] attributes = parseAttributes(moviesArray);

        List<String> titles = getAttributeList(attributes, "title");
        titles.forEach(System.out::println);

        List<String> urlImages = getAttributeList(attributes, "image");
        urlImages.forEach(System.out::println);

        List<String> years = getAttributeList(attributes, "year");
        years.forEach(System.out::println);

        List<String> imDbRatings = getAttributeList(attributes, "imDbRating\"");
        imDbRatings.forEach(System.out::println);

    }

    public static String getJsonAsStringForIMDB() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://imdb-api.com/en/API/Top250Movies/" + System.getenv("KEY")))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        HttpHeaders headers = response.headers();
        headers.map().forEach((k, v) -> System.out.println(k + ":" + v));

        System.out.println(response.statusCode());

       return response.body();
    }

    public static String[] parseJsonMovies(String json) {
        json = json.substring(json.indexOf("[") + 1);
        json = json.substring(0, json.indexOf("]"));

        return  json.split("},");
    }

    public static String[] parseAttributes(String[] attributes) {
        String att = Arrays.toString(attributes);
        return  att.split(",");
    }

    public static List<String> getAttributeList(String[] attributes, String search){
       List<String> attList = new ArrayList<>();
        for (String att: attributes) {
            if(att.contains(search)){
                attList.add(att);
            }
        }
        return attList;
    }

}
