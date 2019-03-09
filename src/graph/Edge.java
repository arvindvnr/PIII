package graph;

public class Edge {
    protected Node jointNode;  
    protected int cost; 
    
    /**
     * Create a directed edge that has a cost and pointing to a node 
     * @param node
     * @param cost
     */
    public Edge(Node node, int cost){
    	this.jointNode = node; 
    	this.cost = cost;     
    }    
}
