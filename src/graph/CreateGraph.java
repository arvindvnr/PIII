package graph;

public class CreateGraph {
	
	public static void main(String[] args){

	    Node Toronto = new Node("Toronto"); 
	    Node Montreal = new Node("Montreal"); 
	    Node Sauga = new Node("Sauga"); 
	    Node Hamilton = new Node("Hamilton"); 
	    Node Ottawa = new Node("Ottawa"); 
	    Node Vancouver = new Node("Vancouver"); 
	    Node Edmonton = new Node("Edmonton");
	    Node Calgary = new Node("Calgary"); 
	    Node NewFoundLand = new Node("New Foundland"); 
	    Node Quebec = new Node("Quebec");
	    
	    NewFoundLand.existsDistribCenter = true; 
	    Montreal.existsDistribCenter = true; 
	    
	    
	    Graph g = new Graph(); 
	    g.addNode(Toronto);
	    g.addNode(Montreal);
	    g.addNode(Sauga);
	    g.addNode(Ottawa);
	    g.addNode(Vancouver);
	    g.addNode(Hamilton);
	    g.addNode(Edmonton);
	    g.addNode(Calgary);
	    
	    g.addEdge(541, Toronto, Montreal);
	    g.addEdge(3358, Toronto, Vancouver);
	    g.addEdge(48, Toronto, Sauga);
	    g.addEdge(401, Toronto, Ottawa);
	    
	    g.addEdge(2713, Toronto, Edmonton);
	    g.addEdge(3420, Toronto, Calgary);
	    g.addEdge(1426, Montreal, NewFoundLand);
	    g.addEdge(1557, Ottawa, NewFoundLand);
	    g.addEdge(3454, Ottawa, Edmonton);
	    g.addEdge(298, Edmonton, Calgary);
	    g.addEdge(7, Sauga, Hamilton);
	    g.addEdge(1000, Quebec, Toronto);
	    g.addEdge(1000, Quebec, NewFoundLand);
	    
	    System.out.println(g.findPath(Hamilton));
	    System.out.println(g.findPath(Toronto)); 
	    
	}
    
}
