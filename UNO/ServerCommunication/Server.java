package ServerCommunication;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import ClientCommunication.GameControl;
import ClientCommunication.GameSessionControl;

public class Server {
    // Constants
    private static final int PORT = 12345;

    // Fields
    private List<GameControl> games = new ArrayList<>();

    // Main method
    public static void main(String[] args) {
        new Server().startServer();
    }

    // Method to start the server
    public void startServer() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Uno server is running and waiting for clients...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected: " + clientSocket);

                // Handle client connection in a new thread
                new Thread(new ClientHandler(clientSocket, this)).start();
            }
        } catch (IOException e) {
            System.out.println("Error in the server: " + e.getMessage());
        }
    }

    // Method to create a new game
    public void createGame(GameControl game) {
        games.add(game);
    }

    // Method to find a game by its ID
    public GameControl findGameById(int gameId) {
        return games.stream().filter(g -> g.getGameId() ==
gameId).findFirst().orElse(null);
    }

    // Method to get an available game session
    public GameSessionControl getAvailableSession() {
        // TODO: Implement logic to find and return an available game session
        return null;


    }
}
