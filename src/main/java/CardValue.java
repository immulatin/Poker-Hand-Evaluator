import java.util.*;

public class CardValue {

    private static Map<String, String> validValues = new HashMap<String, String>(13);

    static {
        validValues.put("2", "Two");
        validValues.put("3", "Three");
        validValues.put("4", "Four");
        validValues.put("5", "Five");
        validValues.put("6", "Six");
        validValues.put("7", "Seven");
        validValues.put("8", "Eight");
        validValues.put("9", "Nine");
        validValues.put("10", "Ten");
        validValues.put("J", "Jack");
        validValues.put("Q", "Queen");
        validValues.put("K", "King");
        validValues.put("A", "Ace");
    }

    private String value;
    private String name;

    public CardValue(String value) throws InvalidHandException.InvalidCardValue {
        if(!validValues.containsKey(value))
            throw new InvalidHandException.InvalidCardValue(value + " is not a valid value for a card. Please provide a valid one");

        this.value = value;
        this.name = validValues.get(value);
    }

    public String getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public static Map<String, String> getValidValues(){
        return Collections.unmodifiableMap(validValues);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CardValue cardValue = (CardValue) o;
        if (value != null ? !value.equals(cardValue.value) : cardValue.value != null) return false;

        return true;
    }

    @Override
    public String toString() {
        return getName();
    }
}