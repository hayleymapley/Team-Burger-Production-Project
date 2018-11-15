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

public class OrderHandler {
	private String databaseUser = "mapleyhayl";
	private String databaseUserPass = "pass123";
	// global method to connect the jdbc
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

	public List<Order> retrieveOrdersFromDB() throws SQLException { 
		// goes to DB and retrieves orders for java FX GUI  list view
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

	//find customerID, customer name and email then create Customer object to be returned



	public void setOrderToComplete(Order order) throws SQLException {
		// method is called upon when "complete order" button is pressed in UI

		// updating the order complete
		int orderId = order.getOrderID();
		String sql = "UPDATE orders SET completed = 't' WHERE order_id = " + orderId;// change order status to complete
		Connection conn = connect();
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.executeUpdate();
		conn.close();

	}

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

