package ClientCommunication;

public class GameManagerControl {
    private Client clientCommunication;

    public GameManagerControl(Client clientCommunication) {
        this.clientCommunication = clientCommunication;
    }

    public void startGame() {
        clientCommunication.sendStartGameRequest();
    }

    public void joinGame() {
        clientCommunication.sendJoinGameRequest();
    }

    public void showHowToPlay() {
        clientCommunication.sendShowHowToPlayRequest();
    }

    public void logout() {
        clientCommunication.sendLogoutRequest();
    }
}