package setup;

import party.Opponent;
import party.Participant;
import party.Player;
import model.Suit;

public final class Match {

    private final Participant player;

    private final Participant opponent;

    private final Suit commander;

    private static Match MATCH_INSTNACE;

    public static Match getInstance(Suit commander) {
        if(MATCH_INSTNACE == null){
            // TODO we should be able to handle other players instead AIs later on as well.
            return new Match(new Player(), new Opponent(), commander);
        }
        return MATCH_INSTNACE;
    }

    private Match (Participant player, Participant opponent, Suit commander) {
        this.player = player;
        this.opponent = opponent;
        this.commander = commander;
        System.out.println("Commander for match instance: " + this.toString() + " is: " + commander);
    }

    public Participant getPlayer() {
        return player;
    }

    public Participant getOpponent() {
        return opponent;
    }

    public void printResult(){
        System.out.println("Game has finished.");
        int playerPoints = player.getWonCards().stream().mapToInt(card -> card.getRank().getPoints()).sum();
        int opponentPoints = opponent.getWonCards().stream().mapToInt(card -> card.getRank().getPoints()).sum();
       if (playerPoints > opponentPoints){
           System.out.println(player.getName() + " has won! (Points: " + playerPoints + ")");
       } else if (playerPoints == opponentPoints) {
           System.out.println("DRAW! - Both participants gathered " + playerPoints + " points!");
       } else {
           System.out.println(opponent.getName() + " has won! (Points: " + opponentPoints + ")");
       }
    }

    public Suit getCommander() {
        return commander;
    }

    /** TODO: Not sure if I like that method in here. */
    public boolean areHandsEmpty(){
        return getPlayer().getCardSheet().isEmpty() && getOpponent().getCardSheet().isEmpty();
    }

    @Override
    public String toString(){
        return player.getName() + " VS. " + opponent.getName();
    }
}
