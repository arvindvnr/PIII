package users;

import graph.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import distributioncenter.DistributionCenter;

import products.Invoice;
import products.Order;
import products.Product;
import products.ShoppingOrder;

public class Customer extends Shopper implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7371093171996521182L;
	private String city;
	private String street;
	private int customerID;
	private HashMap<Order, Integer> orderMap; 
	private List<Invoice> invoiceList;
	protected Integer sessionID;
	protected HashMap<Product, Integer> shoppingCart;
	private double shippingPrice;
	private ShoppingOrder shoppingOrder;

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
	 * Creates a Customer object.
	 * @param ID The ID of customer
	 * @param password the password of customer
	 * @param address the address of customer
	 */
	public Customer(String ID, String password, String city, String street) {
		super(ID, password);
		this.city = city;
		this.street = street;
		shippingPrice = 0.1;
		orderMap = new HashMap<Order, Integer>();
		invoiceList = new ArrayList<Invoice>();
	}
	
	/**
	 * Computes the total cost of the specified Order.
	 * @param order the order whose total cost to compute
	 * @return the total cost of the specified order
	 */
	public double totalCost(Order order) {
		Integer q = orderMap.get(order);
		return order.getPrice() * q;
	}
	
	/**
	 * Returns all the invoices for this Customer.
	 * @param graph the graph to use to calculate the invoice
	 * @return a string representation of all invoices
	 */
	public String totalInvoice(Graph graph) {
		String total = "";
		for (int i = 0; i < invoiceList.size(); i++) {
			total += invoiceList.get(i).toString();
			total += "\n";
		}
		
		total += String.valueOf(shippingCost());
		
		return total;
	}
	
	/**
	 * Returns the total shipping cost for this Customer.
	 * @return the total shipping cost
	 */
	public double shippingCost() {
		Node node = Graph.getNode(city);
		LinkedList<Node> path = Graph.findPath(node);
		int distance = Graph.getCost(path);
		double shippingCost = shippingPrice * distance;
		
		return shippingCost;
	}
	
	/**
	 * Ships all the Orders made by the Customer to the Customer.
	 * @param dc the DistributionCenter to subtract ordered quantity of a Product from
	 */
	public void ship(DistributionCenter dc) {
		for (Order o: orderMap.keySet()) {
			o.quantityDistributionMap.put(dc, o.quantityDistributionMap.get(dc) - orderMap.get(o));
		}
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
	
	/**
	 * Empties the list of orders made by this Customer.
	 */
	public void emptyOrderMap() {
		for (Order key: orderMap.keySet()) {
			orderMap.remove(key);
		}
	}
	
	/**
	 * Allows Customer to purchase products in the list of their orders.
	 * @param graph the Graph that helps Customer make the purchase
	 */
	public void purchase(Graph graph) {
		
		@SuppressWarnings("static-access")
		DistributionCenter dc = graph.getClosestDistribution(city).getDistributionCenter();
		ship(dc);
		
		for (Order o: orderMap.keySet()) {
			
			Invoice invoice = new Invoice(o.getCategory(), o.getImage(), o.getID(), 
					o.getDescription(), o.getPrice(), orderMap.get(o));
			invoice.addToReport();
			invoiceList.add(invoice);
			
		}
		emptyOrderMap();
		
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the street
	 */
	public String getStreet() {
		return street;
	}

	/**
	 * @param street the street to set
	 */
	public void setStreet(String street) {
		this.street = street;
	}

	/**
	 * @return the orderMap
	 */
	public HashMap<Order, Integer> getOrderMap() {
		return orderMap;
	}

	/**
	 * @param orderMap the orderMap to set
	 */
	public void setOrderMap(HashMap<Order, Integer> orderMap) {
		this.orderMap = orderMap;
	}

	/**
	 * @return the invoiceList
	 */
	public List<Invoice> getInvoiceList() {
		return invoiceList;
	}

	/**
	 * @param invoiceList the invoiceList to set
	 */
	public void setInvoiceList(List<Invoice> invoiceList) {
		this.invoiceList = invoiceList;
	}
	
}
