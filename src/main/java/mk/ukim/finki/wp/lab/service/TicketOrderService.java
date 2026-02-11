package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Movie;
import mk.ukim.finki.wp.lab.model.TicketOrder;
import mk.ukim.finki.wp.lab.model.User;

import java.time.LocalDateTime;
import java.util.List;

public interface TicketOrderService {
    TicketOrder placeOrder(User user, Movie movie, int numberOfTickets, LocalDateTime dateCreated);
    List<TicketOrder> getOrdersWithinTimeInterval(LocalDateTime from, LocalDateTime to);

    List<TicketOrder> listAllOrders();
}
