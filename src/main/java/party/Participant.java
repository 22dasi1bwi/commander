package party;

import model.Card;
import model.CardSheet;

import java.util.Collection;
import java.util.Stack;

public abstract class Participant {

    private boolean isInitiator;

    protected CardSheet cardSheet;

    protected final Stack<Card> wonCards = new Stack();

    protected Participant(){
        cardSheet = new CardSheet();
    }

    public void receive (Card card){
        cardSheet.add(card);
    }

    public CardSheet getCardSheet() {
        return cardSheet;
    }

    public void addWonCardsToStack(Collection<Card> wonCards){
        this.wonCards.addAll(wonCards);
    }

    public boolean isInitiator(){
        return isInitiator;
    }

    public void setInitiator (boolean init){
        isInitiator = init;
    }

    public Stack<Card> getWonCards(){
        return wonCards;
    }

    public abstract Card use(Card card);

    public abstract String getName();
}
