
public class PokerHandRank {

    public String getRank(String handString) throws InvalidHandException {
        Hand hand = new Hand(handString.toUpperCase());

        if (hand.isFlush() && hand.isStraight())
            return "Straight Flush";
        else if (hand.getNumberOfPairs() == 6)
            return "Four of a Kind";
        else if (hand.getNumberOfPairs() == 4)
            return "Full House";
        else if (hand.isFlush())
            return "Flush";
        else if (hand.isStraight())
            return "Straight";
        else if (hand.getNumberOfPairs() == 3)
            return "Three of a Kind";
        else if (hand.getNumberOfPairs() == 2)
            return "Two Pair";
        else if (hand.getNumberOfPairs() == 1)
            return "Pair of " + hand.getPairs()[0].getCardName() + "s";

        return hand.getHighestCard().getCardName() + " High";
    }

    public static void main(String[] args) throws InvalidHandException {
        if(args.length != 1){
            printHelp();
            System.exit(42);
        }

        PokerHandRank pokerHandRank = new PokerHandRank();
        String hand = args[0];
        System.out.println(hand + " (" + pokerHandRank.getRank(hand) + ")");
    }

    private static void printHelp() {
        String newline = System.getProperty("line.separator");
        String help = "Run this command with one poker hand to find out its value." + newline;
        help += "\ti.e. \"java -jar poker-exercise.jar 'As Ks Qs Js 10s'\"";
        System.err.println(help);
    }
}
