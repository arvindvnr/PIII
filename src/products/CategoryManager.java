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

/**
 * Manages saving and loading of Students.
 */
public class CategoryManager {
	public static Integer idNum = 1;
	private static final Logger logger = 
			Logger.getLogger(CategoryManager.class.getName());
	private static final Handler consoleHandler = new ConsoleHandler();
	
	// A mapping of Category of code to Category
	public static Map<String, Category> categories = new HashMap<String, Category>();
	
	/**
	 * Creates a new empty CategoryManager.
	 */
	public CategoryManager() {		
		logger.setLevel(Level.ALL);
		consoleHandler.setLevel(Level.ALL);
		logger.addHandler(consoleHandler);
	}

	/**
	 * Adds a new Category to HashMap of existing categories.
	 * @param record Category to add
	 */
	public static void add(Category record) {
		categories.put(record.getDescription(), record);
		logger.log(Level.FINE, "Added new category " + record.toString());
	}
	
	/**
	 * Serializes the HashMap of categories.
	 * @throws IOException
	 */
	public static void saveToFile() throws IOException{
		OutputStream file = new FileOutputStream("categories");
		OutputStream buffer = new BufferedOutputStream(file);
		ObjectOutput writer = new ObjectOutputStream(buffer); 
		
		writer.writeObject(categories);
		writer.close();
		buffer.close();
		file.close();
	}
	
	/**
	 * Deserializes the byte file where the HashMap of categories is stored.
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	@SuppressWarnings("unchecked")
	public static void readFromFile() throws IOException, ClassNotFoundException{
		InputStream file = new FileInputStream("categories"); 
		InputStream buffer = new BufferedInputStream(file); 
		ObjectInput reader = new ObjectInputStream(buffer); 
		
		// deserialize the map
		categories = (HashMap<String, Category>) reader.readObject();    
		file.close(); 
		logger.log(Level.SEVERE, "Cannot read from input");
	}
	
	@Override
	public String toString() {
		String result = "";
		
		for (Category c: categories.values()) {
			result += c.toString() + "\n";
		}
		return result;
	}
	
}

