package social.media.utlis;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Connector {
    private final String connectionUrl = "jdbc:sqlite:socialMedia.db";

    Connector() {
        try {
            createDatabase();
        } catch (SQLException e) {
            String message = e.getMessage(),
                    tableAlreadyExists = ".*\\(table [A-Za-z]+ already exists\\)$"
                ;

            if (message.matches(tableAlreadyExists)) {
                deleteTables();
                new Connector();
            }
        }
    }
    void executeQuery(String query) throws SQLException {
        try (Connection connection = DriverManager.getConnection(connectionUrl);
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }
    private void createUsersTable() throws SQLException {
        String query =
            "CREATE TABLE Users (" +
            "   id INTEGER PRIMARY KEY," +
            "   name TEXT," +
            "   email TEXT," +
            "   password TEXT," +
            "   avatar TEXT" +
            ")";
        executeQuery(query);
    }
    private void createPostsTable() throws SQLException {
        String query =
            "CREATE TABLE Posts (" +
            "   id INTEGER PRIMARY KEY," +
            "   creator INTEGER," +
            "   createdAt TEXT," +
            "   likes TEXT," +
            "   tags TEXT," +
            "   title TEXT," +
            "   text TEXT," +
            "   media TEXT," +
            "   private BOOLEAN," +
            "   FOREIGN KEY (creator) REFERENCES Users(id)" +
            ")";
        executeQuery(query);
    }
    private void createCommentsTable() throws SQLException {
        String query =
            "CREATE TABLE Comments (" +
            "   id INTEGER PRIMARY KEY," +
            "   post INTEGER," +
            "   creator INTEGER," +
            "   FOREIGN KEY (post) REFERENCES Posts(id)" +
            "   FOREIGN KEY (creator) REFERENCES Users(id)" +
            ")";
        executeQuery(query);
    }
    private void createDatabase() throws SQLException {
        createUsersTable();
        createPostsTable();
        createCommentsTable();
    }
    private void deleteTables() {
        try (Connection connection = DriverManager.getConnection(connectionUrl);
             Statement statement = connection.createStatement()) {
            statement.execute("PRAGMA foreign_keys = OFF;");
            statement.execute("DROP TABLE Posts");
            statement.execute("DROP TABLE Users");
            statement.execute("DROP TABLE Comments");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        new Connector();
    }
}
