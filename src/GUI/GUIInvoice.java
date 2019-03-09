package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GUIInvoice extends JFrame implements ActionListener{

	int cartnumber;
	
	/**
	 * Creates a new GUIInvoice window.
	 * @param cartnumber
	 */
	public GUIInvoice(int cartnumber){
		super("Invoice");
		this.cartnumber = cartnumber;
		setSize(300,500);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLayout(new BorderLayout());
		
		add(companytitle(), BorderLayout.PAGE_START);
		add(invoicebody());
		add(pageend(),BorderLayout.PAGE_END);
	}
	
	private JPanel companytitle(){
		JPanel companylogo = new JPanel();
		
		companylogo.setBackground(Color.darkGray);
		companylogo.add(new JLabel(new ImageIcon("fbay.png")));
		return companylogo;
	}
	
	private JPanel invoicebody(){
		JPanel invoicebody = new JPanel();
		
		JPanel invoice_title = new JPanel(new GridLayout(1,4));
		JPanel invoice_cate = new JPanel(new GridLayout());
		
		invoice_title.add(new JLabel("Category"));
		
		invoice_title.add(new JLabel("Product"));
	
		invoice_title.add(new JLabel("Quantity"));
	
		invoice_title.add(new JLabel("Price"));
		
		invoicebody.add(invoice_title, BorderLayout.PAGE_START);
		invoicebody.add(invoice_cate, BorderLayout.CENTER);
		
		return invoicebody;
	}
	
	private JPanel pageend(){
		JPanel pageend = new JPanel();
		
		pageend.setBackground(Color.darkGray);
		pageend.setLayout(new FlowLayout());
		
		for (int i=0; i<3; i++){
			pageend.add(new JButton("1"));
		}
		return pageend;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
