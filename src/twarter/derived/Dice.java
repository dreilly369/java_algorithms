package twarter.derived;

import java.util.ArrayList;

public class Dice {
/*
 * This class allows you to call it with an int representing the
 * number of sides and an int representing the number of dice to roll
 * The pre-set is (2) 6 sided die, but can be set as low (1) 2 sided coin.
 */
	private int sides = 6;
	private int numDie = 2;
	
	public Dice(int numD, int numS){
		if(numS>1){
			if (numS<=100) {
				sides = numS;
			}else{
				System.out.println("Too many sides passed. using Default");
			}
		}else{
			System.out.println("Not enough sides passed. using Default");
		}
		if(numD>0){
			numDie = numD;
		}else{
			System.out.println("Not enough die passed. Using Default");
		}
		
	}
	
	/**
	 * @return the sides
	 */
	public int getSides() {
		return sides;
	}

	/**
	 * @param sides the sides to set
	 */
	public void setSides(int sds) {
		sides = sds;
	}

	/**
	 * @return the numDie
	 */
	public int getNumDie() {
		return numDie;
	}

	/**
	 * @param numDie the numDie to set
	 */
	public void setNumDie(int numDie) {
		this.numDie = numDie;
	}

	public ArrayList<Integer> roll(){
		ArrayList<Integer> die = new ArrayList<Integer>();
		for(int i = 0; i<numDie;i++){
			int rolled = (int)(Math.random()*6)+1;//Gives the possible answers 1-6
			System.out.println("rolled "+rolled);
			die.add(rolled);
		}
		System.out.println(die.size()+" is the num of die rolled.");
		return die ;
	}
}
