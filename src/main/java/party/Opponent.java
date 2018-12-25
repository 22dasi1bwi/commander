package party;

import model.Card;
import model.CardMatchSimulator;
import model.CardSheet;
import model.Suit;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import static java.util.stream.Collectors.toList;

/**
 * Should have full control over the card sheet of the opponent as well. We need that because we want to
 * evaluate the best game plan. As a first step we consider this an AI.
 */
public class Opponent extends Participant {

    @Override
    public Card findBestStrategy (CardSheet playerCardSheet, Card playerCard, Suit commanderSuit){
       // evaluate first which cards could beat the player card
        CardSheet copyOfPlayerCardSheet =  CardSheet.copyOf(playerCardSheet);
        copyOfPlayerCardSheet.remove(playerCard);

        List<Card> availableCards = getCardSheet().getAll();
        List<Card> cardsWhichWouldBeatPlayerCard = availableCards.stream().filter(card -> card.beats(playerCard, commanderSuit)).collect(toList());

        Card bestCardToUse;

        // TODO: Consider the gathered points as well -> breakout of usual algorithm; if card-match would win us the match, take it!
        if(cardsWhichWouldBeatPlayerCard.isEmpty()){
            // TODO: Only return commander if multiples are available
            bestCardToUse = availableCards.stream().min(Comparator.comparing(Card::getValue)).get();
            // TODO: future - card which potentially would we beaten by player (if AI is initiator in the future) and has lowest points
        } else {
            if (playerCard.isMajor() || playerCard.isValuable()){
                // This is ok, as a first approach, but we have to make this more intelligent.
                // TODO: If playerCard is not a major but lower points only, consider to play cards that could not beat that card as well!
                // TODO: We might want to consider to take the points if we have to commanders available
                bestCardToUse = cardsWhichWouldBeatPlayerCard.stream().filter(card -> card.getSuit().equals(playerCard.getSuit())).max(Comparator.comparing(Card::getValue)).orElse(getCardSheet().getLeastForSuit(commanderSuit).get());
            } else {
                Collection<Card> cardsWhichWouldNotBeatPlayerCard = availableCards.stream().filter(card -> !card.beats(playerCard, commanderSuit)).collect(toList());
                if(cardsWhichWouldNotBeatPlayerCard.isEmpty()){
                    // We have no way to play reactive here, since all of our cards beat the player card.
                    // Get the card which rewards us with the most points.
                    bestCardToUse = CardMatchSimulator.simulate(getCardSheet(), playerCard, commanderSuit);
                } else {
                    /** TODO: Should we respect the following scenario?
                     * COMMANDER: COPPE
                     * Opponent's cards: QUATTRO di SPADE, CINQUE di DENARI, CAVALLO di BASTONI*
                     * Player card is: CINQUE di BASTONI *
                     * Current bestCardToUse would be : QUATTRO di SPADE (because of the lowest value)
                     * Is CAVALLO di BASTONI the better option in this case? */
                    bestCardToUse = cardsWhichWouldNotBeatPlayerCard.stream().min(Comparator.comparing(Card::getValue)).get();
                }
            }
        }
        return bestCardToUse;
    }

    @Override
    public Card use(Card card) {
        getCardSheet().remove(card);
        return card;
    }

    @Override
    public String getName() {
        return "Artificial-Intelligence-00" + (int) (Math.random() * 10);
    }
}
