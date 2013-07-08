package Algorithms;

import java.util.*;

import acm.program.*;

public class InsertSort extends ConsoleProgram {
	
	public void run(){
		System.out.println("Running");
		ArrayList<Integer> test = new ArrayList<Integer>();
		for(int i =0;i<10;i++){
			int rand = (int) (Math.random()*100);
			test.add(i, rand);
			System.out.println("Added "+rand);
		}
		System.out.println("Calling ");
		sortIntsAsc(test);
	}
	//This class uses a method of sorting similar to how someone might sort a deck of cards.
	public ArrayList<Integer> sortIntsAsc(ArrayList<Integer> in){
		int curr = 1;
		int swpWith = curr-1;
		ArrayList<Integer> out;
		
		while(curr<in.size()){
			swpWith = curr-1;
			while(in.get(curr)<in.get(swpWith)){
				if (swpWith>0) {
					swpWith--;
				}else{
					swpWith = 0;
				}
				
			}
			in.add(swpWith, in.get(curr));
			System.out.println("Added "+in.get(curr)+" to "+swpWith);
			curr++;
		}
		return null;
	}
}
