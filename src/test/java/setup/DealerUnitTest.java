package setup;

import model.Card;
import model.CardMatchResult;
import model.Rank;
import model.Suit;
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
        Card playerCard = new Card(commander, Rank.CAVALLO);
        Card opponentCard = new Card(commander, Rank.DUE);

        CardMatchResult result = Dealer.processTurn(playerCard, opponentCard);

        assertThat(result.getWinner()).isEqualTo(player);
    }

    @Test
    public void withPositiveCardMatchResultForOpponent (){
        Participant opponent = match.getOpponent();
        opponent.setInitiator(true);
        Card playerCard = new Card(commander, Rank.CAVALLO);
        Card opponentCard = new Card(commander, Rank.RE);

        CardMatchResult result = Dealer.processTurn(playerCard, opponentCard);

        assertThat(result.getWinner()).isEqualTo(opponent);
    }
}
