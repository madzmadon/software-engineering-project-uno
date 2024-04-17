package GameLogic;

public class WildDrawFourCard extends Card {
    public WildDrawFourCard() {
    	super(null, 20, "draw4");
    }

    @Override
    public Color getColor() {
        return null;
    }

    @Override
    public String getSymbol() {
        return "Wild Draw Four";
    }
}

