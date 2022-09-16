package Controllers.Commands;
import dao.UserDAO;
import entities.User;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class CommandShowEditForm implements CommandInterface{

    @Override
    public void executeCommand(HttpServletRequest request, HttpServletResponse response, Connection con) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        User userById = UserDAO.selectUser(id, con);
        RequestDispatcher dispatcher = request.getRequestDispatcher("registration.jsp");
        request.setAttribute("user", userById);
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }
    }
}
