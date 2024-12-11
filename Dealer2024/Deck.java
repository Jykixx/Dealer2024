package Dealer2024;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private final List<Card> cards;

    public Deck() {
        cards = new ArrayList<>();
        char[] suits = {'H', 'D', 'C', 'S'};
        for (String rank : Card.cardRanks) {
            for (char suit : suits) {
                cards.add(new Card(rank, suit));
            }
        }
        Collections.shuffle(cards);
    }

    public Card drawCard() {
        if (cards.isEmpty()) {
            throw new InvalidPokerBoardException("В колоде нет карт");
        }
        return cards.remove(0);
    }
}
