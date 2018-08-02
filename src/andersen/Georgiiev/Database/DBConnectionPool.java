package andersen.Georgiiev.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class DBConnectionPool {
    private int size;
    private String user = "user";
    private String password = "251251";
    private String url = "jdbc:mysql://localhost:3306/test_database?serverTimezone=UTC";
    private ArrayList<Connection> connections;

    protected DBConnectionPool(int size) {
        this.size = size;
        connections = new ArrayList<>(size);
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
                connections.add(connection);
            } catch (SQLException e) {
                System.out.println("Что-то пошло не так при создании пула");
                e.printStackTrace();
            }
        }
    }

    public Connection get() {
        Connection connection = connections.get(0);
        connections.remove(0);
        return connection;
    }

    public void put(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}