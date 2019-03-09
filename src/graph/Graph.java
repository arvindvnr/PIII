package graph;

import java.util.*;
import distributioncenter.*; 

public class Graph {
	
    public static Set<Node> nodes = new HashSet<Node>();
    
    /**
     * Creates a Graph. Default constructor.
     */
    public Graph(){
    	
    }
    
    /**
     * Adding a node to the graph's set of nodes.
     * Its added without any edges at first.
     * @param node
     */
    public static void addNode(Node node){
    	nodes.add(node); 
    }
    
    
    
    /**
     * Adding a bridge (edge) between 2 nodes 
     * Since we are using a directed edge, we create 2 equivalent edges 
     * and point each node to the other. 
     * @param cost
     * @param node1
     * @param node2
     */
    public static void addEdge(int cost, Node node1, Node node2){
    	Edge edge1 = new Edge(node2, cost); 
    	Edge edge2 = new Edge(node1, cost); 
    	node1.edges.add(edge1);
    	node2.edges.add(edge2);     	
    }
    
    
    /**
     * Find the shortest path between 2 nodes using Dijkstra's Algorithm. 
     * @param source
     * @param target
     * @return Node 
     */    
    public static LinkedList<Node> findPath(Node source){
    	source.cumulativeCost = 0; 
    	if (source.existsDistribCenter){
    		return returnPathFrom(source); 
    	}
    	
    	Queue <Node> queue = new Queue<Node>();   //Our Queue class, not the built-in one.  
    	queue.add(source);
    	List<Node> citiesWithDistribution = new ArrayList<Node>(); 
    	
    	while(!queue.isEmpty()){
    		Node parent = queue.pop();    
    		
    		for (Edge edge: parent.edges){
    			Node child = edge.jointNode;
    			
				if( child.cumulativeCost > parent.cumulativeCost + edge.cost){
					child.parent = parent; 
					child.cumulativeCost = parent.cumulativeCost + edge.cost; 
					
					queue.add(child); 
					// Get all the cities with distribution center in a list. 
					if(child.existsDistribCenter){
						citiesWithDistribution.add(child); 
					}
				}    			
    		}
    	}
    	
    	//Now we have all the nodes holding a cumulative cost to reach 
    	//it from the client code. We need the closet node with a distribution center. 
    	Node target = citiesWithDistribution.get(0); 
    	
    	for (Node city: citiesWithDistribution){
    		if (city.cumulativeCost < target.cumulativeCost){
    			target = city; 
    		}
    	}
    	return returnPathFrom(target);  //Return a linkedList from the distribution-center (target) to the client.  
    }
    
    /**
     * Returns path from the specified node.
     * @param node the specified node
     * @return a LinkedList of Nodes in the path
     */
    public static LinkedList<Node> returnPathFrom(Node node){
    	LinkedList<Node> l = new LinkedList<Node>(); 
    	Node current = node; 
    	
    	while(current != null){
    		l.add(current);
    		current = current.parent; 
    	}
    	resetGraph();
    	
    	return l;     	
    }
    
    /**
     * After finding the path, we need to reset for every node both the cumulativeCost 
     * and the parent, so that it could be used in finding other paths for the next clients. 
     */    
    public static void resetGraph(){
    	for (Node node: nodes){
    		node.cumulativeCost = Double.POSITIVE_INFINITY; 
    		node.parent = null; 
    	}
    }
    
    /**
     * Adding a distribution center to a city. 
     * @param city
     * @param d
     * @return
     */
    public static boolean addDistributionCenter(String city, DistributionCenter d){
    	city = city.toLowerCase();
    	
    	if (d == null)
    		return false; 
    	else{
    		for (Node node: nodes){
    			if (node.name == city){
    				
    				if (!node.existsDistribCenter){
    					node.existsDistribCenter = true; 
    					node.distributionCenter = d; 
    					return true; 
    				}
    				
    			}
    		}
    	}
    	return false; 
    }
    
    /**
     * Gets the closest distribution center to the specified city.
     * @param city the specified city
     * @return the Node containing the closest distribution center
     */
    public static Node getClosestDistribution(String city){
    	for (Node node: nodes){
    		if (node.name == city)
    			return findPath(node).get(0); 
    	}
    	return null; 
    }
    
    /**
     * Get the Node containing the specified city.
     * @param city the specified city
     * @return the correct Node
     */
    public static Node getNode(String city) {
    	for (Node node: nodes) {
    		if (node.name == city) {
    			return node;
    		}
    	}
    	return null;
    }
    
    /**
     * Gets the cost of the specified path of nodes.
     * @param path a LinkedList of Nodes
     * @return the cost
     */
    public static int getCost(LinkedList path){
    	Node node = (Node) path.get(path.size() - 1); 
    	return (int) node.cumulativeCost; 
    }
    
}
