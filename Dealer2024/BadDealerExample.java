package Dealer2024;
import java.util.*;
public class BadDealerExample implements Dealer {
    private final Deck deck;
    private final Player player1;
    private final Player player2;
    private final PokerBoard pokerBoard;

    public BadDealerExample() {
        deck = new Deck();
        player1 = new Player();
        player2 = new Player();
        pokerBoard = new PokerBoard();
    }

    public Board dealCardsToPlayers() {
        for (int i = 0; i < 2; i++) {
            player1.addCard(deck.drawCard());
            player2.addCard(deck.drawCard());
        }
        return new Board(player1.toString(), player2.toString(), null, null, null);
    }

    public Board dealFlop(Board board) {
        for (int i = 0; i < 3; i++) {
            pokerBoard.addCard(deck.drawCard());
        }
        return new Board(board.getPlayerOne(), board.getPlayerTwo(), pokerBoard.toString(), null, null);
    }


    public Board dealTurn(Board board) {
        Card turnCard = deck.drawCard();
        pokerBoard.addCard(turnCard);
        return new Board(board.getPlayerOne(), board.getPlayerTwo(), board.getFlop(), turnCard.toString(), null);
    }


    public Board dealRiver(Board board) {
        Card riverCard = deck.drawCard();
        pokerBoard.addCard(riverCard);
        return new Board(board.getPlayerOne(), board.getPlayerTwo(), board.getFlop(), board.getTurn(), riverCard.toString());
    }


    public PokerResult decideWinner(Board board) {
        Player player1 = new Player();
        Player player2 = new Player();

        // Парсинг строк в карты
        for (String cardStr : board.getPlayerOne().replaceAll("[\\[\\]]", "").split(", ")) {
            player1.addCard(new Card(cardStr.substring(0, cardStr.length() - 1), cardStr.charAt(cardStr.length() - 1)));
        }
        for (String cardStr : board.getPlayerTwo().replaceAll("[\\[\\]]", "").split(", ")) {
            player2.addCard(new Card(cardStr.substring(0, cardStr.length() - 1), cardStr.charAt(cardStr.length() - 1)));
        }

        List<Card> boardCards = new ArrayList<>();
        if (board.getFlop() != null) {
            for (String cardStr : board.getFlop().split(" ")) {
                boardCards.add(new Card(cardStr.substring(0, cardStr.length() - 1), cardStr.charAt(cardStr.length() - 1)));
            }
        }
        if (board.getTurn() != null) {
            boardCards.add(new Card(board.getTurn().substring(0, board.getTurn().length() - 1), board.getTurn().charAt(board.getTurn().length() - 1)));
        }
        if (board.getRiver() != null) {
            boardCards.add(new Card(board.getRiver().substring(0, board.getRiver().length() - 1), board.getRiver().charAt(board.getRiver().length() - 1)));
        }

        int comparisonResult = PokerHandEvaluator.compareHands(player1.getCombinedHand(boardCards), player2.getCombinedHand(boardCards));
        if (comparisonResult > 0) {
            return PokerResult.PLAYER_ONE_WIN;
        } else if (comparisonResult < 0) {
            return PokerResult.PLAYER_TWO_WIN;
        } else {
            return PokerResult.DRAW;
        }
    }
}
