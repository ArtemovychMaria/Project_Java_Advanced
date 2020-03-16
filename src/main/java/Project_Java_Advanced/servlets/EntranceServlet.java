package Project_Java_Advanced.servlets;

import Project_Java_Advanced.daos.UserDao;
import Project_Java_Advanced.entities.User;
import Project_Java_Advanced.services.UserService;
import org.apache.commons.lang3.ObjectUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;


@WebServlet("/entrance")
public class EntranceServlet extends HttpServlet {

    private UserService userService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        if (req.getParameter("Registration") != null){
            req.getRequestDispatcher("registration.jsp").forward(req, resp);
        }

        if (req.getParameter("logIn") != null){

            userService=UserService.getUserService();

            String email=req.getParameter("login");
            String password=req.getParameter("password");

            if (!ObjectUtils.allNotNull(email, password)) {
                req.getRequestDispatcher("index.jsp").forward(req, resp);
                return;
            }

            Optional<User> user = userService.selectByEmail(email);

            if (user.isPresent() && user.get().getPassword().equals(password)) {
                req.setAttribute("userIdentyficator", user.get().getFirstName());
                req.getRequestDispatcher("cabinet.jsp").forward(req, resp);
                return;
            }

            req.getRequestDispatcher("index.jsp").forward(req, resp);
        }
    }
}
