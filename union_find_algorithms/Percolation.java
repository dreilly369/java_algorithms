package algorithms;
import acm.graphics.*;
import acm.io.IOConsole;
import acm.program.*;
import algorithms.QuickUnion;

import java.awt.Color;
import java.util.ArrayList;

public class Percolation extends ConsoleProgram{
	private static final int SCREEN_H = 600;
	private static final int SCREEN_W = 600;
	private int[][] nodes;
	private boolean[][] opened;
	private boolean[][] full;
	private QuickUnion qu;
	private int n;
	private IOConsole cs;
	
	
	public Percolation Percolation(int N){
		// create N-by-N grid, with all sites blocked
		nodes = new int[N][N];//All sites start blocked
		opened = new boolean[N][N];//is site 
		full = new boolean[N][N];//list of open sites
		//System.out.println(N);
		this.n = N;
		qu = new QuickUnion();
		qu.QuickUnion(N);
		for(int i=0;i<n;i++){
			for(int j=0;j<n;j++){
				opened[i][j] = false;//All sites start blocked
			}
		}
		return this;
	}
   
	public void open(int i, int j){
		// open site (row i, column j) if it is not already
		//System.out.println("Opening("+i+","+j+")");
		if(this.isOpen(i,j)){
			return;
		}else{
			println("Opening("+i+","+j+")");
			opened[i][j] = true;
			int spot = (n*i)+j;
			//Connect to Spot above
			if(i>0){
				if(this.isOpen(i-1,j)){
					int newspot = (n*(i-1))+j;
					println("\tConnecting ("+spot+","+newspot+")");
					if(isFull(i-1,j)){
						full[i][j] = true;
						println("\tFilling ("+spot+","+newspot+") from above");
					}else{
						println("\tNot Filling ("+spot+","+newspot+") from above");
					}
					qu.connectNodes(spot,newspot);
				}
			}
			
			//Connect to Spot below
			if(i<(n-1)){
				if(this.isOpen(i+1,j)){
					int newspot = (n*(i+1))+j;
					println("\tConnecting ("+spot+","+newspot+")");
					if(isFull(i+1,j)){
						full[i][j] = true;
						println("\tFilling ("+spot+","+newspot+") from below");
					}else{
						println("\tNot Filling ("+spot+","+newspot+") from below");
					}
				}
			}

			//Connect to Spot to the left
			if(j>0){
				if(this.isOpen(i,j-1)){
					int newspot = (n*i)+(j-1);
					println("\tConnecting ("+spot+","+newspot+")");
					if(isFull(i,j-1)){
						full[i][j] = true;
						println("\tFilling ("+spot+","+newspot+") from left");
					}else{
						println("\tNot Filling ("+spot+","+newspot+") from left");
					}
				}
			}else{
				//System.out.println(j+" !> 0");
			}
			if(j<(n-1)){
				//Connect to Spot to the right
				if(this.isOpen(i,j+1)){
					int newspot = (n*i)+(j+1);
					println("\tConnecting ("+spot+","+newspot+")");
					if(isFull(i,j+1)){
						full[i][j] = true;
						println("\tFilling ("+spot+","+newspot+") from right");
					}else{
						println("\tNot Filling ("+spot+","+newspot+") from right");
					}
				}
			}
		}
	}
   
	public boolean isOpen(int i, int j){
		//Bounds checking
		if(i<0 || i>=this.n || j<0 || j>=this.n){
			throw new java.lang.ArrayIndexOutOfBoundsException();
		}
		//println("("+i+","+j+")"+opened[i][j]);
		return opened[i][j];
	}
   
	public boolean isFull(int i, int j){
		//Bounds checking
		if(i<0 || i>=this.n || j<0 || j>=this.n){
			throw new java.lang.ArrayIndexOutOfBoundsException();
		}
		// is site (row i, column j) full?
		if(i == 0 && isOpen(i,j)){
			//println("\t("+i+","+j+") is full");
			return true;//All top row sites are full if open
		}
		//println("\t("+i+","+j+") full = "+full[i][j]);
		return full[i][j];
	}
  
	// given an N-by-N matrix of open sites, return an N-by-N matrix
    // of sites reachable from the top
    public boolean[][] flow(boolean[][] open) {
        int N = open.length;
        boolean[][] full = new boolean[N][N];
        for (int j = 0; j < N; j++) {
            flow(open, full, 0, j);
        }
        return full;
    }
    
	// determine set of full sites using depth first search
    public void flow(boolean[][] open, boolean[][] full, int i, int j) {
        int N = open.length;

        // base cases
        if (i < 0 || i >= N) return;    // invalid row
        if (j < 0 || j >= N) return;    // invalid column
        if (!open[i][j]) return;        // not an open site
        if (full[i][j]) return;         // already marked as full

        // mark i-j as full
        full[i][j] = true;

        flow(open, full, i+1, j);   // down
        flow(open, full, i, j+1);   // right
        flow(open, full, i, j-1);   // left
        flow(open, full, i-1, j);   // up
    }
    
	public boolean percolates() {
		// does the system percolate?
		int i = n-1;
		for(int j=0;j<n;j++){
			if(full[i][j]){
				return true;
			}
		}
		return false;
	}       
	
	public void randOpen(){
		double rand_prob = Math.random();
		double break_point = 0.1;
		println("Rolled: "+rand_prob);
		if(rand_prob>break_point){
			int rand_i = (int) Math.round(Math.random() * (this.n-1));
			int rand_j = (int) Math.round(Math.random() * (this.n-1));
			println("Opening: "+rand_i+","+rand_j);
			System.out.println("Opening: "+rand_i+","+rand_j);
			open(rand_i,rand_j);
			this.full = flow(opened);
		}
	}
	
	public int countOpenSites(){
		//println(opened.length+" number of sites");
		int count = 0;
		for(int i=0;i<opened.length;i++){
			for(int j=0;j<opened.length;j++){
				if(isOpen(i,j)){
					count++;
				}
			}
		}
		return count;
	}
	
	public String printGrid(){
		String line = "";
		for(int i=0;i<n;i++){
			for(int j=0;j<n;j++){
				if(isFull(i,j)&&isOpen(i,j)){
					line += "W ";
				}else if(!isFull(i,j)&&isOpen(i,j)){
					line += "O ";
				}else{
					line += "X ";
				}
			}
			line += "\n";
		}
		return line;
	}
	
	public void run(){
		this.run(15);
	}
	
	public int run(int N){
		Percolation model;
		model = Percolation(N);
		
		//println(qu.printNodes());
		int runs = 0;
		
		while(!this.percolates()){
			boolean stat = model.percolates();
			println("Runs: "+runs);
			runs++;
			randOpen();
			println("Open Sites: "+countOpenSites()+"/"+this.n*this.n);
			println("Status: "+stat);
			println("\n");
			printGrid();
			println("\n");
		}
		return countOpenSites();
		//println(qu.printNodes());
		//println(qu.printNodes());
	}
}