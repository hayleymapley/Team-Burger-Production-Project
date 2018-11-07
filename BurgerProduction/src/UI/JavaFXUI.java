package UI;

import java.util.List;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import productiionLineDataClasses.Ingredient;
import productiionLineDataClasses.Order;

public class JavaFXUI extends Application {
	
	List<Order> orders;
	
	List<Ingredient> ingredients;
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public boolean viewOrder(Order order) {
		
		//TODO: button listener for when an order is clicked on
		// inflates the selected view containing an order
		
		return false;
		
	}
		
	public boolean completeOrder() {
		
		//TODO: changes status of order in database to to completed
		
		return false;
		
	}
	
	
	public boolean updateOrderPanel() {
		
		//TODO: button listener for when "complete order" is pressed 
		// removes order from front of queue
		
		return false;
		
	}
	
}