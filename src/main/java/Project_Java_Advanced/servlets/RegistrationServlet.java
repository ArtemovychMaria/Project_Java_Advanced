package Project_Java_Advanced.servlets;

import Project_Java_Advanced.entities.User;
import Project_Java_Advanced.entities.UserRoles;
import Project_Java_Advanced.services.UserService;
import org.apache.commons.lang3.ObjectUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {
    private UserService userService=UserService.getUserService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (ObjectUtils.allNotNull(firstName, lastName, email, password)) {
            userService.insert(new User(email, firstName, lastName, UserRoles.USER.toString(), password));
            request.setAttribute("userIdentyficator", email);
            request.getRequestDispatcher("cabinet.jsp").forward(request, response);
            return;
        }

        request.getRequestDispatcher("registration.jsp").forward(request,response);
    }

}
