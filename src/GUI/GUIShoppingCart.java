package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import finalproject.PortalAPI;
import finalproject.ProjectV1.ShoppingCartItem;
import products.Product;
import users.Customer;
import users.Shopper;
import users.User;
import users.UserManager;

public class GUIShoppingCart extends JFrame implements ActionListener{

	int cartnumber;
	JButton button_checkout, button_savecart;
	JPanel[] panels_cart = new JPanel[3];
	ArrayList<ShoppingCartItem> shoppinglist = (ArrayList<ShoppingCartItem>) PortalAPI.project.getShoppingCart("");
	Integer sessionID;
	User user;
	ArrayList<JButton> buttons_remove;
	ArrayList<Product> products;
	int size;
	
	/**
	 * Creates a GUIShoppingCart window.
	 * @param cartnumber
	 * @param sessionID
	 * @param products
	 */
	public GUIShoppingCart(int cartnumber, Integer sessionID, ArrayList<Product> products) {
		super("Shopping Cart");
		this.cartnumber = cartnumber;
		
		this.sessionID = sessionID;
		
		this.products = products;
		for (User u: UserManager.userMap.values()) {
			if (u.getSessionID().equals(sessionID)) {
				if (u instanceof Shopper) {
					user = (Shopper) u;
				} else if (u instanceof Customer) {
					user = (Customer) u;
				}
				break;
			}
		}
		
		setSize(400,700);
		setLocation(800,0);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLayout(new BorderLayout());
		
		add(panel_logo(),BorderLayout.PAGE_START);
		add(panel_cartbody(),BorderLayout.CENTER);
		add(panel_actions(),BorderLayout.PAGE_END);
	}
	
	private JPanel panel_logo(){
		JPanel logo = new JPanel();
		logo.setSize(getWidth(), 100);
		logo.setBackground(Color.darkGray);
		logo.add(new JLabel(new ImageIcon("fbayshopcart.png")));
		return logo;
	}
	
	private JPanel panel_cartbody(){
		JPanel cartbody = new JPanel(new BorderLayout());
		cartbody.setBackground(Color.white);
		
		
		if (PortalAPI.project.getShoppingCart(user.getID()) != null){
			size =PortalAPI.project.getShoppingCart(user.getID()).size();
		}
		buttons_remove = new ArrayList<JButton>();
		JPanel type = new JPanel(new GridLayout(1,4));
		type.add(new JLabel("Product ID"));
		type.add(new JLabel("Quantity"));
		type.add(new JLabel("Center"));
		type.add(new JLabel("Modify"));
		
		
		JPanel items = new JPanel(new GridLayout(size,4));
		for (int i=0; i<size; i++){
			ShoppingCartItem si = PortalAPI.project.getShoppingCart(this.user.getID()).get(i);
			buttons_remove.add(new JButton("Remove"));
			items.add(new JLabel(si.getProdID()));
			items.add(new JLabel(si.getQuantity().toString()));
			items.add(new JLabel(si.getCenter().toString()));
			items.add(buttons_remove.get(i));
			buttons_remove.get(i).addActionListener(this);
		}
		
		
		cartbody.add(type, BorderLayout.PAGE_START);
		cartbody.add(items, BorderLayout.CENTER);
		
		
		return cartbody;
	}
	
	private JPanel panel_actions(){
		JPanel actions = new JPanel();
		actions.setLayout(new BorderLayout());
		actions.setBackground(Color.black);
		button_checkout = new JButton("Checkout");
		button_checkout.addActionListener(this);
		button_savecart = new JButton("Save cart");
		button_savecart.addActionListener(this);
		actions.add(button_checkout,BorderLayout.LINE_END);
		actions.add(button_savecart,BorderLayout.LINE_START);
		return actions;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		for (int i=0; i<size; i++){
			if (e.getSource() == buttons_remove.get(i)){
				String input = JOptionPane.showInputDialog("How many would you like to remove?");
				Integer quantity = Integer.parseInt(input);
				
				
				User u = null;
				if (this.user instanceof Shopper) {
					u = (Shopper) this.user;
					//Product product = ((Shopper) u).getShoppingCart().get();
					if (!products.isEmpty()) {
						Product product = products.get(i);
						((Shopper) u).removeFromCart(product, quantity);
						products.remove(i);
					}
				} else if (this.user instanceof Customer) {
					u = (Customer) this.user;
					//Product product = ((Customer) u).getShoppingCart().get();
					if (!products.isEmpty()) {
						Product product = products.get(i);
						((Customer) u).removeFromCart(product, quantity);
						products.remove(i);
					}
				}
				
			}
		}
		if (e.getSource() == button_checkout){
//*******System API from here add information to our record
			Integer temp = JOptionPane.showConfirmDialog(null, "Are you sure you want to checkout?", "Checkout Confirmation", JOptionPane.YES_NO_OPTION);
			
			if (temp == 0) {
				if (user instanceof Shopper) {
					PortalAPI.project.checkout(((Shopper) user).getCustomerID(), sessionID);
				} else if (user instanceof Customer) {
					PortalAPI.project.checkout(((Customer) user).getCustomerID(), sessionID);
				}
				GUIInvoice invoice = new GUIInvoice(this.cartnumber);
				invoice.setVisible(true);
			} 
		}
		
		
		if (e.getSource() == button_savecart){
			if (cartnumber == 0) {
				JOptionPane.showMessageDialog(null, "Cannot save an empty cart!","Error", 
						JOptionPane.ERROR_MESSAGE);
			} else {
				if (user instanceof Shopper) {
					try {
						((Shopper) user).saveCart();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else if (user instanceof Customer) {
					try {
						((Customer) user).saveCart();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		}
		
	}

}
