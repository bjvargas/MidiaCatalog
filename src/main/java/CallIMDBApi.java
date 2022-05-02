package main.java;

import main.java.model.Movie;
import main.java.utilities.HtmlGenerator;
import main.java.utilities.IMDBParseUsingJRE;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class CallIMDBApi {


    public static void main (String[] args) throws IOException, InterruptedException {

        PrintWriter generator = new PrintWriter("src/main/resources/TOP250MoviesIMDB.html");
        new HtmlGenerator(generator).generate((List<Movie>) new IMDBParseUsingJRE().parse());
        generator.close();

    }


}
