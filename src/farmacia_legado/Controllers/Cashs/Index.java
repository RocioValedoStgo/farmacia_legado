package farmacia_legado.Controllers.Cashs;

import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ResourceBundle;
import farmacia_legado.Main;
import farmacia_legado.MySQLConnection;
import farmacia_legado.Controllers.HomeController;
import farmacia_legado.Models.Cash_Register;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;

public class Index implements Initializable {

    @FXML // fx:id="logo"
    private ImageView logo; // Value injected by FXMLLoader

    @FXML // fx:id="table"
    private TableView<Cash_Register> table; // Value injected by FXMLLoader
    
    private TableColumn<Cash_Register, Integer> col_id = new TableColumn<Cash_Register, Integer>("ID");
    
    private TableColumn<Cash_Register, Float> col_total = new TableColumn<Cash_Register, Float>("Venta total");
    
    private TableColumn<Cash_Register, String> col_status = new TableColumn<Cash_Register, String>("Status");
    
    private TableColumn<Cash_Register, Timestamp> col_created = new TableColumn<Cash_Register, Timestamp>("Creado el");
    
    private TableColumn<Cash_Register, String> col_options = new TableColumn<Cash_Register, String>("Opciones");

    @FXML // fx:id="titlePage"
    private Label titlePage; // Value injected by FXMLLoader

    @FXML // fx:id="menuButton"
    private MenuButton menuButton; // Value injected by FXMLLoader
    
    @FXML // fx:id="buttonCortCash"
    private Button buttonCortCash; // Value injected by FXMLLoader

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
    	MySQLConnection MySQL = new MySQLConnection();
    	col_id.setPrefWidth(50);
		col_id.setStyle("-fx-aligment: CENTER;");
		col_id.setStyle("-fx-font-size: 15px");
		col_total.setPrefWidth(150);
		col_total.setStyle("-fx-aligment: CENTER;");
		col_total.setStyle("-fx-font-size: 15px");
		col_status.setPrefWidth(150);
		col_status.setStyle("-fx-aligment: CENTER;");
		col_status.setStyle("-fx-font-size: 15px");
		col_created.setPrefWidth(150);
		col_created.setStyle("-fx-aligment: CENTER;");
		col_created.setStyle("-fx-font-size: 15px");
		table.getColumns().addAll(col_id, col_total, col_created, col_options);
		addButtonShow();
		col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
		col_total.setCellValueFactory(new PropertyValueFactory<>("total"));
		col_status.setCellValueFactory(new PropertyValueFactory<>("status"));
		col_created.setCellValueFactory(new PropertyValueFactory<>("created"));
		try {
			table.setItems(MySQL.indexCashs());
			if (MySQL.getCashActive() == 0) {    			
				buttonCortCash.setDisable(true);
				buttonCortCash.setStyle("-fx-background-color: #ccc");				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
    
    private void addButtonShow() {
		TableColumn<Cash_Register, Void> colBtn = new TableColumn<Cash_Register, Void>();
		Callback<TableColumn<Cash_Register, Void>, TableCell<Cash_Register, Void>> cellFactory = new Callback<TableColumn<Cash_Register, Void>, TableCell<Cash_Register, Void>>() {
			@Override
			public TableCell<Cash_Register, Void> call(final TableColumn<Cash_Register, Void> param) {
				final TableCell<Cash_Register, Void> cell = new TableCell<Cash_Register, Void>() {
					private final Button btn = new Button("Ver");
					{
						btn.setOnAction((ActionEvent event) -> {
							Cash_Register cash_Register = getTableView().getItems().get(getIndex());
							Profile profileCash = new Profile();
							Profile.setPkCash(cash_Register.getId());
							Profile.setDateInitial(cash_Register.getCreated());
							Profile.setDateFinal(cash_Register.getClose());
							try {
								profileCash.showView(event);
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
    void btnBack(MouseEvent event) throws Exception {
    	HomeController home = new HomeController();
    	home.showView(event);
    }
    
    @FXML
    void btnCashCort(MouseEvent event) throws Exception {
    	MySQLConnection MySQL = new MySQLConnection();
    	Alert alert;
    	int cash_id = MySQL.getCashActive();
    	if (MySQL.closeCashRegister(cash_id) == 1) {
    		if (MySQL.newCashRegister()) {
    			alert = new Alert(AlertType.INFORMATION, "Corte de caja exitoso", ButtonType.OK);
    			alert.showAndWait();
    			showView(event);
    		} else {
    			alert = new Alert(AlertType.ERROR, "Error al crear la nueva caja", ButtonType.OK);
    			alert.showAndWait();
    		}
    	} else {
    		alert = new Alert(AlertType.ERROR, "Error al hacer el corte de caja", ButtonType.OK);
    		alert.showAndWait();
    	}
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
				farmacia_legado.Controllers.User.Index indexUsers = new farmacia_legado.Controllers.User.Index();
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
        Parent root = FXMLLoader.load(getClass().getResource("../../../views/Cash Register/index.fxml"));
        Scene scene = new Scene(root);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.getIcons().add(new Image("/assets/images/legado_farmacia.png"));
        appStage.setScene(scene);
        appStage.toFront();
        appStage.show();
    }
}