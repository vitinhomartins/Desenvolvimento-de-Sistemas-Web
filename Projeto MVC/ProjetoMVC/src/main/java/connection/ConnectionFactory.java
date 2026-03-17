package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class ConnectionFactory {

    private static final String URL = "jdbc:postgresql://localhost:5432/registrologinmvc";
    private static final String USER = "postgres"; //
    private static final String PASS = "0145";

    private ConnectionFactory() {
    }

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver do PostgreSQL não encontrado no classpath.", e);
        }

        return DriverManager.getConnection(URL, USER, PASS);
    }
}