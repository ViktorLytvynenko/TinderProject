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
//        return get("jdbc:postgresql://localhost:5432/db", "postgres", "12345User");
        return get("jdbc:postgresql://c6b7lkfdshud3i.cluster-czz5s0kz4scl.eu-west-1.rds.amazonaws.com:5432/d54pg2hiu8b0ve", "ubu9k7bfbdqe3c", "pdde0c6f9a84518a82c05d2bf63612d054ab994d2c0f8469e7ff9d00413f53c91");
    }
}
