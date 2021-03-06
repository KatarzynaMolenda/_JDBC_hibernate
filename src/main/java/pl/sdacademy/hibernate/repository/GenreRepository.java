package pl.sdacademy.hibernate.repository;

import pl.sdacademy.hibernate.model.Genre;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;
import java.util.Optional;

public class GenreRepository {
    private final EntityManager entityManager;

    public GenreRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Genre save(Genre genre) {
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(genre);
            transaction.commit();

            return genre;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            return null;
        }
    }

    public void remove(Genre genre) {
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.remove(genre);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Genre> findByName(String name) {
        return entityManager.createQuery("FROM Genre WHERE name = :name", Genre.class)
                .setParameter("name", name)
                .getResultList();

    }

    public Optional<Genre> findById(long id) {
        return Optional.ofNullable((entityManager.find(Genre.class, id)));

    }

    public List<Genre> findAll() {
        return entityManager.createQuery("FROM Genre", Genre.class).getResultList();
    }
}
