package main.java.utilities;

import main.java.model.Movie;

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

public class ParseUsingJRE {

    public String getJsonAsStringForIMDB() throws IOException, InterruptedException {
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

    private static final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_1_1)
            .connectTimeout(Duration.ofSeconds(10))
            .build();

    public String[] parseJsonMovies(String json) {
        json = json.substring(json.indexOf("[") + 1);
        json = json.substring(0, json.indexOf("]"));

        return  json.split("},");
    }

    public String[] parseAttributes(String[] moviesArray) {
        String att = Arrays.toString(moviesArray);
        return  att.split(",");
    }

    public List<String> getAttributeList(String[] attributes, String search){
        List<String> attList = new ArrayList<>();
        for (String att: attributes) {
            if(att.contains(search)){
                attList.add(att);
            }
        }
        return attList;
    }

    public List<Movie> getMovies(String[] moviesArray) {
        List<Movie> movies = new ArrayList<>();

        for (String movie: moviesArray) {
            movies.add(new Movie(getAtt(movie, "title"),
                    getAtt(movie, "image"),
                    Float.parseFloat(getAtt(movie, "imDbRating\"")),
                    Integer.parseInt(getAtt(movie, "year"))));
        }

        return  movies;
    }

    private String getAtt(String movie, String search) {
        String attSearched = null;
        String[] attributes =  movie.split(",");
        for (String att: attributes) {
            if(att.contains(search)){
                attSearched = att.substring(att.indexOf("\":\"") +3);
                attSearched = attSearched.substring(0, attSearched.length() - 1);
            }
        }
        return attSearched;
    }

}
