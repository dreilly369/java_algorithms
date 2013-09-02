package algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import acm.program.*;

public class QuickUnion{
	//nodes.get(int index) returns the root node of the indexed elements tree
	private  ArrayList<Integer> nodes;
	private int treeSz[];//Holds the root_element_id -> tree_size
	
	/**
	 * @return the treeSz
	 */
	public int[] getTreeSz() {
		return this.treeSz;
	}

	public void QuickUnion(int n){
		this.nodes = new ArrayList<Integer>();
		this.treeSz = new int[n*n];
		//Create an array of N elements with no connections (accept for N->N)
		for(int i = 0; i < (n*n); i++){
			this.nodes.add(i, i);
			this.treeSz[i] = 1;
			//System.out.println(this.treeSz[i] + " qu");
		}
	}
	
	public boolean union(int p, int q){
		//System.out.println("Checking for Union between: "+p+","+q);
		if(root(p) == root(q)){
			//System.out.println("\tUnited");
			return true;
		}else{
			//System.out.println("\tNot United");
			return false;
		}
	}
	
	public void connectNodes(int p, int q){
		System.out.println("QU Connecting: ("+p+","+q+")");
		int node_1_group = root(p);//Root of the First Tree
		int node_2_group = root(q);//Root of the Second Tree
		if(node_1_group == node_2_group){
			//Nothing for now, they are already linked 
			System.out.println("Already linked");
		}else{
			//Set the smaller trees root to the larger trees root.
			int tree_1_sz = this.treeSz[node_1_group];
			int tree_2_sz = this.treeSz[node_2_group];
			int new_tree_sz = tree_1_sz+tree_2_sz;//Add the two trees!
			
			if(tree_1_sz<tree_2_sz){
				System.out.println("\tMoving tree 1 ("+tree_1_sz+") to tree two ("+tree_2_sz+")");
				this.nodes.set(p, node_2_group);	
				this.treeSz[node_2_group] = new_tree_sz;
			}else{
				this.nodes.set(q, node_1_group);	
				this.treeSz[node_1_group] = new_tree_sz;
				System.out.println("\tMoving tree two ("+tree_2_sz+") to tree one ("+tree_1_sz+")");
			}
		}
	}
	
	public String printNodes(){
		String ret = "";
		for(int i = 0; i < this.nodes.size(); i++){
			ret += "Node: "+i+"\n"+"\tRoot: "+this.nodes.get(i)+"\n";
		}
		return ret;
	}
	
	public int root(int p){
		int index = p;
		//System.out.println("checking: "+p);
		int parent = this.nodes.get(p);//Get the parent value set for this node
		if(parent != p){
			//This node isn't a root node (points to itself)
			parent = root(this.nodes.get(p));//To understand recursion 
			this.nodes.set(p, parent);//
			return parent;
		}else{
			return index;
		}	
	}
}
