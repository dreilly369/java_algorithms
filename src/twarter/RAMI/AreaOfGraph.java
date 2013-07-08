package twarter.RAMI;

import java.io.*;
import java.util.*;

import twarter.stats.GraphFromPointFile;

import acm.program.*;

public class AreaOfGraph extends ConsoleProgram {

	double trap_height;
	double minX = 0.0;
	double maxX = 0.0;
	double minY = 0.0;
	double maxY = 0.0;
	ArrayList<Double> x_arr = new ArrayList<Double>();
	ArrayList<Double> y_arr = new ArrayList<Double>();
	int num_traps = 0;
	String fn;
	
	
	public void run(){
		print("Enter the Point Graph File: ");
		fn = readLine();
		try {
			BufferedReader br = new BufferedReader(new FileReader(fn));
			try {
				String line = br.readLine();
				while(line!=null){
					System.out.println(line);
					char delim = ',';
					int div = line.indexOf(delim);
					Double newX = Double.valueOf(line.substring(0, div));
					Double newY = Double.valueOf(line.substring(div+1));
					x_arr.add(newX);
					y_arr.add(newY);
					line = br.readLine();
				}
				println("finished parsing file.");
				println("There are now:"+x_arr.size()+"/"+y_arr.size()
						+" points in the ArrayLists");
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				println("Could not read from file.");
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			println("\nCould not find file: "+fn);
		}
		
		findMinMax();
		setNumTraps(y_arr.size()-1);
		setTrapH((maxX-minX)/num_traps);
		println("The min X is: "+minX);
		println("The max X is: "+maxX);
		println("The H is set to: "+trap_height);
		println("The Area of this Graph is: "+areaOfPoints(y_arr,trap_height)
				+" units ^2");
	}

	private void findMinMax() {
		for(int i = 0; i< x_arr.size(); i++){
			double newX = x_arr.get(i);
			if(newX<minX){
				minX=newX;
			}
			if(newX>maxX){
				maxX=newX;
			}
			double newY = y_arr.get(i);
			if(newY<minY){
				minY=newY;
			}
			if(newY>maxY){
				maxY=newY;
			}
		}
	}

	public double areaOfTrap(double b1, double b2, double h){
		
		double aot = (b1+b2)*h/2;
		return aot ;
	}
	
	public double areaOfPoints(ArrayList<Double> ys, double h){
		double aop = 0.0;
		for(int i =0;i<ys.size()-1;i++){
			aop += areaOfTrap(ys.get(i),ys.get(i+1),trap_height);
		}
		
		return aop ;
	}
	
	public void setTrapH(double th){
		trap_height = th;
	}
	
	public void setNumTraps(int nt){
		num_traps = nt;
	}
}
