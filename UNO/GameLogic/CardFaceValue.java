package GameLogic;

public enum CardFaceValue {

    ZERO("0", 0), ONE("1", 1), TWO("2", 2), THREE("3", 3), FOUR("4", 4),
    FIVE("5", 5), SIX("6", 6), SEVEN("7", 7), EIGHT("8", 8), NINE("9", 9),
    PLUS_TWO("+2", 20), REVERSE("reverse", 20), SKIP("skip", 20), WILDCARD("wildcard", 50),
    PLUS_FOUR("+4", 50);

    private String name;
    private int value;

    private CardFaceValue(String name, int value)
    {
        this.name = name;
        this.value = value;
    }

    // Returns the number of points associated with the face value.
    public int getValue()
    {
        return value;
    }

    // Returns the name of the card.
    public String getName()
    {
        return name;
    }

    // Determines the card face value from a string.
    public static CardFaceValue getFaceValueFromString(String value)
    {

        // Declare local variables.
        CardFaceValue card = CardFaceValue.ZERO;

        // Determine the card based on the value.
        switch(value)
        {

            case "0" -> card = ZERO;
            case "1" -> card = ONE;
            case "2" -> card = TWO;
            case "3" -> card = THREE;
            case "4" -> card = FOUR;
            case "5" -> card = FIVE;
            case "6" -> card = SIX;
            case "7" -> card = SEVEN;
            case "8" -> card = EIGHT;
            case "9" -> card = NINE;
            case "+2" -> card = PLUS_TWO;
            case "reverse" -> card = REVERSE;
            case "skip" -> card = SKIP;

        }

        return card;

    }

    // Determines if the card is a special card.
    public boolean is_special_card()
    {
        return (this == PLUS_TWO) || (this == REVERSE) || (this == SKIP) || (this == WILDCARD);
    }

}
