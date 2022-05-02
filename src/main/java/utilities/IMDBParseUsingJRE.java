package main.java.utilities;

import main.java.model.Content;
import main.java.model.JsonParser;
import main.java.model.Movie;
import main.java.service.ImdbApiClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class IMDBParseUsingJRE implements JsonParser {

    public String[] parseJsonMovies(String json) {
        json = json.substring(json.indexOf("[") + 1);
        json = json.substring(0, json.indexOf("]"));

        return  json.split("},");
    }

    private String getAtt(String movie, String search) {
        String attSearched = null;
        String[] attributes =  movie.split(",");
        for (String att: attributes) {
            if(att.contains(search)){
                attSearched = att.substring(att.indexOf("\":\"") +3);
                attSearched = attSearched.substring(0, attSearched.length() - 1);
                return attSearched;
            }
        }
        return null;
    }

    @Override
    public List<? extends Content> parse() throws IOException, InterruptedException {
        ImdbApiClient imdb = new ImdbApiClient();
        List<Movie> movies = new ArrayList<>();

        for (String movie: parseJsonMovies(imdb.getBody())) {
            movies.add(new Movie(getAtt(movie, "title"),
                    getAtt(movie, "image"),
                    getAtt(movie, "imDbRating\""),
                    Integer.parseInt(getAtt(movie, "year"))));
        }

        return  movies;
    }
}
