package pl.sdacademy.jdbc.ex2;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Movie {
    private Integer id;
    private String title;
    private String genre;
    private Integer yearOfRelease;

    public  Movie (String title, String genre, Integer yearOfRelease) {
        this.title = title;
        this.genre = genre;
        this.yearOfRelease = yearOfRelease;
    }

}
