package ClientCommunication;

import GameLogic.Card;

public class GameControl {
    private Client clientCommunication;

    public GameControl(Client clientCommunication) {
        this.clientCommunication = clientCommunication;
    }

    public void playCard(Card card) {
        clientCommunication.sendPlayCardRequest(card);
    }

    public void drawCard() {
        clientCommunication.sendDrawCardRequest();
    }

    public void callUno() {
        clientCommunication.sendUnoCallRequest();
    }

    public void forfeit() {
        clientCommunication.sendForfeitRequest();
    }
}
