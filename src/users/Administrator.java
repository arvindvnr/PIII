/**
 * 
 */
package users;

import java.awt.Image;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import distributioncenter.DistributionCenter;
import graph.Graph;
import graph.Node;
import products.Category;
import products.Product;
import products.ProductManager;

/**
 *
 */
public class Administrator extends User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2114003988269886377L;
	protected List<Category> allCategories;
	protected List<DistributionCenter> allDCenters;
	protected Integer sessionID;

	/**
	 * Creates an Administrator object.
	 * @param ID the ID of this Administrator
	 * @param password the password of this Administrator
	 * @param categories list of categories to populate allCategories with
	 */
	public Administrator(String ID, String password) {
		super(ID, password);
		// TODO Auto-generated constructor stub
		this.allCategories = new ArrayList<>();
		this.allDCenters = new ArrayList<>();
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
	 * Attempts to add this Administrator to the HashMap of registered users.
	 * @param a Administrator to add
	 * @return true if Administrator is successfully registered, 
	 * false otherwise
	 */
	public boolean addAdmin(Administrator a) {
		if (a != null) {
			
			if (!UserManager.userMap.containsValue(a)) {
				UserManager.userMap.put(a.getID(), a);
				return true;
			}
			
		}
		
		return false;
	}
	
	/**
	 * Adds a new category to the shopping app.
	 * @param cat category to add
	 */
	public void addCategory(Category cat) {
		if (cat != null) {
			
			if (!allCategories.contains(cat)) {
				allCategories.add(cat);
			}
			
		}
	}
	
	/**
	 * Generates a screen report of all existing categories in the app.
	 * @return all existing categories in the app
	 */
	public String getScreenReport() {
		String appCategories = "";
		
		for (Category category: allCategories) {
			String catCodeToString = Integer.toString(category.getCode());
			appCategories += catCodeToString + " " + category.getDescription() + "\n";
		}
		
		return appCategories;
	}
	
	/**
	 * Adds a product to a category in the app.
	 * @param p product to add
	 */
	public void addProduct(Product p) {
		if (p != null) {
			
			for (Category oneCat: allCategories) {
				if (oneCat.equals(p.getCategory())) {
					
					if (!oneCat.getProducts().contains(p)){
						List<Product> products = oneCat.getProducts();
						products.add(p);
						oneCat.setProducts(products);
					}
					oneCat.getProducts().add(p);
					
					if (!ProductManager.products.values().contains(p)) {
						ProductManager.products.put(p.getDescription(), p);
					}
					p.setAvailableQ(p.getAvailableQ() + 1);
					break;
					
				}
			}
			
		}
	}
	
	/**
	 * Changes the photo of the given product.
	 * @param p product whose photo to change
	 * @param photoURL the URL of the new photo
	 */
	public void changePhoto(Product p, Image photoURL) {
		if (p != null) {
			
			for (Category oneCat: allCategories) {
				if (oneCat.getProducts().contains(p)) {
					
					if (photoURL != null) {
						p.setImage(photoURL);
						break;
					}
					
				}
			}
			
		}
	}
	
	/**
	 * Changes the description of the given product.
	 * @param p product whose description to change
	 * @param newDescription the new description of p
	 */
	public void changeDescription(Product p, String newDescription) {
		if (p != null) {
			
			for (Category oneCat: allCategories) {
				if (oneCat.getProducts().contains(p)) {
					
					if (newDescription != null) {
						p.setDescription(newDescription);
						break;
					}
					
				}
			}
			
		}
	}
	
	/**
	 * Changes the price of the given product.
	 * @param p product whose price to change
	 * @param newPrice the new price of p
	 */
	public void changePrice(Product p, double newPrice) {
		if (p != null) {
			
			for (Category oneCat: allCategories) {
				if (oneCat.getProducts().contains(p)) {
					
					if (newPrice > 0) {
						p.setPrice(newPrice);
						break;
					}
					
				}
			}
			
		}
	}
	
	/**
	 * Changes the available quantity of the given product.
	 * @param p product whose available quantity to change
	 * @param differenceQuantity the quantity to increment or decrement 
	 * existing quantity of p by; this can be a positive or a negative number
	 */
	public void changeAvailableQuantity(Product p, int differenceQuantity) {
		if (p != null) {
			
			if (ProductManager.products.containsValue(p)) {
				p.setAvailableQ(p.getAvailableQ() + differenceQuantity);
			}
			
		}
	}
	
	/**
	 * Changes the total quantity of the given product.
	 * @param p product whose available quantity to change
	 * @param differenceQuantity the quantity to increment or decrement 
	 * existing quantity of p by; this can be a positive or a negative number
	 */
	public void changeTotalQuantity(Product p, int differenceQuantity) {
		if (p != null) {
			
			if (ProductManager.products.containsValue(p)) {
				DistributionCenter key = null;
					
				for (Map.Entry<DistributionCenter, Integer> dcEntry: p.quantityDistributionMap.entrySet()) {
					key = dcEntry.getKey();
					break;
				}
				p.quantityDistributionMap.put(key, p.quantityDistributionMap.get(key) + differenceQuantity);
			}
			
		}
	}
	
	/**
	 * Allows to view all products in the app in the specified category.
	 * @param specificCat the specified category
	 * @return an ArrayList of all Products in the specified category
	 */
	public ArrayList<Product> seeProductsByCategory(Category specificCat) {
		List<Product> productsInCat = new ArrayList<>();
		
		for (Category category: allCategories) {
			if (category.equals(specificCat)) {
				for (Product product: category.getProducts()) {
					productsInCat.add(product);
				}
			}
		}
		
		return (ArrayList<Product>) productsInCat;
	}
	
	/**
	 * Allows to view all products in the app sorted by their availability,
	 * in either increasing or decreasing order.
	 * @param order the order in which to sort the Products
	 * (either "ascending" or "descending")
	 * @return an ArrayList of all Products sorted by availability
	 */
	public ArrayList<Product> seeProductsByAvailability(String order) {
		List<Product> productsToSort = new ArrayList<>();
		
		if (order != null) {
			// adds products to array list first
			for (Category category: allCategories) {
				for (Product product: category.getProducts()) {
					productsToSort.add(product);
				}
			}
			
			if (order.equals("ascending")) {
				// sorts array list of products by increasing availability
				Collections.sort(productsToSort, GrowingAvailabilityComparator);
				
			} else if (order.equals("descending")) {
				// sorts array list of products by decreasing availability
				Collections.sort(productsToSort, ShrinkingAvailabilityComparator);
				
			}
		}
		
		return (ArrayList<Product>) productsToSort;
	}
	
	/**
	 * Adds a new DistributionCenter to the list of existing distribution centers.
	 * @param newDC the new DistributionCenter to be added 
	 */
	public void addNewDistributionCenter(DistributionCenter newDC) {
		if (newDC != null) {
			
			if (!(allDCenters.contains(newDC))) {
				allDCenters.add(newDC);
				
				// assigns the distribution center to a city
				// (ie. a particular node in the graph)
				for (Node node: Graph.nodes) {
					if (node.toString().equals(newDC.getCity())) {
						node.setDistributionCenter(newDC);
						node.setExistsDistribCenter(true);
						break;
					}
				}
				
				for (Product p : ProductManager.products.values()) {
					// all existing products in the app will add the
					// distribution center, with an available quantity of 0
					// for this center
					List<Product> pro = new ArrayList<Product>();
					pro.add(p);
					newDC.setAllProducts((ArrayList<Product>) pro);
						
					p.quantityDistributionMap.put(newDC, 0);
				}
			}
			
		}
	}
	
	/* ========================= SORTING COMPARATORS ========================= */
	/**
	 * Sorts all products in app by increasing availability (ascending order).
	 */
	public static Comparator<Product> GrowingAvailabilityComparator = new Comparator<Product>() {
		
		public int compare(Product p1, Product p2) {
			int p1Availability = p1.getAvailableQ();
			int p2Availability = p2.getAvailableQ();
			
			return p1Availability - p2Availability;
		}
		
	};
	
	/**
	 * Sorts all products in app by decreasing availability (descending order).
	 */
	public static Comparator<Product> ShrinkingAvailabilityComparator = new Comparator<Product>() {
		
		public int compare(Product p1, Product p2) {
			int p1Availability = p1.getAvailableQ();
			int p2Availability = p2.getAvailableQ();
			
			return p2Availability - p1Availability;
		}
		
	};

}

