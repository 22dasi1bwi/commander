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
    public void reactWithHighestRankInsteadOfCommanderOnValuableCard(){
        Card playedCard = new Card(Suit.COPPE, Rank.CAVALLO);
        CardSheet playerCardSheet = buildCardSheet(player, playedCard, new Card(Suit.DENARI, Rank.ASSO), new Card(Suit.BASTONI, Rank.CINQUE));

        Card expectedCardToPlay = new Card(Suit.COPPE, Rank.RE);
        buildCardSheet(cut, expectedCardToPlay, new Card(Suit.DENARI, Rank.SEI), new Card(Suit.SPADE, Rank.DUE));

        Card bestCardToUse = cut.findBestStrategy(playerCardSheet, playedCard, COMMANDER);

        assertThat(bestCardToUse).isEqualTo(expectedCardToPlay);
    }

    @Test
    public void reactWithMajorOnMajorInsteadOfSuperLowCommander(){
        Card playedCard = new Card(Suit.COPPE, Rank.TRE);
        CardSheet playerCardSheet = buildCardSheet(player, playedCard, new Card(Suit.DENARI, Rank.ASSO), new Card(Suit.BASTONI, Rank.CINQUE));

        Card expectedCardToPlay = new Card(Suit.COPPE, Rank.ASSO);
        buildCardSheet(cut, expectedCardToPlay, new Card(Suit.SPADE, Rank.SEI), new Card(Suit.DENARI, Rank.QUATTRO));

        Card bestCardToUse = cut.findBestStrategy(playerCardSheet, playedCard, COMMANDER);

        assertThat(bestCardToUse).isEqualTo(expectedCardToPlay);
    }

    @Test
    public void reactWithLowestCommanderOnMajor (){
        Card playedCard = new Card(Suit.COPPE, Rank.TRE);
        CardSheet playerCardSheet = buildCardSheet(player, playedCard, new Card(Suit.BASTONI, Rank.ASSO), new Card(Suit.BASTONI, Rank.CINQUE));

        Card expectedCardToPlay = new Card(Suit.DENARI, Rank.CAVALLO);
        buildCardSheet(cut, expectedCardToPlay, new Card(Suit.SPADE, Rank.SEI), new Card(Suit.DENARI, Rank.ASSO));

        Card bestCardToUse = cut.findBestStrategy(playerCardSheet, playedCard, COMMANDER);

        assertThat(bestCardToUse).isEqualTo(expectedCardToPlay);
    }

    @Test
    public void reactWithNotValuableCardWithOnlyOneCommanderOnHandOnValuableCard (){
        Card playedCard = new Card(Suit.COPPE, Rank.CAVALLO);
        CardSheet playerCardSheet = buildCardSheet(player, playedCard, new Card(Suit.DENARI, Rank.ASSO), new Card(Suit.BASTONI, Rank.CINQUE));

        Card expectedCardToPlay = new Card(Suit.BASTONI, Rank.CINQUE);
        buildCardSheet(cut, expectedCardToPlay, new Card(Suit.SPADE, Rank.RE), new Card(Suit.DENARI, Rank.QUATTRO));

        Card bestCardToUse = cut.findBestStrategy(playerCardSheet, playedCard, COMMANDER);

        assertThat(bestCardToUse).isEqualTo(expectedCardToPlay);
    }

    @Test
    public void reactWithLowestRankCardOnUnbeatableCard(){
        Card playedCard = new Card(Suit.COPPE, Rank.CAVALLO);
        CardSheet playerCardSheet = buildCardSheet(player, playedCard, new Card(Suit.DENARI, Rank.ASSO), new Card(Suit.BASTONI, Rank.CINQUE));

        Card expectedCardToPlay = new Card(Suit.BASTONI, Rank.QUATTRO);
        buildCardSheet(cut, expectedCardToPlay, new Card(Suit.SPADE, Rank.SEI), new Card(Suit.SPADE, Rank.SETTE));

        Card bestCardToUse = cut.findBestStrategy(playerCardSheet, playedCard, COMMANDER);

        assertThat(bestCardToUse).isEqualTo(expectedCardToPlay);
    }

    @Test
    public void reactWithMinorCommanderInsteadOfLosingPointsOnMultipleCommandersInHand(){
        Card playedCard = new Card(Suit.DENARI, Rank.CINQUE);
        CardSheet playerCardSheet = buildCardSheet(player, playedCard, new Card(Suit.DENARI, Rank.ASSO), new Card(Suit.BASTONI, Rank.CINQUE));

        Card expectedCardToPlay = new Card(Suit.DENARI, Rank.DUE);
        buildCardSheet(cut, expectedCardToPlay, new Card(Suit.SPADE, Rank.RE), new Card(Suit.DENARI, Rank.QUATTRO));

        Card bestCardToUse = cut.findBestStrategy(playerCardSheet, playedCard, COMMANDER);

        assertThat(bestCardToUse).isEqualTo(expectedCardToPlay);
    }

    @Test
    public void reactWithNotValuableCardOnValuableCardIfTooMuchPointsWouldBeLost(){
        Card playedCard = new Card(Suit.DENARI, Rank.CINQUE);
        CardSheet playerCardSheet = buildCardSheet(player, playedCard, new Card(Suit.DENARI, Rank.ASSO), new Card(Suit.BASTONI, Rank.CINQUE));

        Card expectedCardToPlay = new Card(Suit.DENARI, Rank.DUE);
        buildCardSheet(cut, expectedCardToPlay, new Card(Suit.SPADE, Rank.RE), new Card(Suit.DENARI, Rank.QUATTRO));

        Card bestCardToUse = cut.findBestStrategy(playerCardSheet, playedCard, COMMANDER);

        assertThat(bestCardToUse).isEqualTo(expectedCardToPlay);
    }

    @Test
    public void reactWithMostRewardingCardOnNotValuableCard(){
        Card playedCard = new Card(Suit.COPPE, Rank.CINQUE);
        CardSheet playerCardSheet = buildCardSheet(player, playedCard, new Card(Suit.DENARI, Rank.ASSO), new Card(Suit.BASTONI, Rank.CINQUE));

        Card expectedCardToPlay = new Card(Suit.DENARI, Rank.ASSO);
        buildCardSheet(cut, expectedCardToPlay, new Card(Suit.COPPE, Rank.TRE), new Card(Suit.DENARI, Rank.CAVALLO));

        Card bestCardToUse = cut.findBestStrategy(playerCardSheet, playedCard, COMMANDER);

        assertThat(bestCardToUse).isEqualTo(expectedCardToPlay);
    }

    @Test
    public void reactWithLowestAndNotBeatingRankCardOnNotValuableCard(){
        Card playedCard = new Card(Suit.COPPE, Rank.CINQUE);
        CardSheet playerCardSheet = buildCardSheet(player, playedCard, new Card(Suit.DENARI, Rank.ASSO), new Card(Suit.BASTONI, Rank.CINQUE));

        Card expectedCardToPlay = new Card(Suit.BASTONI, Rank.SETTE);
        buildCardSheet(cut, expectedCardToPlay, new Card(Suit.DENARI, Rank.SETTE), new Card(Suit.SPADE, Rank.CAVALLO));

        Card bestCardToUse = cut.findBestStrategy(playerCardSheet, playedCard, COMMANDER);

        assertThat(bestCardToUse).isEqualTo(expectedCardToPlay);
    }

    @Test
    public void lol(){
        Card playedCard = new Card(Suit.SPADE, Rank.CAVALLO);
        CardSheet playerCardSheet = buildCardSheet(player, playedCard, new Card(Suit.DENARI, Rank.ASSO), new Card(Suit.BASTONI, Rank.CINQUE));

        Card expectedCardToPlay = new Card(Suit.SPADE, Rank.TRE);
        buildCardSheet(cut, new Card(Suit.DENARI, Rank.DUE), expectedCardToPlay, new Card(Suit.BASTONI, Rank.ASSO));

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
