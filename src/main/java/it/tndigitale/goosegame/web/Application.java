package it.tndigitale.goosegame.web;

import it.tndigitale.goosegame.domain.GooseGame;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

/**
 * Created by IT417 on 31/05/2018.
 */
public class Application {

    private static Server server = new Server(8080);

    public static void main(String... args) throws Exception {
        start(new GooseGame());
    }

    public static void start(GooseGame game) throws Exception {
        ServletContextHandler handler = new ServletContextHandler();
        handler.addServlet(new ServletHolder(new HttpGooseGameServlet(game)), "/gooseGame/*");
        server.setHandler(handler);
        server.start();

    }

    public static void stop() throws Exception {
        server.stop();

    }
}
