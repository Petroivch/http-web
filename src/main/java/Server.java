import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.Executors;

public class Server {

    public Server(){}

    public static final int PORT = 9994;


    void start() {
        final var threadPool = Executors.newFixedThreadPool(64);
        try (final var serversocket = new ServerSocket(PORT)) {

            while (true) {
                final var socket = serversocket.accept();
                threadPool.execute(new ThreadServer(socket));
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("Server has stopped");
        }
    }
}

