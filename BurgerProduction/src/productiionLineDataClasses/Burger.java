package productiionLineDataClasses;

import java.util.List;

public class Burger { 
	
	/**
	 *  Data class which is used to instantiate an order object
	 *  List of ingredient objects
	 */
	
	private List<Ingredient> ingredientsList;
	
	public Burger(List<Ingredient> ingredientsList) {
		this.ingredientsList = ingredientsList;
	}

	public List<Ingredient> getIngredientsList() {
		return ingredientsList;
	}

	public void setIngredientsList(List<Ingredient> ingredientsList) {
		this.ingredientsList = ingredientsList;
	}
	
	
	
	

}
