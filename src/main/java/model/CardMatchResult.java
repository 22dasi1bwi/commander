package model;

import party.Participant;

import java.util.Arrays;

public class CardMatchResult {

    private final Card initiatorCard;

    private final Card reactorCard;

    private final Participant winner;

    private final Participant loser;

    private CardMatchResult (Card initiator, Card reactor, Suit commander, Participant initiatorParticipant, Participant reactorParticipant){
        this.initiatorCard = initiator;
        this.reactorCard = reactor;
        boolean reactorWins = reactor.beats(initiator, commander);
        if(reactorWins){
            this.winner = reactorParticipant;
            this.loser = initiatorParticipant;
            reactorParticipant.setInitiator(true);
            initiatorParticipant.setInitiator(false);
            addCardsToStack();
        } else {
            this.winner = initiatorParticipant;
            this.loser = reactorParticipant;
            reactorParticipant.setInitiator(false);
            initiatorParticipant.setInitiator(true);
            addCardsToStack();
        }
        /** TODO: Could be also used for a kind of statistic. */
        CardMatchResultHistory.getInstance().append(this);

    }

    /** Increase readability for API usage. */
    public static CardMatchResult create (Card initiator, Card reactor, Suit commander, Participant initiatorParticipant, Participant reactorParticipant){
        return new CardMatchResult(initiator, reactor, commander, initiatorParticipant, reactorParticipant);
    }

    private void addCardsToStack(){
        winner.addWonCardsToStack(Arrays.asList(initiatorCard, reactorCard));
    }

    public Participant getWinner() {
        return winner;
    }

    public Participant getLoser() {
        return loser;
    }
}
