package main.java.model;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public interface ApiClient {

    public String getBody() throws IOException, InterruptedException, NoSuchAlgorithmException;

}
