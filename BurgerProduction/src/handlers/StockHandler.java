package handlers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import productiionLineDataClasses.Ingredient;

public class StockHandler {

	private List<Ingredient> ingredientsList;
	private final static String databaseUser = "poperere";
	private final static String databasePass = "pass123";

	//goes to DB and returns list of ingredients for UI

	public Connection connectToDB(String databaseUser, String databasePass) throws SQLException { 
		Connection connection;

		try {

			Class.forName("org.postgresql.Driver");

			String url = "jdbc:postgresql://db.ecs.vuw.ac.nz/"+databaseUser+"_jdbc";

			connection = DriverManager.getConnection(url,databaseUser, databasePass);

			return connection; //if connection works, return connection object

		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}

		return null; //if connection does not work
	}

	public List<Ingredient> retrieveIngredientsFromDB() throws SQLException { 
//		String[] ingredientNameSplit = null;
//		String ingredientType = null;
//		String ingredientTitle = null;
		String ingredientName = null;
		int quantity = 0;
		ingredientsList = new ArrayList<>();
		String query = "select name, quantity from stock_ingredients"; //returns all stock within stock_ingredients table

		Connection connection = connectToDB(databaseUser, databasePass);

		Statement s = connection.createStatement();

		ResultSet rs = s.executeQuery(query);

		while(rs.next()) {

			ingredientName = rs.getString("name");

//			ingredientNameSplit  = name.split("_");

			quantity = rs.getInt("quantity");

//			ingredientType = ingredientNameSplit[0].toString();
//
//			ingredientTitle = ingredientNameSplit[1].toString();
//
//			ingredientName = ingredientTitle + " " + ingredientType;

			Ingredient ingredient = new Ingredient(ingredientName, quantity);

			ingredientsList.add(ingredient);

		}

		rs.close();
		s.close();

		return ingredientsList;
	}

	public int addStockToDB(Ingredient ingredient, int givenQuantity) throws SQLException{ //adds a current type of ingredient to database takes in quantity and ingredient object
		int currentQuantity = checkCurrentStockLevels(ingredient);
		int newQuantity;
		String requiredIngredientName = ingredient.getName();
		String sqlUpdate = "update stock_ingredients set quantity = quantity + " + givenQuantity + " where name = " + "'" + requiredIngredientName + "'"; //find correct syntax for increasing the quantity by x amount

		Connection connection = connectToDB(databaseUser, databasePass);

		PreparedStatement ps = connection.prepareStatement(sqlUpdate);

		ps.executeUpdate();

		newQuantity = checkCurrentStockLevels(ingredient);
		
		System.out.println("Current stock: " + currentQuantity); //print current stock level
		System.out.println("Adding " + givenQuantity + " to current stock level"); //amount we are adding 
		System.out.println("New stock level stock: " + newQuantity); //new amount in database

		return newQuantity;

	}

	public int removeIngredientFromDB(Ingredient ingredient, int givenQuantity) throws SQLException { // used to remove specific ingredients from the stock list i.e. if stock is lost/stolen 
		//givenQuantity = ingredient.getQuantity(); //this will be used if we control burger creation from production line
		int newQuantity;
		int currentQuantity = checkCurrentStockLevels(ingredient);
		String requiredIngredientName = ingredient.getName();
		String sqlUpdate = "update stock_ingredients set quantity = quantity - " + givenQuantity + " where name = " + "'" + requiredIngredientName + "'"; //find correct syntax for increasing the quantity by x amount
		Connection connection = connectToDB(databaseUser, databasePass);

		PreparedStatement ps = connection.prepareStatement(sqlUpdate);

		ps.executeUpdate();

		newQuantity = checkCurrentStockLevels(ingredient);
		
		System.out.println("Current stock: " + currentQuantity); //print current stock level
		System.out.println("Removing " + givenQuantity + " to current stock level"); //amount we are removing 
		System.out.println("New stock level stock: " + newQuantity); //new amount in database

		return newQuantity;

	}

	public int checkCurrentStockLevels(Ingredient ingredient) throws SQLException { //returns the current stock level of a certain ingredient
		int currentStockLevel = 0;
		String requiredIngredientName = ingredient.getName();
		String query = "select quantity from stock_ingredients where name = " + "'" + requiredIngredientName + "'";

		Connection connection = connectToDB(databaseUser, databasePass);

		Statement s = connection.createStatement();

		ResultSet rs = s.executeQuery(query);

		while(rs.next()) {

			currentStockLevel = rs.getInt("quantity");

		}

		rs.close();
		s.close();

		return currentStockLevel;

	}


	public List<Ingredient> getIngredients() {
		return ingredientsList;
	}

	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredientsList = ingredients;
	}

	public static void main(String[] args)  throws SQLException {

		StockHandler s = new StockHandler();
		Ingredient i = new Ingredient("Bun_Lettuce", 1);

		s.removeIngredientFromDB(i, i.getQuantity());

	}

}
