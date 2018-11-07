package handlers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import UI.JavaFXUI;
import productiionLineDataClasses.Burger;
//import productiionLineDataClasses.Customer;
import productiionLineDataClasses.Order;

public class OrderHandler {
	private String databaseUser = "karannchat";
	private String databaseUserPass = "123";
	private List<Order> orders;
	
	public List<Order> getOrders() { 
		// this will help in future for testing purposes
		return orders;
	}

	//global method to connect the jdbc
	private Connection connect() throws  SQLException{
		Connection connection = null;
		try {
		Class.forName("org.postgresql.Driver");
		
		String url = "jdbc:postgresql://db.ecs.vuw.ac.nz/" + databaseUser + "_jdbc";
		connection = DriverManager.getConnection(url, databaseUser, databaseUserPass);
		Statement s = connection.createStatement();
		
			connection = DriverManager.getConnection(url);
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		return connection;
	}

	public List<Order> retrieveOrdersFromDB() {
		// goes to DB and retrieves orders

		/*Connection conn = connect();
		Statement s = conn.createStatement();
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.executeUpdate();
		 try {
		        stmt = conn.createStatement();
		        ResultSet rs = stmt.executeQuery(query);
		        while (rs.next()) {
		            String coffeeName = rs.getString(1);
		            int supplierID = rs.getInt(2);
		            float price = rs.getFloat(3);
		            int sales = rs.getInt(4);
		            int total = rs.getInt(5);
		            System.out.println(coffeeName + "\t" + supplierID +
		                               "\t" + price + "\t" + sales +
		                               "\t" + total);
		        }
		    } catch (SQLException e ) {
		        JDBCTutorialUtilities.printSQLException(e);
		    } finally {
		        if (stmt != null) { stmt.close(); }
		    }
		*/
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		return this.orders;
	}

	public boolean setOrderToComplete(Order order) throws SQLException, ClassNotFoundException {
		// method is called upon when "complete order" button is pressed in UI
	
		//updating the order complete
		int orderId = order.getOrderID();
		String sql = "UPDATE orders SET completed = 't' WHERE order_id = "+ orderId;
		Connection conn = connect();
		Statement s = conn.createStatement();
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.executeUpdate();
		
		return false;

	}


	public  boolean checkOrderStatus(Order order)throws SQLException  {
		boolean complete= false;
		
		int orderId = order.getOrderID();
		String check ="SELECT completed from orders WHERE order_id = " + orderId;
		Connection conn = connect();
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(check);
		
		while(rs.next()){
			complete = rs.getBoolean("completed");	
		}
		rs.close();
		st.close();
		System.out.println(""+ complete);
		
		return complete;
		
	}
	
/*	public static void main(String[] args) throws SQLException {
		OrderHandler o = new OrderHandler();
		List<Burger> burgers = new ArrayList();
		//Customer cx = new Customer("Jeramy", "jeramy@gmail.com", 4);
	
		//Order orderOne = new Order(4, cx, burgers);
		o.checkOrderStatus(orderOne);
		o.setOrderToComplete(orderOne);
	}*/

}
