package mk.ukim.finki.wp.lab.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "production")
public class Production {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String country;
    private String address;

    @OneToMany(mappedBy = "production", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<mk.ukim.finki.wp.lab.model.Movie> movies;

    public Production() {
    }

    public Production(String name, String country, String address) {
        this.name = name;
        this.country = country;
        this.address = address;
    }
}
