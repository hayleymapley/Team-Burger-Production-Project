package handlers;

import java.util.List;

import productiionLineDataClasses.Ingredient;

public class StockHandler {

	private List<Ingredient> ingredients;

	public List<Ingredient> retrieveIngredientsFromDB() { 

		//TODO: goes to DB and returns list of ingredients for UI

		return this.ingredients;
	}

	public int addStockToDB(Ingredient ingredient, int quantity) {
		int newQuantity = 0;

		//TODO: adds a certain amount of an ingredient to the stock 

		return newQuantity;

	}

	public int removeIngredientFromDB(Ingredient ingredient, int quantity) {
		int newQuantity = 0;

		//TODO: used to remove specific ingredients from the stock list i.e. if stock is lost/stolen 

		return newQuantity;

	}


	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}




}
