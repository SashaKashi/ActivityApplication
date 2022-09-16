package Controllers.Commands;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class CommandShowNewForm implements CommandInterface{

    @Override
    public void executeCommand(HttpServletRequest request, HttpServletResponse response, Connection con) throws SQLException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("registration.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }
    }
}
