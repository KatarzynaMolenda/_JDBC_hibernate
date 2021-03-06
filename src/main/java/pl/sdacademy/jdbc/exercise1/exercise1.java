package pl.sdacademy.jdbc.exercise1;

import java.sql.*;

public class exercise1 {
    private static final String H2_DRIVER = "org.h2.Driver";
    private static final String DB_URL = "jdbc:h2:mem:testdb";
    private static final String USER = "sa";
    private static final String PASSWORD = "";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            try (Statement deleteStatement = connection.createStatement()) {
                deleteStatement.execute("DROP TABLE IF EXISTS MOVIES");
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try (Statement createStatement = connection.createStatement()) {
                String sql = "CREATE TABLE MOVIES (id INTEGER PRIMARY KEY AUTO_INCREMENT, title VARCHAR(255), " +
                        "genre VARCHAR(255), yearOfRelease INTEGER)";
                createStatement.execute(sql);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try (Statement insertStatement = connection.createStatement()) {
                String insertMatrixStatement = "INSERT INTO MOVIES (title, genre, yearOfRelease) VALUES ('Matrix', 'sci-fi', 1998)";
                String insertMaskStatement = "INSERT INTO MOVIES (title, genre, yearOfRelease) VALUES ('Mask', 'comedy', 1995)";
                String insertDoomStatement = "INSERT INTO MOVIES (title, genre, yearOfRelease) VALUES ('Doom', 'horror', 1982)";
                insertStatement.execute(insertMatrixStatement);
                insertStatement.execute(insertMaskStatement);
                insertStatement.execute(insertDoomStatement);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try (PreparedStatement updateStatement = connection.prepareStatement("UPDATE movies SET title = ?, yearOfRelease = ?, genre = ? WHERE id = ?")) {
                updateStatement.setString(1, "Matrix");
                updateStatement.setInt(2, 1980);
                updateStatement.setString(3, "s-f");
                updateStatement.setInt(4, 1);
                updateStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try (PreparedStatement deleteStatement = connection.prepareStatement("DELETE FROM movies WHERE id = ?")) {
                deleteStatement.setInt(1, 3);
                deleteStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try(Statement readAllStatement = connection.createStatement()) {
                String readAllMoviesQuery = "SELECT * FROM MOVIES";
                ResultSet resultSet = readAllStatement.executeQuery(readAllMoviesQuery);
                while(resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String title = resultSet.getString("title");
                    String genre = resultSet.getString("genre");
                    int yearOfRelease = resultSet.getInt("yearOfRelease");

                    System.out.println("########");
                    System.out.println("ID: " + id);
                    System.out.println("Title: " + title);
                    System.out.println("Genre: " + genre);
                    System.out.println("Year of release: " + yearOfRelease);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}

