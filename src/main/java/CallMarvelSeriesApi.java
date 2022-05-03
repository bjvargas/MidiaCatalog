package main.java;

import main.java.model.Serie;
import main.java.utilities.HtmlGenerator;
import main.java.utilities.MarvelSeriesParseUsingJRE;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class CallMarvelSeriesApi {


    public static void main (String[] args) throws IOException, InterruptedException, NoSuchAlgorithmException {

        PrintWriter generator = new PrintWriter("src/main/resources/MarvelSeries.html");
        new HtmlGenerator(generator).generate((List<Serie>) new MarvelSeriesParseUsingJRE().parse());
        generator.close();

    }


}
