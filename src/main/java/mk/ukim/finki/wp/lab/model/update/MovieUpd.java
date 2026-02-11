package mk.ukim.finki.wp.lab.model.update;

import lombok.Data;
import mk.ukim.finki.wp.lab.model.Production;

@Data
public class MovieUpd {
    String title;
    String summary;
    private Production production;
    double rating;
    public MovieUpd() {
    }

    public MovieUpd(String title, String summary, double rating, Production production) {
        this.production = production;
        this.title = title;
        this.summary = summary;
        this.rating = rating;
    }
}