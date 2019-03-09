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
import javax.swing.JTextField;

/**
 * @author arvindramesh
 *
 */
public class GUIProductSearch extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8428916253318095109L;
	private String prodName;
	
	private GridLayout prodSearchScreen;
	private JPanel panel;
	private JLabel enterProd;
	private JTextField specificProd;
	private JButton searchProd;
	private ButtonHandler buttonAction;

	/**
	 * Creates a GUIProductSearch window.
	 */
	public GUIProductSearch() {
		// TODO Auto-generated constructor stub
		super("Check and Search Products");
		
		prodSearchScreen = new GridLayout(4, 2);
		this.setLayout(prodSearchScreen);
		
		panel = new JPanel();
		add(new JLabel(new ImageIcon("fbaylogo.png")), BorderLayout.PAGE_START);
		add(panel, BorderLayout.CENTER);
		
		specificProd = new JTextField(20);
		buttonAction = new ButtonHandler();
		
		createLabel("Enter product: ", panel, enterProd);
		createTextBox(panel, specificProd);
		
		searchProd = new JButton("Search product!");
		createButton(searchProd, panel, buttonAction);
		
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
	 * A helper method for creating a text box.
	 * @param somePanel
	 * @param someTextField
	 */
	private void createTextBox(JPanel somePanel, JTextField someTextField) {
		JPanel tempPanel = new JPanel();
		tempPanel.add(someTextField);
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
			if (e.getSource() == searchProd) {
				prodName = specificProd.getText().toString();
				
				
				GUIDCProduct newClass = new GUIDCProduct();
				newClass.setVisible(true);
			}
		}
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GUIProductSearch gps = new GUIProductSearch();
		gps.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gps.setSize(500, 500);
		gps.setVisible(true);
	}

}
