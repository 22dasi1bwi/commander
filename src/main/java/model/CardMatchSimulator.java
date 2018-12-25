package model;

import java.util.Comparator;

public class CardMatchSimulator {

    /** TODO: Do we really need a class for this? */
    public static Card simulate(CardSheet cardSheet, Card cardToBeat, Suit commanderSuit){
        return cardSheet.getAll().stream().max(Comparator.comparing(card -> card.beatsWithValue(cardToBeat, commanderSuit))).get();
    }
}
