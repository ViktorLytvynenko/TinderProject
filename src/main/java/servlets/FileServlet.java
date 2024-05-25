package servlets;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Arrays;

// http://lcoalhost:9000/assets/css/style.css

public class FileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        ClassLoader classLoader = this.getClass().getClassLoader();
        String requestURI = req.getRequestURI(); // getting this part of url /assets/css/style.css
        System.out.println("Request URI: " + requestURI);
        try (
                ServletOutputStream os = resp.getOutputStream();
                InputStream is = classLoader.getResourceAsStream(requestURI.substring(1));
        ) {
            byte[] bytes = is.readAllBytes();
            os.write(bytes);
        } catch (NullPointerException ex) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}