package nju.quadra.hms.net;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by adn55 on 2016/11/22.
 */
public class HttpService {
    private final int port;
    private HttpServer httpServer;

    /**
     * Creates a new HTTP server instance with the given port.
     * @param port
     */
    public HttpService(int port) {
        this.port = port;
    }

    /**
     * Starts the HTTP server.
     * @throws IOException when the HTTP server failed to start
     */
    public void start() throws IOException {
        // create thread pool
        ExecutorService executor = Executors.newCachedThreadPool();
        // setup HTTP server
        httpServer = HttpServer.create(new InetSocketAddress(this.port), 0);
        httpServer.setExecutor(executor);
        httpServer.createContext("/", new HttpQueryHandler());
        httpServer.start();
    }

    /**
     * Stops the HTTP server.
     */
    public void stop() {
        if (httpServer != null) {
            httpServer.stop(0);
        }
    }
}
