package Controllers.Commands;
import dao.ActivityDAO;
import entities.User;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import static java.sql.Connection.TRANSACTION_READ_COMMITTED;

public class CommandListUser implements CommandInterface{

    @Override
    public void executeCommand(HttpServletRequest request, HttpServletResponse response, Connection con) throws SQLException, IOException {
        con.setTransactionIsolation(TRANSACTION_READ_COMMITTED);
        List<User> users = ActivityDAO.getUserList(con);
        request.setAttribute("users", users);
        RequestDispatcher dispatcher = request.getRequestDispatcher("userdetails.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }
    }
}


