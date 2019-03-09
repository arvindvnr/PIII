package graph;

import java.util.*;

public class Queue<T> {
	private List<T> queue; 
	
	/**
	 * Creates a Queue.
	 */
    Queue(){
    	this.queue = new ArrayList<T>();
    	
    }
    
    /**
     * Pops the first item in the Queue.
     * @return the item popped out of the Queue
     */
    public T pop(){
    	return this.queue.remove(0); 
    }
    
    /**
     * Adds an element to the Queue.
     * @param element the element to add to Queue
     */
    public void add(T element){
    	this.queue.add(element); 
    }
    
    /**
     * Returns true or false, based on if the 
     * Queue is empty or not.
     * @return a boolean value
     */
    public boolean isEmpty(){
    	return this.queue.size() == 0; 
    }
    
}
