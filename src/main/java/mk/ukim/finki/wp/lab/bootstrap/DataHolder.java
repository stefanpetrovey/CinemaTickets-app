package mk.ukim.finki.wp.lab.bootstrap;

import lombok.Getter;
import mk.ukim.finki.wp.lab.model.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Getter
public class DataHolder {
    public static List<mk.ukim.finki.wp.lab.model.User> users= new ArrayList<>();
    public static List<mk.ukim.finki.wp.lab.model.Movie> movies= new ArrayList<>();
    public static List<mk.ukim.finki.wp.lab.model.Production> productions= new ArrayList<>();
    public static List<mk.ukim.finki.wp.lab.model.ShoppingCart> shoppingCarts= new ArrayList<>();
    public static List<mk.ukim.finki.wp.lab.model.TicketOrder> ticketOrders= new ArrayList<>();

}
