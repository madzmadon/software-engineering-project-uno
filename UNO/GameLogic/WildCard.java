package GameLogic;

public class WildCard extends Card {
    public WildCard() {
    	super(null, 20, "wildcard");
    }

    @Override
    public Color getColor() {
        return null;
    }

    @Override
    public String getSymbol() {
        return "Wild";
    }
}
