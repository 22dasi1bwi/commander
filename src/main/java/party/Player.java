package party;

import model.Card;
import model.CardSheet;
import model.Suit;

public class Player extends Participant {

    private String name;

    public Player(){
        super();
        this.name = "Anonymous";
    }

    public Player(String name){
        super();
        this.name = name;
    }

    @Override
    public Card use(Card card) {
        getCardSheet().remove(card);
        return card;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Card findBestStrategy(CardSheet playerCardSheet, Card playerCard, Suit commander) {
        throw new UnsupportedOperationException("No need for programmatic evaluation, since the player knows best which card to play.");
    }
}
