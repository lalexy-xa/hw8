package org.example;

import org.example.db.DatabaseStorage;
import org.example.entities.Client;
import org.example.services.ClientService;
import org.flywaydb.core.Flyway;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws Exception {
        String DB_URL = "jdbc:h2:~/test";
        String DB_PASSWORD = "";
        String DB_USER_NAME="sa";

        Flyway fly = Flyway.configure()
                .dataSource(DB_URL, DB_USER_NAME, DB_PASSWORD)
                .load();

        fly.migrate();

    }
}