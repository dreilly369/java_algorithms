package algorithms;

import java.lang.*;

import acm.program.ConsoleProgram;
import algorithms.Percolation;

public class PercolationStats extends ConsoleProgram{
	private int[] run_results;
	
	public void PercolationStats(int N, int T){
		run_results = new int[T];
		//Bounds checks
		if(N <= 0){
			throw new java.lang.IllegalArgumentException();
		}
		if(T <= 0){
			   throw new java.lang.IllegalArgumentException();
		}
		// perform T independent computational experiments on an N-by-N grid
		for(int i = 0; i<T; i++){
			Percolation coffee = new Percolation();
			int res = coffee.run(N);
			println(res+" sites open");
			println(coffee.printGrid());
			run_results[i] = res;
		}
	}
	   public double mean(){
		   double total = 0.0;
		   for(int i =0;i<run_results.length;i++){
			   total += run_results[i];
		   }
		   double mean = total/run_results.length;
		   return mean;
		   // sample mean of percolation threshold
	   }
	   public double stddev(double mean){
		   //Standard deviation of percolation threshold
		   double numer = 0.0;
		   for(int i=0;i<run_results.length;i++){
			   numer += Math.pow((run_results[i]-mean),2);
		   }
		   double denom = run_results.length-1;
		   return numer/denom;
	   }
	   public double confidenceLo(double stDev, double mean){
		   // returns lower bound of the 95% confidence interval
		   double numer = 1.96*stDev;
		   double denom = Math.sqrt(run_results.length);
		   return mean - (numer/denom);
	   }
	   public double confidenceHi(double stDev, double mean){
		// returns lower bound of the 95% confidence interval
		   double numer = 1.96*stDev;
		   double denom = Math.sqrt(run_results.length);
		   return mean + (numer/denom);
		   // returns upper bound of the 95% confidence interval
	   }
	   
	   public void run(){
		   int N = readInt("Enter N:");
		   int T = readInt("Enter T:");
		   PercolationStats(N,T);
		   println("Running...");
		   double mean = mean();
		   double stdDev = stddev(mean);
		   println(mean + " is the Mean");
		   println(stddev(mean) + " is the stdDev");
		   println(confidenceLo(stdDev,mean) + " is the Confidence Low");
		   println(confidenceHi(stdDev,mean) + " is the Confidence High");
	   }
	   /*public static void main(String[] args){
		   // test client, described below
	   }*/
}