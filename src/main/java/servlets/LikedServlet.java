package servlets;

import dao.UserDao;
import utils.TemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

public class LikedServlet extends HttpServlet {
    private final UserDao userDao;
    private final TemplateEngine templateEngine = TemplateEngine.resources("/templates");

    public LikedServlet(UserDao userDao) {
        this.userDao = userDao;
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        templateEngine.render("liked.ftl", HashMap.newHashMap(1), resp);
    }
}
