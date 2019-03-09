package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.EOFException;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import finalproject.PortalAPI;
import products.Category;
import products.CategoryManager;
import products.Product;
import products.ProductManager;
import users.*;

public class GUINEW extends JFrame implements ActionListener,MouseListener{

	JPanel[][] allproducts;
	JButton[][] addtocart;
	
	ArrayList<JButton> buttonlist;
	public static int pagenumber = 1,cartnumber=0;
	JButton button_search, button_checkCart;
	JTextField textfield_search;
	
	private boolean loggedin;
	JComboBox<String> searchfilter;
	
	JComboBox<String> cate,price;
	final String NOTSET = "Not Set";
	
	Integer sessionID;
	ChopDictionary<Product> c;
	
	ArrayList<Product> productpass = new ArrayList<Product>();
	
	//This will be a product container for all of our products 
	ArrayList<String> objectcontainer = new ArrayList<String>();
	
	GUIShoppingCart shoppingCart;
	static ArrayList<Product> products;
	
	/**
	 * Creates a new GUINEW window.
	 * @param loggedin
	 * @param sessionID
	 */
	public GUINEW(boolean loggedin, Integer sessionID){
		super("Shopping, It's just that easy");
		this.loggedin = loggedin;
		this.sessionID = sessionID;
		
		setSize(600,600);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLayout(new BorderLayout());
		
		add(categorySearch(), BorderLayout.PAGE_START);
		add(productlookthrough(), BorderLayout.CENTER);
		add(actionButtons(),BorderLayout.PAGE_END);
		
	}
	
	/**
	 * Gets login status of this User.
	 * @return boolean variable
	 */
	public boolean getloginstatus(){
		return this.loggedin;
	}
	
	/**
	 * Sets the login status of a User.
	 * @param login a boolean variable
	 * @throws EOFException
	 */
	public void setloginstatus(boolean login) throws EOFException{
		this.loggedin = login;
		if (login){
			enableeverything();
		}
		this.setVisible(true);
		
	}
	
	private JPanel categorySearch(){
		JPanel categorysearch = new JPanel();
		categorysearch.setSize(this.getWidth(), 200);
		
		categorysearch.setLayout(new GridLayout(2,1));
		
		JPanel searchPanel = new JPanel();
		JPanel categoryPanel = new JPanel();
		searchPanel.setBackground(Color.darkGray);
		categoryPanel.setBackground(Color.darkGray);
		categoryPanel.setLayout(new GridLayout(1,3));
		
		searchfilter = new JComboBox<String>();
		searchfilter.addItem("Filter");
		searchfilter.addItem("Category");
		searchfilter.addItem("Product");
		
		textfield_search = new JTextField(25);
		textfield_search.setText("Search any product you want");
		textfield_search.setForeground(Color.lightGray);
		
		button_search = new JButton("Search");
		
		textfield_search.addMouseListener(this);
		button_search.addActionListener(this);
		
		cate = new JComboBox<String>();
		
		cate.addItem("Category");
		//Add available category to the set
		if(!CategoryManager.categories.isEmpty()){
			for (Map.Entry<String, Category> cateset : CategoryManager.categories.entrySet()){
				cate.addItem(cateset.getKey());
			}
		}
		price = new JComboBox<String>();
		
		
		price.addItem("Price");
		price.addItem("Ascending");
		price.addItem("Decending");
		
		searchPanel.add(searchfilter);
		searchPanel.add(textfield_search);
		searchPanel.add(button_search);
		
		JLabel category_label = new JLabel("Sort by");
		category_label.setForeground(Color.white);
		
		
		categoryPanel.add(category_label);
		
		categoryPanel.add(cate);
		categoryPanel.add(price);
		
		
		
		categorysearch.add(searchPanel);
		categorysearch.add(categoryPanel);
		
		return categorysearch;
	}
	
	private JScrollPane productlookthrough(){
		allproducts = new JPanel[2][3];
		addtocart = new JButton[2][3];
		
		JPanel productslook = new JPanel();
		productslook.setLayout(new FlowLayout());
		
		JScrollPane scal = new JScrollPane(productslook);
		
		products = new ArrayList<Product>();
		buttonlist = new ArrayList<JButton>();
		for (Product p : ProductManager.products.values()) {
			products.add(p);
			buttonlist.add(new JButton("Add"));
			
		}
		
		productslook.setAutoscrolls(true);
		
		for (int i=0;i<products.size();i++){
			JPanel panel = new JPanel();
			panel.setLayout(new BorderLayout());
			panel.setBackground(Color.WHITE);
			panel.setPreferredSize(new Dimension(350,350));
			
			buttonlist.get(i).addActionListener(this);
			if (this.loggedin == false){
				buttonlist.get(i).setEnabled(false);
			}
			else{
				buttonlist.get(i).setEnabled(true);
			}
			
			JPanel linefirst = new JPanel(new GridLayout(2,1));
			JPanel nameCate = new JPanel(new BorderLayout());
			
			nameCate.add(new JLabel(products.get(i).getDescription()), BorderLayout.LINE_START);
			nameCate.add(new JLabel(products.get(i).getCategory()), BorderLayout.LINE_END);
			linefirst.add(nameCate);
			JTextField textdes = new JTextField(5);
			
			textdes.setText(products.get(i).getDescription());
			textdes.setEditable(false);
			
			linefirst.add(textdes);
			
			
			JPanel linelast = new JPanel(new GridLayout(1,3));
			linelast.add(new JLabel("$"+Double.toString(products.get(i).getPrice())));
			linelast.add(new JLabel("Available: " + Integer.toString(products.get(i).getAvailableQ())));
			linelast.add(buttonlist.get(i));
			
			
			panel.add(linefirst, BorderLayout.PAGE_START);
			panel.add(new JLabel(new ImageIcon("fbaylogo.png")), BorderLayout.CENTER);
			panel.add(linelast, BorderLayout.PAGE_END);
			
			productslook.add(panel);
		}
		
		return scal;
	}
	
	private void removeAllINPanel(){
		for (int row=0; row<2; row++){
			for (int col=0; col<allproducts[row].length; col++){
				allproducts[row][col].removeAll();
			}
			}
	}
	
	private void validateAllINPanel(){
		for (int row=0; row<2; row++){
			for (int col=0; col<allproducts[row].length; col++){
				allproducts[row][col].revalidate();
			}
		}
	}
	
	private JPanel actionButtons(){
		JPanel actions = new JPanel();
		actions.setLayout(new BorderLayout());
		actions.setBackground(Color.darkGray);
		
		button_checkCart = new JButton("Shopping Cart "+"("+cartnumber+")");
		

		
		button_checkCart.addActionListener(this);
		
		
		
		if (this.loggedin == false){
			button_checkCart.setEnabled(false);
		}
		else{
			button_checkCart.setEnabled(true);
		}
		
		
		JPanel pagepanel = new JPanel();
		pagepanel.setBackground(Color.darkGray);
		
		
		
		actions.add(pagepanel, BorderLayout.CENTER);
		actions.add(button_checkCart, BorderLayout.LINE_END);
		
		
		return actions;
	}
	
	private void enableeverything() throws EOFException {
		
		button_checkCart.setEnabled(true);
		for (int i = 0; i<buttonlist.size();i++){
			if (this.loggedin == false){
				buttonlist.get(i).setEnabled(false);
			}
			else{
				buttonlist.get(i).setEnabled(true);
			}
		}
	
	}
	
	private void displaySearchResult(){
		
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if (e.getSource() == button_checkCart){
			
//*******System API from here add information to our record
//Get shopping cart
			
			if (shoppingCart != null){
				shoppingCart.setVisible(true);
			}
			else{
				shoppingCart = new GUIShoppingCart(cartnumber, sessionID, productpass);
			}
			
		}
		
		//Actions for search
		if (e.getSource() == button_search){
			String filter = (String) searchfilter.getSelectedItem();
			if (filter.equals("Filter")){
				JOptionPane.showMessageDialog(null, "Please select search type", "Error 404", JOptionPane.INFORMATION_MESSAGE);
			} else if (filter.equals("Product")) {
				ArrayList<Product> products = (ArrayList<Product>) ProductManager.products.values();
				ArrayList<String> productStrings = new ArrayList<String>();
				
				for (Product p: products) {
					productStrings.add(p.getDescription());
				}
				
				//SAM PLEASE DISPLAY THIS
				ArrayList<String> foundProducts = GUIHelper.search(textfield_search.getText().toString(), productStrings);
				products.clear();
				for (int i = 0; i < foundProducts.size(); i++) {
					products.add(ProductManager.products.get(foundProducts.get(i)));
				}
				
			} else if (filter.equals("Category")) {
				ArrayList<Category> categories = (ArrayList<Category>) CategoryManager.categories.values();
				ArrayList<String> categoryStrings = new ArrayList<String>();
				
				for (Category c: categories) {
					categoryStrings.add(c.getDescription());
				}
				
				//SAM PLEASE DISPLAY THIS
				ArrayList<String> foundCategories = GUIHelper.search(textfield_search.getText().toString(), categoryStrings);
				categories.clear();
				
				for (int i = 0; i < foundCategories.size(); i++) {
					categories.add(CategoryManager.categories.get(foundCategories.get(i)));
				}
				
				ArrayList<Product> products2 = new ArrayList<Product>();
				for (Category c: categories) {
					products2.addAll(c.getProducts());
				}
				
			}
			else{
				JOptionPane.showMessageDialog(null, "The "+filter+": \n\n"+textfield_search.getText()+"\n\ndoes not exist", "Error 404", JOptionPane.INFORMATION_MESSAGE);
			}
		}
		
		//BACKEND OF ADD TO Shopping cart
		int customerID = 0;
		for (Map.Entry<String, User> userSet : UserManager.getUserMap().entrySet()){
			if (userSet.getValue().getSessionID() == sessionID){
				customerID = ((Shopper) userSet.getValue()).getCustomerID();
				System.out.println("find shopper");
			}
		}
		
		for (int i=0; i<buttonlist.size(); i++){
			if (e.getSource() == buttonlist.get(i)){
				System.out.println("Button"+i+"has been clicked once");
				PortalAPI.project.addToShoppingCart(products.get(i).getID(), products.get(i).getAvailableQ(), sessionID, customerID);
				productpass.add(products.get(i));
				cartnumber++;
				button_checkCart.setText("Shopping Cart "+"("+cartnumber+")");
			}
		}
		repaint();
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == textfield_search){
			textfield_search.setText("");
			textfield_search.setForeground(Color.BLACK);
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
