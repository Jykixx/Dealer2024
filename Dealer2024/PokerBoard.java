package Dealer2024;

import java.util.ArrayList;
import java.util.List;

public class PokerBoard {
    private final List<Card> board;

    public PokerBoard() {
        board = new ArrayList<>();
    }

    public void addCard(Card card) {
        board.add(card);
    }


    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Card card : board) {
            sb.append(card.toString()).append(" ");
        }
        return sb.toString().trim();
    }
}
