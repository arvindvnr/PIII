package GUI;

public class DoubleArray<T> {
	public T[][] array; 
	private int index;  
    
	/**
	 * Creates an DoubleArray object of size 2.
	 */
	public DoubleArray(){
		T[] array1 = (T[]) new  Object[] {null, null, null}; 
		T[] array2 = (T[]) new  Object[] {null, null, null}; 
		this.array = (T[][]) new Object[2][3]; 
		this.array[0] = array1; 
		this.array[1] = array2; 
		this.index = 0; 
	}
	
	public String toString(){
		String str = ""; 
		for (T[] array: this.array){
			for (T element: array){
				if (element != null)
				    str += element.toString() + " ";
			}
			str += "\n"; 
		}		
		return str; 		
	}
	
	public void add(T element){
		if (this.index < 3){
			this.array[0][this.index] = element;
			this.index += 1; 
		}
		else if (this.index < 6){
			this.array[1][this.index - 3] = element; 
			this.index += 1; 
		}
		else 
			throw new IndexOutOfBoundsException(); 
		
	}
	
	public boolean isFull(){
		for(T[] array: this.array){
			for (T element: array){
				if (element == null){
					return false; 
				}
			}
		} 
		return true; 
	}

}
