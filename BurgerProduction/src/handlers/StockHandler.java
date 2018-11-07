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
		String[] ingredientNameSplit = null;
		String ingredientType = null;
		String ingredientTitle = null;
		String ingredientName = null;
		int quantity = 0;
		ingredientsList = new ArrayList<>();
		String query = "select name, quantity from stock_ingredients"; //returns all stock within stock_ingredients table

		Connection connection = connectToDB(databaseUser, databasePass);

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

		return ingredientsList;
	}

	public int addStockToDB(Ingredient ingredient, int quantity) throws SQLException{
		int newQuantity = 0;
		String requiredIngredient = ingredient.getName();
		String sqlUpdate = "update stock_ingredients+ set " + requiredIngredient; //find correct syntax for increasing the quantity by x amount

		Connection connection = connectToDB(databaseUser, databasePass);
		
		Statement s = connection.createStatement();
		
		s.executeUpdate(sqlUpdate);

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
		
		s.retrieveIngredientsFromDB();

	}

}
