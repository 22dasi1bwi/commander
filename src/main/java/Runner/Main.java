package Runner;

import model.Card;
import model.CardSheet;
import model.PlayerCard;
import party.Participant;
import setup.Dealer;
import setup.Match;

import java.util.Scanner;

public class Main {

    public static void main (String [] args){
        Match match = Dealer.initializeMatch();
        Participant player = match.getPlayer();
        Participant opponent = match.getOpponent();

        /** TODO: as a first step. */
        player.setInitiator(true);
        opponent.setInitiator(false);

        Scanner scanner = new Scanner(System.in);

        /** TODO: pretty simple approach here. Working with the first card of the card sheet. */
        while(!match.areHandsEmpty()){
            CardSheet playerCardSheet = player.getCardSheet();
            if(player.isInitiator()){
                System.out.println("Choose one card from your current hand: " + playerCardSheet);
                Card playerCard = playerCardSheet.get(scanner.nextInt());
                Card opponentCard = opponent.findBestStrategy(playerCardSheet, playerCard, match.getCommander());
                System.out.println("Opponent's card was: " + opponentCard);
                Dealer.processTurn(player.use(playerCard), opponent.use(opponentCard));
            } else {
                Card opponentCard = opponent.getCardSheet().get(0);
                System.out.println("Opponent's card was: " + opponentCard);
                System.out.println("Choose one card from your current hand: " + playerCardSheet);
                Card playerCard = playerCardSheet.get(scanner.nextInt());
                Dealer.processTurn(player.use(playerCard), opponent.use(opponentCard));
            }
        }
        match.printResult();
    }
}
