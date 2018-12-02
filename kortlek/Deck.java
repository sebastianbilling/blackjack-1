package modul2.blackjack.kortlek;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {
	ArrayList<Card> cards = new ArrayList<Card>();

	public Deck() {

		for (Suit s : Suit.values()) {
			for (Rank r : Rank.values()) {
				cards.add(new Card(r, s));

			}
		}
	}

	public Card draw() {

		Card card = cards.get(0);
		cards.remove(card);
		return card;
	}

	public void shuffle() {
		Collections.shuffle(cards);
	}

	public int size() {
		return cards.size();
	}

}
