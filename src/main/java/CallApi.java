package main.java;

import main.java.model.Movie;
import main.java.utilities.ParseUsingJRE;

import java.io.IOException;
import java.util.List;

public class CallApi {


    public static void main (String[] args) throws IOException, InterruptedException {

        ParseUsingJRE util = new ParseUsingJRE();

        String[] moviesArray = util.parseJsonMovies(util.getJsonAsStringForIMDB());
        String[] attributes = util.parseAttributes(moviesArray);

//        List<String> titles = util.getAttributeList(attributes, "title");
//        List<String> urlImages = util.getAttributeList(attributes, "image");
//        List<String> years = util.getAttributeList(attributes, "year");
//        List<String> imDbRatings = util.getAttributeList(attributes, "imDbRating\"");

        List<Movie> movies = util.getMovies(moviesArray);
        for (Movie movie: movies) {
            System.out.println(movie.toString());
        }

    }


}
