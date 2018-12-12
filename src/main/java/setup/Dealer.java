package setup;

import model.CardMatchResult;
import model.CardMatchResultHistory;
import party.Participant;
import model.Card;
import model.Suit;

public final class Dealer {

    private static Match match;

    private Dealer (){
    }

    public static Match initializeMatch() {
        System.out.println("Starting game...") ;
        Deck.initialize();

        match = Match.getInstance(determineCommanderSuit());
        fillCardSheets(match.getPlayer(), match.getOpponent());
        return match;
    }

    public static CardMatchResult processTurn(Card playerCard, Card opponentCard){
        Participant opponent = match.getOpponent();
        Participant player = match.getPlayer();
        Suit commander = match.getCommander();
        CardMatchResult cardMatchResult;

        if(player.isInitiator()){
            /** TODO: This is still not good, because we have no compiler support if we accidentally swap the first or last two arguments. */
            cardMatchResult = CardMatchResult.create(playerCard, opponentCard, commander, player, opponent);
        } else {
            cardMatchResult = CardMatchResult.create(opponentCard, playerCard, commander, opponent, player);
        }
        serveCards();
        System.out.println();
        System.out.println("Turn is over.");
        System.out.println();

        return cardMatchResult;
    }

    private static void fillCardSheets(Participant player, Participant opponent) {
        // TODO: Pretty simple approach. We can do better here.
        // TODO: Not sure if player.receiveCard is a good API.
        player.receive(initialServe());
        opponent.receive(initialServe());
        player.receive(initialServe());
        opponent.receive(initialServe());
        player.receive(initialServe());
        opponent.receive(initialServe());
    }

    private static Suit determineCommanderSuit(){
        return Deck.peekLast().getSuit();
    }

    private static Card initialServe(){
        return Deck.pull();
    }

    private static void serveCards(){
        CardMatchResultHistory.getInitiator().receive(Deck.pull());
        CardMatchResultHistory.getReactor().receive(Deck.pull());
    }
}
