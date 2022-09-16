package Utils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");

        return DriverManager.getConnection("jdbc:postgresql://35.195.86.13:5432/postgres", "sasha",
                                                                                           "sasha");
    }

}
