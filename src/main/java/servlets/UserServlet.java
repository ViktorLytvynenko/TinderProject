package servlets;


import dao.UserDaoSQL;
import entity.User;
import entity.Vote;
import utils.Params;
import utils.TemplateEngine;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Optional;

public class UserServlet extends HttpServlet {
    private final UserDaoSQL userDaoSQL;
    private final TemplateEngine templateEngine = TemplateEngine.resources("/templates");

    public UserServlet(UserDaoSQL userDaoSQL) {
        this.userDaoSQL = userDaoSQL;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User sessionUser = (User) req.getSession().getAttribute("user");
        Optional<User> unvotedUser = userDaoSQL.getUnvotedUser(sessionUser.getId());

        if (unvotedUser.isPresent()) {
            User user = unvotedUser.get();
            HashMap<String, Object> data = new HashMap<>(3);
            data.put("id", user.getId());
            data.put("name", user.getName());
            data.put("img_url", user.getPhoto());
            templateEngine.render("users.ftl", data, resp);
        } else {
            resp.sendRedirect("/liked");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User sessionUser = (User) req.getSession().getAttribute("user");
        Optional<Long> userId = Params.getLongParam("userId", req);
        Optional<String> vote = Params.getStrParam("vote", req);

        vote.map(Boolean::valueOf)
                .map(v ->
                        userId.map(id -> {
                            Vote voteUser = new Vote(sessionUser.getId(), id, v);
                            System.out.println("User id: " + id + " Vote result: " + v);
                            userDaoSQL.saveVote(voteUser);
                            return id;
                        }));
        resp.sendRedirect("/users");
    }
}