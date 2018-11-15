package handlers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import java.util.List;


import productiionLineDataClasses.Burger;
import productiionLineDataClasses.Customer;
import productiionLineDataClasses.Ingredient;
import productiionLineDataClasses.Order;

/*
 * Class responsible for running queries/updates on Postgres sql database using JDBC
 * Both fields are based on the DB we are using but can be changed as required
 */

public class OrderHandler {
	private String databaseUser = "mapleyhayl";
	private String databaseUserPass = "pass123";


	/**
	 * Method used to set up a connection to database to be used in other methods
	 * @return returns Connection object type 
	 * @throws SQLException
	 */
	private Connection connect() throws SQLException {
		Connection connection = null;
		try {
			Class.forName("org.postgresql.Driver");

			String url = "jdbc:postgresql://db.ecs.vuw.ac.nz/" + databaseUser + "_jdbc";
			connection = DriverManager.getConnection(url, databaseUser, databaseUserPass);

		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		return connection;
	}
	
	
/**
 * Runs a select query on DB and retrieves orders for java FX GUI  list view
 * These orders are listed as 'not completed' in the DB
 * @return List of order objects are returned
 * @throws SQLException
 */
	public List<Order> retrieveOrdersFromDB() throws SQLException { 
		
		Timestamp orderTS = null;
		int orderID = 0;
		boolean orderComplete = false;
		List<Order> orderNotCompleteList = new ArrayList<>();
		int customerID = 0;

		Connection conn = connect();
		Statement stmt = conn.createStatement();
		// Searching not completed orderdId from order table
		String orderSql = "SELECT * FROM  orders WHERE COMPLETED = 'f' ";
		ResultSet rs = stmt.executeQuery(orderSql);
		while (rs.next()) {
			
			orderID = rs.getInt("order_id");

			orderTS = rs.getTimestamp("timestamp");

			orderComplete = rs.getBoolean("completed");
			
			customerID = findCustomerID(orderID);
			
			Order newOrder = new Order(orderComplete, orderTS, orderID, findCustomer(customerID),findBurgerIngredients(orderID));
			
			orderNotCompleteList.add(newOrder);

		}
		
		rs.close();
		stmt.close();
		conn.close();

		return orderNotCompleteList;
	}
	
	/**
	 * Runs a select query on DB and retrieves a list of burgers that match a given orderID
	 * @param orderID the specific ID int for a given order
	 * @return a list of burger objects
	 * @throws SQLException
	 */

	public List<Burger> findBurgerIngredients(int orderID) throws SQLException {

		List <Ingredient> ingredientsInBurgerList = new ArrayList<>();
		List <Burger> currentBurgersList = new ArrayList<>();
		int ingredientQuantity = 0;
		String ingredientName = " ";

		Connection conn = connect();
		Statement stmt = conn.createStatement();
		String burgerSql = "SELECT * FROM  burger_ingredients WHERE order_id = "+ orderID;
		Statement burgerStatement= conn.createStatement();
		ResultSet burgerResult = stmt.executeQuery(burgerSql);
		ResultSetMetaData rsmd = burgerResult.getMetaData();
		while (burgerResult.next()) {

			for(int i = 3; i <= 17; i++) {
				if(burgerResult.getInt(i) != 0) {

					ingredientQuantity = burgerResult.getInt(i);
					ingredientName = rsmd.getColumnName(i);
					Ingredient newIngredient = new Ingredient(ingredientName, ingredientQuantity);
					ingredientsInBurgerList.add(newIngredient);
				}

			}

			Burger newBurger = new Burger(ingredientsInBurgerList);
			currentBurgersList.add(newBurger);

		}

		burgerResult.close();
		burgerStatement.close();
		conn.close();

		return currentBurgersList;
	}
/**
 * Retrieves a particular customerID int which is used to find a specific customer object
 * @param orderID int that is given to find a specific customer
 * @return customer ID int that will be put into a customer object
 * @throws SQLException
 */
	
	public int findCustomerID(int orderID) throws SQLException{ 
		int customerID = 0;

		Connection conn = connect();
		Statement statement = conn.createStatement();
		String customerSql = "SELECT customer_id FROM  orders WHERE order_id = "+ orderID;
		Statement customerStatement= conn.createStatement();
		ResultSet customerResult = statement.executeQuery(customerSql);

		while(customerResult.next()) {
			customerID = customerResult.getInt("customer_id");
		}

		customerResult.close();
		customerStatement.close();
		conn.close();

		//find customerID, customer name and email then create Customer object to be returned

		return customerID;
	}
	/**
	 * Takes a given customerID and returns a customer object with the appropriate fields taken from the database
	 * @param customerID given customerID intended to be returned from the findCustomerID method
	 * @return customer object which is a reflection of an entry in the database
	 * @throws SQLException
	 */

	public Customer findCustomer(int customerID) throws SQLException{ 
		String customerName = null;
		String customerEmail = null;

		Connection conn = connect();
		Statement statement = conn.createStatement();
		String customerSql = "SELECT * FROM  customers WHERE customer_id = "+ customerID;
		Statement customerStatement= conn.createStatement();
		ResultSet customerResult = statement.executeQuery(customerSql);

		while(customerResult.next()) {
			customerName = customerResult.getString("name");
			customerEmail = customerResult.getString("email");
		}

		Customer newCustomer = new Customer(customerID, customerName, customerEmail);

		customerResult.close();
		customerStatement.close();
		conn.close();

		return newCustomer;
	}

	
/**
 * Takes a given order object and sets the completed status of that order in the database to true
 * @param order given order object
 * @throws SQLException
 */


	public void setOrderToComplete(Order order) throws SQLException {
		

		// updating the order complete
		int orderId = order.getOrderID();
		String sql = "UPDATE orders SET completed = 't' WHERE order_id = " + orderId;// change order status to complete
		Connection conn = connect();
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.executeUpdate();
		conn.close();

	}
	
	/**
	 * Returns the completed status of a given order 
	 * @param order object which is checked
	 * @return boolean variable that refers to the completed status
	 * @throws SQLException
	 */

	public boolean checkOrderStatus(Order order) throws SQLException {
		boolean complete = false;

		int orderId = order.getOrderID();
		String check = "SELECT completed from orders WHERE order_id = " + orderId;
		Connection conn = connect();
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(check);

		while (rs.next()) {
			complete = rs.getBoolean("completed");
		}
		rs.close();
		st.close();
		conn.close();

		return complete;

	}


}

