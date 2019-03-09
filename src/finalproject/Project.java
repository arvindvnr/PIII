package finalproject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;

import distributioncenter.DistributionCenter;
import distributioncenter.DistributionCenterManager;
import graph.Edge;
import graph.Graph;
import graph.Node;
import products.Category;
import products.CategoryManager;
import products.Order;
import products.Product;
import products.ProductManager;
import users.Administrator;
import users.Customer;
import users.Shopper;
import users.User;
import users.UserManager;

/**
 * This class is intended to be your API
 * @author Ilir
 *
 */
public class Project {
	
	/**
	 * This method must add a new shopper user or administrator user.
	 * @param userID
	 * @param password
	 * @param admin -> if true, add an administrator user, otherwise add a shopper user
	 * @return -> true if operation successful, false otherwise
	 */
	public boolean addUser(String userID, String password, boolean admin) {
		// Your code goes here.
		// Replace the statement below with the correct code.
		
		/* FINISHED IMPLEMENTATION */
		//ANTHONY APPROVED
		HashMap<String, User> userMap = new HashMap<String, User>();
		userMap.putAll(UserManager.userMap);
		userID = userID.trim();
		password = password.trim();
		
		if (admin == true) {
			if (!userID.equals("") && !password.equals("") && !UserManager.userMap.containsKey(userID)) {
				
        		userMap.put(userID, new Administrator(userID, password));
        		UserManager.setUserMap(userMap);
        		UserManager.userMap.get(userID).setSessionID(-1);
        		UserManager.logger.log(Level.FINE, "Add new Administrator " + userID);
        		
        		try {
        			UserManager.saveToFile();
        		} catch (IOException e) {
        			// TODO Auto-generated catch block
        			e.printStackTrace();
        		}
        		
        		return true;
        		
	    	}
		} else {
			if (!userID.equals("") && !password.equals("") && !UserManager.userMap.containsKey(userID)) {
				
        		userMap.put(userID, new Shopper(userID, password));
        		UserManager.setUserMap(userMap);
        		UserManager.userMap.get(userID).setSessionID(-1);
        		UserManager.userMap.get(userID).setSessionID(-1);
        		UserManager.logger.log(Level.FINE, "Add new Shopper " + userID);
        		
        		try {
        			UserManager.saveToFile();
        		} catch (IOException e) {
        			// TODO Auto-generated catch block
        			e.printStackTrace();
        		}
        		
        		return true;
        		
	    	}
		}
		return false;
		
	}
	
	/**
	 * Authenticates a user an creates an active work session
	 * @param userID 
	 * @param password
	 * @return -> SessionID if authentication successful, -1 otherwise.
	 */
	public int login(String userID, String password) {
		// Your code goes here.
		// Replace the statement below with the correct code.
		
		/* FINISHED IMPLEMENTATION */
		// sessionID is a 6-digit number
		//ANTHONY APPROVED
		userID = userID.trim();
		password = password.trim();
		
		try {
			UserManager.readFromFile();
			ProductManager.readFromFile();
			CategoryManager.readFromFile();
			DistributionCenterManager.readFromFile();
			
			User user = UserManager.userMap.get(userID);
			
			if (user instanceof Shopper) {
				((Shopper) user).uploadCart();
				
				if (!((Shopper) user).getShoppingCart().isEmpty()) {
					
					for (Product p: ((Shopper) user).getShoppingCart().keySet()) {
						if (p.getAvailableQ() < ((Shopper) user).getShoppingCart().get(p)) {
							
							((Shopper) user).cancelProduct(p);
							
						}
					}
					
				}
			} else if (user instanceof Customer) {
				((Customer) user).uploadCart();
				
				if (!((Customer) user).getShoppingCart().isEmpty()) {
					
					for (Product p: ((Customer) user).getShoppingCart().keySet()) {
						if (p.getAvailableQ() < ((Customer) user).getShoppingCart().get(p)) {
							
							((Customer) user).cancelProduct(p);
							
						}
					}
					
				}
			}
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Integer sessionID;
    	User user = null;
    	System.out.println(UserManager.userMap);
    	
    	if (UserManager.userMap.containsKey(userID) && UserManager.userMap.get(userID).getSessionID() == -1) {
    		user = UserManager.userMap.get(userID);
    		
    		if (password.equals(user.getPassword())) {
    			Random random = new Random();
    			Integer randomNum = new Integer(100000 + random.nextInt(900000));
    			sessionID = randomNum;
    			
    			while (UserManager.activeSessionIDs.contains(sessionID)) {
    				sessionID = new Integer(100000 + random.nextInt(900000));
    			}
    			
    			System.out.println("Session (User session before login): "+user.getSessionID());
    			user.setSessionID(sessionID);
    			
    			System.out.println("Session (User session after login): "+user.getSessionID());
    			UserManager.activeSessionIDs.add(sessionID);
    			
    			return sessionID;
    		}
		}
		return -1;
		
	}
	
	/**
	 * Makes sessionID unavailable for connection
	 * @param sessionID
	 */
	public void logout(int sessionID) throws ClassNotFoundException {
		// your code goes here
		/* FINISHED IMPLEMENTATION */
		//ANTHONY APPROVED
		Integer sessionIDLogOut = new Integer(sessionID);
		User user;
		
		// connection removed from the list
		for (Map.Entry<String, User> userEntry : UserManager.userMap.entrySet()) {
			user = userEntry.getValue();
			//Access to it
			
			//System.out.println("Session (Given session): "+sessionIDLogOut);
			if (user.getSessionID().equals(sessionIDLogOut)) {
				
				UserManager.activeSessionIDs.remove(user.getSessionID());
				
				user.setSessionID(-1);
				System.out.println("Session (User session) logout: "+user.getSessionID());
				break;
				
			}
		}

		try {
			UserManager.saveToFile();
			ProductManager.saveToFile();
			CategoryManager.saveToFile();
			DistributionCenterManager.saveToFile();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// connection closed
	}
	
	/**
	 * This method must add a new category in your application.
	 * @param catName -> The name of the category to be added.
	 * @param sessionID -> A session ID that belongs to an authenticated administrator
	 * @return -> The ID of the category you created if successful, -1 if not successful.
	 */
	public int addCategory(String catName, int sessionID) {
		// Your code goes here.
		// Replace the statement below with the correct code. 
		
		/* FINISHED IMPLEMENTATION */
		//ANTHONY APPROVED
		int catCount = 0;
		int newCatCode = 0;	
		Integer sesIDAsInteger = new Integer(sessionID);
		
		/* 
		 * The following code checks if the given category exists or not
		 * by keeping a counter of how many names of the existing categories 
		 * are not the same as that of the given category.
		 */
		for (Map.Entry<String, Category> catEntry : CategoryManager.categories.entrySet()) {
			if (!(catEntry.getValue().getDescription().equals(catName))) {
				catCount++;
			}
		}
		
		if (catCount == CategoryManager.categories.size()) {
			
			for (Map.Entry<String, User> userEntry : UserManager.userMap.entrySet()) {
				if (userEntry.getValue() instanceof Administrator) {
					
					// checks if sessionID is valid or not
					if (userEntry.getValue().getSessionID().equals(sesIDAsInteger) && userEntry.getValue() instanceof Administrator) {
						newCatCode = CategoryManager.categories.size() + 1;
						Category newCat = new Category(newCatCode, catName);
						
						CategoryManager.add(newCat);
						((Administrator) userEntry.getValue()).addCategory(newCat);
						
						return newCatCode;
					}
					
				}
			}
			
		}
		return -1;
		
	}
	
	/**
	 * Adds a distribution center to your application.
	 * If the given distribution center exists, or sessionID invalid, do nothing.
	 * @param city -> The city where distribution center must be based.
	 * @param sessionID -> A session ID that belongs to an authenticated administrator
	 */
	public void addDistributionCenter(String city, int sessionID) {
		// Your code goes here
		/* FINISHED IMPLEMENTATION */
		int dcNonExistentCount = 0;
		Integer sesIDAsInteger = new Integer(sessionID);
		
		for (Map.Entry<String, User> userEntry : UserManager.userMap.entrySet()) {
			// checks if sessionID is valid or not
			if (userEntry.getValue().getSessionID().equals(sesIDAsInteger)) {
				
				/* 
				 * The following code checks if the given distribution center 
				 * exists or not by keeping a counter of how many cities of the 
				 * existing distribution centers are not the same 
				 * as that of the given category.
				 */
				for (Map.Entry<String, DistributionCenter> dcEntry : DistributionCenterManager.distributionmanagers.entrySet()) {
					if (!(dcEntry.getValue().getCity().equals(city))) {
						dcNonExistentCount++;
					}
				}
				
				if (dcNonExistentCount == DistributionCenterManager.distributionmanagers.size()) {
					// adds new distribution center
					int dcMapSize = DistributionCenterManager.distributionmanagers.size();
					int dcNumber = dcMapSize + 1;
					
					ArrayList<Product> newArray = new ArrayList<>();
					DistributionCenter newDC = new DistributionCenter(city, dcNumber, newArray);
					
					DistributionCenterManager.add(newDC);
					((Administrator) userEntry.getValue()).addNewDistributionCenter(newDC);
					
				}
				
			}
		}
	}
	
	/**
	 * Adds a new Customer to your application; the customer record that belongs 
	 * to a newly added shopper user that has no customer record on the system.
	 * @param custName -> The name of the customer
	 * @param city -> The city of the customer address
	 * @param street -> The street address of the customer
	 * @param sessionID -> A valid sessionID that belongs to an authenticated shopper user.
	 * @return -> The added customer ID
	 */
	public int addCustomer(String custName, String city, String street, int sessionID) {
		// Your code goes here.
		// Replace the statement below with the correct code.
		
		/* FINISHED IMPLEMENTATION */
		//ANTHONY APPROVED		
		Integer sesIDAsInteger = new Integer(sessionID);
		
		for (Map.Entry<String, User> userEntry : UserManager.userMap.entrySet()) {
			
			// checks if sessionID is valid or not
			if (userEntry.getValue().getSessionID().equals(sesIDAsInteger)) {
				if (userEntry.getValue() instanceof Shopper) {
					
					Customer customer = new Customer(custName, userEntry.getValue().getPassword(), city, street);
					customer.setCustomerID(((Shopper) userEntry.getValue()).getCustomerID());
					UserManager.userMap.put(custName, customer);
					
					return customer.getCustomerID();
					
				}
			}
			
		}
		return -1;
		
	}
	
	/**
	 * Adds a new Product to your application
	 * @param prodName -> The product name
	 * @param category -> The product category.
	 * @param price -> The product sales price
	 * @param sessionID -> A session ID that belongs to an authenticated administrator
	 * @return -> Product ID if successful, -1 otherwise.
	 */
	public int addProduct(String prodName, int category, double price, int sessionID) {
		// Your code goes here.
		// Replace the statement below with the correct code.
		
		/* FINISHED IMPLEMENTATION */
		//ANTHONY APPROVED		
		Category cat = null;
		Integer sesIDAsInteger = new Integer(sessionID);
		int counter = 0;
		
		for (Category c : CategoryManager.categories.values()) {
			if (c.getCode() != category) {
				counter++;
			}
			else {
				cat = c;
			}
			
			if (counter == CategoryManager.categories.values().size()) {
				return -1;
			}
		}
		
		for (Map.Entry<String, User> userEntry : UserManager.userMap.entrySet()) {
			
			if (userEntry.getValue().getSessionID().equals(sesIDAsInteger) && userEntry.getValue() instanceof Administrator
					&& cat != null) {
				int newProCode = ProductManager.products.size() + 1;
				Product newProd = new Product(cat.getDescription(), null, newProCode, prodName, price);
				
				ProductManager.addProduct(newProd);
				Administrator admin = ((Administrator) userEntry.getValue());
				admin.addProduct(newProd);
				
				return newProCode;
			}
			
		}
		return -1;
		
	}
	
	/**
	 * Computes the available quantity of prodID in a specific distribution center.
	 * @param prodID
	 * @param center
	 * @return -> Available quantity or -1 if prodID or center does not exist in the database
	 */
	public int prodInquiry(int prodID, String center) {
		// Your code goes here.
		// Replace the statement below with the correct code.
		
		/* FINISHED IMPLEMENTATION */
		//ANTHONY APPROVED
		Product foundProduct = null;
		
		for (Map.Entry<String, Product> productEntry : ProductManager.products.entrySet()) {
			if (productEntry.getValue().getID() == prodID) {
				foundProduct = productEntry.getValue();
				break;
			}
		}
		
		if (foundProduct != null) {
			
			for (Map.Entry<DistributionCenter, Integer> productDCEntry: foundProduct.quantityDistributionMap.entrySet()) {
				if (productDCEntry.getKey().getCity().equals(center)) {
					
					Integer availQty = foundProduct.quantityDistributionMap.get(productDCEntry.getKey());
					int availQtyToInt = availQty.intValue();
					
					return availQtyToInt;
					
				}
			}
			
		}
		return -1;
		
	}
	
	/**
	 * Updates the stock quantity of the product identified by prodID
	 * @param prodID -> The product ID to be updated
	 * @param distCentre -> Distribution Center (in effect a city name)
	 * @param quantity -> Quantity to add to the existing quantity
	 * @param sessionID -> A session ID that belongs to an authenticated administrator
	 * If currently the product 112 has quantity 100 in Toronto,
	 * after the statement updateQuantity(112, "Toronto", 51)
	 * same product must have quantity 151 in the Toronto distribution center. 
	 * @return -> true if the operation could be performed, false otherwise.
	 */
	public boolean updateQuantity(int prodID, String distCentre, int quantity, int sessionID) {
		// Your code goes here.
		// Replace the statement below with the correct code.
		
		/* FINISHED IMPLEMENTATION */
		//ANTHONY APPROVED
		Administrator admin = null;
		Product product = null;
		Integer sesIDAsInteger = new Integer(sessionID);
		
		for (Map.Entry<String, User> userEntry : UserManager.userMap.entrySet()) {
			if (userEntry.getValue().getSessionID().equals(sesIDAsInteger) && 
					(userEntry.getValue() instanceof Administrator)) {
				admin = (Administrator) userEntry.getValue();
			}
		}
		
		if (admin != null) {
			
			for (Map.Entry<String, Product> productEntry : ProductManager.products.entrySet()) {
				if (productEntry.getKey() == String.valueOf(prodID)) {
					
					product = productEntry.getValue();
					admin.changeTotalQuantity(product, quantity);
					admin.changeAvailableQuantity(product, quantity);
					
				}
			}
			
		}
		return false;
		
	}
	
	/**
	 * Adds two nodes cityA, cityB to the shipping graph
	 * Adds a route (an edge to the shipping graph) from cityA to cityB with length distance
	 * If the nodes or the edge (or both) exist, does nothing
	 * @param cityA 
	 * @param cityB
	 * @param distance -> distance (in km, between cityA and cityB)
	 * @param sessionID -> A session ID that belongs to an authenticated administrator
	 */
	public void addRoute(String cityA, String cityB, int distance, int sessionID) {
		// Your code goes here
		/* FINISHED IMPLEMENTATION */
		Integer sesIDAsInteger = new Integer(sessionID);
		
		if (cityA != null && cityB != null) {
			
			for (Map.Entry<String, User> userEntry : UserManager.userMap.entrySet()) {
				if (userEntry.getValue() instanceof Administrator) {
					
					if (userEntry.getValue().getSessionID().equals(sesIDAsInteger)) {
						
						Node firstCity = new Node(cityA);
						Node secondCity = new Node(cityB);
						
						Edge edgeOne = new Edge(firstCity, distance);
						Edge edgeTwo = new Edge(secondCity, distance);
						
						// checks that both nodes and the edge between 
						// both nodes don't exist before adding the 
						// nodes and the connecting edge between them
						if (!Graph.nodes.contains(firstCity) && !Graph.nodes.contains(secondCity) && 
								!firstCity.edges.contains(edgeTwo) && !secondCity.edges.contains(edgeOne)) {
							Graph.addNode(firstCity);
							Graph.addNode(secondCity);
							Graph.addEdge(distance, firstCity, secondCity);
						}
					}
					
				}
			}
			
		}
	}
	
	/**
	 * Attempts an order in behalf of custID for quantity units of the prodID
	 * @param custID -> The customer ID
	 * @param prodID -> The product ID
	 * @param quantity -> The desired quantity
	 * @param sessionID -> A valid sessionID that belongs to an authenticated shopper user.
	 * @return -> The orderID if successful, -1 if not.
	 */
	public int placeOrder(int custID, int prodID, int quantity, int sessionID) {
		// Your code goes here.
		// Replace the statement below with the correct code.
		
		/* FINISHED IMPLEMENTATION */
		//ANTHONY APPROVED
		Customer customer = null;
		HashMap<Order, Integer> orderMap = null;
		
		for (Map.Entry<String, User> userEntry: UserManager.userMap.entrySet()) {
			
			if (userEntry.getValue().getSessionID().equals((Integer) sessionID)) {
				customer = (Customer) userEntry.getValue();
				
		    	for (Product p: customer.getShoppingCart().keySet()) {
		    		orderMap = new HashMap<Order, Integer>();
		    		Order newO = new Order(p.getCategory(), p.getImage(), p.getID(), p.getDescription(), p.getPrice());
		    		
		    		newO.setCustomer(customer);
		    		orderMap.put(newO, customer.getShoppingCart().get(p));
		    	}
		    	
		    	customer.setOrderMap(orderMap);
		    	return prodID;
			}
		}
		return -1;
		
	}
    
	/**
	 * Returns the best (shortest) delivery route for a given order 
	 * @param orderID -> The order ID we want the delivery route
	 * @param sessionID -> A valid sessionID that belongs to an authenticated shopper user.
	 * @return -> The actual route as an array list of cities, null if not successful
	 */
	public ArrayList<String> getDeliveryRoute(int orderID, int sessionID) {
		// Your code goes here.
		// Replace the statement below with the correct code.
		
		/* FINISHED IMPLEMENTATION */
		Integer sID = new Integer(sessionID);
		LinkedList<Node> path = null;
		
		Node node = null;
		Customer customer = null;
		
		ArrayList<String> route = new ArrayList<String>(); 
		
		for (Map.Entry<String, User> userEntry: UserManager.userMap.entrySet()) {
			if (userEntry.getValue() instanceof Customer) {
				customer = (Customer) userEntry.getValue();
				
				if (userEntry.getValue().getSessionID().equals(sID)) {
					node = Graph.getNode(customer.getCity());
					path = Graph.findPath(node);
					
					for (int i = 0; i < path.size(); i++) {
						route.add(path.get(i).toString());
					}
					return route;
				}
				
			}
		}
		
		return null;
		
	}
	
	/** 
	 * Computes the invoice amount for a given order.
	 * Please use the fixed price 0.01$/km to compute the shipping cost 
	 * @param orderID
	 * @param sessionID -> A valid sessionID that belongs to an authenticated shopper user.
	 * @return
	 */
	public double invoiceAmount(int orderID, int sessionID) {
		// Your code goes here.
		// Replace the statement below with the correct code.
		
		/* FINISHED IMPLEMENTATION */
		//ANTHONY APPROVED
		Customer customer = null;
		Order order = null;
		double amount = 0;
		
		for (Map.Entry<String, User> userEntry : UserManager.userMap.entrySet()) {
			if (userEntry.getValue().getSessionID().equals((Integer) sessionID) && userEntry.getValue() instanceof Customer) {
				customer = (Customer) userEntry.getValue();
				int counter = 0;
				
				for (Order o: customer.getOrderMap().keySet()) {
					if (o.getID() == orderID) {
						order = o;
					}
					counter++;
				}
				
				if (counter == customer.getOrderMap().size()) {
					break;
				}
				
				amount += customer.totalCost(order);
				amount += customer.shippingCost();
			}
		}
		return amount;
		
	}
	
}
