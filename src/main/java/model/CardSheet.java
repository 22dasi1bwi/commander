package model;

import java.util.ArrayList;
import java.util.List;

public class CardSheet {

    private static final int MAX_CARD_SHEET_SIZE = 3;

    private final List<Card> cards;

    public CardSheet(){
        this.cards = new ArrayList(MAX_CARD_SHEET_SIZE);
    }

    public void add(Card card) {
        if(cards.size() > MAX_CARD_SHEET_SIZE) {
            throw new IllegalStateException("You can't hold more than " + MAX_CARD_SHEET_SIZE + " cards!");
        }
        cards.add(card);
    }

    public void remove(Card card){
        cards.remove(card);
    }

    public Card get(int index){
        return cards.get(index);
    }

    public boolean isEmpty(){
        return cards.stream().filter(p -> p != null).count() == 0;
    }

    @Override
    public String toString(){
        return cards.toString();
    }
}
