package andersen.Georgiiev.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Класс, реализующий паттерн пула соединений
 */

class DBConnectionPool {
    private int size;
    private String user = "user";
    private String password = "251251";
    private String url = "jdbc:mysql://localhost:3306/test_database?serverTimezone=UTC&useSSL=true";
    private ArrayList<Connection> availableConnections;
    private ArrayList<Connection> connectionsInUse;

    DBConnectionPool(int size) {
        this.size = size;
        availableConnections = new ArrayList<>(size);
        connectionsInUse = new ArrayList<>(size);
        openConnections();
    }

    private void openConnections() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection connection = null;
        for (int i = 0; i < size; i++) {
            try {
                connection = DriverManager.getConnection(url, user, password);
                availableConnections.add(connection);
            } catch (SQLException e) {
                System.out.println("Что-то пошло не так при создании пула");
                e.printStackTrace();
            }
        }
    }

    Connection get() {
        if (availableConnections.isEmpty()) return null;
        Connection connection = availableConnections.get(0);
        availableConnections.remove(0);
        connectionsInUse.add(connection);
        return connection;
    }

    void put(Connection connection) {
        if (!connectionsInUse.contains(connection))
            throw new IllegalArgumentException("передаваемое соединение не принадлежит пулу");
        availableConnections.add(connection);
        connectionsInUse.remove(connection);
    }
}