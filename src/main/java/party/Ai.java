package party;

import model.Card;

/**
 * AI. Should have full control over the card sheet of the opponent as well. We need that because we want to
 * evaluate the best game plan.
 */
public class Ai extends Participant {

    @Override
    public Card use(Card card) {
        getCardSheet().remove(card);
        return card;
    }

    @Override
    public String getName() {
        return "Artificial-Intelligence-007";
    }
}
