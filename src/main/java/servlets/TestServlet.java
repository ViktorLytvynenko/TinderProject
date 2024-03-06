package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name="test")
public class TestServlet extends HttpServlet {

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
