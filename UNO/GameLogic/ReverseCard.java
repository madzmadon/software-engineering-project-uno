package GameLogic;


public class ReverseCard extends Card {
    public ReverseCard(Color color) {
    	super(color, 20, color.name().toLowerCase() + "_reverse");
    }

    @Override
    public String getSymbol() {
        return "Reverse";
    }
}
