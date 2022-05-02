package main.java.utilities;

import main.java.model.Content;
import main.java.model.JsonParser;
import main.java.model.Serie;
import main.java.service.MarvelApiClient;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class MarvelSeriesParseUsingJRE implements JsonParser {

    @Override
    public List<? extends Content> parse() throws IOException, InterruptedException, NoSuchAlgorithmException {
        MarvelApiClient marvel = new MarvelApiClient();
        List<Serie> series = new ArrayList<>();
        for (String serie : parseJsonSeries(marvel.getBody())) {
            String image = getAtt(serie, "thumbnail") + "." + getAtt(serie, "extension");
            series.add(new Serie(getAtt(serie, "title"),
                    image,
                    getAtt(serie, "rating"),
                    Integer.parseInt(getYear(serie))));
        }
        return series;
    }

    public String[] parseJsonSeries(String json) {
        json = json.substring(json.indexOf("[") + 1);
        json = json.substring(0, json.indexOf("}]}"));

        return  json.split("},\\{\"id");
    }

    private String getAtt(String serie, String search) {
        String attSearched = null;
        String[] attributes =  serie.split(",");
        for (String att: attributes) {
            if(att.contains(search)){
                attSearched = att.substring(att.indexOf("\":\"") +3);
                attSearched = attSearched.substring(0, attSearched.length() - 1);
                return attSearched;
            }
        }
        return null;
    }

    private String getYear(String serie) {
        String attSearched = null;
        String[] attributes =  serie.split(",");
        for (String att: attributes) {
            if(att.contains("startYear")){
                attSearched = att.substring(att.indexOf("\":") +2);
                return attSearched;
            }
        }
        return null;
    }

}

