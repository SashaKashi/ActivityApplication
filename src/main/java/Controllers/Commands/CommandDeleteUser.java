package Controllers.Commands;
import dao.UserDAO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class CommandDeleteUser implements CommandInterface{

    @Override
    public void executeCommand(HttpServletRequest request, HttpServletResponse response, Connection con)
                               throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        UserDAO.deleteUser(con, id);
        response.sendRedirect("list");
    }
}