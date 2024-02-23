import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import servlets.TestServlet;

public class App {
    public static void main(String[] args) throws Exception {
        int port = 8080;
        Server server = new Server(port);
        ServletContextHandler handler = new ServletContextHandler();

        handler.addServlet(TestServlet.class, "/test");
        server.setHandler(handler);

        server.start();
        server.join();
    }
}
