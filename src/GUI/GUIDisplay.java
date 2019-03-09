package GUI;

import java.util.Map;

public class GUIDisplay {

	/**
	 * Creates a GUIDisplay window.
	 */
	public GUIDisplay(){
		
	}
	
	//this function is to get the exact location of each product should be present in the GUI
	/**
	 * Returns the total page numbers of the display window.
	 * @param object
	 * @param objectMap
	 * @return an int
	 */
	public int displaytotalpage(Object object, Map<String,Object> objectMap){
		int mapsize = objectMap.size();
		int pagenum = 0;
		int indexloc = 0;
		//if the objects are just enough to display 
		if (mapsize < 6){
			pagenum = 1;
		}
		else{
			if (mapsize % 6 != 0 ){
				pagenum = (mapsize - mapsize % 6) / 6 + 1;
			}
			else {
				pagenum = (mapsize / 6);
			}
		}
		
		for (Map.Entry<String, Object> objectEntry :objectMap.entrySet()){
			if (objectEntry.getValue().equals(object)){
				
			}
		}
		return pagenum;
		
	}
	
}
