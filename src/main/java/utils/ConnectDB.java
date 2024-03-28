package utils;


import org.postgresql.Driver;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Properties;

public class ConnectDB {
    public static Optional<Connection> get(String url, String username, String password) {
        try {
            Properties properties = new Properties();
            properties.put("user", username);
            properties.put("password", password);

            return Optional.ofNullable(new Driver().connect(url, properties));
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return Optional.empty();
        }
    }

    public static Optional<Connection> get() {
        return get("jdbc:postgresql://localhost:5432/db", "postgres", "12345User");
    }
}
