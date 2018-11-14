package UI;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import handlers.OrderHandler;
import handlers.StockHandler;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

	//Main screen
	private BorderPane root = new BorderPane();

	private HBox titleBar = new HBox();
	private Text ordersText = new Text();
	private Text orderDetailsText = new Text();
	private Text inventoryText = new Text();

	private VBox orderDetails;
	private Text orderIDText;
	private Text timeStampText;
	private Text customerText;
	private Text ingredientsText;

	private BorderPane notificationPane;
	private VBox inventoryVBox;
	private Button inventoryFunctions;

	private VBox orderPanelVBox = new VBox();
	private VBox refreshVBox = new VBox();
	private Button refreshButton = new Button();

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

	//Handlers
	private static StockHandler stockHandler;
	private static OrderHandler orderHandler;

	//Lists
	private List<TextField> fields;
	private static List<Order> orders = new ArrayList<>();
	private static List<Ingredient> ingredients;
	private static ObservableList<Order> ordersList;
	private static ListView<Order> ordersListView;
	private static ListView<String> notificationsListView;
	private static List<String> notifications = new ArrayList<>();
	private static ObservableList<String> notificationsList;

	@Override
	public void start(Stage primaryStage) throws Exception {

		try {
			Scene mainScene = new Scene(root,1200,900);
			mainScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			Scene inventoryScene = new Scene(inventoryRoot,1200,900);
			inventoryScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			stockHandler = new StockHandler();
			orderHandler = new OrderHandler();

			//Sets up main UI elements
			initialiseMainElements();	
			//Sets up inventory UI elements
			initialiseInventoryElements();

			updateOrderPanel();
			if (!(ordersList.isEmpty()) && ordersList.get(ordersList.size()-1) !=  null) {
				viewOrder(ordersList.get(ordersList.size()-1));
				ordersListView.getSelectionModel().select(ordersList.size()-1);
			}

			updateNotificationPanel();

			ingredients = stockHandler.retrieveIngredientsFromDB();

			ordersListView.getSelectionModel().selectedItemProperty()
			.addListener(new ChangeListener<Order>() {
				@Override
				public void changed(ObservableValue<? extends Order> observable, Order oldValue, Order newValue) {
					if (ordersListView.getSelectionModel().getSelectedItem() != null) {
						viewOrder(ordersListView.getSelectionModel().getSelectedItem());
					} else {
						if (!(ordersList.isEmpty())) {
							viewOrder(ordersList.get(ordersList.size()-1));
							ordersListView.getSelectionModel().select(ordersList.size()-1);
						}
					}
				}
			});

			inventoryFunctions.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					primaryStage.setScene(inventoryScene);
					try {
						displayStockLevels();
					} catch (SQLException e) {e.printStackTrace();}
				}	
			});

			backButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					primaryStage.setScene(mainScene);
					try {
						updateNotificationPanel();
						updateOrderPanel();
					} catch (SQLException e) {
						e.printStackTrace();
					}

				}	
			});

			adjustButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event){
					try {
						ingredients = stockHandler.retrieveIngredientsFromDB();
						adjustStock();
						ingredients = stockHandler.retrieveIngredientsFromDB();
						displayStockLevels();
						for (int i=0; i<fields.size(); i++) {
							fields.get(i).setText("");
						}
					} catch (Exception e) {e.printStackTrace();}
				}	
			});

			refreshButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent arg0) {
					try {
						updateOrderPanel();
					} catch (SQLException e) {
						e.printStackTrace();
					}
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
		titleBar.setSpacing(300);
		titleBar.setPadding(new Insets(15,15,15,15));
		titleBar.setAlignment(Pos.CENTER);
		titleBar.setId("titleBar");

		//ListView
		ordersListView = new ListView<>();
		ordersListView.setCellFactory(new Callback<ListView<Order>, ListCell<Order>>() {
			@Override
			public ListCell<Order> call(ListView<Order> param) {
				return new OrdersCell();
			}
		});
		ordersListView.setId("ordersList");
		ordersListView.setPrefHeight(770);

		//set graphic refreshButton
		Image refreshImage= new Image(getClass().getResourceAsStream("refresh.png"));
		ImageView refreshView = new ImageView(refreshImage);
		refreshView.setFitWidth(25);
		refreshView.setFitHeight(25);
		refreshButton.setGraphic(refreshView);
		refreshButton.setAlignment(Pos.CENTER);

		//Refresh VBox
		refreshVBox.setStyle("-fx-background-color: #A9A9A9;");
		refreshVBox.setPrefWidth(250);
		refreshVBox.setPadding(new Insets(15,15,15,15));
		refreshVBox.setSpacing(5);
		refreshVBox.setAlignment(Pos.TOP_CENTER);

		refreshVBox.getChildren().add(refreshButton);
		
		orderPanelVBox.getChildren().addAll(ordersListView, refreshVBox);
		orderPanelVBox.setAlignment(Pos.TOP_CENTER);

		//Order Details
		orderDetails = new VBox();
		orderDetails.setPadding(new Insets(15,15,15,15));

		orderIDText = new Text();
		orderIDText.setFont(Font.font(30));

		timeStampText = new Text();
		timeStampText.setFont(Font.font(15));

		customerText = new Text();
		customerText.setFont(Font.font(20));

		ingredientsText = new Text();
		ingredientsText.setFont(Font.font(20));

		orderDetails.getChildren().addAll(orderIDText, timeStampText, customerText, ingredientsText);

		//Notifcation ListView
		notificationsListView = new ListView<>();
		notificationsListView.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
			@Override
			public ListCell<String> call(ListView<String> param) {
				return new NotificationsCell();
			}
		});
		notificationsListView.setId("notificationsList");
		notificationsListView.setPrefHeight(770);

		//Inventory Box
		inventoryVBox = new VBox();
		inventoryVBox.setStyle("-fx-background-color: #A9A9A9;");
		inventoryVBox.setPrefWidth(250);
		inventoryVBox.setPadding(new Insets(15,15,15,15));
		inventoryVBox.setSpacing(5);
		inventoryVBox.setAlignment(Pos.TOP_CENTER);

		inventoryFunctions = new Button();
		inventoryFunctions.setPrefWidth(200);
		inventoryFunctions.setText("Inventory functions");
		inventoryFunctions.setId("blue");

		inventoryVBox.getChildren().addAll(inventoryFunctions);

		//Notification Pane
		notificationPane = new BorderPane();
		notificationPane.setStyle("-fx-background-color: #A9A9A9;");
		notificationPane.setTop(notificationsListView);
		notificationPane.setBottom(inventoryVBox);

		//BorderPane
		root.setTop(titleBar);
		root.setCenter(orderDetails);
		root.setLeft(orderPanelVBox);
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

	public void viewOrder(Order order) {

		// inflates the selected view containing an order
		orderIDText.setText("Order #" + Integer.toString(order.getOrderID()));
		timeStampText.setText(order.getTimestamp().toString());
		customerText.setText("Customer: " + order.getCustomer().getCustomerName() + "\n");

		List<Ingredient> burgerIngredients = order.getBurgers().get(0).getIngredientsList();
		List<String> burgerIngredientsStr = new ArrayList<>();
		for (int i=0; i<burgerIngredients.size(); i++) {
			burgerIngredientsStr.add(burgerIngredients.get(i).getName() + " * " + burgerIngredients.get(i).getQuantity());
		}

		String ingredientsString = String.join("\n", burgerIngredientsStr);
		ingredientsText.setText(ingredientsString);	//ONLY GETS THE FIRST BURGER OF EACH ORDER
	}

	public static void completeOrder(Order order) throws SQLException {

		orderHandler.setOrderToComplete(order); 
		//TODO call updateOrderPanel from this method
		updateNotificationPanel();
		updateOrderPanel();

	}

	public static void updateOrderPanel() throws SQLException {

		//Retrieve list of orders from the database
		orders = orderHandler.retrieveOrdersFromDB();

		ordersList = FXCollections.<Order>observableArrayList(orders);

		Collections.sort(ordersList, Comparator.comparingInt(Order::getOrderID).reversed());

		ordersListView.getItems().clear();
		ordersListView.getItems().addAll(ordersList);
	}

	public void displayStockLevels() throws SQLException {

		ingredients = stockHandler.retrieveIngredientsFromDB();

		bunLettuce.setText("Lettuce bun: \t" + ingredients.get(0).getQuantity());
		bunStandard.setText("Standard bun: \t" + ingredients.get(1).getQuantity());
		vegeLettuce.setText("Lettuce: \t\t" + ingredients.get(2).getQuantity());
		vegeTomato.setText("Tomato: \t\t" + ingredients.get(3).getQuantity());
		vegeOnion.setText("Onion: \t\t" + ingredients.get(4).getQuantity());
		vegePickles.setText("Pickles: \t\t" + ingredients.get(5).getQuantity());
		vegeBeetroot.setText("Beetroot: \t\t" + ingredients.get(6).getQuantity());
		cheeseCheddar.setText("Cheddar cheese: " + ingredients.get(7).getQuantity());
		cheeseVegan.setText("Vegan cheese: " + ingredients.get(8).getQuantity());
		pattyBeef.setText("Beef patty: \t" + ingredients.get(9).getQuantity());
		pattyChicken.setText("Chicken patty: \t" + ingredients.get(10).getQuantity());
		pattyTofu.setText("Tofu patty: \t" + ingredients.get(11).getQuantity());
		sauceTomato.setText("Tomato sauce: " + ingredients.get(12).getQuantity());
		sauceChilli.setText("Chilli sauce: \t" + ingredients.get(13).getQuantity());
		sauceAioli.setText("Aioli sauce: \t" + ingredients.get(14).getQuantity());

	}

	public void adjustStock() {

		fields = new ArrayList<>();
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
					stockHandler.adjustQuantitiesInDB(ingredients.get(i), Integer.parseInt(fields.get(i).getText()));
				}
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public static void checkForStockWarnings() throws SQLException {

		ingredients = stockHandler.retrieveIngredientsFromDB();

		for (Ingredient i : ingredients) {
			if (i.getQuantity() < 30 && !(notifications.contains(i.getName() + " quantity is low"))) {
				notifications.add(0, i.getName() + " quantity is low");
			}
		}
	}

	public static void updateNotificationPanel() throws SQLException {

		checkForStockWarnings();

		notificationsList = FXCollections.<String>observableArrayList(notifications);

		notificationsListView.getItems().clear();
		notificationsListView.getItems().addAll(notificationsList);

	}

	static class OrdersCell extends ListCell<Order> {
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
					try {
						completeOrder(getItem());
						updateOrderPanel();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			});
		}

		@Override
		protected void updateItem(Order item, boolean empty) {
			super.updateItem(item, empty);
			setText(null);  // No text in label of super class
			if (empty) {
				setGraphic(null);
			} else {
				label.setText(item!=null ? "Order #" + item.getOrderID() : "<null>");
				setGraphic(hbox);
			}
		}
	}

	static class NotificationsCell extends ListCell<String> {
		HBox hbox = new HBox();
		Label label = new Label("(empty)");
		Pane pane = new Pane();
		Image exclamation = new Image(getClass().getResourceAsStream("exclamation.png"));
		ImageView imageView = new ImageView(exclamation);
		Button button = new Button();

		public NotificationsCell() {
			super();
			hbox.getChildren().addAll(label, pane, button);
			HBox.setHgrow(pane, Priority.ALWAYS);
			imageView.setFitWidth(20);
			imageView.setFitHeight(25);
			button.setId("listButton");
			button.setGraphic(imageView);
			button.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent arg0) {
					//						notifications.remove(getItem());
					//						updateNotificationPanel();
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