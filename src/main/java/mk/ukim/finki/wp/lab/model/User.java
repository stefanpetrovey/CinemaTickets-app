package mk.ukim.finki.wp.lab.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Data
@Entity
@Table(name = "cinema_users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    private String name;

    private String surname;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate dateOfBirth;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<mk.ukim.finki.wp.lab.model.ShoppingCart> carts;

    @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Set<mk.ukim.finki.wp.lab.model.TicketOrder> ticketOrders = new HashSet<>();

    public void addTicketOrder(mk.ukim.finki.wp.lab.model.TicketOrder ticketOrder) {
        this.ticketOrders.add(ticketOrder);
        ticketOrder.setUser(this);
    }
    public User() {

    }
    public User(String username, String password, String name, String surname, LocalDate dateOfBirth) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, name, surname, dateOfBirth);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof User)) return false;
        User other = (User) obj;
        return id != null && id.equals(other.id);
    }


}
