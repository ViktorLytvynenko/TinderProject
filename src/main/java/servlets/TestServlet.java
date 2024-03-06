package servlets;

import utils.TemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

public class TestServlet extends HttpServlet {
    private final TemplateEngine templateEngine = TemplateEngine.resources("/templates");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HashMap<String, Object> data = new HashMap<>(3);
        data.put("id", 12L);
        data.put("name", "Vadym");
        data.put("img_url", "https://html.com/wp-content/uploads/flamingo.jpg");
        templateEngine.render("users.ftl", data, resp);
    }

}
