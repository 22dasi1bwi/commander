package party;

import model.Card;
import model.CardSheet;
import model.Rank;
import model.Suit;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class OpponentUnitTest {

    private static final Suit COMMANDER = Suit.DENARI;

    private static Opponent cut;

    private static Player player;

    @BeforeClass
    public static void setup(){
        cut = new Opponent();
        player = new Player();
    }

    @Before
    public void clearCardSheets(){
        cut.getCardSheet().getAll().clear();
        player.getCardSheet().getAll().clear();
    }

    @Test
    public void reactWithHighestRankInsteadOfCommander(){
        Card playedCard = new Card(Suit.COPPE, Rank.CAVALLO);
        CardSheet playerCardSheet = buildCardSheet(player, playedCard, new Card(Suit.DENARI, Rank.ASSO), new Card(Suit.BASTONI, Rank.CINQUE));

        Card expectedCardToPlay = new Card(Suit.COPPE, Rank.RE);
        buildCardSheet(cut, expectedCardToPlay, new Card(Suit.DENARI, Rank.SEI), new Card(Suit.SPADE, Rank.DUE));

        Card bestCardToUse = cut.findBestStrategy(playerCardSheet, playedCard, COMMANDER);

        assertThat(bestCardToUse).isEqualTo(expectedCardToPlay);
    }

    @Test
    public void reactWithLowestRankCardOnUnbeatableCard(){
        Card playedCard = new Card(Suit.COPPE, Rank.CAVALLO);
        CardSheet playerCardSheet = buildCardSheet(player, playedCard, new Card(Suit.DENARI, Rank.ASSO), new Card(Suit.BASTONI, Rank.CINQUE));

        Card expectedCardToPlay = new Card(Suit.COPPE, Rank.QUATTRO);
        buildCardSheet(cut, expectedCardToPlay, new Card(Suit.SPADE, Rank.SEI), new Card(Suit.SPADE, Rank.SETTE));

        Card bestCardToUse = cut.findBestStrategy(playerCardSheet, playedCard, COMMANDER);

        assertThat(bestCardToUse).isEqualTo(expectedCardToPlay);
    }

    @Test
    public void reaactWithLowestRankCardOnUnbeatableCard(){
        Card playedCard = new Card(Suit.COPPE, Rank.CAVALLO);
        CardSheet playerCardSheet = buildCardSheet(player, playedCard, new Card(Suit.DENARI, Rank.ASSO), new Card(Suit.BASTONI, Rank.CINQUE));

        Card expectedCardToPlay = new Card(Suit.COPPE, Rank.QUATTRO);
        buildCardSheet(cut, expectedCardToPlay, new Card(Suit.SPADE, Rank.SEI), new Card(Suit.SPADE, Rank.SETTE));

        Card bestCardToUse = cut.findBestStrategy(playerCardSheet, playedCard, COMMANDER);

        assertThat(bestCardToUse).isEqualTo(expectedCardToPlay);
    }

    private static CardSheet buildCardSheet(Participant participant, Card card1, Card card2, Card card3){
        participant.receive(card1);
        participant.receive(card2);
        participant.receive(card3);

       return participant.getCardSheet();
    }
}
