package GameLogic;

public class DrawTwoCard extends Card {
    public DrawTwoCard(Color color) {
        super(color, 20, color.name().toLowerCase() + "_+2");
    }

    @Override
    public String getSymbol() {
        return "Draw Two";
    }
}