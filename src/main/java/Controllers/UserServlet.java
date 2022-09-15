package Controllers;
import Utils.Util;
import dao.UserDAO;
import entities.User;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/")
public class UserServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getServletPath();

        try {

            Connection conn = Util.getConnection();

            switch (action) {
                case "/new":
                    showNewForm(request, response, conn);
                    break;
                case "/insert":
                    insertUser(request, response, conn);
                    break;
                case "/delete":
                    deleteUser(request, response, conn);
                    break;
                case "/edit":
                    showEditForm(request, response, conn);
                    break;
                case "/update":
                    updateUser(request, response, conn);
                    break;
                default:
                    listUser(request, response, conn);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

        private void listUser (HttpServletRequest request, HttpServletResponse response, Connection con)
			throws SQLException, IOException, ServletException {
            List<User> users = UserDAO.getUserList(con);
            request.setAttribute("users", users);
            RequestDispatcher dispatcher = request.getRequestDispatcher("userdetails.jsp");
            dispatcher.forward(request, response);
        }


        private void showEditForm (HttpServletRequest request, HttpServletResponse response, Connection con)
			throws SQLException, ServletException, IOException {
            int id = Integer.parseInt(request.getParameter("id"));
            User userById = UserDAO.selectUser(id, con);
            RequestDispatcher dispatcher = request.getRequestDispatcher("registration.jsp");
            request.setAttribute("user", userById);
            dispatcher.forward(request, response);

        }

        private void updateUser (HttpServletRequest request, HttpServletResponse response, Connection con)
			throws SQLException, IOException {
            int id = Integer.parseInt(request.getParameter("id"));
            User updatedUser = new User();
            updatedUser.setId(id);
            updatedUser.setFirstName(request.getParameter("firstName"));
            updatedUser.setLastName(request.getParameter("lastName"));
            updatedUser.setAge(Integer.parseInt(request.getParameter("age")));
            UserDAO.updateUser(updatedUser, con);
            response.sendRedirect("list");
        }

        private void deleteUser (HttpServletRequest request, HttpServletResponse response, Connection con)
			throws SQLException, IOException {
            int id = Integer.parseInt(request.getParameter("id"));
            UserDAO.deleteUser(con, id);
            response.sendRedirect("list");

        }

    private void insertUser (HttpServletRequest request, HttpServletResponse response, Connection con)
            throws SQLException, IOException {
        User newUser = new User();
        newUser.setFirstName(request.getParameter("firstName"));
        newUser.setLastName(request.getParameter("lastName"));
        newUser.setAge(Integer.parseInt(request.getParameter("age")));
        UserDAO.registerUser(newUser, con);
        response.sendRedirect("list");
    }

    private void showNewForm (HttpServletRequest request, HttpServletResponse response, Connection con)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("registration.jsp");
        dispatcher.forward(request, response);
    }

}
