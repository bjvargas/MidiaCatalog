package main.java.model;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface JsonParser {

    List<? extends Content> parse() throws IOException, InterruptedException, NoSuchAlgorithmException;

}
