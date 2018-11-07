package productiionLineDataClasses;

import java.util.List;

public class Burger { //comment
	
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
