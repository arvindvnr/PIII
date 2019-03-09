package products;

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

public class ProductManager {

	private static final Logger logger = Logger.getLogger(ProductManager.class.getName());
	private static final Handler consoleHandler = new ConsoleHandler();
	
	public static Map<String, Product> products = new HashMap<>();
	
	/**
	 * Creates a new ProductManager.
	 */
	public ProductManager() {		
		logger.setLevel(Level.ALL);
		consoleHandler.setLevel(Level.ALL);
		logger.addHandler(consoleHandler);
	}
	
	/**
	 * Adds product to HashMap of existing products.
	 * @param product product to add
	 */
	public static void addProduct(Product product){
		products.put(String.valueOf(product.getID()), product);
		logger.log(Level.ALL, "Added new product " + product.toString());
	}
	
	/**
	 * Serializes HashMap of products.
	 * @throws IOException
	 */
	public static void saveToFile() throws IOException{
		OutputStream file = new FileOutputStream("products");
		OutputStream buffer = new BufferedOutputStream(file);
		ObjectOutput output = new ObjectOutputStream(buffer);
		
		// serialize the map
		output.writeObject(products);
		output.close();
		buffer.close();
		file.close();
	}
	
	/**
	 * Deserializes HashMap of products from a byte file.
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	@SuppressWarnings("unchecked")
	public static void readFromFile() throws IOException, ClassNotFoundException {
		InputStream file = new FileInputStream("products");
		InputStream buffer = new BufferedInputStream(file);
		ObjectInput input = new ObjectInputStream(buffer);
		
		// deserialize the Map
		products = (Map<String,Product>)input.readObject();
		file.close();
		logger.log(Level.SEVERE, "Cannot read from input");
	}
	
	@Override
	public String toString(){
		String result = "";
		for (Product product : products.values()){
			result += product.toString() + "\n";
		}
		return result;
	}
	
}
