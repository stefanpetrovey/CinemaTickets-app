package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Movie;
import mk.ukim.finki.wp.lab.model.Production;
import mk.ukim.finki.wp.lab.model.update.MovieUpd;
import mk.ukim.finki.wp.lab.repository.jpa.MovieRepositoryInterface;
import mk.ukim.finki.wp.lab.repository.jpa.ProductionRepositoryInterface;
import mk.ukim.finki.wp.lab.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {
    private final MovieRepositoryInterface movieRepositoryInterface;
    private final ProductionRepositoryInterface productionRepositoryInterface;
    @Autowired
    public MovieServiceImpl(MovieRepositoryInterface movieRepositoryInterface,
                            ProductionRepositoryInterface productionRepositoryInterface){
        this.movieRepositoryInterface = movieRepositoryInterface;
        this.productionRepositoryInterface = productionRepositoryInterface;
    }

    @Override
    public List<Movie> listAll() {
        return movieRepositoryInterface.findAll();
    }

    @Override
    public Optional<Movie> findById(Long id) {
        return movieRepositoryInterface.findById(id);
    }


    @Override
    public Optional<Movie> save(String title, String summary, double rating, Long production, String image) {
        Optional<Production> productionOptional = productionRepositoryInterface.findById(production);
        if (productionOptional.isPresent()) {
            Movie movie = new Movie(title, summary, rating, productionOptional.get(), image);
            return Optional.of(movieRepositoryInterface.save(movie));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Movie> save(MovieUpd movieUpd) {
        Movie movie = convertDtoToMovie(movieUpd);
        return Optional.of(movieRepositoryInterface.save(movie));
    }

    private Movie convertDtoToMovie(MovieUpd movieUpd) {
        Movie movie=new Movie();
        updateMovieFromDto(movie, movieUpd);
        return movie;
    }

    private void updateMovieFromDto(Movie movie, MovieUpd movieUpd) {
        movie.setTitle(movieUpd.getTitle());
        movie.setSummary(movieUpd.getSummary());
        movie.setRating(movieUpd.getRating());
        Production production = productionRepositoryInterface.findById(movieUpd.getProduction().getId())
                .orElseThrow(() -> new RuntimeException("Production not found"));
        movie.setProduction(production);
    }

    @Override
    public Optional<Movie> edit(Long id, String title, String summary, double rating, Long production, String image) {
        Optional<Movie> optionalMovie = movieRepositoryInterface.findById(id);
        if (optionalMovie.isPresent()) {
            Movie movie = optionalMovie.get();
            movie.setTitle(title);
            movie.setSummary(summary);
            movie.setRating(rating);
            movie.setImage(image);

            Optional<Production> productionOptional = productionRepositoryInterface.findById(production);
            productionOptional.ifPresent(movie::setProduction);

            return Optional.of(movieRepositoryInterface.save(movie));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Movie> edit(Long id, MovieUpd movieUpd) {
        Optional<Movie> optionalMovie=movieRepositoryInterface.findById(id);
        if(optionalMovie.isPresent()){
            Movie movie=optionalMovie.get();
            updateMovieFromDto(movie, movieUpd);
            return Optional.of(movieRepositoryInterface.save(movie));
        }
        return Optional.empty();
    }

    @Override
    public void deleteById(Long id) {
        movieRepositoryInterface.deleteById(id);
    }
}
