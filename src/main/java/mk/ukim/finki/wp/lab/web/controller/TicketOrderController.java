package mk.ukim.finki.wp.lab.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.wp.lab.model.Movie;
import mk.ukim.finki.wp.lab.model.TicketOrder;
import mk.ukim.finki.wp.lab.model.User;
import mk.ukim.finki.wp.lab.repository.jpa.UserRepositoryInterface;
import mk.ukim.finki.wp.lab.service.MovieService;
import mk.ukim.finki.wp.lab.service.TicketOrderService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/ticket-order")
public class TicketOrderController {
    private final TicketOrderService ticketOrderService;
    private final MovieService movieService;
    private final UserRepositoryInterface userRepositoryInterface;

    public TicketOrderController(TicketOrderService ticketOrderService,
                                 MovieService movieService,
                                 UserRepositoryInterface userRepositoryInterface) {
        this.ticketOrderService = ticketOrderService;
        this.movieService=movieService;
        this.userRepositoryInterface = userRepositoryInterface;
    }

    @GetMapping("ticket-form")
    public String showTicketOrderForm(Model model) {
        List<Movie> movies = movieService.listAll();
        model.addAttribute("movies", movies);
        return "ticket-order-form";
    }

    @GetMapping("/orders-in-time-interval")
    public String getOrdersWithinTimeInterval(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd' 'HH:mm") LocalDateTime from,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd' 'HH:mm") LocalDateTime to,
            Model model) {
        List<TicketOrder> orders = ticketOrderService.getOrdersWithinTimeInterval(from, to);
        model.addAttribute("orders", orders);
        return "order-confirmation";
    }

    @GetMapping
    public String orderConfirmation(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        try {
            List<Movie> movies = movieService.listAll();
            model.addAttribute("movies", movies != null ? movies : new ArrayList<>());
            List<TicketOrder> orders = ticketOrderService.listAllOrders();
            model.addAttribute("orders", orders != null ? orders : new ArrayList<>());
            model.addAttribute("username", userDetails != null ? userDetails.getUsername() : "Guest");
            return "ticket-order";
        } catch (Exception e) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", "Failed to load ticket orders: " + e.getMessage());
            return "error"; // Ensure you have an error.html template
        }
    }

    @PostMapping("/place-order")
    public String placeTicketOrder(@RequestParam Long movieId,
                                   @RequestParam int numberOfTickets,
                                   @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd' 'HH:mm") LocalDateTime dateCreated,
                                   Model model,
                                   @AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null) {
            return "redirect:/login";
        }
        try {
            Optional<Movie> optionalMovie = movieService.findById(movieId);
            if (optionalMovie.isPresent()) {
                Movie movie = optionalMovie.get();
                User user = userRepositoryInterface.findByUsername(userDetails.getUsername())
                        .orElseThrow(() -> new UsernameNotFoundException("User not found: " + userDetails.getUsername()));
                TicketOrder ticketOrder = ticketOrderService.placeOrder(user, movie, numberOfTickets, dateCreated);
                model.addAttribute("order", ticketOrder);
                return "order-confirmation";
            } else {
                return "redirect:/movies?error=MovieNotFound";
            }
        } catch (UsernameNotFoundException e) {
            return "redirect:/login?error=UserNotFound";
        } catch (Exception e) {
            return "redirect:/ticket-order?error=" + e.getMessage();
        }
    }
}
