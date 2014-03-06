

public enum Suit {
    H ("Hearts"),
    S ("Spades"),
    C ("Clubs"),
    D ("Diamonds");

    private final String name;

    private Suit(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
