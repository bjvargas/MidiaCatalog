package main.java.utilities;

import main.java.model.Movie;

import java.io.PrintWriter;
import java.util.List;

public class HtmlGenerator {

    private final PrintWriter writer;

    public HtmlGenerator(PrintWriter writer) {
        this.writer = writer;
    }

    public void generate(List<Movie> movies) {
        writer.println("<html>");
        writer.println("<head>");
        writer.println("<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css\" integrity=\"sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T\" crossorigin=\"anonymous\">");
        writer.println("<script src=\"https://code.jquery.com/jquery-3.3.1.slim.min.js\" integrity=\"sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo\" crossorigin=\"anonymous\"></script>");
        writer.println("<script src=\"https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js\" integrity=\"sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1\" crossorigin=\"anonymous\"></script>");
        writer.println("<script src=\"https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js\" integrity=\"sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM\" crossorigin=\"anonymous\"></script>");
        writer.println("<meta charset=\"utf-8\">");
        writer.println("</head>");
        writer.println("<body>");

        for (Movie movie : movies) {
            String div = "<div class=\"card text-white bg-dark mb-3\" style=\"max-width: 18rem;\">"
                    + "<h4 class=\"card-header\">%s</h4>"
                    + "<div class=\"card-body\">"
                    + "<img class=\"card-img\" src=\"%s\" alt=\"%s\">"
                    + "<p class=\"card-text mt-2\">Nota: %s - Ano: %s</p>"
                    + " </div> </div>";

            writer.println(String.format(div, movie.getTitle(), movie.getUrlImage(), movie.getTitle(), movie.getRating(), movie.getYear()));
        }
        writer.println("</body>");
        writer.println("</html>");

    }
}