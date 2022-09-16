package Controllers.Commands;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public interface CommandInterface {

    void executeCommand(HttpServletRequest request, HttpServletResponse response, Connection con)
                        throws SQLException, IOException;

}
