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
import productiionLineDataClasses.Order;

public class OrderHandler {
	private String databaseUser = "yauloui";
	private String databaseUserPass = "pass123";
	private HashMap<Integer, Order> currentOrders;

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

	public List<Order> retrieveOrdersFromDB() throws SQLException { 
		// goes to DB and retrieves orders for java FX GUI  list view
		int order_id = 0;

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

	public Order findBurgerIngredients(int orderID) throws SQLException {
		//finds burger details and adds them into a burger object
		int bunLettuceAmount = 0, bunStandardAmount = 0, vegeLettuceAmount = 0,
				vegeTomatoAmount = 0, vegeOnionAmount = 0, vegePicklesAmount = 0, 
				vegeBeetrootAmount = 0, cheeseCheddarAmount = 0, cheeseVeganAmount = 0,
				pattyBeefAmount = 0, pattyChickenAmount = 0, pattyTofuAmount = 0, 
				sauceTomatoAmount = 0, sauceChilliAmount = 0, sauceAioliAmount = 0;

		String bunLettuceName = null, bunStandardName = null, vegeLettuceName = null,
				vegeTomatoName = null, vegeOnionName = null, vegePicklesName = null,
				vegeBeetrootName = null, cheeseCheddarName = null, cheeseVeganName = null,
				pattyBeefName = null, pattyChickenName = null, pattyTofuName = null, 
				sauceTomatoName = null, sauceChilliName = null, sauceAioliName = null;

		Connection conn = connect();
		Statement stmt = conn.createStatement();
		String burgerSql = "SELECT * FROM  burger_ingredients WHERE order_id = "+ orderID;
		ResultSet burgerResult = stmt.executeQuery(burgerSql);
		Statement burgerStatement= conn.createStatement();
		while (burgerResult.next()) {
			bunLettuceAmount = 	burgerResult.getInt("Bun_Lettuce");
			bunStandardAmount = burgerResult.getInt("Bun_Standard");
			vegeLettuceAmount = burgerResult.getInt("Vege_Lettuce");
			vegeTomatoAmount = burgerResult.getInt("Vege_Tomato");
			vegeOnionAmount = burgerResult.getInt("Vege_Onion");
			vegePicklesAmount = burgerResult.getInt("Vege_Pickles");
			vegeBeetrootAmount = burgerResult.getInt("Vege_Beetroot");
			cheeseCheddarAmount = burgerResult.getInt("Cheese_Cheddar");
			cheeseVeganAmount = burgerResult.getInt("Cheese_Vegan");
			pattyBeefAmount = burgerResult.getInt("Patty_Beef");
			pattyChickenAmount = burgerResult.getInt("Patty_Beef");
			pattyTofuAmount = burgerResult.getInt("Patty_Tofu");
			sauceTomatoAmount = burgerResult.getInt("Sauce_Tomato");
			sauceChilliAmount = burgerResult.getInt("Sauce_Chilli");
			sauceAioliAmount = burgerResult.getInt("Sauce_Aioli");

		}
		burgerResult.close();
		burgerStatement.close();
		return null;
	}

	public boolean setOrderToComplete(Order order) throws SQLException {
		// method is called upon when "complete order" button is pressed in UI

		// print out current status of order
		System.out.println("Check Order status: " + checkOrderStatus(order));

		// updating the order complete
		int orderId = order.getOrderID();
		String sql = "UPDATE orders SET completed = 't' WHERE order_id = " + orderId;// change order status to complete
		Connection conn = connect();
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.executeUpdate();
		
		// print the status after status change
		System.out.println("Check Order status once update has been completed: " + checkOrderStatus(order));
		
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

		//Order orderOne = new Order(4, cx, burgers);
		o.retrieveOrdersFromDB();
		// o.setOrderToComplete(orderOne);
	}

}
