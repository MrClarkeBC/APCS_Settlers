/**
 * Card.java
 *
 * <code>Card</code> represents a playing card.
 */
public class Card {

    /**
     * String value that holds the suit of the card
     */
    private SOC.DEVCARDS type;

    /**
     * String value that holds the rank of the card
     */
    //private String rank;

    /**
     * int value that holds the point value.
     */
    //private int pointValue;

    /**
     * Creates a new <code>Card</code> instance.
     *
     * @param cardRank  a <code>String</code> value
     *                  containing the rank of the card
     * @param cardSuit  a <code>String</code> value
     *                  containing the suit of the card
     * @param cardPointValue an <code>int</code> value
     *                  containing the point value of the card
     */
    public Card(SOC.DEVCARDS cardType) {
        //initializes a new Card with the given rank, suit, and point value
        type = cardType;
        //suit = cardSuit;
        //pointValue = cardPointValue;
    }

    /**
     * Accesses this <code>Card's</code> suit.
     * @return this <code>Card's</code> suit.
     */
    public SOC.DEVCARDS type() {
        return type;
    }

}
