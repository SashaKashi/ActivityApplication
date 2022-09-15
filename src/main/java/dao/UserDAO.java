package dao;
import entities.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class UserDAO {

    private static final String SELECT_USER_BY_ID = "SELECT id,first_name,last_name,age FROM users WHERE id =?;";
    private static final String SELECT_ALL_USERS = "SELECT * FROM users;";
    private static final String DELETE_USER = "DELETE FROM users WHERE id = ?;";
    private static final String UPDATE_USER = "UPDATE users SET first_name = ?, last_name= ?, age =? where id = ?;";
    private static final String INSERT_USER = "INSERT INTO users (first_name, last_name, age) VALUES (?, ?, ?);";


    public static int registerUser(User user, Connection conn) throws SQLException {

        int result = 0;

        PreparedStatement stm = conn.prepareStatement(INSERT_USER);
        int i = 1;
        stm.setString(i++, user.getFirstName());
        stm.setString(i++, user.getLastName());
        stm.setInt(i++, user.getAge());

        result = stm.executeUpdate();

        return result;
    }


    public static User selectUser(int id, Connection conn) throws SQLException {
        User user = new User();

        PreparedStatement preparedStatement = conn.prepareStatement(SELECT_USER_BY_ID);
        preparedStatement.setInt(1, id);
        System.out.println(preparedStatement);

        ResultSet rs = preparedStatement.executeQuery();

        while (rs.next()) {
            user.setId(id);
            user.setFirstName(rs.getString("first_name"));
            user.setLastName(rs.getString("last_name"));
            user.setAge(rs.getInt("age"));
        }

        return user;
    }

    public static List<User> getUserList(Connection conn) throws SQLException {

        PreparedStatement stm = conn.prepareStatement(SELECT_ALL_USERS);

        ResultSet rs = stm.executeQuery();

        List<User> list = new ArrayList<>();

        while (rs.next()) {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setFirstName(rs.getString("first_name"));
            user.setLastName(rs.getString("last_name"));
            user.setAge(rs.getInt("age"));
            list.add(user);
        }
        Comparator <User> comparator = Comparator.comparingInt(User::getId);
        Collections.sort(list, comparator);
        return list;

    }

    public static boolean deleteUser(Connection conn, int id) throws SQLException {

        PreparedStatement stm = conn.prepareStatement(DELETE_USER);

        stm.setInt(1, id);
        return stm.executeUpdate() > 0;
    }

    public static boolean updateUser(User user, Connection conn) throws SQLException {
        PreparedStatement statement = conn.prepareStatement(UPDATE_USER);
        int i = 1;
        statement.setString(i++, user.getFirstName());
        statement.setString(i++, user.getLastName());
        statement.setInt(i++, user.getAge());
        statement.setInt(i++, user.getId());

        return statement.executeUpdate() > 0;
    }

    private static void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}