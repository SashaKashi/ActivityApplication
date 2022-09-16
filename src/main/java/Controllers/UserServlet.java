package Controllers;
import Controllers.Commands.*;
import Utils.Util;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import static java.util.Map.entry;

@WebServlet("/")
public class UserServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    CommandInterface commandInterface;
    Map <String, CommandInterface> allCommands = Map.ofEntries(entry("/new", new CommandShowNewForm()),
                                                               entry("/insert", new CommandInsertUser()),
                                                               entry("/delete", new CommandDeleteUser()),
                                                               entry("/update", new CommandUpdateUser()),
                                                               entry("/edit", new CommandShowEditForm()));

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getServletPath();

        try {
            Connection con = Util.getConnection();
            allCommands.getOrDefault(action, new CommandListUser()).executeCommand(request, response, con);
        } catch (SQLException ex) {
            throw new ServletException(ex);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
