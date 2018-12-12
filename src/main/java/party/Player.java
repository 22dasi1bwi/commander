package party;

import model.Card;

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
}
