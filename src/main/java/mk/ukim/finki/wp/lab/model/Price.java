package mk.ukim.finki.wp.lab.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "price")
public class Price {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double value;
    private Double minValue;
    private Double maxValue;
    private Boolean discount;

}
