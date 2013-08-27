package algorithms;
import java.util.ArrayList;
import acm.program.*;

public class UnionFind extends ConsoleProgram{
	private ArrayList<Integer> nodes;
	
	public void UnionFind(int n){
		nodes = new ArrayList<Integer>();
		//Create an array of N elements with no connections (accept for N->N)
		for(int i = 0; i < n; i++){
			nodes.add(i, i);
			//System.out.println(i);
		}
	}
	
	public boolean hasUnion(int p, int q){
		if(nodes.get(p) == nodes.get(q)){
			return true;
		}else{
			return false;
		}
	}
	
	public void connectNodes(int p, int q){
		int node_1_group = nodes.get(p);
		int node_2_group = nodes.get(q);
		if(node_1_group == node_2_group){
			//Nothing for now, they are already linked 
			System.out.println("Already linked");
		}else{
			//Iterate over the array and change all the points in group q to p's group
			for(int i=0;i<nodes.size();i++){
				if(nodes.get(i) == node_2_group){
					nodes.set(i, node_1_group);
				}
			}
		}
	}
	
	public void printNodes(){
		for(int i = 0; i < nodes.size(); i++){
			System.out.println("Node: "+i+"\n"+"Connection Pool: "+nodes.get(i));
			System.out.println("\n");
		}
	}
	
	public void run(){
		//The testing harness for UnionFind
		UnionFind uf = new UnionFind();
		uf.UnionFind(1000);
		uf.connectNodes(6,8);
		uf.connectNodes(8,9);
		uf.connectNodes(40,9);
		uf.connectNodes(12,40);
		uf.printNodes();
		println("Enter points to check:");
		int l = readInt();
		int k = readInt();
		println(uf.hasUnion(l, k));
	}
}
