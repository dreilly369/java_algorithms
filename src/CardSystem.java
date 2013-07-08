import java.util.ArrayList;

import twarter.derived.DeckPlayingCards;
import acm.program.*;


public class CardSystem extends ConsoleProgram {

	public void run(){
		//This program uses the derived DeckplayingCards class to deal
		//5 hands of 5 cards each. The integer passed is the number of times
		//the deck is shuff
		DeckPlayingCards shuffled = new DeckPlayingCards(1000);
		println("Shuffled deck =================");
		ArrayList hand1= null;
		ArrayList hand2= null;
		ArrayList hand3= null;
		ArrayList hand4= null;
		ArrayList hand5= null;

		for(int i =0;i<5;i++){
			hand1.add(shuffled.drawCard());
			hand2.add(shuffled.drawCard());
			hand3.add(shuffled.drawCard());
			hand4.add(shuffled.drawCard());
			hand5.add(shuffled.drawCard());
		}
	}
}
