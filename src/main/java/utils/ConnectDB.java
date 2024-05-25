package utils;


import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.configuration.FluentConfiguration;
import org.postgresql.Driver;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Properties;

public class ConnectDB {

//    Heroku connection
    private static final String DB_URL = "jdbc:postgresql://c6b7lkfdshud3i.cluster-czz5s0kz4scl.eu-west-1.rds.amazonaws.com:5432/d54pg2hiu8b0ve";
    private static final String DB_USERNAME = "ubu9k7bfbdqe3c";
    private static final String DB_PASSWORD = "pdde0c6f9a84518a82c05d2bf63612d054ab994d2c0f8469e7ff9d00413f53c91";

//    Local connection
//    private static final String DB_URL = "jdbc:postgresql://localhost:5432/db";
//    private static final String DB_USERNAME = "postgres";
//    private static final String DB_PASSWORD = "12345User";
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
        return get(DB_URL, DB_USERNAME, DB_PASSWORD);
    }

    public static void migrateDatabase() {
        migrateDatabase(DB_URL, DB_USERNAME, DB_PASSWORD);
    }

    public static void migrateDatabase(String url, String user, String password) {
        Flyway flyway = Flyway.configure().dataSource(url, user, password).load();
        flyway.migrate();
    }

}
