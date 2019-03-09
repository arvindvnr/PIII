package products;

import java.util.HashMap;

public class ShoppingOrder {
	
	public HashMap<Product, Integer> orders;
	public static int idNum = 1;
	public int orderID;
	
	/**
	 * Creates a ShoppingOrder out of Product objects.
	 * @param orders the HashMap of Product objects
	 */
	public ShoppingOrder(HashMap<Product, Integer> orders) {
		orderID = idNum;
		this.orders = orders;
		idNum++;
	}
	
}
