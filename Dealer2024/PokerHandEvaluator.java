package Dealer2024;

import java.util.*;

public class PokerHandEvaluator {
    private static final Map<String, Integer> handRanks = Map.of(
            "HighCard", 1,
            "Pair", 2,
            "TwoPair", 3,
            "ThreeOfAKind", 4,
            "Straight", 5,
            "Flush", 6,
            "FullHouse", 7,
            "FourOfAKind", 8,
            "StraightFlush", 9,
            "RoyalFlush", 10
    );

    public static int evaluateHand(List<Card> cards) {
        cards.sort(Collections.reverseOrder());

        boolean flush = isFlush(cards);
        boolean straight = isStraight(cards);

        if (flush && straight && cards.get(0).getRank().equals("A")) {
            return handRanks.get("RoyalFlush");
        } else if (flush && straight) {
            return handRanks.get("StraightFlush");
        } else if (isThreeOfAKind(cards, 4)) {
            return handRanks.get("FourOfAKind");
        } else if (hasFullHouse(cards)) {
            return handRanks.get("FullHouse");
        } else if (flush) {
            return handRanks.get("Flush");
        } else if (straight) {
            return handRanks.get("Straight");
        } else if (isThreeOfAKind(cards, 3)) {
            return handRanks.get("ThreeOfAKind");
        } else if (hasTwoPair(cards)) {
            return handRanks.get("TwoPair");
        } else if (isThreeOfAKind(cards, 2)) {
            return handRanks.get("Pair");
        } else {
            return handRanks.get("HighCard");
        }
    }

    private static boolean isFlush(List<Card> cards) {
        char suit = cards.get(0).getSuit();
        return cards.stream().allMatch(card -> card.getSuit() == suit);
    }

    private static boolean isStraight(List<Card> cards) {
        for (int i = 0; i < cards.size() - 1; i++) {
            int currentRank = Card.cardRanks.indexOf(cards.get(i).getRank());
            int nextRank = Card.cardRanks.indexOf(cards.get(i + 1).getRank());
            if (currentRank - nextRank != 1) return false;
        }
        return true;
    }

    private static boolean isThreeOfAKind(List<Card> cards, int n) {
        Map<String, Integer> rankCount = new HashMap<>();
        for (Card card : cards) {
            rankCount.put(card.getRank(), rankCount.getOrDefault(card.getRank(), 0) + 1);
        }
        return rankCount.containsValue(n);
    }

    private static boolean hasTwoPair(List<Card> cards) {
        return rankCounts(cards).values().stream().filter(count -> count == 2).count() == 2;
    }

    private static boolean hasFullHouse(List<Card> cards) {
        Map<String, Integer> counts = rankCounts(cards);
        return counts.containsValue(3) && counts.containsValue(2);
    }

    private static Map<String, Integer> rankCounts(List<Card> cards) {
        Map<String, Integer> rankCount = new HashMap<>();
        for (Card card : cards) {
            rankCount.put(card.getRank(), rankCount.getOrDefault(card.getRank(), 0) + 1);
        }
        return rankCount;
    }

    public static int compareHands(List<Card> hand1, List<Card> hand2) {
        int rank1 = evaluateHand(hand1);
        int rank2 = evaluateHand(hand2);

        if (rank1 != rank2) {
            return Integer.compare(rank1, rank2);
        }

        List<Card> sortedHand1 = new ArrayList<>(hand1);
        List<Card> sortedHand2 = new ArrayList<>(hand2);
        sortedHand1.sort(Collections.reverseOrder());
        sortedHand2.sort(Collections.reverseOrder());

        for (int i = 0; i < sortedHand1.size(); i++) {
            int compare = sortedHand1.get(i).compareTo(sortedHand2.get(i));
            if (compare != 0) {
                return compare;
            }
        }

        return 0;
    }
}
