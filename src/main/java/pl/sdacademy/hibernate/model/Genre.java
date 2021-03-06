package pl.sdacademy.hibernate.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "genres")
@Data
@EqualsAndHashCode(callSuper = true)
public class Genre extends BaseEntity {

    public  Genre (String name) {
        this.name = name;
    }

   private String name;

    @OneToMany
    @JoinColumn(name="genre_id")
    private List<Movie> movies = new ArrayList<>();
}
