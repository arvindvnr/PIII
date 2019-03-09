/**
 * 
 */
package users;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.HashMap;

import products.Product;
import products.ShoppingOrder;

/**
 *
 */
public class Shopper extends User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5045957456396941760L;
	protected HashMap<Product, Integer> shoppingCart;
	protected Integer sessionID;
	private int customerID;
	public static int idNum = 0;
	private ShoppingOrder shoppingOrder;
	
	/**
	 * Cancels product ordered by this Shopper.
	 * @param p product to cancel
	 */
	public void cancelProduct(Product p) {
		// returns the available quantity in shopper's shopping cart to the available quantity
		p.setAvailableQ(p.getAvailableQ() + shoppingCart.get(p));
		shoppingCart.remove(p);
	}
	
	/**
	 * @return the shoppingOrder
	 */
	public ShoppingOrder getShoppingOrder() {
		return shoppingOrder;
	}

	/**
	 * @param shoppingOrder the shoppingOrder to set
	 */
	public void setShoppingOrder(ShoppingOrder shoppingOrder) {
		this.shoppingOrder = shoppingOrder;
	}

	/**
	 * Creates a Shopper object.
	 * @param ID the ID of this Shopper
	 * @param password the password of this Shopper
	 */
	public Shopper(String ID, String password) {
		super(ID, password);
		shoppingCart = new HashMap<Product, Integer>();
		setCustomerID(idNum);
		idNum++;
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Saves the cart of Products by serializing it.
	 * @throws IOException
	 */
	public void saveCart() throws IOException {
		OutputStream file = new FileOutputStream("cart");    
		OutputStream buffer = new BufferedOutputStream(file); 
		ObjectOutput writer = new ObjectOutputStream(buffer); 
		
		writer.writeObject(shoppingCart);
		writer.close();
		buffer.close();
		file.close();
	}
	
	/**
	 * Deserializes the saved cart of Products.
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public void uploadCart() throws IOException, ClassNotFoundException {
		InputStream file = new FileInputStream("cart"); 
		InputStream buffer = new BufferedInputStream(file); 
		ObjectInput reader = new ObjectInputStream(buffer); 
		
		shoppingCart = (HashMap<Product, Integer>) reader.readObject();    
		file.close(); 
	}
	
	/**
	 * Adds a specified quantity of product from cart.
	 * @param product product to add to cart
	 * @param quantity quantity of product to add to cart
	 * @return whether product was successfully added or not
	 */
	public boolean addToShoppingCart(Product product, Integer quantity) {
		Integer cartQuantity = shoppingCart.get(product);
		Integer newAvailQ = product.getAvailableQ() - quantity;
		
		if (quantity <= product.getAvailableQ()) {
			if (!shoppingCart.containsKey(product)) {
				
				shoppingCart.put(product, quantity);
				product.setAvailableQ(newAvailQ);
				return true;
				
			} else {
				
				shoppingCart.put(product, cartQuantity + quantity);
				product.setAvailableQ(newAvailQ);
				return true;
				
			}
		}
		
		return false;
	}
	
	/**
	 * Removes a specified quantity of product from cart.
	 * @param product product to remove from cart
	 * @param quantity quantity of product to remove from cart
	 * @return whether product was successfully removed or not
	 */
	public boolean removeFromCart(Product product, Integer quantity) {
		Integer cartQuantity = shoppingCart.get(product);
		Integer newAvailQ = product.getAvailableQ() + quantity;
		
		if (!shoppingCart.containsKey(product)) {
			return false;
		} else if (quantity > cartQuantity) {
			return false;
		} else if (quantity <= cartQuantity) {
			product.setAvailableQ(newAvailQ);
			shoppingCart.put(product, cartQuantity - quantity);
			
			if (cartQuantity == 0) {
				shoppingCart.remove(product);
			}
			
			return true;
		}
		return false;
	}
	
	/**
	 * Removes all existing items in the Shopper's shopping cart.
	 */
	public void cancelCart() {
		for (Product key: shoppingCart.keySet()) {
			shoppingCart.remove(key);
		}
	}
	
	/**
	 * Returns a shopping cart of Product objects
	 * @return a shopping cart
	 */
	public HashMap<Product, Integer> getShoppingCart() {
		return shoppingCart;
	}
	
	

	/**
	 * @return the sessionID
	 */
	public Integer getSessionID() {
		return sessionID;
	}

	/**
	 * @param sessionID the sessionID to set
	 */
	public void setSessionID(Integer sessionID) {
		this.sessionID = sessionID;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.getID();
	}

	/**
	 * @return the customerID
	 */
	public int getCustomerID() {
		return customerID;
	}

	/**
	 * @param customerID the customerID to set
	 */
	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

}


