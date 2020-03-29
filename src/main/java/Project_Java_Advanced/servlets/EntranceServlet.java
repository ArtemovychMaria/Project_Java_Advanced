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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;


@WebServlet("/login")
public class EntranceServlet extends HttpServlet {


    private UserService userService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        userService = UserService.getUserService();

        String email = req.getParameter("email");
        String password = req.getParameter("password");

        if (!isParametersValid(email,password)) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        Optional<User> user = userService.getByEmailAndPassword(email,password);

        if (user.isPresent()) {
            HttpSession session = req.getSession(true);
            session.setAttribute("userId", user.get().getId());
            session.setAttribute("userRole",user.get().getRole());
            resp.setStatus(HttpServletResponse.SC_OK);
            return;
        }
        resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
    }

    private boolean isParametersValid(String email, String password) {
        return ObjectUtils.allNotNull(email, password);
    }
}
