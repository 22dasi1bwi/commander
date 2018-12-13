package model;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CardUnitTest {

    private static final Suit COMMANDER_SUIT = Suit.COPPE;

    @Test
    public void isHigherOnHigherValue(){
        Card higherValueCard = new Card(Suit.BASTONI, Rank.CAVALLO);
        Card lowerValueCard = new Card(Suit.SPADE, Rank.DUE);

        boolean result = higherValueCard.hasHigherRankThan(lowerValueCard);

        assertThat(result).isTrue();
    }

    @Test
    public void isLowerOnLowerValue(){
        Card higherValueCard = new Card(Suit.BASTONI, Rank.CAVALLO);
        Card lowerValueCard = new Card(Suit.SPADE, Rank.DUE);

        boolean result = lowerValueCard.hasHigherRankThan(higherValueCard);

        assertThat(result).isFalse();
    }

    @Test
    public void matchesCommanderSuit(){
        Card card = new Card(Suit.COPPE, Rank.CAVALLO);

        boolean result = card.matchesCommanderSuit(COMMANDER_SUIT);

        assertThat(result).isTrue();
    }

    @Test
    public void doesNotMatchCommanderSuit(){
        Card card = new Card(Suit.BASTONI, Rank.CAVALLO);

        boolean result = card.matchesCommanderSuit(COMMANDER_SUIT);

        assertThat(result).isFalse();
    }

    @Test
    public void beatsWithDifferentSuitsWithoutCommanderSuitInvolved(){
        Card reactorCard = new Card(Suit.BASTONI, Rank.DUE);
        Card initiatorCard = new Card(Suit.SPADE, Rank.CAVALLO);

        boolean result = reactorCard.beats(initiatorCard, COMMANDER_SUIT);

        assertThat(result).isFalse();
    }

    @Test
    public void doesNotBeatWithReactorCommanderSuit(){
        Card reactorCard = new Card(Suit.BASTONI, Rank.CAVALLO);
        Card initiatorCard = new Card(COMMANDER_SUIT, Rank.DUE);

        boolean result = reactorCard.beats(initiatorCard, COMMANDER_SUIT);

        assertThat(result).isFalse();
    }

    @Test
    public void doesNotBeatOnSameSuitButLowerRank(){
        Card reactorCard = new Card(Suit.BASTONI, Rank.CAVALLO);
        Card initiatorCard = new Card(Suit.BASTONI, Rank.RE);

        boolean result = reactorCard.beats(initiatorCard, COMMANDER_SUIT);

        assertThat(result).isFalse();
    }
}
