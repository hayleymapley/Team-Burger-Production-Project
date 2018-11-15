package productiionLineDataClasses;

public class Ingredient {
	
	private String name;
	private int quantity;
	
	/**
	 *  Data class which is used to instantiate an ingredient object
	 * @param name String variable
	 * @param quantity int variable
	 */
	
	public Ingredient(String name, int quantity) {
		this.setName(name);
		this.setQuantity(quantity);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	

}
