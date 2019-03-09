/**
 * 
 */
package finalproject;

import java.io.EOFException;

import GUI.GUIHelper;

/**
 *
 */
public class TestShoppingScreen {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ShoppingScreen sampleGUI;
		try {
			sampleGUI = new ShoppingScreen();
			sampleGUI.setVisible(true);
			System.out.println(GUIHelper.getProductList().get(0)[1][2]);
		} catch (EOFException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
