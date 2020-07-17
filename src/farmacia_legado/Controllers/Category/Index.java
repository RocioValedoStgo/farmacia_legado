package farmacia_legado.Controllers.Category;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import farmacia_legado.Main;
import farmacia_legado.MySQLConnection;
import farmacia_legado.Controllers.HomeController;
import farmacia_legado.Models.Category;
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

	@FXML
	private Label titlePage;

	@FXML
	private Label labelBtnAdd;

	@FXML
	private TableView<Category> table;

	@FXML
	private TableColumn<Category, Integer> col_id = new TableColumn<>("ID");

	@FXML
	private TableColumn<Category, String> col_name = new TableColumn<>("Nombre");

	@FXML
	private TableColumn<Category, Integer> col_father_id = new TableColumn<>("Categoría padre");

	@FXML
	private TableColumn<Category, String> col_options = new TableColumn<>("Opciones");

	@FXML
	private MenuButton menuButtonNavbar;

	@FXML
	private MenuItem optionHome;

	@FXML
	private MenuItem optionUsers;

	@FXML
	private MenuItem optionProducts;

	@FXML
	private MenuItem optionCategories;

	@FXML
	private MenuItem optionProviders;

	@FXML
	private MenuItem optionLogOut;

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
		col_father_id.setPrefWidth(150);
		col_father_id.setStyle("-fx-aligment: CENTER;");
		col_father_id.setStyle("-fx-font-size: 15px");
		table.getColumns().addAll(col_id, col_name, col_father_id, col_options);
		addButtonShow();
		addButtonDestroy();
		col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
		col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
		col_father_id.setCellValueFactory(new PropertyValueFactory<>("father_id"));
		MySQLConnection MySQL = new MySQLConnection();
		try {
			table.setItems(MySQL.indexCategories());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void addButtonShow() {
		TableColumn<Category, Void> colBtn = new TableColumn<Category, Void>();
		Callback<TableColumn<Category, Void>, TableCell<Category, Void>> cellFactory = new Callback<TableColumn<Category, Void>, TableCell<Category, Void>>() {
			@Override
			public TableCell<Category, Void> call(final TableColumn<Category, Void> param) {
				final TableCell<Category, Void> cell = new TableCell<Category, Void>() {
					private final Button btn = new Button("Ver");
					{
						btn.setOnAction((ActionEvent event) -> {
							Category category = getTableView().getItems().get(getIndex());
							Profile profileCategory = new Profile();
							profileCategory.setPkCategory(category.getId());
							try {
								profileCategory.showView(event);
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
		TableColumn<Category, Void> colBtn = new TableColumn<Category, Void>();

		Callback<TableColumn<Category, Void>, TableCell<Category, Void>> cellFactory = new Callback<TableColumn<Category, Void>, TableCell<Category, Void>>() {
			@Override
			public TableCell<Category, Void> call(final TableColumn<Category, Void> param) {
				final TableCell<Category, Void> cell = new TableCell<Category, Void>() {
					private final Button btn = new Button("Borrar");
					{
						btn.setOnAction((ActionEvent event) -> {
							Category category = getTableView().getItems().get(getIndex());
							Alert alert;
							MySQLConnection MySQL = new MySQLConnection();
							try {
								if (MySQL.destroyCategory(category.getId()) == 1) {
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
	void btnAddCategory(MouseEvent event) throws Exception {
		Save postCategory = new Save();
		postCategory.showView(event);
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
				Index indexCategories = new Index();
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
				farmacia_legado.Controllers.Product.Index indexProducts = new farmacia_legado.Controllers.Product.Index();
				try {
					indexProducts.showView(event);
				} catch (Exception e) {
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
		Parent root = FXMLLoader.load(getClass().getResource("../../../views/Category/index.fxml"));
		Scene scene = new Scene(root);
		Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		appStage.getIcons().add(new Image("/assets/images/legado_farmacia.png"));
		appStage.setScene(scene);
		appStage.toFront();
		appStage.show();
	}
}
