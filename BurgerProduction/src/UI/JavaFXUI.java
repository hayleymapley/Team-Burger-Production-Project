package UI;

import java.util.List;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import productiionLineDataClasses.Ingredient;
import productiionLineDataClasses.Order;

public class JavaFXUI extends Application {
	
	List<Order> orders;
	
	List<Ingredient> ingredients;
	
	//Main screen
	private BorderPane root = new BorderPane();
	
	private HBox titleBar = new HBox();
	private Text ordersText = new Text();
	private Text orderDetailsText = new Text();
	private Text inventoryText = new Text();
	
	private ListView<String> ordersListView;
	
	private VBox orderDetails;
	private Text orderIDText;
	private Text timeStampText;
	private Text ingredientsText;
	
	private VBox inventory;
	private Button viewLevels;
	private Button receiptIn;
	
	//Inventory Screen
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		// TODO Auto-generated method stub
		try {
			Scene scene = new Scene(root,1400,900);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			//Sets up UI elements
			initialisePane();	
			
			primaryStage.setTitle("Burger Production Application");
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
		ordersText.setFill(Color.WHITE);
		orderDetailsText.setText("Order Details");
		orderDetailsText.setFont(Font.font(30));
		orderDetailsText.setFill(Color.WHITE);
		inventoryText.setText("Inventory");
		inventoryText.setFont(Font.font(30));
		inventoryText.setFill(Color.WHITE);

		//Title bar
		titleBar.getChildren().addAll(ordersText, orderDetailsText, inventoryText);
		titleBar.setSpacing(430);
		titleBar.setPadding(new Insets(15,15,15,15));
		titleBar.setAlignment(Pos.CENTER);
		titleBar.setId("titleBar");
		
		//ListView
		ordersListView = new ListView<>();
		ordersListView.getItems().addAll("Order #54625", "Order #58965");
		ordersListView.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
			@Override
			public ListCell<String> call(ListView<String> param) {
				return new CustomCell();
			}
		});
		ordersListView.setId("listView");
		
		//Order Details
		orderDetails = new VBox();
		orderDetails.setPadding(new Insets(15,15,15,15));
		
		orderIDText = new Text();
		orderIDText.setText("Order ID: ");
		orderIDText.setFont(Font.font(30));
		
		timeStampText = new Text();
		timeStampText.setText("07/11/18 19:33");
		timeStampText.setFont(Font.font(15));
		
		ingredientsText = new Text();
		ingredientsText.setText("\nTomato * 1, Lettuce *1, Tofu * 2");
		ingredientsText.setFont(Font.font(20));
	
		orderDetails.getChildren().addAll(orderIDText, timeStampText, ingredientsText);
		
		//Inventory
		inventory = new VBox();
		inventory.setStyle("-fx-background-color: #A9A9A9;");
		inventory.setPrefWidth(250);
		inventory.setPadding(new Insets(15,15,15,15));
		inventory.setSpacing(5);
		inventory.setAlignment(Pos.TOP_CENTER);
		
		viewLevels = new Button();
		viewLevels.setPrefWidth(200);
		viewLevels.setText("View stock levels");
		viewLevels.setId("blue");
		
		receiptIn = new Button();
		receiptIn.setPrefWidth(200);
		receiptIn.setText("Receipt in stock");
		receiptIn.setId("blue");
	
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
	
	public boolean displayStockLevels() {
		
		return false;
	}
	
	public boolean receiptInStock() {
		
		return false;
	}
	
	public boolean updateNotificationPanel() {
		
		return false;
	}
	
	static class CustomCell extends ListCell<String> {
		HBox hbox = new HBox();
		Label label = new Label("(empty)");
        Pane pane = new Pane();
        Image checkmark = new Image(getClass().getResourceAsStream("checkmark.png"));
        ImageView imageView = new ImageView(checkmark);
        Button button = new Button();
        
        public CustomCell() {
        	super();
        	hbox.getChildren().addAll(label, pane, button);
        	HBox.setHgrow(pane, Priority.ALWAYS);
        	imageView.setFitWidth(25);
        	imageView.setFitHeight(25);
        	button.setGraphic(imageView);
        	button.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent arg0) {
					// TODO Auto-generated method stub
					System.out.println(getItem());
				}
        	});
        }
        
        @Override
        protected void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            setText(null);  // No text in label of super class
            if (empty) {
                setGraphic(null);
            } else {
                label.setText(item!=null ? item : "<null>");
                setGraphic(hbox);
            }
        }
	}
	
}