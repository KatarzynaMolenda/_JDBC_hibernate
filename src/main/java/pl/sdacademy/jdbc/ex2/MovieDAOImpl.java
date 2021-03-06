package pl.sdacademy.jdbc.ex2;

import com.mysql.cj.ServerPreparedQuery;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class MovieDAOImpl implements MovieDAO {

    private final Connection connection;

    public MovieDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void createTable() {
        try (Statement statement = connection.createStatement()) {
            String sql = "CREATE TABLE MOVIES (id INTEGER PRIMARY KEY AUTO_INCREMENT, " +
                    "title VARCHAR(100), " +
                    "genre VARCHAR(100)," +
                    "yearOfRelease INTEGER)";
            statement.execute(sql);
        } catch (SQLException e) {
            throw new DatabaseActionException(e);
        }

    }

    @Override
    public void deleteTable() {
        try (Statement deleteStatement = connection.createStatement()) {
            deleteStatement.execute("DROP TABLE IF EXISTS MOVIES");
        } catch (SQLException e) {
            throw new DatabaseActionException(e);
        }
    }

    @Override
    public void createMovie(Movie movie) {
        try (PreparedStatement insertStatement = connection.prepareStatement(
                "INSERT INTO MOVIES (title, genre, yearOfRelease) VALUES (?, ?, ?)")) {
            insertStatement.setString(1, movie.getTitle());
            insertStatement.setString(2, movie.getGenre());
            insertStatement.setInt(3, movie.getYearOfRelease());
            insertStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseActionException(e);
        }
    }


    @Override
    public void deleteMovie(int id) {
        try (PreparedStatement deleteStatement = connection.prepareStatement(
                "DELETE FROM MOVIES WHERE id = ?")) {
            deleteStatement.execute();
        } catch (SQLException e) {
            throw new DatabaseActionException(e);
        }
    }

    @Override
    public void updateMoviesTitle(int id, String newTitle) {
        try (PreparedStatement updateStatement = connection.prepareStatement("UPDATE MOVIES SET title = ? WHERE id = ?")) {
            updateStatement.setString(1, newTitle);
            updateStatement.setInt(2, id);
            updateStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Movie> findMovieById(int id) {
        try (PreparedStatement findMovieByIdStatement = connection.prepareStatement("SELECT * FROM MOVIES WHERE id = ?")) {
            findMovieByIdStatement.setInt(1, id);
            boolean isMovieFound = findMovieByIdStatement.execute();
            if (isMovieFound) {
                ResultSet foundMovie = findMovieByIdStatement.getResultSet();
                if (foundMovie.next()) {
                    String title = foundMovie.getString(2);
                    String genre = foundMovie.getString(3);
                    Integer yearOfRelease = foundMovie.getInt(4);
                    return Optional.of(new Movie(id, title, genre, yearOfRelease));
                }
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new DatabaseActionException(e);
        }
    }

    @Override
    public List<Movie> findAll() {
        try (Statement statement = connection.createStatement()) {
            ResultSet moviesResultSet = statement.executeQuery("SELECT * FROM MOVIES");
            List<Movie> movies = new ArrayList<>();
            while (moviesResultSet.next()) {
                Integer id = moviesResultSet.getInt(1);
                String title = moviesResultSet.getString(2);
                String genre = moviesResultSet.getString(3);
                Integer yearOfRelease = moviesResultSet.getInt(4);
                movies.add(new Movie(id, title, genre, yearOfRelease));
            }
            return movies;
        } catch (SQLException e) {
            throw new DatabaseActionException(e);
        }
    }

    @Override
    public void updateMovie(int id, Movie movie) {
        try (PreparedStatement updateStatement = connection.prepareStatement(
                "UPDATE MOVIES SET title =?, genre =?, yearOfRelease =? WHERE id =?")) {
            updateStatement.setString(1, movie.getTitle());
            updateStatement.setString(2, movie.getGenre());
            updateStatement.setInt(3, movie.getYearOfRelease());
            updateStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Movie> findMoviesByGenre(String genre) {
        return null;
    }

//    @Override
//    public List<Movie> findMoviesByGenre(String genre) {
//        try(PreparedStatement )
//    }
//}
}
