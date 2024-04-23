package UserInterface;

import java.awt.GridLayout;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import GameLogic.Card;

class PlayerHandPanel extends JPanel {
    private Card selectedCard;

    public PlayerHandPanel() {
        setLayout(new GridLayout(1, 0, 10, 10));
        selectedCard = null;
    }

    public void updateHand(List<Card> hand) {
        removeAll();
        for (Card card : hand) {
            JButton cardButton = new JButton(new ImageIcon(card.getCardURL()));
            cardButton.addActionListener(e -> {
                selectedCard = card;
            });
            add(cardButton);
        }
        revalidate();
        repaint();
    }

    public Card getSelectedCard() {
        return selectedCard;
    }
}