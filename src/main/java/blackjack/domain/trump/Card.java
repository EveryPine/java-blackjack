package blackjack.domain.trump;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public final class Card {

    private static final Map<Suit, Map<Denomination, Card>> CACHE;

    private final Suit suit;
    private final Denomination denomination;

    static {
        CACHE = new LinkedHashMap<>();
        for (Suit suit : Suit.values()) {
            CACHE.put(suit, new LinkedHashMap<>());
        }
    }

    public static Card valueOf(final Suit suit, final Denomination denomination) {
        final Map<Denomination, Card> secondaryMap = CACHE.get(suit);

        if (!secondaryMap.containsKey(denomination)) {
            secondaryMap.put(denomination, new Card(suit, denomination));
        }
        return secondaryMap.get(denomination);
    }

    private Card(final Suit suit, final Denomination denomination) {
        this.suit = suit;
        this.denomination = denomination;
    }

    public Suit getSuit() {
        return suit;
    }

    public Denomination getDenomination() {
        return denomination;
    }

    public int toScore() {
        return denomination.toScore();
    }

    public boolean isAce() {
        return denomination == Denomination.ACE;
    }

    public boolean isEqualTo(final Card other) {
        return this.suit == other.suit && this.denomination == other.denomination;
    }

    @Override
    public boolean equals(final Object object) {
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        final Card card = (Card) object;
        return suit == card.suit && denomination == card.denomination;
    }

    @Override
    public int hashCode() {
        return Objects.hash(suit, denomination);
    }
}
