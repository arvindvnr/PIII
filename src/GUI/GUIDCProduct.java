/**
 * 
 */
package GUI;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author arvindramesh
 *
 */
public class GUIDCProduct extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6856812442768538744L;
	private GridLayout specifiedPScreen;
	private JPanel panel;
	private JLabel[] prodProperties = new JLabel[4];
	private JButton[] searchProperties = new JButton[4];
	private ButtonHandler buttonAction;
	
	/**
	 * Creates a GUIDCProduct window.
	 */
	public GUIDCProduct() {
		// TODO Auto-generated constructor stub
		super("Product in Distribution Center");
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(600, 700);
		
		specifiedPScreen = new GridLayout(4, 2);
		this.setLayout(specifiedPScreen);
		
		panel = new JPanel();
		add(new JLabel(new ImageIcon("fbaylogo.png")), BorderLayout.PAGE_START);
		add(panel, BorderLayout.CENTER);
		
		createLabel("Product Image: ", panel, prodProperties[0]);
		searchProperties[0] = new JButton("Change image");
		createButton(searchProperties[0], panel, buttonAction);
		
		createLabel("Product Description: ", panel, prodProperties[1]);
		searchProperties[1] = new JButton("Change description");
		createButton(searchProperties[1], panel, buttonAction);
		
		createLabel("Product Price: ", panel, prodProperties[2]);
		searchProperties[2] = new JButton("Change price");
		createButton(searchProperties[2], panel, buttonAction);
		
		createLabel("Product Quantity: ", panel, prodProperties[3]);
		searchProperties[3] = new JButton("Change quantity");
		createButton(searchProperties[3], panel, buttonAction);
		
		this.add(panel);
	}
	
	/**
	 * A helper method for creating a label.
	 * @param textLabel
	 * @param somePanel
	 * @param label
	 * @param textField
	 * @param layout
	 */
	private void createLabel(String textLabel, JPanel somePanel, JLabel label) {
		label = new JLabel(textLabel);
		JPanel tempPanel = new JPanel(new BorderLayout());
		
		tempPanel.add(label);
		somePanel.add(tempPanel);
	}
	
	/**
	 * A helper method for creating a button.
	 * @param button
	 * @param somePanel
	 * @param someHandler
	 */
	private void createButton(JButton button, JPanel somePanel, ButtonHandler someHandler) {
		JPanel tempPanel = new JPanel();
		button.addActionListener(someHandler);
		tempPanel.add(button);
		somePanel.add(tempPanel);
	}
	
	/**
	 * Creates a ButtonHandler for all buttons.
	 */
	private class ButtonHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}

}
