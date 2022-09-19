package Controllers.Commands;
import dao.ActivityDAO;
import entities.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class CommandInsertUser implements CommandInterface{

    @Override
    public void executeCommand(HttpServletRequest request, HttpServletResponse response, Connection con)
                               throws SQLException, IOException {
        User newUser = new User();
        newUser.setFirstName(request.getParameter("firstName"));
        newUser.setLastName(request.getParameter("lastName"));
        newUser.setAge(Integer.parseInt(request.getParameter("age")));
        ActivityDAO.registerUser(newUser, con);
        response.sendRedirect("list");
    }
}
