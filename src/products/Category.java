package products;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Category is Java class used to instantiate objects that constitute various
 * categories of merchandised products for transactions on the shopping site.
 * A Category object encapsulates the information needed to describe a
 * category, such as its code and description, and a list products which belong to this category.
 */

public class Category implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6420351197727985364L;
	private int code;
	private String description;
	public static List<Product> products = new ArrayList<Product>();
	
	/** 
	 * Creates a Category object.
	 * @param code A unique code of this category 
	 * @param description A string description of this category
	 * @param products A list of all products which belong to this category
	 */
	public Category(int code, String description) {
		super();
		this.code = code;
		this.description = description;
	}

	/**
	 * @return the code
	 */
	public int getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(int code) {
		this.code = code;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the products
	 */
	public List<Product> getProducts() {
		return products;
	}

	/**
	 * @param products the products to set
	 */
	public void setProducts(List<Product> p) {
		products = p;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {	
		return description;
	}
	
}
