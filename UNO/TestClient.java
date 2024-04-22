
import Database.LoginData;
import GameLogic.*;
import ServerCommunication.AccountResponse;
import ocsf.client.AbstractClient;

import java.io.IOException;

/*
 * Disclaimer: This code would typically interface with the UI; however, that is outside the scope of this experiment.
 */

public class TestClient extends AbstractClient {

    private final static int PORT = 12345;
    private final static String ADDRESS = "localhost";

    public TestClient()
    {
        super(ADDRESS, PORT);
    }

    @Override
    protected void handleMessageFromServer(Object o) {

        if (o instanceof GameResponse)
        {

            // Convert the object to game response.
            GameResponse response = (GameResponse)o;

            System.out.println(response.getResponse().name());

        }

    }

    @Override
    protected void connectionEstablished() {
        super.connectionEstablished();

        // Declare local variables.
        // LoginData data = new LoginData("Admin1", "Password1");

        GameRequest request1 = new GameRequest(RequestCode.START_GAME);
        GameRequest request2 = new GameRequest(RequestCode.PLAY_CARD);
        GameRequest request3 = new GameRequest(RequestCode.DRAW_CARD);
        GameRequest request4 = new GameRequest(RequestCode.ANNOUNCE_UNO);

        // Attempt to send the game request information to the server.
        try {

            // Send the request to the server.
            // sendToServer(data);
            sendToServer(request1);
            sendToServer(request2);
            sendToServer(request3);
            sendToServer(request4);

        } catch (IOException exception)
        {

            // Display the exception information.
            exception.printStackTrace();

        }

    }

    public static void main(String[] args) {

        // Declare variables.
        TestClient client = new TestClient();

        // Attempt to connect to the server.
        try
        {

            // Connect to the server.
            client.openConnection();

        } catch (IOException exception)
        {

            // Display the exception information to the user.
            exception.printStackTrace();

        }

    }

}
