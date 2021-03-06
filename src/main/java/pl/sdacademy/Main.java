package pl.sdacademy;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import pl.sdacademy.hibernate.model.Actor;
import pl.sdacademy.hibernate.model.Genre;
import pl.sdacademy.hibernate.model.Movie;
import pl.sdacademy.hibernate.repository.GenreRepository;

import java.util.List;
import java.util.Optional;

@Slf4j
public class Main {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Movie.class)
                .addAnnotatedClass(Actor.class)
                .addAnnotatedClass(Genre.class)
                .buildSessionFactory();

        callGenreQueries(sessionFactory);
    }

    private static void callGenreQueries(SessionFactory sessionFactory) {
        GenreRepository genreRepository = new GenreRepository(sessionFactory.createEntityManager());
//        genreRepository.save(new Genre("Action"));
        genreRepository.save(new Genre("Comedy"));
        genreRepository.save(new Genre("Drama"));
        Genre actionGenre = new Genre("Action");
        genreRepository.save(actionGenre);
//        genreRepository.remove(actionGenre);
        List<Genre> comedyGenres = genreRepository.findByName("Comedy");
        log.info("Comedy genres: ");
        for (Genre comedyGenre : comedyGenres) {
            log.info(comedyGenres.toString());
        }
        Optional<Genre> genreId3 = genreRepository.findById(3L);
        log.info("Genre id = 3: ");
        if(genreId3.isPresent()) {
            log.info(genreId3.get().toString());
        } else {
            log.info("Not found.");
        }
        List<Genre> allGenres = genreRepository.findAll();
        log.info("All genres: ");
        allGenres.forEach(genre -> log.info(genre.toString()));
    }
}
