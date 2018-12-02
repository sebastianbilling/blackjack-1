package modul2.blackjack;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Blackjack blackjack = Blackjack.getInstance();
		Scanner sc = new Scanner(System.in);
		System.out.println("Välj ett av följande alternativ: \n 1. Hit \n 2. Stand \n 3. Reset \n 4. Avsluta");

		while (true) {
			int in = sc.nextInt();

			if (in == 1) {
				blackjack.hit();
				if (in == 3) {
					blackjack.reset();
				}
				else if (in == 4) {
					sc.close();
					System.out.println("Du har avslutat spelet. Välkommen tillbaka en annan gång!");
					break;
				}
			
			} else if (in == 2) {
				blackjack.stand();
				System.out.println("\n" + "Välj ett av följande alternativ: \n 3. Reset \n 4. Avsluta");

			} else if (in == 3) {
				blackjack.reset();
				System.out.println(
						"\n" + "Välj ett av följande alternativ: \n 1. Hit \n 2. Stand \n 3. Reset \n 4. Avsluta");
			} else if (in == 4) {
				sc.close();
				System.out.println("Du har avslutat spelet. Välkommen tillbaka en annan gång!");
				break;
			}
		}
	}
}