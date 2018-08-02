package andersen.Georgiiev.Database;

import andersen.Georgiiev.Model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class DatabaseHandler {
    private static DBConnectionPool pool = null;
    private final int CONNECTIONS_AMOUNT = 10;

    public DatabaseHandler() {
        if (pool == null) {
            pool = new DBConnectionPool(CONNECTIONS_AMOUNT);
        }
    }

    public void insertUser(User user) {

    }

    public void deleteUser(User user) {

    }

    public DBConnectionPool getPool() {
        return pool;
    }

}
