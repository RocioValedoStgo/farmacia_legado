package farmacia_legado.Controllers.User;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import farmacia_legado.Main;
import farmacia_legado.MySQLConnection;
import farmacia_legado.Controllers.HomeController;
import farmacia_legado.Models.User;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;

public class Index implements Initializable {

	@FXML // fx:id="titlePage"
	private Label titlePage; // Value injected by FXMLLoader

	@FXML // fx:id="labelBtnAdd"
	private Label labelBtnAdd; // Value injected by FXMLLoader

	@FXML // fx:id="table"
	private TableView<User> table; // Value injected by FXMLLoader

	@FXML
	private TableColumn<User, Integer> col_id = new TableColumn<User, Integer>("ID");

	@FXML
	private TableColumn<User, String> col_name = new TableColumn<User, String>("Nombre completo");

	@FXML
	private TableColumn<User, String> col_turn = new TableColumn<User, String>("Turno");

	@FXML
	private TableColumn<User, String> col_rol = new TableColumn<User, String>("Rol");

	@FXML
	private TableColumn<User, String> col_options = new TableColumn<User, String>("Opciones");

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

	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		menuButton.setText(MySQLConnection.User_username);
		col_id.setPrefWidth(50);
		col_id.setStyle("-fx-aligment: CENTER;");
		col_id.setStyle("-fx-font-size: 15px");
		col_name.setPrefWidth(250);
		col_name.setStyle("-fx-aligment: CENTER;");
		col_name.setStyle("-fx-font-size: 15px");
		col_turn.setPrefWidth(115);
		col_turn.setStyle("-fx-aligment: CENTER;");
		col_turn.setStyle("-fx-font-size: 15px");
		col_rol.setPrefWidth(80);
		col_rol.setStyle("-fx-aligment: CENTER;");
		col_rol.setStyle("-fx-font-size: 15px");
		table.getColumns().addAll(col_id, col_name, col_turn, col_rol, col_options);
		addButtonShow();
		addButtonDestroy();
		addButtonResetPassword();
		addButtonChangeRol();
		col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
		col_name.setCellValueFactory(new PropertyValueFactory<>("full_name"));
		col_turn.setCellValueFactory(new PropertyValueFactory<>("turn"));
		col_rol.setCellValueFactory(new PropertyValueFactory<>("rol"));
		MySQLConnection MySQL = new MySQLConnection();
		try {
			table.setItems(MySQL.indexUsers());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void addButtonShow() {
		TableColumn<User, Void> colBtn = new TableColumn<User, Void>();
		Callback<TableColumn<User, Void>, TableCell<User, Void>> cellFactory = new Callback<TableColumn<User, Void>, TableCell<User, Void>>() {
			@Override
			public TableCell<User, Void> call(final TableColumn<User, Void> param) {
				final TableCell<User, Void> cell = new TableCell<User, Void>() {
					private final Button btn = new Button("Ver");
					{
						btn.setOnAction((ActionEvent event) -> {
							User user = getTableView().getItems().get(getIndex());
							Profile profileUser = new Profile();
							try {
								Profile.setPkUser(user.getId());
								profileUser.showView(event);
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
		TableColumn<User, Void> colBtn = new TableColumn<User, Void>();

		Callback<TableColumn<User, Void>, TableCell<User, Void>> cellFactory = new Callback<TableColumn<User, Void>, TableCell<User, Void>>() {
			@Override
			public TableCell<User, Void> call(final TableColumn<User, Void> param) {
				final TableCell<User, Void> cell = new TableCell<User, Void>() {
					private final Button btn = new Button("Borrar");
					{

						btn.setOnAction((ActionEvent event) -> {
							User user = getTableView().getItems().get(getIndex());
							Alert alert;
							MySQLConnection MySQL = new MySQLConnection();
							try {
								if (MySQL.destroyUser(user.getId()) == 1) {
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

	private void addButtonResetPassword() {
		TableColumn<User, Void> colBtn = new TableColumn<User, Void>();
		Callback<TableColumn<User, Void>, TableCell<User, Void>> cellFactory = new Callback<TableColumn<User, Void>, TableCell<User, Void>>() {
			@Override
			public TableCell<User, Void> call(final TableColumn<User, Void> param) {
				final TableCell<User, Void> cell = new TableCell<User, Void>() {
					private final Button btn = new Button("Cambiar Contraseña");
					{
						btn.setOnAction((ActionEvent event) -> {
							User user = getTableView().getItems().get(getIndex());
							Dialog<String> dialog = new Dialog<>();
							dialog.setTitle("Usuario: " + user.getFull_name());
							dialog.setHeaderText("Cambio de contraseña");
							dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
							PasswordField pwd = new PasswordField();
							HBox content = new HBox();
							content.setAlignment(Pos.CENTER_LEFT);
							content.setSpacing(10);
							content.getChildren().addAll(new Label("Por favor ingresa la nueva contraseña:"), pwd);
							dialog.getDialogPane().setContent(content);
							dialog.setResultConverter(dialogButton -> {
								if (dialogButton == ButtonType.OK && !pwd.getText().isBlank()) {
									System.out.println("Si 230 " + pwd.getText());
									showMessageSuccessfully(pwd.getText());
									return null;
								} else {
									System.out.println("No 232 ");
									showMessageError();
									return null;
								}
							});
							dialog.showAndWait();
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

	private void addButtonChangeRol() {
		TableColumn<User, Void> colBtn = new TableColumn<User, Void>();
		Callback<TableColumn<User, Void>, TableCell<User, Void>> cellFactory = new Callback<TableColumn<User, Void>, TableCell<User, Void>>() {
			@Override
			public TableCell<User, Void> call(final TableColumn<User, Void> param) {
				final TableCell<User, Void> cell = new TableCell<User, Void>() {
					private final Button btn = new Button("Cambiar Rol");
					{
						btn.setOnAction((ActionEvent event) -> {
							User user = getTableView().getItems().get(getIndex());
							List<String> choices = new ArrayList<>();
							choices.add("1. Dueño");
							choices.add("2. Empleado");
							ChoiceDialog<String> dialog = new ChoiceDialog<>("", choices);
							dialog.setTitle("Usuario: " + user.getFull_name());
							dialog.setHeaderText("Por favor, selecciona");
							dialog.setContentText("El rol a asignar:");
							Optional<String> result = dialog.showAndWait();
							Alert alert;
							if (result.isPresent()) {
								MySQLConnection MySQL = new MySQLConnection();
								try {
									if (result.get().equals("1. Dueño")) {
										if (MySQL.editRolUser(user.getId(), 2) == 1) {
											alert = new Alert(AlertType.INFORMATION, "Rol cambiado con exito",
													ButtonType.OK);
											alert.showAndWait();
											showView(event);
										}
									} else if (result.get().equals("2. Empleado")) {
										if (MySQL.editRolUser(user.getId(), 3) == 1) {
											alert = new Alert(AlertType.INFORMATION, "Rol cambiado con exito",
													ButtonType.OK);
											alert.showAndWait();
											showView(event);
										}
									}
								} catch (Exception e) {
									e.printStackTrace();
								}
							} else {
								alert = new Alert(AlertType.ERROR, "Es necesario indicar el nuevo rol del usuario",
										ButtonType.OK);
								alert.showAndWait();
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
	void btnAddUser(MouseEvent event) throws Exception {
		Save saveUser = new Save();
		saveUser.showView(event);
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

		optionUsers.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				Index indexUsers = new Index();
				try {
					indexUsers.showView(event);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		optionProviders.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				farmacia_legado.Controllers.Provider.Index indexProviders = new farmacia_legado.Controllers.Provider.Index();
				try {
					indexProviders.showView(event);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		optionCategories.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				farmacia_legado.Controllers.Category.Index indexCategories = new farmacia_legado.Controllers.Category.Index();
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
		Parent root = FXMLLoader.load(getClass().getResource("../../../views/User/index.fxml"));
		Scene scene = new Scene(root);
		Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		appStage.getIcons().add(new Image("/assets/images/legado_farmacia.png"));
		appStage.setScene(scene);
		appStage.toFront();
		appStage.show();
	}
	
	public void showMessageSuccessfully(String newPwd) {
		Alert alert = new Alert(AlertType.INFORMATION, "Cambio de contraseña correcto:"+newPwd, ButtonType.OK);
		alert.showAndWait();
	}
	
	public void showMessageError() {
		Alert alert = new Alert(AlertType.ERROR, "Ocurrio un error al cambiar la contraseña", ButtonType.OK);
		alert.showAndWait();
	}
}