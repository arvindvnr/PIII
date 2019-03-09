package finalproject;

import java.io.EOFException;

import GUI.GUIHelper;

public class PortalAPI {

	
	public static ProjectV1 project = new ProjectV1();
	
	public static void main(String[] args){
		ShoppingScreen sampleGUI;
		try {
			sampleGUI = new ShoppingScreen();
			sampleGUI.setVisible(true);
			
//			System.out.println(GUIHelper.getProductList().get(0)[0][0]);
//			System.out.println(GUIHelper.getProductList().get(0)[0][1]);
//			System.out.println(GUIHelper.getProductList().get(0)[0][2]);
		} catch (EOFException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//project.logout(sess);
		//System.out.println(CategoryManager.categories);
		//System.out.println(ProductManager.products);
		//System.out.println(DistributionCenterManager.distributionmanagers);
		
		
	}
}
