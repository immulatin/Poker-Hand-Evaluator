public class Card {

    private CardValue value;
    private Suit suit;

    public Card(String card) throws InvalidHandException {
        if(card.length() < 2 || card.length() > 3)
            throw new InvalidHandException("Invalid card: " + card + " Expecting a number and a suit (i.e. 5s Ah Kc)");
        this.setValue(card.substring(0, card.length() - 1));
        this.setSuit(card.substring(card.length() - 1, card.length())); // Use just the last character as the suit
    }

    public String getCardValue() {
        return value.getValue();
    }

    public String getCardName(){
        return value.getName();
    }

    public Suit getSuit() {
        return suit;
    }

    private void setValue(String value) throws InvalidHandException {
        this.value = new CardValue(value);
    }

    private void setSuit(String suit) throws InvalidHandException {
        try {
            this.suit = Suit.valueOf(suit);
        } catch (IllegalArgumentException e) {
            throw new InvalidHandException.InvalidSuit(suit + " is not a valid suit");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Card card = (Card) o;

        if (suit != card.suit) return false;
        if (value != null ? !value.equals(card.value) : card.value != null) return false;

        return true;
    }

    @Override
    public String toString() {
        return value.getValue() + suit;
    }
}
