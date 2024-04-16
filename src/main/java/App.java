import dao.UserDao;
import dao.UserDaoSQL;
import filter.LoginFilter;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.session.SessionHandler;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlets.LikedServlet;
import servlets.LoginServlet;
import servlets.MessagesServlet;
import servlets.UserServlet;
import utils.ConnectDB;

import javax.servlet.DispatcherType;
import java.sql.Connection;
import java.util.EnumSet;
import java.util.Optional;

public class App {

    public static void main(String[] args) throws Exception {
        int port = Integer.parseInt(System.getenv("PORT"));
        Server server = new Server(port);
        ServletContextHandler handler = new ServletContextHandler();
        final Optional<Connection> connection = ConnectDB.get();
//        UserDao userDao = new UserDao();
        UserDaoSQL userDaoSQL = new UserDaoSQL(connection);
//        userDaoSQL.getUnvotedUser(6L);

        SessionHandler sessionHandler = new SessionHandler();
        handler.setSessionHandler(sessionHandler);

        handler.addServlet(servlets.TestServlet.class, "/test");
        handler.addServlet(new ServletHolder(servlets.FileServlet.class), "/assets/*");
        handler.addFilter(new FilterHolder(new LoginFilter()), "/*", EnumSet.of(DispatcherType.REQUEST));
        handler.addServlet(new ServletHolder(new UserServlet(userDaoSQL)), "/users");
        handler.addServlet(new ServletHolder(new LikedServlet(userDaoSQL)), "/liked");
        handler.addServlet(new ServletHolder(new MessagesServlet(userDaoSQL)), "/messages/*");
        handler.addServlet(new ServletHolder(new LoginServlet(userDaoSQL)), "/login");

        server.setHandler(handler);

        server.start();
        server.join();
    }
}
