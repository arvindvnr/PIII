/**
 * 
 */
package finalproject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;

import products.CategoryManager;
import products.ProductManager;
import users.Administrator;
import users.Customer;
import users.Shopper;
import users.UserManager;
import GUI.*;
/**
 *
 */
public class ShoppingScreen extends JFrame implements ActionListener {

	/**
	 * Instance variables.
	 */
	private static final long serialVersionUID = 3495141417520038965L;
	public String curUsername;
	public String curPassword;
	
	private JLabel usernameLabel, passwordLabel;
	private JTextField usernameField, passwordField;
	private JButton loginButton, cancelButton, registerButton,button_logout,browsebutton;
	private JButton button_shopping,button_help,button_setting,button_admin_panel,button_admin_shopping;
	
	private Boolean loggedin;
	int sessionID=-1;
	String loggedinUsername="",loggedinPassword="";

	GUINEW productsearch;
	GUIAdminCommands adminPanel;
	
	/**
	 * Creates a GUI layout of the shopping website login screen.
	 */
	public ShoppingScreen() throws EOFException {
		// TODO Auto-generated constructor stub
		super("User Login");
		setSize(300,250);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(true);
		
		loggedin = false;
		add(loginPanel());
		
		try {
			UserManager.readFromFile();
		
			ProductManager.readFromFile();
		
			CategoryManager.readFromFile();
		} catch (IOException | ClassNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		exitLogout();
	}
	
	private void exitLogout() {
		this.addWindowListener(new java.awt.event.WindowAdapter() {
			
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				try {
					if (UserManager.activeSessionIDs.contains(sessionID)) {
						PortalAPI.project.logout(sessionID);
					}
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.exit(0);
		    }
		    
		});
	}
	
	/**
	 * Creates and returns a User Registration GUI screen.
	 * @return a User Registration screen
	 */
	private JFrame userRegistrationFrame() {
		JFrame register = new JFrame("User Registration");
		register.setSize(350, 300);
		register.setDefaultCloseOperation(register.DISPOSE_ON_CLOSE);
		register.setLayout(new GridLayout(8,1));
		register.setVisible(true);
		
		JTextField usernameRegister = new JTextField(20);
		JPasswordField passwordRegister = new JPasswordField(20);
		JPasswordField repasswordRegister = new JPasswordField(20);
		
		JButton registerBtn = new JButton("Register");
		registerBtn.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				String username123 = usernameRegister.getText().toString();
				String password123 = passwordRegister.getText().toString(); 
				
				if (username123.equals("") || password123.equals("")){
					JOptionPane.showMessageDialog(null, "Username and Password cannot be empty",
							"Registeration Error",JOptionPane.INFORMATION_MESSAGE);
				}
				else if (PortalAPI.project.addUser(username123, password123, false)){
					JOptionPane.showMessageDialog(null, "Registration Successful.", 
							"Register", JOptionPane.INFORMATION_MESSAGE);
					
					register.dispose();
				}
				else {
					JOptionPane.showMessageDialog(null, "Oops, Username taken!", "Register", JOptionPane.ERROR_MESSAGE);
				}				
				
			}
			
		});
		
		JButton resetBtn = new JButton("Reset");
		resetBtn.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent e){
				usernameRegister.setText("");
				passwordRegister.setText("");
				repasswordRegister.setText("");
			}
			
		});
	
		register.add(new JLabel("Enter your username: "));
		register.add(usernameRegister);
		register.add(new JLabel("Enter your password: "));
		register.add(passwordRegister);
		register.add(new JLabel("Confirm your password:"));
		register.add(repasswordRegister);
		register.add(registerBtn);
		register.add(resetBtn);
		
		return register;
	}
	
	private JPanel loginPanel(){
		JPanel loginpanel = new JPanel();
		loginpanel.setLayout(new BorderLayout());
		usernameLabel = new JLabel("Username: ");
		passwordLabel = new JLabel("Password: ");
		
		usernameField = new JTextField(12);
		passwordField = new JPasswordField(12);
		
		loginButton = new JButton("Login");
		loginButton.addActionListener(this);
		
		cancelButton = new JButton("Clear");
		cancelButton.addActionListener(this);
		
		registerButton = new JButton("Register");
		registerButton.addActionListener(this);
		
		browsebutton = new JButton("Browse");
		browsebutton.addActionListener(this);
		
		JLabel image = new JLabel(new ImageIcon("fbaylogo.png"));
		image.setPreferredSize(new Dimension(250,100));
		loginpanel.add(image,BorderLayout.PAGE_START);
		
		JPanel labels_login = new JPanel(new FlowLayout());
		JPanel buttons_login = new JPanel(new GridLayout(2,2));
		
		buttons_login.add(loginButton);
		buttons_login.add(cancelButton);
		buttons_login.add(registerButton);
		buttons_login.add(browsebutton);
		labels_login.add(usernameLabel);
		labels_login.add(usernameField);
		labels_login.add(passwordLabel);
		labels_login.add(passwordField);
		loginpanel.add(labels_login, BorderLayout.CENTER);
		loginpanel.add(buttons_login, BorderLayout.PAGE_END);
		
		return loginpanel;
	}
	
	private JPanel shopperMainPanel(int sessionID){
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(5,2));
		
		Date date = new Date();
		DateFormat fd = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		
		JLabel label1 = new JLabel("User: ");
		JLabel label2 = new JLabel(curUsername);
		JLabel label3 = new JLabel("SessionID: ");
		JLabel label4 = new JLabel("(Shopper) "+sessionID);
		JLabel label5 = new JLabel("LoginTime:");
		JLabel label6 = new JLabel(fd.format(date));
		button_help = new JButton("Help");
		button_logout = new JButton("Logout");
		button_setting = new JButton("Setting");
		button_shopping = new JButton("ShoppingScreen");
		button_logout.addActionListener(this);
		button_shopping.addActionListener(this);
		button_help.addActionListener(this);
		button_setting.addActionListener(this);
		panel.add(label1);
		panel.add(label2);
		panel.add(label3);
		panel.add(label4);
		panel.add(label5);
		panel.add(label6);
		panel.add(button_shopping);
		panel.add(button_help);
		panel.add(button_setting);
		panel.add(button_logout);
		
		return panel;
	}
	
	private JPanel adminPanel(int sessionID){
		JPanel adminpanel = new JPanel();
		
		adminpanel.setLayout(new GridLayout(5,2));
		
		Date date = new Date();
		DateFormat fd = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		JLabel label1 = new JLabel("Admin: ");
		JLabel label2 = new JLabel(curUsername);
		JLabel label3 = new JLabel("SessionID:");
		JLabel label4 = new JLabel("(Admin)" + sessionID);
		JLabel label5 = new JLabel("LoginTime:");
		JLabel label6 = new JLabel(fd.format(date));
		button_help = new JButton("Help");
		button_logout = new JButton("Logout");
		button_admin_panel = new JButton("Admin Panel");
		button_admin_shopping = new JButton("ShoppingScreen");
		
		button_logout.addActionListener(this);
		button_admin_shopping.addActionListener(this);
		button_help.addActionListener(this);
		button_admin_panel.addActionListener(this);
		
		adminpanel.add(label1);
		adminpanel.add(label2);
		adminpanel.add(label3);
		adminpanel.add(label4);
		adminpanel.add(label5);
		adminpanel.add(label6);
		adminpanel.add(button_admin_shopping);
		adminpanel.add(button_help);
		adminpanel.add(button_admin_panel);
		adminpanel.add(button_logout);
		
		return adminpanel;
	}
	
	

	private void reset(){
		
		//reseting the panel to login panel
		this.getContentPane().removeAll();
		this.add(loginPanel());
		this.validate();
		this.setTitle("User Login");
		
	}

	
	
	private JFrame userProfile(){
		JFrame userprofile = new JFrame(curUsername);
		
		userprofile.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		userprofile.setSize(300, 500);
		userprofile.setLayout(new BorderLayout());
		
		JPanel logo = new JPanel();
		JPanel information = new JPanel();
		JPanel settings = new JPanel();
		
		logo.add(new JLabel(new ImageIcon("fbaylogo.png")));
		logo.setBackground(Color.darkGray);
		information.setLayout(new GridLayout(5,1));
		
		JPanel setName = new JPanel(new GridLayout(2,1));
		JPanel setPassword = new JPanel(new GridLayout(2,1));
		JPanel setAddress = new JPanel(new GridLayout(2,1));
		JPanel allOrder = new JPanel(new GridLayout(2,1));
		JPanel other = new JPanel(new GridLayout(2,1));
		
		setName.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
		setPassword.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
		setAddress.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
		allOrder.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
		other.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
		
		JTextField userField = new JTextField(8);
		userField.setText(curUsername);
		
		setName.add(new JLabel("Username"));
		setName.add(userField);
		
		JPasswordField passField = new JPasswordField(8);
		passField.setText(curPassword);
		setPassword.add(new JLabel("Password"));
		setPassword.add(passField);
		
		JTextField addrField = new JTextField(8);
		
		setAddress.add(new JLabel("Address"));
		setAddress.add(addrField);
		
		allOrder.add(new JLabel("Order History" + "    CustomerID:"));
		allOrder.add(new JButton("HISTORY"));
		other.add(new JLabel("Comfirmation"));
		other.add(new JButton("Confirm"));
		
		information.add(setName);
		information.add(setPassword);
		information.add(setAddress);
		information.add(allOrder);
		information.add(other);
		
		settings.setLayout(new GridLayout(2,2));
		settings.add(new JButton(""));
		settings.add(new JButton(""));
		settings.add(new JButton(""));
		settings.add(new JButton(""));
		userprofile.add(logo,BorderLayout.PAGE_START);
		userprofile.add(information);
		
		return userprofile;
	}
	
	
	private JFrame frame_help(){
		JFrame helpwindow = new JFrame("Live Chat");
		helpwindow.setSize(400, 600);
		helpwindow.setLocation(550, 50);
		
		helpwindow.setLayout(new BorderLayout());
		
		JTextArea window = new JTextArea();
		window.setEditable(false);
		window.setForeground(Color.white);
		window.setBackground(Color.BLACK);
		Date curdate = new Date();
		DateFormat fd = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String dialog = "               "+fd.format(curdate)+"\n"+
				"TeamFbay: \n\nHello\n\nWelcome to live chat session,"
				+ "\nhow can I help you?";
		window.setText(dialog);
		window.setRows(25);
		JPanel userp = new JPanel();
		JTextField usert = new JTextField(20);
		
		JButton button_send = new JButton("Send");
		button_send.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				Date curdate = new Date();
				String userresponse = "\n\n\n" +"               "+fd.format(curdate)+
									"\n"+curUsername +":\n\n"+usert.getText();
				window.setText(window.getText() + userresponse);
				
				usert.setText("");
			}
		});
		
		userp.add(usert);
		userp.add(button_send);
		helpwindow.add(userp, BorderLayout.PAGE_END);
		helpwindow.add(window, BorderLayout.CENTER);
		return helpwindow;
	}
	
	
	/**
	 * Performs appropriate action for each button in the GUI screen.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == loginButton) {

			System.out.println("Shopping clicked");
			loggedinUsername = usernameField.getText().toString();
			loggedinPassword = passwordField.getText().toString();
			sessionID = PortalAPI.project.login(loggedinUsername, loggedinPassword);
			
			if (sessionID != -1) {
				JOptionPane.showMessageDialog(null, "Login Successful.", "Login", JOptionPane.INFORMATION_MESSAGE);
				
				curUsername = loggedinUsername;
				curPassword = loggedinPassword;
				
				this.getContentPane().removeAll();
				this.setTitle("Welcome! "+curUsername);
				//Check if user is admin or shoppers
				
				//CHECKER
				System.out.println("login status ID:"+sessionID);
				if (UserManager.getUserMap().get(curUsername) instanceof Shopper){
					this.add(shopperMainPanel(sessionID));
					this.validate();
					
					//This is for the product search page when the shopper login.
					if (productsearch != null){
						try {
							productsearch.setloginstatus(true);
						} catch (EOFException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						productsearch.setVisible(true);
					}
					else{
						productsearch = new GUINEW(true,sessionID);
						productsearch.setVisible(true);
						
						
					}
					
				}
				else if (UserManager.getUserMap().get(curUsername) instanceof Customer){
					this.add(shopperMainPanel(sessionID));
					this.validate();
					
					//This is for the product search page when the shopper login.
					if (productsearch != null){
						try {
							productsearch.setloginstatus(true);
						} catch (EOFException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					else{
						productsearch = new GUINEW(true,sessionID);
						productsearch.setVisible(true);
						
						
					}
					
				}
				else if (UserManager.getUserMap().get(curUsername) instanceof Administrator){
					this.add(adminPanel(sessionID));
					this.validate();
					
					//This is for the shopping window when the admin login
					if (productsearch != null){
						try {
							productsearch.setloginstatus(false);
						} catch (EOFException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					else{
						productsearch = new GUINEW(false,sessionID);
						productsearch.setVisible(true);
						
					}
					
				}
				
				
				try {
					UserManager.saveToFile();
				
					ProductManager.saveToFile();
				
					CategoryManager.saveToFile();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else if (UserManager.getUserMap().containsKey(curUsername)) {
				JOptionPane.showMessageDialog(null, "Username/password incorrect.", "Login", JOptionPane.ERROR_MESSAGE);
			}
			else{
				JOptionPane.showMessageDialog(null, "Username and password can't be found.", "Error 404", JOptionPane.ERROR_MESSAGE);
			}
			
		} else if (e.getSource() == cancelButton) {
			
			usernameField.setText("");
			passwordField.setText("");
		
		} else if (e.getSource() == registerButton) {
			// registers user as a new user
			JFrame regist = userRegistrationFrame();
			if (regist.isVisible()){
				
			}
			else{
				userRegistrationFrame();
			}
		}
		else if (e.getSource() == browsebutton){
			
			productsearch = new GUINEW(false,-1);
			productsearch.setVisible(true);
			
		}
		
		else if (e.getSource() == button_setting){
			userProfile().setVisible(true);
		}
		
		else if (e.getSource() == button_admin_panel){
			if (productsearch!=null){
				productsearch.dispose();
			}
			if (adminPanel!=null){
				adminPanel.setVisible(true);
			}
			else{
				adminPanel = new GUIAdminCommands(sessionID);
				adminPanel.setVisible(true);
			}
			
		}
		
		else if (e.getSource() == button_admin_shopping){
			//OLD product list window close and replace new ones
			
			adminPanel.dispose();
			if (productsearch != null){
				productsearch.setVisible(true);
			}
			else{
				productsearch = new GUINEW(false,sessionID);
				productsearch.setVisible(true);
			}
			
			
		}
		
		else if (e.getSource() == button_logout){
			try {
				UserManager.saveToFile();
			
				ProductManager.saveToFile();
			
				CategoryManager.saveToFile();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			int in = JOptionPane.showConfirmDialog(null, "Are you sure to logout?","Logout",JOptionPane.INFORMATION_MESSAGE);
			if (in == JOptionPane.CANCEL_OPTION){
				
			}
			else if (in == JOptionPane.NO_OPTION){
				
			}
			else if (in == JOptionPane.YES_OPTION){
				try {
					//CHECKER
					System.out.println("logout status:" + sessionID);
					
					//API connector
					PortalAPI.project.logout(sessionID);
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				reset();
				if (productsearch!=null){
					try {
						productsearch.setloginstatus(false);
					} catch (EOFException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
			
		}
		
		else if (e.getSource() == button_shopping){
			System.out.println("Shopping clicked");
			if (productsearch.equals(null)){
				productsearch = new GUINEW(true,sessionID);
				productsearch.setVisible(true);
		}
			else if (!productsearch.isActive()){
				productsearch.setVisible(true);
			}
			
			
			
		}
		else if (e.getSource() == button_help){
			frame_help().setVisible(true);
		}
	}
	
}
