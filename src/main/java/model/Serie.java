package main.java.model;

public class Serie implements Content {

    private String title;

    private String thumbnail;

    private String rating;

    private Integer startYear;

    public Serie(String title, String thumbnail, String rating, Integer startYear) {
        this.title = title;
        this.thumbnail = thumbnail;
        this.rating = rating;
        this.startYear = startYear;
    }

    @Override
    public String title() {
        return this.title;
    }

    @Override
    public String urlImage() {
        return this.thumbnail;
    }

    @Override
    public String rating() {
        return this.rating;
    }

    @Override
    public Integer year() {
        return this.startYear;
    }
}
