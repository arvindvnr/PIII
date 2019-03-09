package GUI;

import java.util.ArrayList;
import java.util.Map;

import products.Category;
import products.CategoryManager;
import products.Product;
import products.ProductManager;
import users.User;
import users.UserManager;

public class GUIHelper {

	/**
	 * Creates a GUIHelper window.
	 */
	public GUIHelper(){
		
	}
	
	/**
	 * Performs a search through an ArrayList of String objects.
	 * @param key
	 * @param words
	 * @return an ArrayList of String objects
	 */
	public static ArrayList<String> search(String key, ArrayList<String> words) {
		
		key = key.toLowerCase();
		ArrayList<String> related = new ArrayList<String>();
		
		for (int i = 0; i < words.size(); i++) {
			
			String word = words.get(i);
			word = word.toLowerCase();

			if (word.contains(key)) {
				related.add(word);
			}
		}
		return related;
	}	
	
	/**
	 * Gets an ArrayList of Products.
	 * @return an ArrayList of Products
	 */
	public ArrayList<Product> getLlist(){
		ArrayList<Product> productList = new ArrayList<Product>();
		
		
		for (Map.Entry<String, Product> productEntry: ProductManager.products.entrySet()){
			productList.add(productEntry.getValue());
		}
		return productList;
	}
	
	/**
	 * Gets an ArrayList of arrays of size 2.
	 * @return an ArrayList of arrays of size 2
	 */
	public static ArrayList<Product[][]> getProductList(){
		ArrayList<Product> productList = new ArrayList<Product>();
		
		
		for (Map.Entry<String, Product> productEntry: ProductManager.products.entrySet()){
			productList.add(productEntry.getValue());
		}
		
		
		ArrayList<Product[][]> productsList = new ArrayList<Product[][]>();
		int page = 0;
		if (productList.size()<6){
			page = 1;
		}
		else{
			if (productList.size()%6==0){
				page=productList.size()/6;
			}
			else{
				page=((productList.size()-productList.size()%6)/6)+1;
			}
		}
		
		
		
		for (int i=0; i<page; i++){
			productsList.add(new Product[2][3]);
		}
		
		for (int i = 0; i < productsList.size(); i++) {
			
			int n = 0;
			int m = 0;
			
			for (Product p: productList) {
				if (n == 2) {
					n = 0;
					m = 0;
				}
				if (m == 3) {
					n++;
					m = 0;
				}
				
				productsList.get(i)[n][m] = p;
				m++;
			}
		}
			
		return productsList;
	}
	
	/**
	 * Gets an ArrayList of Users.
	 * @return an ArrayList of Users
	 */
	public static ArrayList<User> getUserList(){
		ArrayList<User> userList = new ArrayList<User>();
		for (Map.Entry<String, User> userEntry: UserManager.userMap.entrySet()){
			userList.add(userEntry.getValue());
		}
		return userList;
	}
	
	/**
	 * Gets an ArrayList of Category objects.
	 * @return an ArrayList of Category objects
	 */
	public static ArrayList<Category> getCategoryList(){
		ArrayList<Category> categoryList = new ArrayList<Category>();
		for (Map.Entry<String, Category> categoryEntry: CategoryManager.categories.entrySet()){
			categoryList.add(categoryEntry.getValue());
		}
		return categoryList;
	}
	
	public static void searchAlgorithm(){
		
	}
	
}
