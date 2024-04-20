package GameLogic;

import java.io.Serializable;

public class Card implements Serializable {

    private CardColor color;
    private CardFaceValue face_value;

    public Card(CardColor color, CardFaceValue face_value)
    {
        this.color = color;
        this.face_value = face_value;
    }

    // Returns the color of the card.
    public CardColor get_card_color()
    {
        return color;
    }

    // Returns the face value of the card.
    public CardFaceValue get_face_value()
    {
        return face_value;
    }

    // Returns the point value of the card.
    public int getPointValue()
    {

        return this.face_value.getValue();

    }

    // Returns the url of the card's image.
    public String getCardURL()
    {

        // Declare local variables.
        StringBuilder builder = new StringBuilder();

        // Determine if the card is a wildcard.
        if (color != CardColor.NONE)
        {

            // Add the color of the card.
            builder.append(color.get_color());

            // Add an underscore to the string.
            builder.append('_');

        }

        // Add the face value to the card.
        builder.append(face_value.getName());

        // Add the file extension to the end.
        builder.append(".png");

        return builder.toString();

    }

    // Determines if this and another card match either the color or face value.
    public boolean cardMatches(Card other)
    {
        return (color == other.get_card_color()) || (face_value == other.get_face_value());
    }

}