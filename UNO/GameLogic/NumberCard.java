package GameLogic;

public class NumberCard extends Card {
    private int number;

    public NumberCard(Color color, int number) {
        super(color, number, color.name().toLowerCase() + "_" + number);
        this.number = number;
    }

    @Override
    public String getSymbol() {
        return String.valueOf(number);
    }
}