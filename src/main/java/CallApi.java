package main.java;

import main.java.service.ImdbApiClient;
import main.java.utilities.HtmlGenerator;

import java.io.IOException;
import java.io.PrintWriter;

public class CallApi {


    public static void main (String[] args) throws IOException, InterruptedException {

        PrintWriter generator = new PrintWriter("src/main/resources/movies.html");
        new HtmlGenerator(generator).generate(new ImdbApiClient().getJsonAsStringForIMDB());
        generator.close();

    }


}
