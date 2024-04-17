package GameLogic;

public class SkipCard extends Card {
    public SkipCard(Color color) {
    	super(color, 20, color.name().toLowerCase() + "_skip");
    }

    @Override
    public String getSymbol() {
        return "Skip";
    }
}