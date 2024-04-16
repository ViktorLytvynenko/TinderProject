package servlets;

import dao.UserDaoSQL;
import entity.User;
import utils.Params;
import utils.TemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Optional;

public class LoginServlet extends HttpServlet {
    private final UserDaoSQL userDaoSQL;
    private final TemplateEngine templateEngine = TemplateEngine.resources("/templates");

    public LoginServlet(UserDaoSQL userDaoSQL) {
        this.userDaoSQL = userDaoSQL;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        templateEngine.render("login.ftl", new HashMap<>(), resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Optional<String> email = Params.getStrParam("email", req);
        Optional<String> password = Params.getStrParam("password", req);
        Optional<User> user = email.flatMap(userDaoSQL::findUserByEmail).filter(u ->
                password.isPresent() && password.get().equals(u.getPassword())
        );
        System.out.println(user);
        System.out.println(email);
        System.out.println(password);
        if (user.isPresent()) {
            req.getSession().setAttribute("user", user.get());
            resp.sendRedirect("/users");
        }
        resp.sendRedirect("/login");
    }
}
