package products;

import java.awt.Image;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import distributioncenter.DistributionCenter;
import distributioncenter.DistributionCenterManager;

/**
 * Product is Java class used to instantiate objects that constitute various
 * merchandised products for transactions on the shopping site.
 * A Product object encapsulates the information needed to describe a
 * product, such as its category, image png or jpg, ID, description and price.
 */

public class Product implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2500722506269638574L;
	private String category;
	private Image image;
	private int ID;
	private String description;
	private Double price;
	public Map<DistributionCenter, Integer> quantityDistributionMap;
	private Integer availableQ;
	
	/** 
	 * Creates a Product object.
	 * @param category The Category of this product
	 * @param image An image of this product
	 * @param iD An identification information of this product
	 * @param description A description product of this product
	 * @param price The price of this product
	 */
	public Product(String category, Image image, int iD, String description, Double price) {
		super();
		this.category = category;
		this.image = image;
		this.ID = iD;
		this.description = description;
		this.price = price;
		
		quantityDistributionMap = new HashMap<DistributionCenter, Integer>(); {
			for (DistributionCenter dc: DistributionCenterManager.distributionmanagers.values()) {
				quantityDistributionMap.put(dc, 0);
			}
		}
		availableQ = 0;
	}
	
	/**
	 * @return the availableQ
	 */
	public Integer getAvailableQ() {
		return availableQ;
	}



	/**
	 * @param availableQ the availableQ to set
	 */
	public void setAvailableQ(Integer availableQ) {
		this.availableQ = availableQ;
	}
	
	
	
	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}



	/**
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}



	/**
	 * @return the image
	 */
	public Image getImage() {
		return image;
	}



	/**
	 * @param image the image to set
	 */
	public void setImage(Image image) {
		this.image = image;
	}



	/**
	 * @return the iD
	 */
	public int getID() {
		return ID;
	}



	/**
	 * @param iD the iD to set
	 */
	public void setID(int iD) {
		ID = iD;
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
	 * @return the price
	 */
	public Double getPrice() {
		return price;
	}



	/**
	 * @param price the price to set
	 */
	public void setPrice(Double price) {
		this.price = price;
	}



	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String priceToString = Double.toString(price);
		
		return "Product: " + description + 
				"\n" + "Category: " + category + 
				"\n" + "ID: " + String.valueOf(ID) +
				"\n" + "Price: " + priceToString;
	}
	
}


