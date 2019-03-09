package distributioncenter;

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
import java.util.HashMap;
import java.util.Map;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import products.Product;

/**
 * Manages saving and loading of Students.
 */
public class DistributionCenterManager {
	private static final Logger logger = 
			Logger.getLogger(DistributionCenterManager.class.getName());
	private static final Handler consoleHandler = new ConsoleHandler();
	
	public static Map<String, DistributionCenter> distributionmanagers = new HashMap<String, DistributionCenter>();
	
	/**
	 * Creates a new empty DistributionCenterManager.
	 */
	public DistributionCenterManager() {		
		logger.setLevel(Level.ALL);
		consoleHandler.setLevel(Level.ALL);
		logger.addHandler(consoleHandler);
	}
	
	/**
	 * Transfers specified product from all of each DistributionCenter into
	 * that product's HashMap, if there is enough of that product in a 
	 * DistributionCenter.
	 * @param dc a DistributionCenter
	 * @param product the specified Product
	 * @param quantity the specified quantity to transfer from DistributionCenter
	 */
	public static void transferProduct(DistributionCenter dc, Product product, Integer quantity) {
		Integer temp = 0;
		Integer currProductQ;

		while (temp < quantity) {
			for (DistributionCenter d: product.quantityDistributionMap.keySet()) {
				currProductQ = product.quantityDistributionMap.get(d);
				
				if (currProductQ > 0) {
					product.quantityDistributionMap.put(d, currProductQ - 1);
					temp++;
					
					if (temp == quantity) {
						break;
					}
				}
			}
		}
		
		product.quantityDistributionMap.put(dc, product.quantityDistributionMap.get(dc) + temp);
	}
	
	/**
	 * Adds a new DistributionCenter.
	 * @param record new DistributionCenter to add
	 */
	public static void add(DistributionCenter record) {
		distributionmanagers.put(record.getCity(), record);
		logger.log(Level.FINE, "Added new Distribution Center " + record.toString());
	}
	
	/**
	 * Serializes the HashMap of distribution centers.
	 * @throws IOException
	 */
	public static void saveToFile() throws IOException {
		OutputStream file = new FileOutputStream("distributioncenters");    
		OutputStream buffer = new BufferedOutputStream(file); 
		ObjectOutput writer = new ObjectOutputStream(buffer); 
		
		writer.writeObject(distributionmanagers);
		writer.close();
		buffer.close();
		file.close();
	}
    
	/**
	 * Deserializes the HashMap of distribution centers.
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	@SuppressWarnings("unchecked")
	public static void readFromFile() throws IOException, ClassNotFoundException{
		InputStream file = new FileInputStream("distributioncenters"); 
		InputStream buffer = new BufferedInputStream(file); 
		ObjectInput reader = new ObjectInputStream(buffer); 
		
		// deserialize the map
		distributionmanagers = (HashMap<String, DistributionCenter>) reader.readObject();    
		file.close(); 
		logger.log(Level.SEVERE, "Cannot read from input");
	}
	
	@Override
	public String toString() {
		String result = "";
		for (DistributionCenter c: distributionmanagers.values()) {
			result += c.toString() + "\n";
		}
		return result;
	}
}


