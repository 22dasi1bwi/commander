package model;

public final class Card {

    private final Suit suit;

    private final Rank rank;

    public Card (Suit suit, Rank rank){
        this.suit = suit;
        this.rank = rank;
    }

    boolean hasHigherRankThan(Card other){
        return rank.getValue() > other.rank.getValue();
    }

    boolean matchesCommanderSuit(Suit commanderSuit){
        return suit.equals(commanderSuit);
    }

    /** Decides whether this instance beats the passed card instance, which means the passed instance is the 'initiator-card'.
     * Initiator cards means 'played first'.
     * The card is beaten if the given instance ...
     * 1) is higher when both cards have the same suit.
     * 2) the suit of the passed instance does not match the commander suit.
     */
    public boolean beats(Card initiatorCard, Suit commanderSuit){
        Suit reactorSuit = initiatorCard.suit;
        boolean isSameSuit = reactorSuit.equals(suit);
        if(isSameSuit){
            return hasHigherRankThan(initiatorCard);
        } else {
            return this.matchesCommanderSuit(commanderSuit);
        }
    }

    public Suit getSuit() {
        return suit;
    }

    public Rank getRank() {
        return rank;
    }

    @Override
    public String toString(){
        return "(" + rank + " di " + suit + ")";
    }

}
