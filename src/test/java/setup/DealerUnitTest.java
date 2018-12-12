package setup;

import model.*;
import org.junit.Before;
import org.junit.Test;
import party.Participant;

import static org.assertj.core.api.Assertions.*;

public class DealerUnitTest {

    private static Match match;

    private static Suit commander;

    @Before
    public void initialize(){
        match = Dealer.initializeMatch();
        commander = match.getCommander();
    }

    @Test
    public void withPositiveCardMatchResultForPlayer (){
        Participant player = match.getPlayer();
        player.setInitiator(true);
        Card playerCard = new PlayerCard(commander, Rank.CAVALLO);
        Card opponentCard = new OpponentCard(commander, Rank.DUE);

        CardMatchResult result = Dealer.processTurn(playerCard, opponentCard);

        assertThat(result.getWinner()).isEqualTo(player);
    }

    @Test
    public void withPositiveCardMatchResultForOpponent (){
        Participant opponent = match.getOpponent();
        opponent.setInitiator(true);
        Card playerCard = new PlayerCard(commander, Rank.CAVALLO);
        Card opponentCard = new OpponentCard(commander, Rank.RE);

        CardMatchResult result = Dealer.processTurn(playerCard, opponentCard);

        assertThat(result.getWinner()).isEqualTo(opponent);
    }
}
