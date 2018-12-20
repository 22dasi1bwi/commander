package setup;

import model.*;
import party.Participant;

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
        PlayerCard cardForPlayer = new PlayerCard(playerCard.getSuit(), playerCard.getRank());
        OpponentCard cardForOpponent = new OpponentCard(opponentCard.getSuit(), opponentCard.getRank());

        CardMatchResult cardMatchResult = CardMatchResult.create(cardForPlayer, cardForOpponent, match.getCommander(), match.getPlayer(), match.getOpponent());
        serveCards();
        printEndOfTurn();

        return cardMatchResult;
    }

    private static void printEndOfTurn() {
        System.out.println();
        System.out.println("Turn is over.");
        System.out.println();
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
