package andersen.Georgiiev.Database;

import andersen.Georgiiev.Cache.CustomCache;
import andersen.Georgiiev.Model.Developer;
import andersen.Georgiiev.Model.User;

import java.sql.*;

/**
 * Класс, предоставляющий возможности доступа к базе данных через
 * статические методы с применением пула соединений
 */

public class DatabaseHandler {
    private static DBConnectionPool pool = null;
    private static final int CONNECTIONS_AMOUNT = 10;

    static {
        pool = new DBConnectionPool(CONNECTIONS_AMOUNT);
    }

    private DatabaseHandler() {
    }

    public static int countUsers() {
        Connection connection = pool.get();
        if (connection == null) return -1;
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery("select max(id) from users");
            while (rs.first()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            return -1;
        } finally {
            pool.put(connection);
        }
        return -1;
    }

    public static int countDevelopers() {
        Connection connection = pool.get();
        if (connection == null) return -1;
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery("select max(id) from developers");
            while (rs.first()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            return -1;
        } finally {
            pool.put(connection);
        }
        return -1;
    }

    public static boolean insertUser(User user) {
        CustomCache.put(user);
        Connection connection = pool.get();
        if (connection == null) return false;
        try (PreparedStatement pstmt = connection.prepareStatement("insert " +
                "into users values (null , ?, ?, ?)")) {
            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getSurname());
            pstmt.setInt(3, user.getExperience());
            return pstmt.execute();
        } catch (SQLException e) {
            return false;
        } finally {
            pool.put(connection);
        }
    }

    public static boolean updateUser(User user) {
        Connection connection = pool.get();
        if (connection == null) return false;
        try (PreparedStatement pstmt = connection.prepareStatement("update " +
                "users set name = ? , surname = ?, experience = ? where  id = ?")) {
            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getSurname());
            pstmt.setInt(3, user.getExperience());
            pstmt.setInt(4, user.getId());
            return pstmt.execute();
        } catch (SQLException e) {
            return false;
        } finally {
            pool.put(connection);
        }
    }

    public static User getUser(int id) {
        User user = (User) CustomCache.get(id, User.class);
        if (user != null) return user;
        Connection connection = pool.get();
        if (connection == null) return null;
        try (PreparedStatement pstmt = connection.prepareStatement("select " +
                "id, name, surname, experience from users where id = ?")) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.first()) {
                user = new User(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getInt(4));
                return user;
            }
        } catch (SQLException e) {
            return null;
        } finally {
            pool.put(connection);
        }
        return null;
    }

    public static boolean deleteUser(int id) {
        Connection connection = pool.get();
        if (connection == null) return false;
        try (PreparedStatement pstmt = connection.prepareStatement("delete " +
                "from users where id = ?")) {
            pstmt.setInt(1, id);
            return pstmt.execute();
        } catch (SQLException e) {
            return false;
        } finally {
            pool.put(connection);
        }
    }

    public static boolean insertDeveloper(Developer developer) {
        CustomCache.put(developer);
        Connection connection = pool.get();
        if (connection == null) return false;
        try (PreparedStatement pstmt = connection.prepareStatement("insert " +
                "into developers values (null , ?, ?, ?)")) {
            pstmt.setString(1, developer.getName());
            pstmt.setString(2, developer.getSurname());
            pstmt.setInt(3, developer.getSalary());
            return pstmt.execute();
        } catch (SQLException e) {
            return false;
        } finally {
            pool.put(connection);
        }
    }

    public static boolean updateDeveloper(Developer developer) {
        Connection connection = pool.get();
        if (connection == null) return false;
        try (PreparedStatement pstmt = connection.prepareStatement("update " +
                "developers set name = ? , surname = ?, salary = ? where  id = ?")) {
            pstmt.setString(1, developer.getName());
            pstmt.setString(2, developer.getSurname());
            pstmt.setInt(3, developer.getSalary());
            pstmt.setInt(4, developer.getId());
            return pstmt.execute();
        } catch (SQLException e) {
            return false;
        } finally {
            pool.put(connection);
        }
    }

    public static Developer getDeveloper(int id) {
        Developer developer = (Developer)CustomCache.get(id, Developer.class);
        if (developer != null) return developer;
        Connection connection = pool.get();
        if (connection == null) return null;
        try (PreparedStatement pstmt = connection.prepareStatement("select " +
                "id, name, surname, salary from developers where id = ?")) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.first()) {
                developer = new Developer(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getInt(4));
                return developer;
            }
        } catch (SQLException e) {
            return null;
        } finally {
            pool.put(connection);
        }
        return null;
    }

    public static boolean deleteDeveloper(int id) {
        Connection connection = pool.get();
        if (connection == null) return false;
        try (PreparedStatement pstmt = connection.prepareStatement("delete " +
                "from developers where id = ?")) {
            pstmt.setInt(1, id);
            return pstmt.execute();
        } catch (SQLException e) {
            return false;
        } finally {
            pool.put(connection);
        }
    }
}
