package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import distributioncenter.DistributionCenter;
import distributioncenter.DistributionCenterManager;
import graph.Graph;
import finalproject.PortalAPI;
import products.Category;
import products.CategoryManager;
import products.Invoice;
import products.Product;
import products.ProductManager;
import users.Administrator;
import users.User;
import users.UserManager;

public class GUIAdminCommands extends JFrame implements ActionListener{

	JButton button_addAdmin,button_addProduct,button_addCategory,button_addDistributionCenter;
	JButton button_checkProduct, button_checkDistributionCenter, button_showScreenReport;
	JButton button_addRoute;
	JButton button_modifyQuantity;
	JButton button_salesReport;
	
	JTextField text_adminname;
	JPasswordField text_adminpassword,text_adminrepeat;
	JTextField text_productname,text_productcategory,text_productprice,text_productdescription,text_productdescription2;
	JTextField text_categorycode, text_categorydescription,text_productquantity;
	JTextField text_disname,text_discity;
	JTextField text_cityA, text_cityB, text_distance;
	
	Integer sessionID;
	Administrator admin;
	
	/**
	 * Creates a GUIAdminCommands window.
	 * @param sessionID that of the Administrator
	 */
	public GUIAdminCommands(Integer sessionID){
		
		super("Administrator Command Center");
		
		admin = null;
		
		for (User u : UserManager.getUserMap().values()) {
			if (u.getSessionID().equals(sessionID)) {
				admin = (Administrator) u;
			}
		}
		
		this.sessionID = sessionID;
		setSize(500,600);
		setLayout(new GridLayout(8,1));
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		button_addAdmin = new JButton("Add New");
		button_addProduct = new JButton("Add New");
		button_addCategory = new JButton("Add New");
		button_addDistributionCenter = new JButton("Add New");
		
		button_checkProduct = new JButton("Check Products");
		button_checkDistributionCenter = new JButton("Check Centers");
		button_showScreenReport = new JButton("Screen Report");
		button_modifyQuantity = new JButton("Modify");
		button_salesReport = new JButton("Sales Report");
		
		button_addRoute = new JButton("Add New");
		
		text_cityA = new JTextField(6);
		text_cityB = new JTextField(6);
		text_distance = new JTextField(6);
		
		text_adminname = new JTextField(6);
		text_adminpassword = new JPasswordField(6);
		text_adminrepeat = new JPasswordField(6);
		text_productname = new JTextField(6);
		text_productcategory = new JTextField(6);
		text_productprice = new JTextField(6);
		text_productdescription = new JTextField(6);
		text_productdescription2 = new JTextField(6);
		text_categorycode = new JTextField(6);
		text_categorydescription = new JTextField(6);
		text_disname = new JTextField(6);
		text_discity = new JTextField(6);
		text_productquantity = new JTextField(6);
		button_modifyQuantity.addActionListener(this);
		
		button_addRoute.addActionListener(this);
		
		button_salesReport.addActionListener(this);
		
		button_addAdmin.addActionListener(this);
		button_addProduct.addActionListener(this);
		button_addCategory.addActionListener(this);
		button_addDistributionCenter.addActionListener(this);
		
		button_checkProduct.addActionListener(this);
		button_checkDistributionCenter.addActionListener(this);
		button_showScreenReport.addActionListener(this);
		
		JPanel logopanel = new JPanel();
		logopanel.add(new JLabel(new ImageIcon("fbaylogo.png")));
		logopanel.setBackground(Color.darkGray);
		
		add(logopanel);
		add(admincontrol());
		add(productcontrol());
		add(productmodifycontrol());
		add(categorycontrol());
		add(distributioncentercontrol());
		add(routecontrol());
		add(salesreportcontrol());
		
		//API connection start
		
	}
	
	private JPanel salesreportcontrol() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setBorder(BorderFactory.createLineBorder(Color.white));
		JPanel showlabel = new JPanel(new BorderLayout());
		JLabel label = new JLabel("Sales Report");
		label.setForeground(Color.blue);
		showlabel.add(label, BorderLayout.LINE_START);
		showlabel.add(button_salesReport, BorderLayout.LINE_END);
		
		panel.add(showlabel, BorderLayout.PAGE_START);
		return panel;
	}
	
	private JPanel routecontrol() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setBorder(BorderFactory.createLineBorder(Color.white));
		JLabel label = new JLabel("City Manager");
		label.setForeground(Color.blue);
		JPanel showlabel = new JPanel(new BorderLayout());
		showlabel.add(label, BorderLayout.LINE_START);
		showlabel.add(button_addRoute, BorderLayout.LINE_END);
		JPanel inputcontrol = new JPanel(new GridLayout(3,2));
		
		inputcontrol.add(new JLabel("From City:"));
		inputcontrol.add(text_cityA);
		inputcontrol.add(new JLabel("To City:"));
		inputcontrol.add(text_cityB);
		inputcontrol.add(new JLabel("Distance:"));
		inputcontrol.add(text_distance);
		
		panel.add(showlabel, BorderLayout.PAGE_START);
		panel.add(inputcontrol);
		return panel;
	}
	
	private JPanel admincontrol(){
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setBorder(BorderFactory.createLineBorder(Color.white));
		JLabel label = new JLabel("Administrator Manager");
		label.setForeground(Color.blue);
		JPanel showlabel = new JPanel(new BorderLayout());
		showlabel.add(label, BorderLayout.LINE_START);
		showlabel.add(button_addAdmin, BorderLayout.LINE_END);
		JPanel inputcontrol = new JPanel(new GridLayout(3,2));
		
		inputcontrol.add(new JLabel("Username:"));
		inputcontrol.add(text_adminname);
		inputcontrol.add(new JLabel("Password:"));
		inputcontrol.add(text_adminpassword);
		inputcontrol.add(new JLabel("Repeat Password:"));
		inputcontrol.add(text_adminrepeat);
		
		panel.add(showlabel, BorderLayout.PAGE_START);
		panel.add(inputcontrol);
		return panel;
	}
	
	private JPanel productmodifycontrol() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		JLabel label = new JLabel("Product Modification");
		label.setForeground(Color.blue);
		
		JPanel buttons_action = new JPanel();
		buttons_action.add(button_modifyQuantity);
		
		JPanel inputcontrol = new JPanel(new GridLayout(2,2));
		
		inputcontrol.add(new JLabel("Description:"));
		inputcontrol.add(text_productdescription);
		inputcontrol.add(new JLabel("Product Quantity:"));
		inputcontrol.add(text_productquantity);
		JPanel showlabel = new JPanel(new BorderLayout());
		showlabel.add(label, BorderLayout.LINE_START);
		showlabel.add(buttons_action, BorderLayout.LINE_END);
		
		
		showlabel.add(buttons_action, BorderLayout.LINE_END);
		
		panel.add(showlabel, BorderLayout.PAGE_START);
		panel.add(inputcontrol);
		return panel;
	}
	
	private JPanel productcontrol(){
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setBorder(BorderFactory.createLineBorder(Color.white));
		JLabel label = new JLabel("Product Manager");
		label.setForeground(Color.blue);
		
		JPanel buttons_action = new JPanel();
		buttons_action.add(button_checkProduct);
		buttons_action.add(button_addProduct);
		JPanel showlabel = new JPanel(new BorderLayout());
		showlabel.add(label, BorderLayout.LINE_START);
		showlabel.add(buttons_action, BorderLayout.LINE_END);
		
		
		JPanel inputcontrol = new JPanel(new GridLayout(2,4));
		
		inputcontrol.add(new JLabel("Name:"));
		inputcontrol.add(text_productname);
		inputcontrol.add(new JLabel("Category:"));
		inputcontrol.add(text_productcategory);
		inputcontrol.add(new JLabel("Price:"));
		inputcontrol.add(text_productprice);
		inputcontrol.add(new JLabel("Description:"));
		inputcontrol.add(text_productdescription2);
		
				
		panel.add(showlabel, BorderLayout.PAGE_START);
		panel.add(inputcontrol);
		return panel;
	}
	
	private JPanel categorycontrol(){
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setBorder(BorderFactory.createLineBorder(Color.white));
		JLabel label = new JLabel("Category Manager");
		label.setForeground(Color.blue);
		JPanel showlabel = new JPanel(new BorderLayout());
		JPanel buttons_action = new JPanel();
		buttons_action.add(button_showScreenReport);
		buttons_action.add(button_addCategory);
		showlabel.add(label, BorderLayout.LINE_START);
		showlabel.add(buttons_action, BorderLayout.LINE_END);
		JPanel inputcontrol = new JPanel(new GridLayout(1,2));
		
		
		inputcontrol.add(new JLabel("Description:"));
		inputcontrol.add(text_categorydescription);
		
		
		panel.add(showlabel, BorderLayout.PAGE_START);
		panel.add(inputcontrol);
		return panel;
	}
	
	private JPanel distributioncentercontrol(){
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setBorder(BorderFactory.createLineBorder(Color.white));
		JLabel label = new JLabel("Distribution Center Manager");
		label.setForeground(Color.blue);
		JPanel showlabel = new JPanel(new BorderLayout());
		JPanel buttons_action = new JPanel();
		buttons_action.add(button_checkDistributionCenter);
		buttons_action.add(button_addDistributionCenter);
		
		showlabel.add(label, BorderLayout.LINE_START);
		showlabel.add(buttons_action, BorderLayout.LINE_END);
		JPanel inputcontrol = new JPanel(new GridLayout(1,2));
		
		inputcontrol.add(new JLabel("City:"));
		inputcontrol.add(text_discity);
		
		panel.add(showlabel, BorderLayout.PAGE_START);
		panel.add(inputcontrol);
		return panel;
	}
	
	private JFrame seeDistributionCenters(){
		JFrame centers = new JFrame();
		centers.setTitle("Search Distribution Center");
		centers.setSize(400, 175);
		centers.setLocation(400,300);
		centers.setLayout(new BorderLayout());
		centers.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		centers.setBackground(Color.WHITE);
		JPanel searchbar = new JPanel();
		
		JTextField searchfield = new JTextField(15);
		JButton searchbutton = new JButton("Search");
		
		
		searchbar.add(searchfield);
		searchbar.add(searchbutton);
		centers.add(new JLabel(new ImageIcon("fbaylogo.png")), BorderLayout.PAGE_START);
		centers.add(searchbar, BorderLayout.CENTER);
		centers.setVisible(true);
		return centers;
	}
	
	private JFrame allProduct(){
		JFrame rp = new JFrame();
		rp.setTitle("All Products");
		rp.setSize(600, 400);
		rp.setLocation(400,300);
		rp.setLayout(new BorderLayout());
		rp.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		ArrayList<Product> productlist = new ArrayList<Product>(); 
		for (Entry<String, Product> entryset : ProductManager.products.entrySet()){
			productlist.add(entryset.getValue());
		}
		JPanel category = new JPanel(new GridLayout(1,5));
		JPanel products = new JPanel(new GridLayout(productlist.size(),5));
		
		category.add(new JLabel("Product Name"));
		category.add(new JLabel("Product Categoty"));
		category.add(new JLabel("Product Price"));
		category.add(new JLabel("Product Quantity"));
		category.add(new JLabel("Product Description"));
		
		
		for (Product p : productlist){
			products.add(new JLabel(p.getDescription()));
			products.add(new JLabel(p.getCategory()));
			products.add(new JLabel(Double.toString(p.getPrice())));
			products.add(new JLabel(Integer.toString(p.getAvailableQ())));
			products.add(new JLabel(p.getDescription()));
			
		}
		
		rp.add(category, BorderLayout.PAGE_START);
		rp.add(products, BorderLayout.CENTER);
		return rp;
	}
	
	private JFrame allDistributionCenter(){
		
		JFrame frame = new JFrame("Distribution Center");
		frame.setSize(400, 400);
		frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		
		ArrayList<DistributionCenter> dislist = new ArrayList<DistributionCenter>();
		for (Map.Entry<String, DistributionCenter> dc : DistributionCenterManager.distributionmanagers.entrySet()){
			dislist.add(dc.getValue());
		}
		if (!dislist.isEmpty()){
			JPanel labels = new JPanel(new GridLayout(1,4));
			JPanel types = new JPanel(new GridLayout(dislist.size(),4));
			
			labels.add(new JLabel("Name"));
			labels.add(new JLabel("City"));
			labels.add(new JLabel("Center ID"));
			labels.add(new JLabel("Manage"));
			for (DistributionCenter dc: dislist){
				types.add(new JLabel(dc.getCity()));
				types.add(new JLabel(((Integer)dc.getID()).toString()));
				types.add(new JButton("Edit"));
			}
			
			frame.add(labels, BorderLayout.PAGE_START);
			frame.add(types, BorderLayout.CENTER);
			
		}
		
		return frame;
		
	}
	
	private JFrame showReport(){
		JFrame rp = new JFrame();
		rp.setTitle("Screen Report");
		rp.setSize(800, 800);
		rp.setLocation(400,300);
		rp.setLayout(new BorderLayout());
		rp.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		Date date = new Date();
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		
		//DEFAULT
		int size = CategoryManager.categories.size();
		JPanel showlabel = new JPanel(new GridLayout(1,2));
		JPanel allcate = new JPanel(new GridLayout(size, 2));
		if (size == 0){
			rp.add(new JLabel("Empty"));
		}
		else{
			showlabel.add(new JLabel("Code"));
			showlabel.add(new JLabel("Description"));
			
			for (Map.Entry<String, Category> entrySet: CategoryManager.categories.entrySet()){
				allcate.add(new JLabel(Integer.toString(entrySet.getValue().getCode())));
				allcate.add(new JLabel(entrySet.getValue().getDescription()));
			}
			rp.add(showlabel, BorderLayout.PAGE_START);
			rp.add(allcate, BorderLayout.CENTER);
		}
		
		rp.setVisible(true);
		return rp;
	}
	
	//helper function from the backend, will be removed later
	private boolean newAdminChecker(String user, String pass, String repeatp){
		if (user.equals("") || pass.equals("")){
			JOptionPane.showMessageDialog(null, "Username and Password cannot be empty", "Error", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
		else if (!pass.equals(repeatp)){
			JOptionPane.showMessageDialog(null, "Passwords do not match ", "Error", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
		//System API from here add information to our record
//*******System API from here add information to our record		
		
		return PortalAPI.project.addUser(user, pass, true);
		
	}
	
	private boolean newProductChecker(String name, String category, String price,String description){
		//Check if any field is empty
		for (String s: ProductManager.products.keySet()) {
			if (s.equals(description)) {
				JOptionPane.showMessageDialog(null, "Product already exists, adding to quantity!", "Product Exists", JOptionPane.INFORMATION_MESSAGE);
				
				return false;
			}
		}
		
		if (name.equals("") || category.equals("") || price.equals("") || description.equals("")){
			JOptionPane.showMessageDialog(null, "All fields are required", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		//System API from here add information to our record
		//category needs to be int and price needs to be double
		
		//Converting the categoryID and price to our system
		Category c = CategoryManager.categories.get(category);
		int categoryInt = c.getCode();
		double priceDouble = Double.parseDouble(price);

//*******System API from here add information to our record
		
		//admin need the session ID at here -->
		System.out.println(this.sessionID);
		PortalAPI.project.addProduct(name, categoryInt, priceDouble, this.sessionID);
		System.out.println(ProductManager.products);
		return true;
	}
	
	private boolean newCategoryChecker(String categorydescription){
		if (categorydescription.equals("")){
			JOptionPane.showMessageDialog(null, "All fields are required", "Error", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
//*******System API from here add information to our record
		//categorycode need to be int
		
		PortalAPI.project.addCategory(categorydescription, sessionID);
		try {
			CategoryManager.saveToFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	
	private boolean newDistributionChecker(String city){
		if (city.equals("")){
			JOptionPane.showMessageDialog(null, "All fields are required", "Error", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
//******* System API from here add information to our record
		//ID need to be int object
		PortalAPI.project.addDistributionCenter(city, this.sessionID);
		try {
			DistributionCenterManager.saveToFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	
	private void addImage(){
		JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            System.out.println("Selected file: " + selectedFile.getAbsolutePath());
        } 
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == button_addAdmin){
			
			String adminname = text_adminname.getText().toString();
			String adminpassword = text_adminpassword.getText().toString();
			String adminrepa = text_adminrepeat.getText().toString();
			
			System.out.println(adminname);
			System.out.println(adminpassword);
			System.out.println(adminrepa);
			
			if (newAdminChecker(adminname, adminpassword, adminrepa)){
				JOptionPane.showMessageDialog(null, "You have added new Admin "+adminname, "Add Admin", JOptionPane.INFORMATION_MESSAGE);
				
				//if successful, then reset all the field
				text_adminname.setText("");
				text_adminpassword.setText("");
				text_adminrepeat.setText("");
				
			} else {
				JOptionPane.showMessageDialog(null, "Operation failed.", "Add Admin", JOptionPane.ERROR_MESSAGE);
			}
			
		}
		
		if (e.getSource() == button_salesReport) {
			JOptionPane.showMessageDialog(null, Invoice.invoiceList, "Sales Report", JOptionPane.OK_OPTION);
			System.out.println(Invoice.invoiceList);
		}
		
		if (e.getSource() == button_modifyQuantity) {
			int quantity = Integer.parseInt(text_productquantity.getText().toString());
			String description = text_productdescription.getText().toString();
			
			Product product = null;
			
			int counter = 0;
			for (Product p: ProductManager.products.values()) {
				if (p.getDescription().equals(description)) {
					product = p;
				}
				else {
					counter++;
				}
			}
			if (counter == ProductManager.products.size()) {
				JOptionPane.showMessageDialog(null, "Product does not exist", "Error", JOptionPane.ERROR_MESSAGE);
			}
			
			for (User u: UserManager.userMap.values()) {
				if (u.getSessionID() == sessionID) {
					admin = (Administrator) u;
				}
				counter++;
			}
			
			if (admin != null && product != null) {
				System.out.println(product.getAvailableQ());
				admin.changeTotalQuantity(product, quantity);
				admin.changeAvailableQuantity(product, quantity);
				System.out.println(product.getAvailableQ());
				JOptionPane.showMessageDialog(null, "Quantity changed successfully!", "Quantity", JOptionPane.INFORMATION_MESSAGE);
				
				text_productquantity.setText("");
				text_productdescription.setText("");
			}
		}
		
		if (e.getSource() == button_addRoute) {
			String fromCity = text_cityA.getText().toString();
			String toCity = text_cityB.getText().toString();
			int distance = Integer.parseInt(text_distance.getText().toString());
			
			if (fromCity.length() > 0 && toCity.length() > 0 && distance > 0) {
				PortalAPI.project.addRoute(fromCity, toCity, distance, sessionID);
				JOptionPane.showMessageDialog(null, "Successfully added route!", "Route", JOptionPane.INFORMATION_MESSAGE);
				System.out.println(Graph.nodes);
				text_cityA.setText("");
				text_cityB.setText("");
				text_distance.setText("");
			} else {
				JOptionPane.showMessageDialog(null, "Error", "Error", JOptionPane.ERROR_MESSAGE);
			}
			
		}
		
		if (e.getSource() == button_addCategory){
			String categorydescription = text_categorydescription.getText().toString();
			
			if (CategoryManager.categories.containsKey(categorydescription)){
				System.out.println(CategoryManager.categories);
				JOptionPane.showMessageDialog(null, "Category already exists", "Error 404", JOptionPane.INFORMATION_MESSAGE);
				
			}
			else{
				if (newCategoryChecker(categorydescription)){
					JOptionPane.showMessageDialog(null, "You have added new Category: "+categorydescription, "Add Category", JOptionPane.INFORMATION_MESSAGE);
					System.out.println(CategoryManager.categories);
				
					//If adding successfully, reset all the fields
					text_categorycode.setText("");
					text_categorydescription.setText("");
				}
			}
		}
		
		if (e.getSource() == button_addProduct){
			
			String name = text_productname.getText().toString();
			String category = text_productcategory.getText().toString();
			String price = text_productprice.getText().toString();
			String description = text_productdescription2.getText().toString();
			
			if (!CategoryManager.categories.keySet().contains(category)) {
				JOptionPane.showMessageDialog(null, "Category does not exist!", "Error", JOptionPane.ERROR_MESSAGE);
			}
			else {
				if (newProductChecker(name, category, price,description)){
					JOptionPane.showMessageDialog(null, "You have added new Product: "+name, "Add Product", JOptionPane.INFORMATION_MESSAGE);	
					
					text_productname.setText("");
					text_productcategory.setText("");
					text_productprice.setText("");
					text_productdescription2.setText("");
				} else {
					JOptionPane.showMessageDialog(null, "Product not added!", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		
		if (e.getSource() == button_addDistributionCenter){
	
			String city = text_discity.getText().toString();
			//String id = text_disid.getText().toString();
			
			if (newDistributionChecker(city)){
				PortalAPI.project.addDistributionCenter(city, sessionID);
				System.out.println(DistributionCenterManager.distributionmanagers);
				JOptionPane.showMessageDialog(null, "You have added new Distribution Center to: " + city, "Add Distribution Center", JOptionPane.INFORMATION_MESSAGE);
				text_discity.setText("");
			//	text_disid.setText("");
			}
		}
		
		if (e.getSource() == button_checkProduct){
			//This is generated message when nothing input yet
			if (ProductManager.products.isEmpty()){
				JOptionPane.showMessageDialog(null, "Sorry, No product yet. Why not add one now?","Uh-oh",JOptionPane.INFORMATION_MESSAGE);
			}
			else{
				//GUIOneClickSearch search = new GUIOneClickSearch("Product");
				//search.setVisible(true);
				allProduct().setVisible(true);
			}	
			
		}
		if (e.getSource() == button_checkDistributionCenter){
			//This is generate message when nothing input yet
			if (DistributionCenterManager.distributionmanagers.isEmpty()){
				JOptionPane.showMessageDialog(null, "Sorry, No Distribution Center yet. Why not add one now?","Uh-oh",JOptionPane.INFORMATION_MESSAGE);
			}
			else{
				allDistributionCenter().setVisible(true);;
				//GUIOneClickSearch search = new GUIOneClickSearch("Distribution Center");
				//search.setVisible(true);
			}
		}
		
		if (e.getSource() == button_showScreenReport){
			showReport();
		}

	}
	
}
