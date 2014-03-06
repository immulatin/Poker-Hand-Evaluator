import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.fail;

public class PokerCardComparatorTest {

    @Test
    public void testRankForAllCards() throws Exception {
        Map<String,Integer> ranks = PokerCardComparator.getCardRanks();

        for(String value : CardValue.getValidValues().keySet()){
            if(!ranks.containsKey(value)){
                fail("Poker ranks does not contain rank for " + value);
            }
        }
    }
}
