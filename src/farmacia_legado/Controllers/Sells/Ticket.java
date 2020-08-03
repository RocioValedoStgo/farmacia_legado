package farmacia_legado.Controllers.Sells;

import java.net.URL;
import java.util.ResourceBundle;
import farmacia_legado.Main;
import farmacia_legado.MySQLConnection;
import farmacia_legado.Controllers.HomeController;
import farmacia_legado.Models.Sell_Detail;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Ticket implements Initializable {

	@FXML // fx:id="logo"
	private ImageView logo; // Value injected by FXMLLoader

	@FXML // fx:id="table"
	private TableView<Sell_Detail> table; // Value injected by FXMLLoader

	private TableColumn<Sell_Detail, Integer> col_id = new TableColumn<Sell_Detail, Integer>("ID");

	private TableColumn<Sell_Detail, String> col_name = new TableColumn<Sell_Detail, String>("Nombre");

	private TableColumn<Sell_Detail, Float> col_price = new TableColumn<Sell_Detail, Float>("Precio unitario");

	private TableColumn<Sell_Detail, Integer> col_quantity = new TableColumn<Sell_Detail, Integer>("Cantidad comprada");

	private TableColumn<Sell_Detail, Float> col_subtotal = new TableColumn<Sell_Detail, Float>("Subtotal");

	@FXML // fx:id="titlePage"
	private Label titlePage; // Value injected by FXMLLoader

	@FXML // fx:id="TotalSell"
	private Label TotalSell; // Value injected by FXMLLoader

	@FXML // fx:id="menuButton"
	private MenuButton menuButton; // Value injected by FXMLLoader

	@FXML // fx:id="optionHome"
	private MenuItem optionHome; // Value injected by FXMLLoader

	@FXML // fx:id="optionUsers"
	private MenuItem optionUsers; // Value injected by FXMLLoader

	@FXML // fx:id="optionProducts"
	private MenuItem optionProducts; // Value injected by FXMLLoader

	@FXML // fx:id="optionCategories"
	private MenuItem optionCategories; // Value injected by FXMLLoader

	@FXML // fx:id="optionProviders"
	private MenuItem optionProviders; // Value injected by FXMLLoader

	@FXML // fx:id="optionLogOut"
	private MenuItem optionLogOut; // Value injected by FXMLLoader

	private static int pkSell;
	private static float auxSubtotal = 0;

	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		MySQLConnection MySQL = new MySQLConnection();
		try {
			col_id.setPrefWidth(50);
			col_id.setStyle("-fx-aligment: CENTER;");
			col_id.setStyle("-fx-font-size: 15px");
			col_name.setPrefWidth(170);
			col_name.setStyle("-fx-aligment: CENTER;");
			col_name.setStyle("-fx-font-size: 15px");
			col_price.setPrefWidth(120);
			col_price.setStyle("-fx-aligment: CENTER;");
			col_price.setStyle("-fx-font-size: 15px");
			col_quantity.setPrefWidth(170);
			col_quantity.setStyle("-fx-aligment: CENTER;");
			col_quantity.setStyle("-fx-font-size: 15px");
			col_subtotal.setPrefWidth(120);
			col_subtotal.setStyle("-fx-aligment: CENTER;");
			col_subtotal.setStyle("-fx-font-size: 15px");
			table.getColumns().addAll(col_name, col_price, col_quantity, col_subtotal);
			col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
			col_price.setCellValueFactory(new PropertyValueFactory<>("price"));
			col_quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
			col_subtotal.setCellValueFactory(new PropertyValueFactory<>("subtotal"));
			table.setItems(MySQL.indexSell(getPkSell()));
			titlePage.setText("Compra #"+getPkSell());
			menuButton.setText(MySQLConnection.User_username);
			for (Sell_Detail item : table.getItems()) {
				auxSubtotal = auxSubtotal + col_subtotal.getCellObservableValue(item).getValue();
			}
			float auxTotal = (float) (auxSubtotal + (auxSubtotal * 0.16));
			TotalSell.setText("Subtotal: $"+auxSubtotal+"\nIVA: 16%\nTotal: $"+auxTotal);
			auxSubtotal = 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	void btnBack(MouseEvent event) throws Exception {
		HomeController homeController = new HomeController();
		homeController.showView(event);
	}

	@FXML
	void btnLogo(MouseEvent event) {

	}

	@FXML
	void btnNavbar(MouseEvent event) {
		optionHome.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				HomeController home = new HomeController();
				try {
					home.showView(event);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		optionUsers.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				if (MySQLConnection.User_rol < 3) {
					farmacia_legado.Controllers.User.Index indexUsers = new farmacia_legado.Controllers.User.Index();
					try {
						indexUsers.showView(event);
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					Alert alert = new Alert(AlertType.ERROR, "No cuentas con los permisos", ButtonType.OK);
					alert.showAndWait();
				}
			}
		});

		optionProviders.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				if (MySQLConnection.User_rol < 3) {
					farmacia_legado.Controllers.Provider.Index indexProviders = new farmacia_legado.Controllers.Provider.Index();
					try {
						indexProviders.showView(event);
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					Alert alert = new Alert(AlertType.ERROR, "No cuentas con los permisos", ButtonType.OK);
					alert.showAndWait();
				}
			}
		});

		optionCategories.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				if (MySQLConnection.User_rol < 3) {
					farmacia_legado.Controllers.Category.Index indexCategories = new farmacia_legado.Controllers.Category.Index();
					try {
						indexCategories.showView(event);
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					Alert alert = new Alert(AlertType.ERROR, "No cuentas con los permisos", ButtonType.OK);
					alert.showAndWait();
				}
			}
		});

		optionProducts.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				if (MySQLConnection.User_rol < 3) {
					farmacia_legado.Controllers.Product.Index indexProducts = new farmacia_legado.Controllers.Product.Index();
					try {
						indexProducts.showView(event);
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					Alert alert = new Alert(AlertType.ERROR, "No cuentas con los permisos", ButtonType.OK);
					alert.showAndWait();
				}
			}
		});

		optionLogOut.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				Main login = new Main();
				try {
					login.showView(event);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void showView(Event event) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("../../../views/Sells/ticket.fxml"));
		Scene scene = new Scene(root);
		Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		appStage.getIcons().add(new Image("/assets/images/legado_farmacia.png"));
		appStage.setScene(scene);
		appStage.toFront();
		appStage.show();
	}

	/**
	 * @return the pkSell
	 */
	public static int getPkSell() {
		return pkSell;
	}

	/**
	 * @param pkSell the pkSell to set
	 */
	public static void setPkSell(int pkSell) {
		Ticket.pkSell = pkSell;
	}
}