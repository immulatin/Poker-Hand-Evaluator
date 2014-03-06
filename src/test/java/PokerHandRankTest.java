import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class PokerHandRankTest {

    PokerHandRank pokerHandRank;

    @Before
    public void setUp() throws Exception {
        pokerHandRank = new PokerHandRank();
    }

    @After
    public void tearDown() throws Exception {
        pokerHandRank = null;
    }

    @org.junit.Test
    public void testInvalidSuit() throws Exception {
        String invalidNum = "5s 8h 10c 3a Ks";
        try {
            pokerHandRank.getRank(invalidNum);
            fail("Expected exception");
        } catch (InvalidHandException.InvalidSuit e){
            assertTrue(e.getMessage().contains("A is not a valid suit"));
        }
    }

    @Test
    public void testInvalidValue() throws Exception {
        String invalidNum = "5s 8h 10c 1s Ks";
        try {
            pokerHandRank.getRank(invalidNum);
            fail("Expected exception");
        } catch (InvalidHandException.InvalidCardValue e){
             assertTrue(e.getMessage().contains("1 is not a valid value"));
        }
    }

    @Test
    public void testDuplicateCards() throws Exception {
        String duplicateCards = "5s 10s 4h Kc 5s";
        try {
            pokerHandRank.getRank(duplicateCards);
            fail("Expected exception");
        } catch (InvalidHandException.DuplicateCards e){
            assertTrue(e.getMessage().contains("Card 5S is already in the hand"));
        }
    }

    @Test
    public void testWrongNumberCards() throws Exception {
        // No cards
        String duplicateCards = "";
        try {
            pokerHandRank.getRank(duplicateCards);
            fail("Expected exception");
        } catch (InvalidHandException e){
            System.out.println(e.getMessage());
            assertTrue(e.getMessage().contains("The hand provided is empty, please deal some cards first"));
        }

        // Too many
        String tooManyCards = "5s 10s 4h Kc 5s Jc";
        try {
            pokerHandRank.getRank(tooManyCards);
            fail("Expected exception");
        } catch (InvalidHandException e){
            assertTrue(e.getMessage().contains("Whoa, you can only have 5 but got dealt 6"));
        }

        // Too little
        String tooFewCards = "5s 10s 4h Kc";
        try {
            pokerHandRank.getRank(tooFewCards);
            fail("Expected exception");
        } catch (InvalidHandException e){
            assertTrue(e.getMessage().contains("Whoa, you can only have 5 but got dealt 4"));
        }

    }

    @Test
    public void testHighCard() throws Exception {
        // Make sure ace is high
        String highCard = "Ah Kd 2s 5c 9d";
        assertEquals("Ace High", pokerHandRank.getRank(highCard));

        // Make sure we don't wrap around on straights
        highCard = "Kh Ad 2c 3s 4c";
        assertEquals("Ace High", pokerHandRank.getRank(highCard));

        highCard = "3h 10d 2s 5c 9d";
        assertEquals("Ten High", pokerHandRank.getRank(highCard));
    }

    @Test
    public void testPair() throws Exception {
        String pairJacks = "Jh Kd 2s Jc 9d";
        assertEquals("Pair of Jacks", pokerHandRank.getRank(pairJacks));

        String pairThrees = "Kh 3d 3s Jc 9d";
        assertEquals("Pair of Threes", pokerHandRank.getRank(pairThrees));
    }

    @Test
    public void testTwoPair() throws Exception {
        String twoPair = "Jh 3d 3s Jc 9d";
        assertEquals("Two Pair", pokerHandRank.getRank(twoPair));
    }

    @Test
    public void testThreeOfAKind() throws Exception {
        String threePairs = "Ah 3d 9s 9c 9d";
        assertEquals("Three of a Kind", pokerHandRank.getRank(threePairs));
    }

    @Test
    public void testStraight() throws Exception {
        String straight = "8h Jd 10s 9c Qd";
        assertEquals("Straight", pokerHandRank.getRank(straight));

        String lowStraight = "Ah 2d 3s 4c 5d";
        assertEquals("Straight", pokerHandRank.getRank(lowStraight));

        String highStraight = "Ah Qd Js Kc 10d";
        assertEquals("Straight", pokerHandRank.getRank(highStraight));
    }

    @Test
    public void testFlush() throws Exception {
        String clubFlush = "3c 10c 2c 5c 9c";
        assertEquals("Flush", pokerHandRank.getRank(clubFlush));

        String spadeFlush = "5s 9s Js As 2s";
        assertEquals("Flush", pokerHandRank.getRank(spadeFlush));

        String heartFlush = "7h Qh 2h 3h 10h";
        assertEquals("Flush", pokerHandRank.getRank(heartFlush));

        String diamondFlush = "2d 8d Jd 4d Kd";
        assertEquals("Flush", pokerHandRank.getRank(diamondFlush));
    }

    @Test
    public void testFullHouse() throws Exception {
        String fullHouse = "Ad Ah 10s 10d 10c";
        assertEquals("Full House", pokerHandRank.getRank(fullHouse));
    }

    @Test
    public void testFourOfAKind() throws Exception {
        String sixPair = "Qd Qh Qs Qc 2s";
        assertEquals("Four of a Kind", pokerHandRank.getRank(sixPair));
    }

    @Test
    public void testStraightFlush() throws Exception {
        String straightFlush = "4h 8h 6h 5h 7h";
        assertEquals("Straight Flush", pokerHandRank.getRank(straightFlush));
    }
}
