package main.java;

import main.java.model.Movie;
import main.java.utilities.HtmlGenerator;
import main.java.utilities.ParseUsingJRE;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class CallApi {


    public static void main (String[] args) throws IOException, InterruptedException {

        ParseUsingJRE util = new ParseUsingJRE();

        String[] moviesArray = util.parseJsonMovies(util.getJsonAsStringForIMDB());

        List<Movie> movies = util.getMovies(moviesArray);

        PrintWriter generator = new PrintWriter("src/main/resources/movies.html");
        new HtmlGenerator(generator).generate(movies);
        generator.close();

    }


}
