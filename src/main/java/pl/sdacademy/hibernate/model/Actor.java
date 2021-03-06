package pl.sdacademy.hibernate.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table (name="actors")
@Data
public class Actor extends BaseEntity {

    private String name;
    private String lastName;

    @Column(name = "year_of_birth")
    private int yearOfBirth;

    @ManyToMany
    @JoinTable(name="actors_to_movies",
            joinColumns = @JoinColumn(name="actor_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "movie_id", referencedColumnName = "id"))
    private List<Movie> movies = new ArrayList<>();

}

