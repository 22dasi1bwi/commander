package model;

import party.Participant;

import java.util.ArrayList;
import java.util.List;

public final class CardMatchResultHistory {

    private static CardMatchResultHistory CARD_MATCH_RESULT_HISTORY_INSTANCE;

    private static final List<CardMatchResult> cardMatchResults = new ArrayList<>();

    private CardMatchResultHistory(){
    }

    static CardMatchResultHistory getInstance(){
        if(CARD_MATCH_RESULT_HISTORY_INSTANCE == null){
            return new CardMatchResultHistory();
        } else{
            return CARD_MATCH_RESULT_HISTORY_INSTANCE;
        }
    }

    static void append (CardMatchResult cardMatchResult) {
        cardMatchResults.add(cardMatchResult);
    }

    public static Participant getInitiator(){
        return cardMatchResults.get(cardMatchResults.size() - 1).getWinner();
    }

    public static Participant getReactor(){
        return cardMatchResults.get(cardMatchResults.size() - 1).getLoser();
    }
}
