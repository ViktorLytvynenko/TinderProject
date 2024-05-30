package servlets;


import dao.MessageDaoSQL;
import dao.UserDaoSQL;
import entity.Message;
import entity.User;
import utils.Params;
import utils.TemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class MessagesServlet extends HttpServlet {
    private final UserDaoSQL userDaoSQL;
    private final MessageDaoSQL messageDaoSQL;

    private final TemplateEngine templateEngine = TemplateEngine.resources("/templates");

    public MessagesServlet(UserDaoSQL userDaoSQL, MessageDaoSQL messageDaoSQL) {
        this.userDaoSQL = userDaoSQL;
        this.messageDaoSQL = messageDaoSQL;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User sessionUser = (User) req.getSession().getAttribute("user");
        Long userId = Long.valueOf(req.getPathInfo().substring(1));
        HashMap<String, Object> data = new HashMap<>(1);
        User user = userDaoSQL.findUserById(userId).orElseThrow();
        data.put("user", user);
        List<Message> messagesBetweenTwoUsers = messageDaoSQL.getMessagesBetweenTwoUsers(sessionUser.getId(), userId);
        data.put("messages", messagesBetweenTwoUsers);
        templateEngine.render("chat.ftl", data, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User sessionUser = (User) req.getSession().getAttribute("user");
        Long userId = Long.valueOf(req.getPathInfo().substring(1));
        Optional<String> someText = Params.getStrParam("someText", req);
        System.out.println(someText + " " + userId);
        someText.map(text -> {
            messageDaoSQL.addMessageBetweenTwoUsers(sessionUser.getId(), userId, text);
            System.out.println(text);
            return text;
        });
        resp.sendRedirect("/messages/" + userId);
    }
}