package ClientCommunication;

public class GameSessionControl {
    private Client clientCommunication;

    public GameSessionControl(Client clientCommunication) {
        this.clientCommunication = clientCommunication;
    }

    public void startGameSession() {
        clientCommunication.sendStartGameSessionRequest();
    }

    public void leaveGameSession() {
        clientCommunication.sendLeaveGameSessionRequest();
    }
}