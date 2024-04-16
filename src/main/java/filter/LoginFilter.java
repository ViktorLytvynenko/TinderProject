package filter;

import entity.User;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginFilter implements HttpFilter {
    @Override
    public void doHttpFilter(HttpServletRequest rq, HttpServletResponse rs, FilterChain chain) throws IOException, ServletException {
        HttpSession session = rq.getSession();
        User user = session != null ? (User) session.getAttribute("user") : null;
        if (rq.getRequestURI().contains("assets/")) {
            chain.doFilter(rq, rs);
            System.out.println('1');
        }
        else if (user == null && !rq.getRequestURI().equals(rq.getContextPath() + "/login")) {
            System.out.println('2');
            rs.sendRedirect(rq.getContextPath() + "/login");
        } else {
            System.out.println('3');
            chain.doFilter(rq, rs);
        }
    }
}
