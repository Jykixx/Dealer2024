package Dealer2024;

import java.util.Arrays;
import java.util.List;

public class Card implements Comparable<Card> {
    private final String rank;
    private final char suit;
    public static final List<String> cardRanks =
            Arrays.asList("2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A");

    public Card(String rank, char suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public String getRank() {
        return rank;
    }

    public char getSuit() {
        return suit;
    }

    public String toString() {
        return rank + suit;
    }

    public int compareTo(Card other) {
        return Integer.compare(cardRanks.indexOf(this.rank), cardRanks.indexOf(other.rank));
    }
}
