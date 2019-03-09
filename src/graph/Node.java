package graph;

import distributioncenter.*; 

import java.util.*;

public class Node {
	protected String name;
	public Set<Edge> edges; 
	protected Node child; 
	protected Node parent; 
	protected double cumulativeCost; 
	protected boolean existsDistribCenter;	// NEW GETTER AND SETTER FOR THIS
	protected DistributionCenter distributionCenter;	// NEW SETTER FOR THIS 

	/**
	 * Creates a new Node.
	 * @param name the name of the new Node (represents the city that
	 * the Node represents)
	 */
	public Node(String name){
    	this.name = name.toLowerCase(); 
    	this.edges = new HashSet<Edge>();               
    	this.child = null;   
    	this.parent = null; 
    	this.cumulativeCost = Double.POSITIVE_INFINITY ; 
    }
    
	/**
	 * @return the distributionCenter
	 */
    public DistributionCenter getDistributionCenter() {
    	return distributionCenter;
    }
    
    /**
	 * @param distributionCenter the distributionCenter to set
	 */
	public void setDistributionCenter(DistributionCenter distributionCenter) {
		this.distributionCenter = distributionCenter;
	}
	
	/**
	 * @return the existsDistribCenter
	 */
	public boolean isExistsDistribCenter() {
		return existsDistribCenter;
	}

	/**
	 * @param existsDistribCenter the existsDistribCenter to set
	 */
	public void setExistsDistribCenter(boolean existsDistribCenter) {
		this.existsDistribCenter = existsDistribCenter;
	}
    
    @Override
    public String toString(){
    	return this.name; 
    }
    
    @Override 
    public boolean equals(Object obj){
    	if (this == obj){
    		return true; 
    	}
    	if (obj == null){
    		return false; 
    	}
    	if (obj instanceof Node){
    		Node testNode = (Node) obj; 
    		if (this.name == testNode.name ){
    			return true; 
    		}
    	}
    	return false;     	
    }  
    
    /**
     * Returns a positive or negative int, depending on whether
     * the difference between the cumulativeCost of this node 
     * and that of node is positive or negative.
     * @param node the Node whose cumulativeCost to compare
     * @return an int
     */
    public int compareTo(Node node){
    	return  (int) (this.cumulativeCost - node.cumulativeCost); 
    }
    
}
