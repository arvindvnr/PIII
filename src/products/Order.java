package products;

import java.awt.Image;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import distributioncenter.DistributionCenter;

import users.Customer;

public class Order extends Product implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2767048108163527612L;
	public Customer customer;
	public Map<DistributionCenter, Integer> quantityDistributionMap;

	/**
	 * Creates an Order object. It is an extension of Product,
	 * with some added properties.
	 * @param category 
	 * @param image 
	 * @param iD 
	 * @param description 
	 * @param price
	 */
	public Order(String category, Image image, int iD, String description, double price) {
		super(category, image, iD, description, price);
		quantityDistributionMap = new HashMap<DistributionCenter, Integer>();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the customer
	 */
	public Customer getCustomer() {
		return customer;
	}


	/**
	 * @param customer the customer to set
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
}
