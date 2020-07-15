package farmacia_legado.Controllers.Product;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import farmacia_legado.Main;
import farmacia_legado.MySQLConnection;
import farmacia_legado.Controllers.HomeController;
import farmacia_legado.Models.Product;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;

public class Index implements Initializable {

	@FXML // fx:id="titlePage"
	private Label titlePage; // Value injected by FXMLLoader

	@FXML // fx:id="labelBtnAdd"
	private Label labelBtnAdd; // Value injected by FXMLLoader

	@FXML // fx:id="table"
	private TableView<Product> table; // Value injected by FXMLLoader

	@FXML
	private TableColumn<Product, Integer> col_id = new TableColumn<Product, Integer>("ID");

	@FXML
	private TableColumn<Product, String> col_name = new TableColumn<Product, String>("Nombre");

	@FXML
	private TableColumn<Product, Float> col_price = new TableColumn<Product, Float>("Precio");

	@FXML
	private TableColumn<Product, Integer> col_quantity = new TableColumn<Product, Integer>("Cantidad");

	@FXML
	private TableColumn<Product, String> col_options = new TableColumn<Product, String>("Opciones");

	@FXML // fx:id="menuButtonNavbar"
	private MenuButton menuButtonNavbar; // Value injected by FXMLLoader

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

	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		col_id.setPrefWidth(50);
		col_id.setStyle("-fx-aligment: CENTER;");
		col_id.setStyle("-fx-font-size: 15px");
		col_name.setPrefWidth(150);
		col_name.setStyle("-fx-aligment: CENTER;");
		col_name.setStyle("-fx-font-size: 15px");
		col_price.setPrefWidth(150);
		col_price.setStyle("-fx-aligment: CENTER;");
		col_price.setStyle("-fx-font-size: 15px");
		col_quantity.setPrefWidth(150);
		col_quantity.setStyle("-fx-aligment: CENTER;");
		col_quantity.setStyle("-fx-font-size: 15px");
		table.getColumns().addAll(col_id, col_name, col_price, col_quantity, col_options);
		addButtonShow();
		addButtonDestroy();
		col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
		col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
		col_price.setCellValueFactory(new PropertyValueFactory<>("price"));
		col_quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
		MySQLConnection MySQL = new MySQLConnection();
		try {
			table.setItems(MySQL.indexProducts());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void addButtonShow() {
		TableColumn<Product, Void> colBtn = new TableColumn<Product, Void>();
		Callback<TableColumn<Product, Void>, TableCell<Product, Void>> cellFactory = new Callback<TableColumn<Product, Void>, TableCell<Product, Void>>() {
			@Override
			public TableCell<Product, Void> call(final TableColumn<Product, Void> param) {
				final TableCell<Product, Void> cell = new TableCell<Product, Void>() {
					private final Button btn = new Button("Ver");
					{
						/*
						 * btn.setOnAction((ActionEvent event) -> { Product Product =
						 * getTableView().getItems().get(getIndex()); Profile profileProduct = new
						 * Profile(); profileProduct.setPkProduct(Product.getId()); try {
						 * profileProduct.showView(event); } catch (Exception e) { e.printStackTrace();
						 * } });
						 */
					}

					@Override
					public void updateItem(Void item, boolean empty) {
						super.updateItem(item, empty);
						if (empty) {
							setGraphic(null);
						} else {
							setGraphic(btn);
						}
					}
				};
				return cell;
			}
		};
		colBtn.setCellFactory(cellFactory);
		col_options.getColumns().add(colBtn);
	}

	private void addButtonDestroy() {
		TableColumn<Product, Void> colBtn = new TableColumn<Product, Void>();

		Callback<TableColumn<Product, Void>, TableCell<Product, Void>> cellFactory = new Callback<TableColumn<Product, Void>, TableCell<Product, Void>>() {
			@Override
			public TableCell<Product, Void> call(final TableColumn<Product, Void> param) {
				final TableCell<Product, Void> cell = new TableCell<Product, Void>() {
					private final Button btn = new Button("Borrar");
					{
						/*
						 * btn.setOnAction((ActionEvent event) -> { Product Product =
						 * getTableView().getItems().get(getIndex()); Alert alert; MySQLConnection MySQL
						 * = new MySQLConnection(); try { if (MySQL.destroyProduct(Product.getId()) ==
						 * 1) { alert = new Alert(Alert.AlertType.INFORMATION, "Borrado con exito");
						 * alert.showAndWait(); showView(event); } else { alert = new
						 * Alert(Alert.AlertType.ERROR, "Ocurrio un error"); alert.showAndWait(); } }
						 * catch (Exception e) { e.printStackTrace(); } });
						 */
					}

					@Override
					public void updateItem(Void item, boolean empty) {
						super.updateItem(item, empty);
						if (empty) {
							setGraphic(null);
						} else {
							setGraphic(btn);
						}
					}
				};
				return cell;
			}
		};
		colBtn.setCellFactory(cellFactory);
		col_options.getColumns().add(colBtn);
	}

	@FXML
	void btnAddProduct(MouseEvent event) throws Exception {
		Save saveProduct = new Save();
		saveProduct.showView(event);
	}

	@FXML
	void btnBack(MouseEvent event) throws Exception {
		HomeController home = new HomeController();
		home.showView(event);
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

		optionCategories.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				farmacia_legado.Controllers.Product.Index indexCategories = new farmacia_legado.Controllers.Product.Index();
				try {
					indexCategories.showView(event);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		optionProducts.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				try {
					showView(event);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		optionLogOut.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				Main main = new Main();
				try {
					main.showView(event);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void showView(Event event) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("../../../views/Product/index.fxml"));
		Scene scene = new Scene(root);
		Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		appStage.getIcons().add(new Image("/assets/images/legado_farmacia.png"));
		appStage.setScene(scene);
		appStage.toFront();
		appStage.show();
	}
}
