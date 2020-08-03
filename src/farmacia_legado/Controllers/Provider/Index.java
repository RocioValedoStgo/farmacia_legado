package farmacia_legado.Controllers.Provider;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import farmacia_legado.Main;
import farmacia_legado.MySQLConnection;
import farmacia_legado.Controllers.HomeController;
import farmacia_legado.Models.Provider;
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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
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
	private TableView<Provider> table; // Value injected by FXMLLoader

	@FXML
	private TableColumn<Provider, Integer> col_id = new TableColumn<Provider, Integer>("ID");

	@FXML
	private TableColumn<Provider, String> col_name = new TableColumn<Provider, String>("Nombre");

	@FXML
	private TableColumn<Provider, Integer> col_phone = new TableColumn<Provider, Integer>("Telefono");

	@FXML
	private TableColumn<Provider, String> col_options = new TableColumn<Provider, String>("Opciones");

	@FXML // fx:id="menuButtonNavbar"
	private MenuButton menuButtonNavbar; // Value injected by FXMLLoader

	@FXML // fx:id="optionHome"
	private MenuItem optionHome; // Value injected by FXMLLoader

	@FXML // fx:id="optionUsers"
	private MenuItem optionUsers; // Value injected by FXMLLoader

	@FXML // fx:id="optionProviders"
	private MenuItem optionProviders; // Value injected by FXMLLoader

	@FXML // fx:id="optionProducts"
	private MenuItem optionProducts;

	@FXML // fx:id="optionCategories"
	private MenuItem optionCategories; // Value injected by FXMLLoader

	@FXML // fx:id="optionLogOut"
	private MenuItem optionLogOut; // Value injected by FXMLLoader

	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		menuButtonNavbar.setText(MySQLConnection.User_username);
		col_id.setPrefWidth(50);
		col_id.setStyle("-fx-aligment: CENTER;");
		col_id.setStyle("-fx-font-size: 15px");
		col_name.setPrefWidth(150);
		col_name.setStyle("-fx-aligment: CENTER;");
		col_name.setStyle("-fx-font-size: 15px");
		col_phone.setPrefWidth(150);
		col_phone.setStyle("-fx-aligment: CENTER;");
		col_phone.setStyle("-fx-font-size: 15px");
		table.getColumns().addAll(col_id, col_name, col_phone, col_options);
		addButtonShow();
		addButtonDestroy();
		col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
		col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
		col_phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
		MySQLConnection MySQL = new MySQLConnection();
		try {
			table.setItems(MySQL.indexProviders());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void addButtonShow() {
		TableColumn<Provider, Void> colBtn = new TableColumn<Provider, Void>();
		Callback<TableColumn<Provider, Void>, TableCell<Provider, Void>> cellFactory = new Callback<TableColumn<Provider, Void>, TableCell<Provider, Void>>() {
			@Override
			public TableCell<Provider, Void> call(final TableColumn<Provider, Void> param) {
				final TableCell<Provider, Void> cell = new TableCell<Provider, Void>() {
					private final Button btn = new Button("Ver");
					{
						btn.setOnAction((ActionEvent event) -> {
							Provider Provider = getTableView().getItems().get(getIndex());
							Profile profileProvider = new Profile();
							Profile.setPkProvider(Provider.getId());
							try {
								profileProvider.showView(event);
							} catch (Exception e) {
								e.printStackTrace();
							}
						});
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
		TableColumn<Provider, Void> colBtn = new TableColumn<Provider, Void>();

		Callback<TableColumn<Provider, Void>, TableCell<Provider, Void>> cellFactory = new Callback<TableColumn<Provider, Void>, TableCell<Provider, Void>>() {
			@Override
			public TableCell<Provider, Void> call(final TableColumn<Provider, Void> param) {
				final TableCell<Provider, Void> cell = new TableCell<Provider, Void>() {
					private final Button btn = new Button("Borrar");
					{
						btn.setOnAction((ActionEvent event) -> {
							Provider Provider = getTableView().getItems().get(getIndex());
							Alert alert;
							MySQLConnection MySQL = new MySQLConnection();
							try {
								if (MySQL.destroyProvider(Provider.getId()) == 1) {
									alert = new Alert(Alert.AlertType.INFORMATION, "Borrado con exito");
									alert.showAndWait();
									showView(event);
								} else {
									alert = new Alert(Alert.AlertType.ERROR, "Ocurrio un error");
									alert.showAndWait();
								}
							} catch (Exception e) {
								e.printStackTrace();
							}
						});
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
	void btnAdd(MouseEvent event) throws Exception {
		Save addProvider = new Save();
		addProvider.showView(event);
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
				if (MySQLConnection.User_rol < 3) {
					HomeController home = new HomeController();
					try {
						home.showView(event);
					} catch (Exception e) {
						e.printStackTrace();
					}					
				} else {
					Alert alert = new Alert(AlertType.ERROR, "No cuentas con los permisos", ButtonType.OK);
					alert.showAndWait();
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
		Parent root = FXMLLoader.load(getClass().getResource("../../../views/Provider/index.fxml"));
		Scene scene = new Scene(root);
		Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		appStage.getIcons().add(new Image("/assets/images/legado_farmacia.png"));
		appStage.setScene(scene);
		appStage.toFront();
		appStage.show();
	}
}