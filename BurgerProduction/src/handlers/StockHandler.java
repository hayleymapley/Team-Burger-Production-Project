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
/**
 *This class instantiate the Stock Handler class
 *This class hold list of Ingredient list and two string
 *global variable to hold database username and password
 * @author chadz
 *
 */
public class StockHandler {

	private List<Ingredient> ingredientsList;
	private final static String databaseUser = "mapleyhayl";
	private final static String databasePass = "pass123";

	//goes to DB and returns list of ingredients for UI

	/**
	 * This is the method  which connect the 
	 * jdbc datbase  by initialising username and password 
	 * @param databaseUser static String  value use as database username 
	 * @param databasePass static String value use as database password 
	 * @return null if connection does not work
	 * @throws SQLException
	 */
		
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

	/**
	 * This is the method  which connect the 
	 * jdbc datbase  to retrieve Ingredients From DB
	 * This will return all the Ingredients in stock list 
	 * @return ingredientsList  returining list values inside the ingredientsList
	 * @throws SQLException
	 */
	
	public List<Ingredient> retrieveIngredientsFromDB() throws SQLException { 

		String ingredientName = null;
		int quantity = 0;
		ingredientsList = new ArrayList<>();
		String query = "select name, quantity from stock_ingredients order by orderthiscolumnby asc"; //returns all stock within stock_ingredients table

		Connection connection = connectToDB(databaseUser, databasePass);

		Statement s = connection.createStatement();

		ResultSet rs = s.executeQuery(query);

		while(rs.next()) {

			ingredientName = rs.getString("name");

			quantity = rs.getInt("quantity");

			Ingredient ingredient = new Ingredient(ingredientName, quantity);

			ingredientsList.add(ingredient);

		}

		rs.close();
		s.close();
		connection.close();

		return ingredientsList;
		
	}
/**
 * 
 * This is the method  find correct syntax for increasing the quantity by X amount
 * @param ingredient as an ingredient object 
 * @param givenQuantity as an  integer value
 * @return newQuantity as an integer value
 * @throws SQLException
 */
	public int adjustQuantitiesInDB(Ingredient ingredient, int givenQuantity)throws SQLException{ //adds a current type of ingredient to database takes in quantity and ingredient object 
		int newQuantity;
		String requiredIngredientName = ingredient.getName();
		String sqlUpdate = "update stock_ingredients set quantity = quantity + " + givenQuantity + " where name = " + "'" + requiredIngredientName + "'";
			
		//find correct syntax for increasing the quantity by x amount

		Connection connection = connectToDB(databaseUser, databasePass);

		PreparedStatement ps = connection.prepareStatement(sqlUpdate);

		ps.executeUpdate();

		newQuantity = checkCurrentStockLevels(ingredient);

		connection.close();
		
		return newQuantity;

	}
/**
 * This is the method returns the current stock level of a certain ingredient
 * @param ingredient as an ingredient object
 * @return currentStockLevel as integer 
 * @throws SQLException
 */
	
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
		
		connection.close();

		return currentStockLevel;

	}


	public List<Ingredient> getIngredients() {
		return ingredientsList;
	}

	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredientsList = ingredients;
	}
}
