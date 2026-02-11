package mk.ukim.finki.wp.lab.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "movie")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    String title;
    String summary;
    @ManyToOne(cascade  = CascadeType.PERSIST,fetch = FetchType.LAZY)
    private Production production;
    double rating;
    String image;

//    @OneToMany
//    private List<Price> prices;
    @OneToMany(mappedBy = "movie", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<TicketOrder> ticketOrders;

    public Movie() {
    }

    public Movie(String title, String summary, double rating, Production production, String image) {
        this.production = production;
        this.title = title;
        this.summary = summary;
        this.rating = rating;
        this.image = image;
    }
}
