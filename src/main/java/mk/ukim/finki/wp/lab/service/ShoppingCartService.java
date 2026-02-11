package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.ShoppingCart;
import mk.ukim.finki.wp.lab.model.TicketOrder;

import java.time.LocalDateTime;
import java.util.List;

public interface ShoppingCartService {

    List<TicketOrder> listAllTicketsInShoppingCart(Long cartId);
    void deleteByMovieId(Long movieId);
    ShoppingCart getActiveShoppingCart(String username);
    ShoppingCart addMovieToShoppingCart(String username, Long movieId, Long numberOfTickets, LocalDateTime dateCreated);

}
