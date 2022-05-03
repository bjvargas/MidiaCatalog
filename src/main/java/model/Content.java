package main.java.model;

public interface Content extends Comparable<Content>{

    String title();
    String urlImage();
    String rating();
    Integer year();

}
