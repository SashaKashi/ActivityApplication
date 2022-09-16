package Controllers.Commands;
import dao.UserDAO;
import entities.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class CommandUpdateUser implements CommandInterface{

    @Override
    public void executeCommand(HttpServletRequest request, HttpServletResponse response, Connection con) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        User updatedUser = new User();
        updatedUser.setId(id);
        updatedUser.setFirstName(request.getParameter("firstName"));
        updatedUser.setLastName(request.getParameter("lastName"));
        updatedUser.setAge(Integer.parseInt(request.getParameter("age")));
        UserDAO.updateUser(updatedUser, con);
        response.sendRedirect("list");
    }
}
