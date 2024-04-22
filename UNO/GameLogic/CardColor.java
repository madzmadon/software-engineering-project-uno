package GameLogic;

public enum CardColor {

     BLUE("blue"), GREEN("green"), RED("red"), YELLOW("yellow"), NONE("none");

    private String color;

    private CardColor(String color)
    {
        this.color = color;
    }

    // Returns the color of the card.
    public String get_color()
    {
        return color;
    }

    // Determines the card's color from a string.
    public static CardColor get_color_from_string(String color)
    {

        // Declare local variables.
        CardColor card = NONE;

        // Determine the card from the color.
        switch(color)
        {

            case "blue" -> card = BLUE;
            case "green" -> card = GREEN;
            case "red" -> card = RED;
            case "yellow" -> card = YELLOW;
            default -> card = NONE;

        }

        return card;

    }

}
