package servlets;

import dao.UserDao;
import dao.UserDaoSQL;
import entity.User;
import utils.TemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class LikedServlet extends HttpServlet {
    private final UserDaoSQL userDaoSQL;
    private final TemplateEngine templateEngine = TemplateEngine.resources("/templates");

    public LikedServlet(UserDaoSQL userDaoSQL) {
        this.userDaoSQL = userDaoSQL;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User sessionUser = (User) req.getSession().getAttribute("user");
        List<User> likedUsers = userDaoSQL.getLikedUsers(sessionUser.getId());
        HashMap<String, Object> data = new HashMap<>(3);
        data.put("likedUsers", likedUsers);
        templateEngine.render("liked.ftl", data, resp);
    }
}
