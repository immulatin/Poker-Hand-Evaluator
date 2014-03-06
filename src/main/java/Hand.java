import java.util.*;

public class Hand {

    private static final int NUMBER_CARDS_PER_HAND = 5;

    private List<Card> cardsInHand = new ArrayList<Card>();

    public Hand(String hand) throws InvalidHandException {
        if(hand == null || hand.isEmpty())
            throw new InvalidHandException("The hand provided is empty, please deal some cards first");

        String[] cards = hand.split(" ");
        if (cards.length != NUMBER_CARDS_PER_HAND)
            throw new InvalidHandException("Whoa, you can only have " + NUMBER_CARDS_PER_HAND + " but got dealt " + cards.length);

        for (String cardString : cards) {
            Card card = new Card(cardString);

            if (!cardsInHand.contains(card))
                cardsInHand.add(card);
            else
                throw new InvalidHandException.DuplicateCards("Card " + card + " is already in the hand. Looks like you need a new deck..");
        }

        Collections.sort(cardsInHand, new PokerCardComparator()); // Sort from low to high
    }

    public List<Card> getCardsInHand() {
        return Collections.unmodifiableList(cardsInHand);
    }

    public Card getHighestCard(){
        return cardsInHand.get(cardsInHand.size()-1);
    }

    public int getNumberOfPairs(){
        return findPairCards().size();
    }

    public boolean isFlush() {
        Suit suit = cardsInHand.get(0).getSuit();
        for(Card card : cardsInHand){
            if(!card.getSuit().equals(suit))
                return false; // Once we find one suit that doesn't match return.
        }
        return true;
    }

    public boolean isStraight() {
        int numCards = cardsInHand.size();
        // Check to see if we have an Ace that needs to be use on the low-end straight
        if(getHighestCard().getCardValue().equals("A") && cardsInHand.get(0).getCardValue().equals("2")) {
            numCards--; // Don't check the last card since we know its an ace.
        }

        Integer expectedRank = PokerCardComparator.getRankForCard(cardsInHand.get(0));
        for(int i = 0; i < numCards; i++){
            Integer currRank = PokerCardComparator.getRankForCard(cardsInHand.get(i));
            if(!currRank.equals(expectedRank))
                return false;

            expectedRank++;
        }
        return true;
    }

    public Card[] getPairs(){
        HashSet<Card> uniqueCards = new HashSet<Card>(findPairCards());
        return uniqueCards.toArray(new Card[uniqueCards.size()]);
    }

    private List<Card> findPairCards(){
        List<Card> pairCards = new ArrayList<Card>();
        Card currCard;
        Card compareCard;
        for(int i = 0; i < cardsInHand.size(); i++){
            currCard = cardsInHand.get(i);
            for(int j = i + 1; j < cardsInHand.size(); j++){
                compareCard = cardsInHand.get(j);
                if(currCard.getCardValue().equals(compareCard.getCardValue()))
                    pairCards.add(compareCard);
            }
        }

        return pairCards;
    }
}
