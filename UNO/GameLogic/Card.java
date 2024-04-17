package GameLogic;

import javax.swing.*;

import UserInterface.DesignUtils;

import java.util.HashMap;
import java.util.Map;

public abstract class Card {
    private Color color;
    private int points;
    private String imageName;

    private static final Map<String, ImageIcon> cardImages = new HashMap<>();
    private static final ImageIcon backOfCardImage = DesignUtils.loadCardImage("BackOfCard.png");

    static {
        // Load all card images
    	cardImages.put("blue_1", DesignUtils.loadCardImage("blue_1.png"));
    	cardImages.put("blue_2", DesignUtils.loadCardImage("blue_2.png"));
    	cardImages.put("blue_3", DesignUtils.loadCardImage("blue_3.png"));
    	cardImages.put("blue_4", DesignUtils.loadCardImage("blue_4.png"));
    	cardImages.put("blue_5", DesignUtils.loadCardImage("blue_5.png"));
    	cardImages.put("blue_6", DesignUtils.loadCardImage("blue_6.png"));
    	cardImages.put("blue_7", DesignUtils.loadCardImage("blue_7.png"));
    	cardImages.put("blue_8", DesignUtils.loadCardImage("blue_8.png"));
    	cardImages.put("blue_9", DesignUtils.loadCardImage("blue_9.png"));
    	cardImages.put("blue_0", DesignUtils.loadCardImage("blue_0.png"));
    	cardImages.put("blue_reverse", DesignUtils.loadCardImage("blue_reverse.png"));
    	cardImages.put("blue_skip", DesignUtils.loadCardImage("blue_skip.png"));
    	cardImages.put("blue_+2", DesignUtils.loadCardImage("blue_+2.png"));

    	cardImages.put("red_1", DesignUtils.loadCardImage("red_1.png"));
    	cardImages.put("red_2", DesignUtils.loadCardImage("red_2.png"));
    	cardImages.put("red_3", DesignUtils.loadCardImage("red_3.png"));
    	cardImages.put("red_4", DesignUtils.loadCardImage("red_4.png"));
    	cardImages.put("red_5", DesignUtils.loadCardImage("red_5.png"));
    	cardImages.put("red_6", DesignUtils.loadCardImage("red_6.png"));
    	cardImages.put("red_7", DesignUtils.loadCardImage("red_7.png"));
    	cardImages.put("red_8", DesignUtils.loadCardImage("red_8.png"));
    	cardImages.put("red_9", DesignUtils.loadCardImage("red_9.png"));
    	cardImages.put("red_0", DesignUtils.loadCardImage("red_0.png"));
    	cardImages.put("red_reverse", DesignUtils.loadCardImage("red_reverse.png"));
    	cardImages.put("red_skip", DesignUtils.loadCardImage("red_skip.png"));
    	cardImages.put("red_+2", DesignUtils.loadCardImage("red_+2.png"));

    	cardImages.put("green_1", DesignUtils.loadCardImage("green_1.png"));
    	cardImages.put("green_2", DesignUtils.loadCardImage("green_2.png"));
       	cardImages.put("green_3", DesignUtils.loadCardImage("green_3.png"));
    	cardImages.put("green_4", DesignUtils.loadCardImage("green_4.png"));
    	cardImages.put("green_5", DesignUtils.loadCardImage("green_5.png"));
    	cardImages.put("green_6", DesignUtils.loadCardImage("green_6.png"));
    	cardImages.put("green_7", DesignUtils.loadCardImage("green_7.png"));
    	cardImages.put("green_8", DesignUtils.loadCardImage("green_8.png"));
    	cardImages.put("green_9", DesignUtils.loadCardImage("green_9.png"));
    	cardImages.put("green_0", DesignUtils.loadCardImage("green_0.png"));
    	cardImages.put("green_reverse", DesignUtils.loadCardImage("green_reverse.png"));
    	cardImages.put("green_skip", DesignUtils.loadCardImage("green_skip.png"));
    	cardImages.put("green_+2", DesignUtils.loadCardImage("green_+2.png"));

    	cardImages.put("yellow_1", DesignUtils.loadCardImage("yellow_1.png"));
    	cardImages.put("yellow_2", DesignUtils.loadCardImage("yellow_2.png"));
       	cardImages.put("yellow_3", DesignUtils.loadCardImage("yellow_3.png"));
    	cardImages.put("yellow_4", DesignUtils.loadCardImage("yellow_4.png"));
    	cardImages.put("yellow_5", DesignUtils.loadCardImage("yellow_5.png"));
    	cardImages.put("yellow_6", DesignUtils.loadCardImage("yellow_6.png"));
    	cardImages.put("yellow_7", DesignUtils.loadCardImage("yellow_7.png"));
    	cardImages.put("yellow_8", DesignUtils.loadCardImage("yellow_8.png"));
    	cardImages.put("yellow_9", DesignUtils.loadCardImage("yellow_9.png"));
    	cardImages.put("yellow_0", DesignUtils.loadCardImage("yellow_0.png"));
    	cardImages.put("yellow_reverse", DesignUtils.loadCardImage("yellow_reverse.png"));
    	cardImages.put("yellow_skip", DesignUtils.loadCardImage("yellow_skip.png"));
    	cardImages.put("yellow_+2", DesignUtils.loadCardImage("yellow_+2.png"));

    	cardImages.put("draw4", DesignUtils.loadCardImage("draw4.png"));
    	cardImages.put("wildcard", DesignUtils.loadCardImage("wildcard.png"));
    }

    public Card(Color color, int points, String imageName) {
        this.color = color;
        this.points = points;
        this.imageName = imageName;
    }

    public Color getColor() {
        return color;
    }

    public int getPoints() {
        return points;
    }

    public ImageIcon getImage() {
        return cardImages.get(imageName);
    }

    public static ImageIcon getBackOfCardImage() {
        return backOfCardImage;
    }

    public abstract String getSymbol();
}