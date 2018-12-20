package party;

import model.Card;
import model.CardSheet;
import model.PlayerCard;
import model.Suit;

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
        Card bestCardToUse = availableCards.get(0);

        if(cardsWhichWouldBeatPlayerCard.isEmpty()){
            // first step - return card with least value
            bestCardToUse = availableCards.stream().min(Comparator.comparing(Card::getRank)).get();
            // TODO: future - card which potentially would we beaten by player (if AI is initiator in the future) and has lowest points

        } else {
            // judge if it's worth to beat it
            if (playerCard.isValuable()){
                // TODO: either highest rank of matching suit or least commander
                bestCardToUse = cardsWhichWouldBeatPlayerCard.stream().max(Comparator.comparing(Card::getRank)).orElse(getCardSheet().getLeastForSuit(commanderSuit).get());
            } else {
               // evaluate if the cards which would not beat the player's card are too much of a loss to use. In that case we would beat the card anyway.
                List<Card> cardsWhichWouldNotBeatPlayersCard = availableCards.stream().filter(cardsWhichWouldBeatPlayerCard::contains).collect(toList());
//                cardsWhichWouldNotBeatPlayersCard.stream().filter(Card::isMajor)
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
