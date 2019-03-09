/**
 * 
 */
package distributioncenter;

import java.io.Serializable;
import java.util.ArrayList;

import products.Product;

/**
 *
 */
public class DistributionCenter implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7352586202481046249L;
	String centerCity;
	int centerID;
	ArrayList<Product> products;
	
	/**
	 * Creates a DistributionCenter object.
	 * @param centerName the name of this DistributionCenter
	 * @param centerCity the city of this DistributionCenter
	 * @param centerID the id of this DistributionCenter
	 * @param products the products that this DistributionCenter contains
	 */
	public DistributionCenter(String centerCity, int centerID, ArrayList<Product> products){
		this.centerCity = centerCity;
		this.centerID = centerID;
		this.products = products;
	}
	
	/**
	 * Sets the city of this DistributionCenter.
	 * @param city city of this DistributionCenter
	 */
	public void setCity(String city){
		this.centerCity = city;
	}
	
	/**
	 * Sets the ID of this DistributionCenter.
	 * @param ID ID of this DistributionCenter
	 */
	public void setID(int ID){
		this.centerID = ID;
	}
	
	
	/**
	 * Returns the city of this DistributionCenter.
	 * @return city of this DistributionCenter
	 */
	public String getCity(){
		return this.centerCity;
	}
	
	/**
	 * Returns the ID of this DistributionCenter.
	 * @return ID of this DistributionCenter
	 */
	public int getID(){
		return this.centerID;
	}
	

	/**
	 * @param products the products to set
	 */
	public void setAllProducts(ArrayList<Product> products) {
		this.products = products;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DistributionCenter [centerCity=" + centerCity + ", centerID=" + centerID + "]";
	}

	/**
	 * Returns all the products in this DistributionCenter.
	 * @return products
	 */
	public ArrayList<Product> getAllProducts(){
		return products;
	}
	
	//Distribution Center latest version
	
}
