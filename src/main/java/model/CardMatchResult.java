package model;

import party.Opponent;
import party.Participant;
import party.Player;

import java.util.Arrays;

public class CardMatchResult {

    private final Card initiatorCard;

    private final Card reactorCard;

    private Participant winner;

    private Participant loser;

    private CardMatchResult (PlayerCard playerCard, OpponentCard opponentCard, Suit commander, Player player, Opponent opponent){
        /** TODO: Can we improve this somehow? */
        if (player.isInitiator()){
            this.initiatorCard = playerCard;
            this.reactorCard = opponentCard;
            if(reactorCard.beats(initiatorCard, commander)){
                setWinner(opponent);
                setLoser(player);
            } else {
                setWinner(player);
                setLoser(opponent);
            }
        } else {
            this.initiatorCard = opponentCard;
            this.reactorCard = playerCard;
            if(reactorCard.beats(initiatorCard, commander)){
                setWinner(player);
                setLoser(opponent);
            } else {
                setWinner(opponent);
                setLoser(player);
            }
        }
        /** TODO: Could be also used for a kind of statistic. */
        CardMatchResultHistory.getInstance().append(this);
    }

    /** Increase readability for API usage. */
    public static CardMatchResult create (Card playerCard, Card opponentCard, Suit commander, Participant player, Participant opponent){
        return new CardMatchResult((PlayerCard) playerCard, (OpponentCard) opponentCard, commander, (Player) player, (Opponent) opponent);
    }

    private void setWinner(Participant participant){
        this.winner = participant;
        participant.setInitiator(true);
        addCardsToStack();
    }

    private void setLoser (Participant participant){
        this.loser = participant;
        participant.setInitiator(false);
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
