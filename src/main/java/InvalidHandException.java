public class InvalidHandException extends Exception {

    public InvalidHandException(String message) {
        super(message);
    }

    public static final class InvalidCardValue extends InvalidHandException {
        public InvalidCardValue(String message) {
            super(message);
        }
    }

    public static final class InvalidSuit extends InvalidHandException {
        public InvalidSuit(String message) {
            super(message);
        }
    }

    public static final class DuplicateCards extends InvalidHandException {
       public DuplicateCards(String message){
           super(message);
       }
    }


}
