package UI;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import handlers.StockHandler;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
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

	Map<String, Order> orders;
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

	private BorderPane notificationPane;
	private ListView<String> notifications;
	private VBox inventoryButton;
	private Button inventoryFunctions;

	//Inventory Screen
	private BorderPane inventoryRoot = new BorderPane();

	private HBox inventoryTitleBar = new HBox();
	private Text inventoryDetailsText = new Text();
	private Button backButton = new Button();

	private VBox inventoryView = new VBox();
	private Text inventoryTitle = new Text();
	private Text bunLettuce = new Text();
	private Text bunStandard = new Text();
	private Text vegeLettuce = new Text();
	private Text vegeTomato = new Text();
	private Text vegeOnion = new Text();
	private Text vegePickles = new Text();
	private Text vegeBeetroot = new Text();
	private Text cheeseCheddar = new Text();
	private Text cheeseVegan = new Text();
	private Text pattyBeef = new Text();
	private Text pattyChicken = new Text();
	private Text pattyTofu = new Text();
	private Text sauceTomato = new Text();
	private Text sauceChilli = new Text();
	private Text sauceAioli = new Text();

	private GridPane inventoryAdjustment = new GridPane();
	private Text adjustmentTitle = new Text();
	private Label bunLettuceLabel = new Label();
	private TextField bunLettuceField = new TextField();
	private Label bunStandardLabel = new Label();
	private TextField bunStandardField = new TextField();
	private Label vegeLettuceLabel = new Label();
	private TextField vegeLettuceField = new TextField();
	private Label vegeTomatoLabel = new Label();
	private TextField vegeTomatoField = new TextField();
	private Label vegeOnionLabel = new Label();
	private TextField vegeOnionField = new TextField();
	private Label vegePicklesLabel = new Label();
	private TextField vegePicklesField = new TextField();
	private Label vegeBeetrootLabel = new Label();
	private TextField vegeBeetrootField = new TextField();
	private Label cheeseCheddarLabel = new Label();
	private TextField cheeseCheddarField = new TextField();
	private Label cheeseVeganLabel = new Label();
	private TextField cheeseVeganField = new TextField();
	private Label pattyBeefLabel = new Label();
	private TextField pattyBeefField = new TextField();
	private Label pattyChickenLabel = new Label();
	private TextField pattyChickenField = new TextField();
	private Label pattyTofuLabel = new Label();
	private TextField pattyTofuField = new TextField();
	private Label sauceTomatoLabel = new Label();
	private TextField sauceTomatoField = new TextField();
	private Label sauceChilliLabel = new Label();
	private TextField sauceChilliField = new TextField();
	private Label sauceAioliLabel = new Label();
	private TextField sauceAioliField = new TextField();

	private Button adjustButton = new Button("Adjust stock");

	StockHandler stockHandler;

	@Override
	public void start(Stage primaryStage) throws Exception {

		// TODO Auto-generated method stub
		try {
			Scene mainScene = new Scene(root,1200,900);
			mainScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			Scene inventoryScene = new Scene(inventoryRoot,1200,900);
			inventoryScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			stockHandler = new StockHandler();

			//Sets up main UI elements
			initialiseMainElements();	
			//Sets up inventory UI elements
			initialiseInventoryElements();

			ingredients = stockHandler.retrieveIngredientsFromDB();

			inventoryFunctions.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					primaryStage.setScene(inventoryScene);
				}	
			});

			backButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					primaryStage.setScene(mainScene);
				}	
			});

			adjustButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					adjustStock();
					//Update stock levels
				}	
			});

			primaryStage.setTitle("Burger Production Application");
			primaryStage.setScene(mainScene);
			primaryStage.show();

		} catch(Exception e) {

			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	public void initialiseMainElements() {
		//Top Labels
		ordersText.setText("Orders");
		ordersText.setFont(Font.font(30));
		ordersText.setFill(Color.WHITE);
		orderDetailsText.setText("Order Details");
		orderDetailsText.setFont(Font.font(30));
		orderDetailsText.setFill(Color.WHITE);
		inventoryText.setText("Notifications");
		inventoryText.setFont(Font.font(30));
		inventoryText.setFill(Color.WHITE);

		//Title bar
		titleBar.getChildren().addAll(ordersText, orderDetailsText, inventoryText);
		titleBar.setSpacing(340);
		titleBar.setPadding(new Insets(15,15,15,15));
		titleBar.setAlignment(Pos.CENTER);
		titleBar.setId("titleBar");

		//ListView
		ordersListView = new ListView<>();
		ordersListView.getItems().addAll("Order #54625", "Order #58965");
		ordersListView.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
			@Override
			public ListCell<String> call(ListView<String> param) {
				return new OrdersCell();
			}
		});
		ordersListView.setId("ordersList");

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

		//Notifcation ListView
		notifications = new ListView<>();
		notifications.getItems().addAll("Stock is low", "Also stock is low");
		notifications.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
			@Override
			public ListCell<String> call(ListView<String> param) {
				return new NotificationsCell();
			}
		});
		ordersListView.setId("notificationsList");

		//Inventory Button
		inventoryButton = new VBox();
		inventoryButton.setStyle("-fx-background-color: #A9A9A9;");
		inventoryButton.setPrefWidth(250);
		inventoryButton.setPadding(new Insets(15,15,15,15));
		inventoryButton.setSpacing(5);
		inventoryButton.setAlignment(Pos.TOP_CENTER);

		inventoryFunctions = new Button();
		inventoryFunctions.setPrefWidth(200);
		inventoryFunctions.setText("Inventory functions");
		inventoryFunctions.setId("blue");

		inventoryButton.getChildren().addAll(inventoryFunctions);

		//Notification Pane
		notificationPane = new BorderPane();
		notificationPane.setStyle("-fx-background-color: #A9A9A9;");
		notificationPane.setTop(notifications);
		notificationPane.setBottom(inventoryButton);

		//BorderPane
		root.setTop(titleBar);
		root.setCenter(orderDetails);
		root.setLeft(ordersListView);
		root.setRight(notificationPane);
	}

	public void initialiseInventoryElements() {

		//Title bar
		inventoryDetailsText.setText("Inventory Details");
		inventoryDetailsText.setFont(Font.font(30));
		inventoryDetailsText.setFill(Color.WHITE);

		Image back = new Image(getClass().getResourceAsStream("back.png"));
		ImageView backView = new ImageView(back);
		backButton.setGraphic(backView);
		backButton.setId("backButton");

		inventoryTitleBar.getChildren().addAll(backButton, inventoryDetailsText);
		inventoryTitleBar.setSpacing(430);
		inventoryTitleBar.setPadding(new Insets(15,15,15,15));
		inventoryTitleBar.setAlignment(Pos.CENTER_LEFT);
		inventoryTitleBar.setId("titleBar");

		//Stock Levels
		inventoryTitle.setText("Stock Levels\n");
		inventoryTitle.setId("title");

		bunLettuce.setText("Lettuce bun: \t0");
		bunStandard.setText("Standard bun: \t0");
		vegeLettuce.setText("Lettuce: \t\t0");
		vegeTomato.setText("Tomato: \t\t0");
		vegeOnion.setText("Onion: \t\t0");
		vegePickles.setText("Pickles: \t\t0");
		vegeBeetroot.setText("Beetroot: \t\t0");
		cheeseCheddar.setText("Cheddar cheese: 0");
		cheeseVegan.setText("Vegan cheese: 0");
		pattyBeef.setText("Beef patty: \t0");
		pattyChicken.setText("Chicken patty: \t0");
		pattyTofu.setText("Tofu patty: \t0");
		sauceTomato.setText("Tomato sauce: 0");
		sauceChilli.setText("Chilli sauce: \t0");
		sauceAioli.setText("Aioli sauce: \t0");

		inventoryView.getChildren().addAll(inventoryTitle, bunLettuce, bunStandard, vegeLettuce,
				vegeTomato, vegeOnion, vegePickles, vegeBeetroot, cheeseCheddar, cheeseVegan,
				pattyBeef, pattyChicken, pattyTofu, sauceTomato, sauceChilli, sauceAioli);
		inventoryView.setSpacing(5);
		inventoryView.setPadding(new Insets(25,25,25,25));
		inventoryView.setAlignment(Pos.TOP_RIGHT);
		inventoryView.setId("inventoryView");

		//Stock Adjustment
		adjustmentTitle.setText("Inventory Adjustment\n");
		adjustmentTitle.setId("title");

		bunLettuceLabel.setText("Lettuce bun: ");
		bunStandardLabel.setText("Standard bun: ");
		vegeLettuceLabel.setText("Lettuce: ");
		vegeTomatoLabel.setText("Tomato: ");
		vegeOnionLabel.setText("Onion: ");
		vegePicklesLabel.setText("Pickles: ");
		vegeBeetrootLabel.setText("Beetroot: ");
		cheeseCheddarLabel.setText("Cheddar cheese: ");
		cheeseVeganLabel.setText("Vegan cheese: ");
		pattyBeefLabel.setText("Beef patty: ");
		pattyChickenLabel.setText("Chicken patty: ");
		pattyTofuLabel.setText("Tofu patty: ");
		sauceTomatoLabel.setText("Tomato sauce: ");
		sauceChilliLabel.setText("Chilli sauce: ");
		sauceAioliLabel.setText("Aioli sauce: ");

		adjustButton.setId("blue");

		inventoryAdjustment.getChildren().addAll(adjustmentTitle, bunLettuceLabel, bunLettuceField, bunStandardLabel, bunStandardField, vegeLettuceLabel, vegeLettuceField, 
				vegeTomatoLabel, vegeTomatoField, vegeOnionLabel, vegeOnionField, vegePicklesLabel, vegePicklesField, vegeBeetrootLabel, vegeBeetrootField, cheeseCheddarLabel, 
				cheeseCheddarField, cheeseVeganLabel, cheeseVeganField, pattyBeefLabel, pattyBeefField, pattyChickenLabel, pattyChickenField, pattyTofuLabel, pattyTofuField,
				sauceTomatoLabel, sauceTomatoField, sauceChilliLabel, sauceChilliField, sauceAioliLabel, sauceAioliField, adjustButton);

		GridPane.setConstraints(adjustmentTitle, 0, 1);
		GridPane.setConstraints(bunLettuceLabel, 0, 2);
		GridPane.setConstraints(bunStandardLabel, 0, 3);
		GridPane.setConstraints(vegeLettuceLabel, 0, 4);
		GridPane.setConstraints(vegeTomatoLabel, 0, 5);
		GridPane.setConstraints(vegeOnionLabel, 0, 6);
		GridPane.setConstraints(vegePicklesLabel, 0, 7);
		GridPane.setConstraints(vegeBeetrootLabel, 0, 8);
		GridPane.setConstraints(cheeseCheddarLabel, 0, 9);
		GridPane.setConstraints(cheeseVeganLabel, 0, 10);
		GridPane.setConstraints(pattyBeefLabel, 0, 11);
		GridPane.setConstraints(pattyChickenLabel, 0, 12);
		GridPane.setConstraints(pattyTofuLabel, 0, 13);
		GridPane.setConstraints(sauceTomatoLabel, 0, 14);
		GridPane.setConstraints(sauceChilliLabel, 0, 15);
		GridPane.setConstraints(sauceAioliLabel, 0, 16);

		GridPane.setConstraints(bunLettuceField, 1, 2);
		GridPane.setConstraints(bunStandardField, 1, 3);
		GridPane.setConstraints(vegeLettuceField, 1, 4);
		GridPane.setConstraints(vegeTomatoField, 1, 5);
		GridPane.setConstraints(vegeOnionField, 1, 6);
		GridPane.setConstraints(vegePicklesField, 1, 7);
		GridPane.setConstraints(vegeBeetrootField, 1, 8);
		GridPane.setConstraints(cheeseCheddarField, 1, 9);
		GridPane.setConstraints(cheeseVeganField, 1, 10);
		GridPane.setConstraints(pattyBeefField, 1, 11);
		GridPane.setConstraints(pattyChickenField, 1, 12);
		GridPane.setConstraints(pattyTofuField, 1, 13);
		GridPane.setConstraints(sauceTomatoField, 1, 14);
		GridPane.setConstraints(sauceChilliField, 1, 15);
		GridPane.setConstraints(sauceAioliField, 1, 16);
		GridPane.setConstraints(adjustButton, 0, 18);
		inventoryAdjustment.setPadding(new Insets(25,25,25,25));

		inventoryRoot.setTop(inventoryTitleBar);
		inventoryRoot.setLeft(inventoryView);
		inventoryRoot.setRight(inventoryAdjustment);
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

	public boolean adjustStock() {

		List<TextField> fields = new ArrayList<>();
		fields.add(bunLettuceField);
		fields.add(bunStandardField);
		fields.add(vegeLettuceField);
		fields.add(vegeTomatoField);
		fields.add(vegeOnionField);
		fields.add(vegePicklesField);
		fields.add(vegeBeetrootField);
		fields.add(cheeseCheddarField);
		fields.add(cheeseVeganField);
		fields.add(pattyBeefField);
		fields.add(pattyChickenField);
		fields.add(pattyTofuField);
		fields.add(sauceTomatoField);
		fields.add(sauceChilliField);
		fields.add(sauceAioliField);

		try {
			for (int i=0; i<fields.size(); i++) {
				if (!fields.get(i).getText().trim().isEmpty()) {
					switch (i) {
					case 0:
						stockHandler.addStockToDB(ingredients.get(0), Integer.parseInt(fields.get(i).getText()));
						break;
					case 1:
						stockHandler.addStockToDB(ingredients.get(1), Integer.parseInt(fields.get(i).getText()));
						break;
					case 2:
						stockHandler.addStockToDB(ingredients.get(2), Integer.parseInt(fields.get(i).getText()));
						break;
					case 3:
						stockHandler.addStockToDB(ingredients.get(3), Integer.parseInt(fields.get(i).getText()));
						break;
					case 4:
						stockHandler.addStockToDB(ingredients.get(4), Integer.parseInt(fields.get(i).getText()));
						break;
					case 5:
						stockHandler.addStockToDB(ingredients.get(5), Integer.parseInt(fields.get(i).getText()));
						break;
					case 6:
						stockHandler.addStockToDB(ingredients.get(6), Integer.parseInt(fields.get(i).getText()));
						break;
					case 7:
						stockHandler.addStockToDB(ingredients.get(7), Integer.parseInt(fields.get(i).getText()));
						break;
					case 8:
						stockHandler.addStockToDB(ingredients.get(8), Integer.parseInt(fields.get(i).getText()));
						break;
					case 9:
						stockHandler.addStockToDB(ingredients.get(9), Integer.parseInt(fields.get(i).getText()));
						break;
					case 10:
						stockHandler.addStockToDB(ingredients.get(10), Integer.parseInt(fields.get(i).getText()));
						break;
					case 11:
						stockHandler.addStockToDB(ingredients.get(11), Integer.parseInt(fields.get(i).getText()));
						break;
					case 12:
						stockHandler.addStockToDB(ingredients.get(12), Integer.parseInt(fields.get(i).getText()));
						break;
					case 13:
						stockHandler.addStockToDB(ingredients.get(13), Integer.parseInt(fields.get(i).getText()));
						break;
					case 14:
						stockHandler.addStockToDB(ingredients.get(14), Integer.parseInt(fields.get(i).getText()));
						break;
					}
				}
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	return true; //unnnecessary
}

public boolean updateNotificationPanel() {

	return false;
}

static class OrdersCell extends ListCell<String> {
	HBox hbox = new HBox();
	Label label = new Label("(empty)");
	Pane pane = new Pane();
	Image checkmark = new Image(getClass().getResourceAsStream("checkmark.png"));
	ImageView imageView = new ImageView(checkmark);
	Button button = new Button();

	public OrdersCell() {
		super();
		hbox.getChildren().addAll(label, pane, button);
		HBox.setHgrow(pane, Priority.ALWAYS);
		imageView.setFitWidth(20);
		imageView.setFitHeight(20);
		button.setId("listButton");
		button.setGraphic(imageView);
		button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				// TODO set order to complete and update listview
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

static class NotificationsCell extends ListCell<String> {
	HBox hbox = new HBox();
	Label label = new Label("(empty)");
	Pane pane = new Pane();
	Image delete = new Image(getClass().getResourceAsStream("delete.png"));
	ImageView imageView = new ImageView(delete);
	Button button = new Button();

	public NotificationsCell() {
		super();
		hbox.getChildren().addAll(label, pane, button);
		HBox.setHgrow(pane, Priority.ALWAYS);
		imageView.setFitWidth(20);
		imageView.setFitHeight(20);
		button.setId("listButton");
		button.setGraphic(imageView);
		button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				// TODO remove notification from notifications
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