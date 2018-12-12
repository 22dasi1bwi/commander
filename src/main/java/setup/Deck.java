package setup;

import model.Card;
import model.Rank;
import model.Suit;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;


final class Deck {

    private static Deque<Card> CARDS;

    public static void initialize(){
        if (CARDS == null){
            new Deck();
        }
    }

    private Deck (){
        Rank[] ranks = Rank.values();
        Suit[] suits = Suit.values();

        ArrayList deckAsList = new ArrayList(ranks.length * suits.length);
        for(Suit suit : suits) {
            for(Rank rank : ranks){
                deckAsList.add(new Card(suit, rank));
            }
        }
        Collections.shuffle(deckAsList);
        CARDS = new ArrayDeque<Card>(deckAsList);
    }

    static Card pull(){
        Card cardPulled = CARDS.poll();
        CARDS.remove(cardPulled);

        return cardPulled;
    }

    static Card peekLast(){
        return CARDS.peekLast();
    }
}
