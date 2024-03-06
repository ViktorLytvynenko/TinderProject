package servlets;

import utils.Params;
import utils.TemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Optional;

public class UserServlet extends HttpServlet {
    private final TemplateEngine templateEngine = TemplateEngine.resources("/templates");
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            HashMap<String, Object> data = new HashMap<>(3);
            data.put("id", 12L);
            data.put("name", "Vadym");
            data.put("img_url", "https://html.com/wp-content/uploads/flamingo.jpg");
            templateEngine.render("users.ftl", data, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Optional<Long> userId = Params.getLongParam("userId", req);
        Optional<String> vote = Params.getStrParam("vote", req);

        vote.map(Boolean::valueOf)
                .map(v ->
                        userId.map(id -> {
                            System.out.println("User id: " + id + " Vote result: " + v);
                            return id;
                        }));


    }
}
