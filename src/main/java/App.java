import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlets.TestServlet;

public class App {
    public static void main(String[] args) throws Exception {
        int port = 8082;
        Server server = new Server(port);
        ServletContextHandler handler = new ServletContextHandler();

        handler.addServlet(servlets.TestServlet.class, "/test");
        handler.addServlet(new ServletHolder(servlets.FileServlet.class), "/assets/*");
        handler.addServlet(new ServletHolder(servlets.UserServlet.class), "/users");

        server.setHandler(handler);

        server.start();
        server.join();
    }
}
