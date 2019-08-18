package sample.databaseConnection;

import java.sql.Connection;

public interface DatabaseConnection {
    public Connection createConnection();
}
