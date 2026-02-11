package mk.ukim.finki.wp.lab.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import mk.ukim.finki.wp.lab.model.ShoppingCart;
import mk.ukim.finki.wp.lab.model.User;
import mk.ukim.finki.wp.lab.service.ShoppingCartService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
@Controller
@RequestMapping("/shopping-cart")
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;

    public ShoppingCartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }
    @GetMapping
    public String getShoppingCartPage(@RequestParam(required = false) String error,
                                      Model model,
                                      @AuthenticationPrincipal UserDetails userDetails) {

        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }

        if (userDetails == null) {
            return "redirect:/login";
        }

        String username = userDetails.getUsername();
        ShoppingCart shoppingCart = this.shoppingCartService.getActiveShoppingCart(username);
        model.addAttribute("movies", this.shoppingCartService.listAllTicketsInShoppingCart(shoppingCart.getId()));

        return "shopping-cart";
    }

    @PostMapping("/add-movie/{id}")
    public String addMovieToShoppingCart(@PathVariable Long id,
                                         @RequestParam Long numOfTickets,
                                         @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd' 'HH:mm") LocalDateTime dateCreated,
                                         @AuthenticationPrincipal UserDetails userDetails) {
        try {
            if (userDetails == null) {
                return "redirect:/login";
            }

            String username = userDetails.getUsername();
            this.shoppingCartService.addMovieToShoppingCart(username, id, numOfTickets, dateCreated);
            return "redirect:/movies";
        } catch (RuntimeException exception) {
            return "redirect:/shopping-cart?error=" + exception.getMessage();
        }
    }
}
