package modul2.blackjack;

import java.util.ArrayList;
import java.util.Scanner;

import modul2.blackjack.kortlek.Card;
import modul2.blackjack.kortlek.Deck;
import modul2.blackjack.kortlek.Rank;

public class Blackjack {
	int vinster, förluster, oavgjort, insats, bet = 0;
	private static Blackjack instance;

	ArrayList<Card> hand = new ArrayList<Card>();
	ArrayList<Card> dealer = new ArrayList<Card>();

	Deck deck = new Deck();

	public Blackjack() {
		deck.shuffle();
		hand.add(deck.draw());
		hand.add(deck.draw());
		if (score(hand) <= 21) {
			dealer.add(deck.draw());
		}
		else if (score(hand) == 21) {
			System.out.println("BLACKJACK! \nDu har vunnit " + bet *1.5 + "kr");
		}
	
		insats();
		bet();
		System.out.println("Du har " + hand.get(0) + ", " + hand.get(1) + " " + "(" + score(hand) + ")");
	
		}
	public static Blackjack getInstance() {
		if (instance == null) {
			instance = new Blackjack();
		}
		return instance;
	}

	public void hit() {
		hand.add(deck.draw());
		
		if(score(hand) <= 21){
			System.out.println("Du fick: " + hand.get(hand.size() - 1) + " " + "(" + score(hand) + ")");
			System.out.println(
					"\n" + "Välj ett av följande alternativ: \n 1. Hit \n 2. Stand \n 3. Reset \n 4. Avsluta");
		}
		if (score(hand) > 21) {
			if(stand() == "Dealern vann :(") {
				System.out.println("\n" + "Välj ett av följande alternativ: \n 3. Reset \n 4. Avsluta");
			}
		}
	}

	public String stand() {
		String förlust = "Dealern vann :(";
		String vinst = "Du vann!";
		String oavgjort = "Det slutade oavgjort!";
		
		
		System.out.println("Du fick: " + score(hand));

		while (score(dealer) < 17) {
			dealer.add(deck.draw());
		}

		String d = "";
		for (int i = 0; i < dealer.size(); i++) {
			d = d + dealer.get(i) + ", ";
		}
		System.out.println("Dealern fick: " + d + "(" + score(dealer) + ")");
		
		if (score(hand) > score(dealer) && score(hand) <= 21) {
		System.out.println("Du vann! \nDu har vunnit " + bet + " kr " + "och den totala insatsen är nu " + (bet * 2 + insats) + " kr." );
			insats = insats + bet * 2;
			vinster+= 1;
			return vinst;
		} 
		else if (score(dealer) > 21 && score(hand) <= 21) {
			System.out.println("Du vann! \nDu har vunnit " + bet + " kr " + "och den totala insatsen är nu " + (bet * 2 + insats) + " kr." );
			insats = insats + bet * 2;
			vinster+= 1;
			return vinst;
		} 
		
		else if (score(hand) < score(dealer) && score(dealer) <= 21) {
			System.out.println("Dealern vann :( \nDu har " + (insats - bet) + " kr kvar att spela för.");
			insats = insats-bet;
			förluster+= 1;
			return förlust;
		} else if (score(hand) == score(dealer)) {
			System.out.println("Dealern vann :( \nDu har " + (insats - bet) + " kr kvar att spela för.");
			insats = insats-bet;
			förluster+= 1;
			return förlust;
		} else if (score(hand) > 21 && score(dealer) <= 21) {
			System.out.println("Dealern vann :( \nDu har " + (insats - bet) + " kr kvar att spela för.");
			insats = insats-bet;
			förluster+= 1;
			return förlust;
		} 
		
		else if((16 < score(hand) && score(hand) < 20) &&(16 < score(dealer) && score(dealer) < 20)) {
			System.out.println("Dealern vann :( \nDu har " + (insats - bet)+ " kr kvar att spela för.");
			insats = insats-bet;
			förluster+= 1;
			return förlust;
		}
		
		else if((19 < score(hand) && score(hand) < 22) &&(19 < score(dealer) && score(dealer) < 22)) {
			System.out.println("Det slutade oavgjort!");
			System.out.println("Du behåller din insats motsvarande " + bet + " kr.");
			this.oavgjort+= 1;
			return oavgjort;
		}
			
		else {
			System.out.println("Dealern vann :( \nDu har " + (insats - bet)+ " kvar att spela för.");
			insats = insats-bet;
			förluster+= 1;
			return förlust;
		}
	}
	

	public void reset() {
		deck.shuffle();
		hand.removeAll(hand);
		hand.add(deck.draw());
		hand.add(deck.draw());

		dealer.removeAll(dealer);
		dealer.add(deck.draw());
		dealer.add(deck.draw());
		if (förluster > 0 && insats <= 0) {
		insats = 0;
		bet = 0;
		insats();
		bet();}
		
		else if (insats > 0) {
		bet();
		}
		
		System.out.println(
				"Spelet har resettats och du har fått korten " + hand.get(0) + ", " + hand.get(1) + " " + "(" + score(hand) + ")");
	}

	private int score(ArrayList<?> hand) {
		int score = 0;

		for (int i = 0; i < hand.size(); i++)
			if (((Card) hand.get(i)).getRank() == Rank.ACE) {
				score += 11;
			} else if (((Card) hand.get(i)).getRank() == Rank.TWO) {
				score += 2;
			} else if (((Card) hand.get(i)).getRank() == Rank.THREE) {
				score += 3;
			} else if (((Card) hand.get(i)).getRank() == Rank.FOUR) {
				score += 4;
			} else if (((Card) hand.get(i)).getRank() == Rank.FIVE) {
				score += 5;
			} else if (((Card) hand.get(i)).getRank() == Rank.SIX) {
				score += 6;
			} else if (((Card) hand.get(i)).getRank() == Rank.SEVEN) {
				score += 7;
			} else if (((Card) hand.get(i)).getRank() == Rank.EIGHT) {
				score += 8;
			} else if (((Card) hand.get(i)).getRank() == Rank.NINE) {
				score += 9;
			} else if (((Card) hand.get(i)).getRank() == Rank.TEN) {
				score += 10;
			} else if (((Card) hand.get(i)).getRank() == Rank.JACK) {
				score += 10;
			} else if (((Card) hand.get(i)).getRank() == Rank.QUEEN) {
				score += 10;
			} else if (((Card) hand.get(i)).getRank() == Rank.KING) {
				score += 10;
			}

		return score;
	}
	
	public int insats() {
		Scanner insatsin = new Scanner(System.in);
		System.out.println("Välkommen till Blackjack-spelet!\nAnge hur mycket du vill lägga in som insats:");
		insats = insatsin.nextInt();
		return insats;

	}
	public int bet() {
		Scanner betin = new Scanner(System.in);
		System.out.println("Ange hur mycket av din insats du vill betta med:");
		this.bet = betin.nextInt();
		return bet;
	}
}