package main.java;

import main.java.model.Content;
import main.java.model.Movie;
import main.java.utilities.HtmlGenerator;
import main.java.utilities.IMDBParseUsingJRE;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CallIMDBApi {


    public static void main (String[] args) throws IOException, InterruptedException {

        List<? extends Content> contentList = (List<Movie>) new IMDBParseUsingJRE().parse();
        Collections.sort(contentList, Comparator.comparing(Content::year));

        PrintWriter generator = new PrintWriter("src/main/resources/TOP250MoviesIMDB.html");
        new HtmlGenerator(generator).generate(contentList);
        generator.close();

    }


}
