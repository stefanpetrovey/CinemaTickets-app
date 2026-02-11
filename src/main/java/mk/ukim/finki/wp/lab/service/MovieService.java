package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Movie;
import mk.ukim.finki.wp.lab.model.update.MovieUpd;

import java.util.List;
import java.util.Optional;

public interface MovieService {
    List<Movie> listAll();
    Optional<Movie> findById(Long id);

    Optional<Movie> save(String title, String summary, double rating, Long production, String image);

    Optional<Movie> save(MovieUpd movieUpd);

    Optional<Movie> edit(Long id, String title, String  summary,double rating, Long production, String image);

    Optional<Movie> edit(Long id, MovieUpd movieUpd);

    void deleteById(Long id);

}
