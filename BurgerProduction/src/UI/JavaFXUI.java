package UI;

import java.util.List;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import productiionLineDataClasses.Ingredient;
import productiionLineDataClasses.Order;

public class JavaFXUI extends Application {
	
	List<Order> orders;
	
	List<Ingredient> ingredients;
	
	private BorderPane root = new BorderPane();
	
	private HBox titleBar = new HBox();
	private Text ordersText = new Text();
	private Text orderDetailsText = new Text();
	private Text inventoryText = new Text();
	
	private ListView<String> ordersListView;
	
	private VBox orderDetails;
	
	private VBox inventory;
	private Button viewLevels;
	private Button receiptIn;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		// TODO Auto-generated method stub
		try {
			Scene scene = new Scene(root,1400,900);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			initialisePane();	
			
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void initialisePane() {
		//Top Labels
		ordersText.setText("Orders");
		ordersText.setFont(Font.font(30));
		orderDetailsText.setText("Order Details");
		orderDetailsText.setFont(Font.font(30));
		inventoryText.setText("Inventory");
		inventoryText.setFont(Font.font(30));

		//Title bar
		titleBar.getChildren().addAll(ordersText, orderDetailsText, inventoryText);
		titleBar.setSpacing(460);
		titleBar.setPadding(new Insets(15,15,15,15));
		titleBar.setStyle("-fx-background-color: #336699;");
		
		//ListView
		ordersListView = new ListView<>();
		ordersListView.getItems().addAll("Order #54625", "Order #58965");
		
		//Order Details
		orderDetails = new VBox();
		
		//Inventory
		inventory = new VBox();
		inventory.setStyle("-fx-background-color: #A9A9A9;");
		inventory.setPrefWidth(250);
		inventory.setPadding(new Insets(15,15,15,15));
		inventory.setAlignment(Pos.TOP_CENTER);
		
		viewLevels = new Button();
		viewLevels.setPrefWidth(200);
		viewLevels.setText("View stock levels");
		
		receiptIn = new Button();
		receiptIn.setPrefWidth(200);
		receiptIn.setText("Receipt in stock");
	
		inventory.getChildren().addAll(viewLevels, receiptIn);
		
		//BorderPane
		root.setTop(titleBar);
		root.setCenter(orderDetails);
		root.setLeft(ordersListView);
		root.setRight(inventory);
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