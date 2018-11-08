package handlers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import UI.JavaFXUI;
import productiionLineDataClasses.Burger;
import productiionLineDataClasses.Customer;
import productiionLineDataClasses.Order;

public class OrderHandler {
	private String databaseUser = "karannchat";
	private String databaseUserPass = "123";

	// global method to connect the jdbc
	private Connection connect() throws SQLException {
		Connection connection = null;
		try {
			Class.forName("org.postgresql.Driver");

			String url = "jdbc:postgresql://db.ecs.vuw.ac.nz/" + databaseUser + "_jdbc";
			connection = DriverManager.getConnection(url, databaseUser, databaseUserPass);
			Statement s = connection.createStatement();

		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		return connection;
	}

	public List<Order> retrieveOrdersFromDB() throws SQLException { // goes to DB and retrieves orders for java fx GUI list view
		int order_id=0;

		List<Order> orderNotCompleteList = new ArrayList<>();
		Connection conn = connect();
		Statement stmt = conn.createStatement();
		// Searching not completed orderdId from order table
		String orderSql = "SELECT order_id FROM  orders WHERE COMPLETED= 'f' ";
		ResultSet rs = stmt.executeQuery(orderSql);
		
		while (rs.next()) {
			
			order_id = rs.getInt("order_id");
			
			System.out.println("order_id for uncomplete order is: " + order_id);
		}

		rs.close();
		stmt.close();

		return orderNotCompleteList;
	}
	
	public Order findBurgerIngredients(int orderID) throws SQLException {//finds burger details and adds them into a burger object
		int bunLettuceAmount, bunStandardAmount, vegeLettuceAmount,
		vegeTomatoAmount, vegeOnionAmount, vegePicklesAmount, vegeBeetrootAmount, cheeseCheddarAmount, cheeseVeganAmount,
		pattyBeefAmount, pattyChickenAmount, pattyTofuAmount, sauceTomatoAmount, sauceChilliAmount, sauceAioliAmount;
		
		String bunLettuceName, bunStandardName, vegeLettuceName,
		vegeTomatoName, vegeOnionName, vegePicklesName, vegeBeetrootName, cheeseCheddarName, cheeseVeganName,
		pattyBeefName, pattyChickenName, pattyTofuName, sauceTomatoName, sauceChilliName, sauceAioliName;
		
		
		
		Connection conn = connect();
		Statement stmt = conn.createStatement();
		String burgerSql = "SELECT * FROM  burger_ingredients WHERE order_id = "+ orderID;
		ResultSet burgerResult = stmt.executeQuery(burgerSql);
		Statement burgerStatement= conn.createStatement();
		while (burgerResult.next()) {
			
			

		}
		burgerResult.close();
		burgerStatement.close();

		return null;

	}


	public boolean setOrderToComplete(Order order) throws SQLException {
		// method is called upon when "complete order" button is pressed in UI

		System.out.println("Check Order status: " + checkOrderStatus(order));// print out current status of order

		// updating the order complete
		int orderId = order.getOrderID();
		String sql = "UPDATE orders SET completed = 't' WHERE order_id = " + orderId;// change order status to complete

		Connection conn = connect();
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.executeUpdate();

		System.out.println("Check Order status once update has been completed: " + checkOrderStatus(order));
		// print the status after status change


		return false;

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

		return complete;

	}


	public static void main(String[] args) throws SQLException {
		OrderHandler o = new OrderHandler();
		List<Burger> burgers = new ArrayList();

		Customer cx = new Customer(4, "Jeramy", "jeramy@gmail.com");

		Order orderOne = new Order(4, cx, burgers);
		o.retrieveOrdersFromDB();
		//o.setOrderToComplete(orderOne);
	}

}
