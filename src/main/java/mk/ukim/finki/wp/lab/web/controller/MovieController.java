package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Movie;
import mk.ukim.finki.wp.lab.model.Production;
import mk.ukim.finki.wp.lab.model.User;
import mk.ukim.finki.wp.lab.service.MovieService;
import mk.ukim.finki.wp.lab.service.ProductionService;
import mk.ukim.finki.wp.lab.service.ShoppingCartService;
import mk.ukim.finki.wp.lab.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/movies")
public class MovieController {
    private final MovieService movieService;
    private final ProductionService productionService;
    private final ShoppingCartService shoppingCartService;
    private final UserService userService;

    public MovieController(MovieService movieService,
                           ProductionService productionService,
                           ShoppingCartService shoppingCartService,
                           UserService userService) {
        this.movieService = movieService;
        this.productionService = productionService;
        this.shoppingCartService = shoppingCartService;
        this.userService = userService;
    }

    @GetMapping
    public String getMoviePage(@RequestParam(required = false) String error, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        List<Movie> movies = this.movieService.listAll();
        List<User> users=this.userService.getAllUsers();
        model.addAttribute("movies", movies);
        model.addAttribute("users",users);
        return "listMovies";
    }

    @GetMapping("/delete/{id}")
    public String deleteMovie(@PathVariable Long id) {
        this.shoppingCartService.deleteByMovieId(id);
        this.movieService.deleteById(id);
        return "redirect:/movies";
    }

    @GetMapping("/edit-form/{id}")
    public String editMoviePage(@PathVariable Long id, Model model) {
        if (this.movieService.findById(id).isPresent()) {
            Movie movie = this.movieService.findById(id).get();
            List<Production> productions = this.productionService.listAll();
            model.addAttribute("productions", productions);
            model.addAttribute("movie", movie);
            return "add-movie";
        }
        return "redirect:/movies?error=MovieNotFound";
    }

    @GetMapping("/add-form")
    public String addMoviePage(Model model) {
        List<Production> productions = this.productionService.listAll();
        model.addAttribute("productions", productions);
        return "add-movie";
    }

    @PostMapping("/add")
    public String saveMovie(
            @RequestParam(required = false) Long id,
            @RequestParam String title,
            @RequestParam String summary,
            @RequestParam double rating,
            @RequestParam Long productions,
            @RequestParam String image) {
        if(id!=null){
            this.movieService.edit(id,title, summary, rating, productions, image);
        }else {
            this.movieService.save(title, summary, rating, productions, image);
        }
        return "redirect:/movies";
    }
}