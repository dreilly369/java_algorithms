/*
 * This is the Craps Table Class by Twarter369 
 * it handles tracking craps bet category and amounts.
 * it also check if dice totals fall into certain categories.
 * 
 */


package twarter.derived;

import java.util.*;

public class CrapsTable {

	ArrayList<String> spot = new ArrayList<String>();
	ArrayList<Integer> bets = new ArrayList<Integer>();
	
	public ArrayList<String> getSpot() {
		return spot;
	}

	int pointNum = 0;
	private int[] field = {2,3,4,9,10,11,12};
	private int[] pointNums = {4,5,6,8,9,10};
	
	public CrapsTable(){
		
	}

	public boolean isPoint0(){
		if(pointNum <= 0){
			return true;
		}else{
			return false;
		}
	}
	public int addBet(String spt, int bet){
		
		if(spot.contains(spt)){
			//adding to the existing bet on this spot
			System.out.println("Adding to bet: "+spt);
			int theSub = spot.indexOf(spt);
			Integer totalBet = bet+bets.get(theSub);
			bets.add(theSub, totalBet);
			System.out.println("Bet added");
			return 1;
		}else{
			System.out.println("Creating bet: "+spt);
			spot.add(spt);
			bets.add(spot.indexOf(spt), bet);
			System.out.println("Bet added at index: "+spot.indexOf(spt));
			return 0;
		}
	}
	
	public int[] getPntNums(){
		return pointNums;
	}

	public int[] getFieldNums(){
		return field;
	}
	
	public int getPointNum(){
		return pointNum;
	}
	
	public void setPointNum(int pnt){
		pointNum = pnt;
	}
	
	public void removeAll(ArrayList<String> strs){
		for(int i=0;i<strs.size();i++){
			String st = strs.get(i);
			if (getBetByCategory(st)>0) {
				removeBet(st);
			}
		}
	}
	
	public boolean isPointNum(int roll){
		for(int i=0;i<pointNums.length;i++){
			int chk = pointNums[i];
			if(roll==chk){
				System.out.println(roll+" comp to "+i);
				//There is a match. This is a Point Number
				return true;
			}
		}
		//Exited the loop without returning so this is not a point number
		return false;
	}
	
	public boolean isFieldNum(int roll){
		for(int i=0;i<field.length;i++){
			int chk = field[i];
			if(roll==chk){
				//There is a match. This is a field Number
				return true;
			}
		}
		//Exited the loop without returning so this is not a field number
		return false;
	}
	
	public int getBetByCategory(String cat){
		//Returns the amount of the bet if one is found. if none is found it 
		//returns -1.
		
		System.out.println("Retrieving bet: "+cat);
		for(int i =0; i<bets.size();i++){
			System.out.println("Retrieving bet: "+bets.get(i));
		}
			if(spot.contains(cat)){
				//There is a match. This bet exisis.
				int catInt = spot.indexOf(cat);
				System.out.println("bet found at: "+catInt);
				return bets.get(catInt);
			}else{
		//Exited the loop without returning so this bet isn't present
		return -1;
			}
	}
	
	public void removeBet(String cat){
		if(spot.contains(cat)){
			int catInd = spot.indexOf(cat);
			bets.remove(catInd);
			spot.remove(catInd);
		}
	}
	
}
