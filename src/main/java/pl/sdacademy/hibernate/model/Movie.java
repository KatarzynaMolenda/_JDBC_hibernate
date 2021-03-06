package pl.sdacademy.hibernate.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="movies")
@Data
@EqualsAndHashCode(callSuper = true)

public class Movie extends BaseEntity {

    private String title;

    @Column(name="year_of_release")
    private int yearOfRelease;

    @ManyToOne
    private Genre genre;

    @ManyToMany(mappedBy = "movies")
    private List<Actor> actors = new ArrayList<>();

}
