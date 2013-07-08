package twarter.derived;

import java.util.Stack;

public class DeckPlayingCards {

	Stack theDeck = new Stack();
	Stack theDiscard = new Stack();
	//This constructor results in a brand new pack of cards 
	//arranged like they are in a new pack. 
	public DeckPlayingCards(){
		theDeck.add("A-C");
		theDeck.add("2-C");
		theDeck.add("3-C");
		theDeck.add("4-C");
		theDeck.add("5-C");
		theDeck.add("6-C");
		theDeck.add("7-C");
		theDeck.add("8-C");
		theDeck.add("9-C");
		theDeck.add("10-C");
		theDeck.add("J-C");
		theDeck.add("Q-C");
		theDeck.add("K-C");
		
		theDeck.add("A-H");
		theDeck.add("2-H");
		theDeck.add("3-H");
		theDeck.add("4-H");
		theDeck.add("5-H");
		theDeck.add("6-H");
		theDeck.add("7-H");
		theDeck.add("8-H");
		theDeck.add("9-H");
		theDeck.add("10-H");
		theDeck.add("J-H");
		theDeck.add("Q-H");
		theDeck.add("K-H");
		
		theDeck.add("A-S");
		theDeck.add("2-S");
		theDeck.add("3-S");
		theDeck.add("4-S");
		theDeck.add("5-S");
		theDeck.add("6-S");
		theDeck.add("7-S");
		theDeck.add("8-S");
		theDeck.add("9-S");
		theDeck.add("10-S");
		theDeck.add("J-S");
		theDeck.add("Q-S");
		theDeck.add("K-S");
		
		theDeck.add("A-D");
		theDeck.add("2-D");
		theDeck.add("3-D");
		theDeck.add("4-D");
		theDeck.add("5-D");
		theDeck.add("6-D");
		theDeck.add("7-D");
		theDeck.add("8-D");
		theDeck.add("9-D");
		theDeck.add("10-D");
		theDeck.add("J-D");
		theDeck.add("Q-D");
		theDeck.add("K-D");
	}
	//this version of the constructor takes an int
	//as the number of times to shuffle the deck before 
	//returning it for use.
	public DeckPlayingCards(int shuffles){
		theDeck.add("A-C");
		theDeck.add("2-C");
		theDeck.add("3-C");
		theDeck.add("4-C");
		theDeck.add("5-C");
		theDeck.add("6-C");
		theDeck.add("7-C");
		theDeck.add("8-C");
		theDeck.add("9-C");
		theDeck.add("10-C");
		theDeck.add("J-C");
		theDeck.add("Q-C");
		theDeck.add("K-C");
		
		theDeck.add("A-H");
		theDeck.add("2-H");
		theDeck.add("3-H");
		theDeck.add("4-H");
		theDeck.add("5-H");
		theDeck.add("6-H");
		theDeck.add("7-H");
		theDeck.add("8-H");
		theDeck.add("9-H");
		theDeck.add("10-H");
		theDeck.add("J-H");
		theDeck.add("Q-H");
		theDeck.add("K-H");
		
		theDeck.add("A-S");
		theDeck.add("2-S");
		theDeck.add("3-S");
		theDeck.add("4-S");
		theDeck.add("5-S");
		theDeck.add("6-S");
		theDeck.add("7-S");
		theDeck.add("8-S");
		theDeck.add("9-S");
		theDeck.add("10-S");
		theDeck.add("J-S");
		theDeck.add("Q-S");
		theDeck.add("K-S");
		
		theDeck.add("A-D");
		theDeck.add("2-D");
		theDeck.add("3-D");
		theDeck.add("4-D");
		theDeck.add("5-D");
		theDeck.add("6-D");
		theDeck.add("7-D");
		theDeck.add("8-D");
		theDeck.add("9-D");
		theDeck.add("10-D");
		theDeck.add("J-D");
		theDeck.add("Q-D");
		theDeck.add("K-D");
		
		shuffle(shuffles);
	}

	//Shuffles the deck by splitting it into 2 stacks and pseudo randomly
	//adding cards from one pack or the other 
	public void shuffle(int numReps){
		int packSize = theDeck.size()/2;
		//System.out.println(packSize+" is the pack size. "+numReps+" is the number of repititions");
		for (int h = 0; h <numReps; h++) {
			Stack pack1 = new Stack();
			for (int i = 0; i < packSize; i++) {
				Object addT = theDeck.pop();
				pack1.push(addT);
				//System.out.println(addT+" was added to pack1");
			}
			
		//	System.out.println(theDeck.size()+" is the deck size after the split.");
			Stack pack2 = new Stack();
			while(theDeck.isEmpty()==false) {
				Object addT = theDeck.pop();
				pack2.push(addT);
			//	System.out.println(addT+" was added to pack2 at "+pack2.size());
				
			}
			while (pack1.size() > 0 || pack2.size() > 0) {
				//System.out.println(pack1.size()+" in pack1 "+pack2.size()+" in pack2");
				double choice = Math.random();
			//	System.out.println(choice+" is the random");
				if (choice >= 0.5) {
					if (pack1.isEmpty() == false) {
						Object addT = pack1.pop();
						theDeck.push(addT);
						//System.out.println(addT+" was added from pack1");
					} else {
						Object addT = pack2.pop();
						theDeck.push(addT);
						//System.out.println(addT+" was added from pack2");
					}
				} else {
					if (pack2.isEmpty() == false) {
						Object addT = pack2.pop();
						theDeck.push(addT);
						//System.out.println(addT+" was added from pack2");
					} else {
						Object addT = pack1.pop();
						theDeck.push(addT);
						//System.out.println(addT+" was added from pack1");
					}
				}
			}
			
		}
		//SystemPrintDeck();
	}
	
	public void SystemPrintDeck(){
		for(int i =0; i<theDeck.size();i++){
			System.out.println(theDeck.elementAt(i));
		}
	}
	
	public Object drawCard(){
		return theDeck.pop();
	}
}
