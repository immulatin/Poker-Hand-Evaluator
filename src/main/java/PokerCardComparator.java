import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.HashMap;

public class PokerCardComparator implements Comparator<Card> {

    private static Map<String, Integer> cardRanks = new HashMap<String, Integer>();

    static {
        cardRanks.put("2", 0);
        cardRanks.put("3", 1);
        cardRanks.put("4", 2);
        cardRanks.put("5", 3);
        cardRanks.put("6", 4);
        cardRanks.put("7", 5);
        cardRanks.put("8", 6);
        cardRanks.put("9", 7);
        cardRanks.put("10", 8);
        cardRanks.put("J", 9);
        cardRanks.put("Q", 10);
        cardRanks.put("K", 11);
        cardRanks.put("A", 12);
    }

    public int compare(Card card1, Card card2) {
        return getRankForCard(card1).compareTo(getRankForCard(card2));
    }

   public static Integer getRankForCard(Card card){
        return cardRanks.get(card.getCardValue());
   }

    public static Map<String, Integer> getCardRanks() {
        return Collections.unmodifiableMap(cardRanks);
    }
}
