package products;

import java.awt.Image;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Invoice extends Product implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5820261726294575567L;
	public static List<Invoice> invoiceList = new ArrayList<Invoice>();
	private Integer quantity;
	private Double cost;

	/**
	 * Creates an Invoice object. It is an extension of Product, 
	 * with some added properties.
	 * @param category
	 * @param image
	 * @param iD
	 * @param description
	 * @param price
	 * @param quantity
	 */
	public Invoice(String category, Image image, int iD, String description, Double price, Integer quantity) {
		super(category, image, iD, description, price);
		// TODO Auto-generated constructor stub
		this.quantity = quantity;
		this.cost = price * this.quantity;
	}
	
	/**
	 * Adds this Invoice to list of Invoices.
	 */
	public void addToReport() {
		invoiceList.add(this);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.getDescription() + ", quantity=" + quantity + ", price=" + this.getPrice() + ", cost=" + cost;
	}

}

