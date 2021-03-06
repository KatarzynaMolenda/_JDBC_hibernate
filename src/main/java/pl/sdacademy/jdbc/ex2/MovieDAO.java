package pl.sdacademy.jdbc.ex2;

import java.util.List;
import java.util.Optional;

public interface MovieDAO {
    void createTable();
    void deleteTable();

    void createMovie(final Movie movie);
    void deleteMovie (int id);
    void updateMoviesTitle(int id, String newTitle);
    Optional<Movie> findMovieById(int id);
    List<Movie> findAll();

    void updateMovie(int id, Movie movie);

    List<Movie> findMoviesByGenre(String genre);
}
