package handlers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import productiionLineDataClasses.Ingredient;

public class StockHandler {

	private List<Ingredient> ingredientsList;

	public List<Ingredient> retrieveIngredientsFromDB(String databaseUser, String databasePass) throws SQLException { 
		String[] ingredientNameSplit = null;
    	String ingredientType = null;
    	String ingredientTitle = null;
    	String ingredientName = null;
    	int quantity = 0;
    	ingredientsList = new ArrayList<>();
		
        try {
        	
			Class.forName("org.postgresql.Driver");
			
			 Connection connection = null;  
			 
			 	String query = "select name, quantity from stock_ingredients";
			 
		        String url = "jdbc:postgresql://db.ecs.vuw.ac.nz/"+databaseUser+"_jdbc";
		        
		        connection = DriverManager.getConnection(url,databaseUser, databasePass);
		        
		        Statement s = connection.createStatement();
		        
		        ResultSet rs = s.executeQuery(query);
		  
		        while(rs.next()) {
		        
		        	String name = rs.getString("name");
		        	
		        	ingredientNameSplit  = name.split("_");
		        	
		        	quantity = rs.getInt("quantity");
		        	
		        	ingredientType = ingredientNameSplit[0].toString();
		        	
		        	ingredientTitle = ingredientNameSplit[1].toString();
		       
		        	ingredientName = ingredientTitle + " " + ingredientType;
		        	
		        	Ingredient ingredient = new Ingredient(ingredientName, quantity);
		        	
		        	ingredientsList.add(ingredient);
		        	
		        }
		        
		       rs.close();
		       s.close();
		        
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}

		//TODO: goes to DB and returns list of ingredients for UI

		return ingredientsList;
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
		return ingredientsList;
	}

	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredientsList = ingredients;
	}

	public static void main(String[] args) throws SQLException {
		StockHandler s = new StockHandler();
		s.retrieveIngredientsFromDB("poperere", "pass123");
		
	}

}
