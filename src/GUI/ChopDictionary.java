package GUI;

import java.text.MessageFormat;
import java.util.*;

public class ChopDictionary<T> {
    protected ArrayList<DoubleArray<T>> list;
    
    /**
     * Creates a ChopDictionary object.
     * @param dictionary the dictionary from which to create the
     * ChopDictionary object
     */
    public ChopDictionary(Map<String, T> dictionary){
    	this.list = new ArrayList<DoubleArray<T>>(); 
        this.populate(dictionary);
        
    }
    
    /**
     * Populates the Arraylist in this class with the
     * specified dictionary's objects.
     * @param dictionary
     */
    private void populate(Map<String, T> dictionary){
    	
    	DoubleArray<T> doubleArray = new DoubleArray<T>(); 
    	this.list.add(doubleArray);
    	for (String key: dictionary.keySet()){
    		doubleArray.add(dictionary.get(key));
    		if (doubleArray.isFull()){
    			doubleArray = returnNode(); 
    			this.list.add(doubleArray);   //the same idea of current in linked-list 
    			System.out.println(doubleArray);
    		} 
    	}
    }
    
    private DoubleArray<T> returnNode(){
    	return new DoubleArray<T>(); 
    }
    
    public ArrayList<DoubleArray<T>> getList(){
    	return this.list; 
    }
    
    public String toString(){
    	String str = ""; 
    	for (DoubleArray<T> node: this.list){
    		str += node.toString(); 
    	}
    	return str; 
    }
    
}
