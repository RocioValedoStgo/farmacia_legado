package farmacia_legado.Controllers.Sells;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import org.controlsfx.control.textfield.TextFields;
import farmacia_legado.Main;
import farmacia_legado.MySQLConnection;
import farmacia_legado.Controllers.HomeController;
import farmacia_legado.Controllers.User.Index;
import farmacia_legado.Models.Product;
import farmacia_legado.Models.Sell_Detail;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;

public class Save  implements Initializable {

    @FXML // fx:id="logo"
    private ImageView logo; // Value injected by FXMLLoader

    @FXML // fx:id="table"
    private TableView<Sell_Detail> table; // Value injected by FXMLLoader
    
    private TableColumn<Sell_Detail, String> col_name = new TableColumn<Sell_Detail, String>("Nombre");
    
    private TableColumn<Sell_Detail, Float> col_price = new TableColumn<Sell_Detail, Float>("Precio");
    
    private TableColumn<Sell_Detail, Integer> col_quantity = new TableColumn<Sell_Detail, Integer>("Cantidad");
    
    private TableColumn<Sell_Detail, Float> col_subtotal = new TableColumn<Sell_Detail, Float>("Subtotal");
    
    private TableColumn<Sell_Detail, String> col_options = new TableColumn<Sell_Detail, String>("Opciones");
    
    @FXML // fx:id="titlePage"
    private Label titlePage; // Value injected by FXMLLoader

    @FXML // fx:id="input_name_product"
    private TextField input_name_product; // Value injected by FXMLLoader
    
    @FXML // fx:id="labelTotal"
    private Label labelTotal; // Value injected by FXMLLoader

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
    
    private ObservableList<Sell_Detail> listDetails = FXCollections.observableArrayList();
    private float total = 0;
    
    @SuppressWarnings("unchecked")
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	MySQLConnection MySQL = new MySQLConnection();
    	try {
    		col_name.setPrefWidth(250);
    		col_name.setStyle("-fx-aligment: CENTER;");
    		col_name.setStyle("-fx-font-size: 15px");
    		col_price.setPrefWidth(75);
    		col_price.setStyle("-fx-aligment: CENTER;");
    		col_price.setStyle("-fx-font-size: 15px");
    		col_quantity.setPrefWidth(75);
    		col_quantity.setStyle("-fx-aligment: CENTER;");
    		col_quantity.setStyle("-fx-font-size: 15px");
    		col_subtotal.setPrefWidth(120);
    		col_subtotal.setStyle("-fx-aligment: CENTER;");
    		col_subtotal.setStyle("-fx-font-size: 15px");
    		table.getColumns().addAll(col_name, col_price, col_quantity, col_subtotal, col_options);
    		addButtonRemove();
    		col_name.setCellValueFactory(new PropertyValueFactory<>("product_id"));
    		col_price.setCellValueFactory(new PropertyValueFactory<>("price"));
    		col_quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
    		col_subtotal.setCellValueFactory(new PropertyValueFactory<>("subtotal"));
    		menuButton.setText(MySQLConnection.User_username);
    		labelTotal.setText("IVA: 16%\nTotal: 0");
    		TextFields.bindAutoCompletion(input_name_product, MySQL.getProducts());
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
	}
    
    private void addButtonRemove() {
		TableColumn<Sell_Detail, Void> colBtn = new TableColumn<Sell_Detail, Void>();
		Callback<TableColumn<Sell_Detail, Void>, TableCell<Sell_Detail, Void>> cellFactory = new Callback<TableColumn<Sell_Detail, Void>, TableCell<Sell_Detail, Void>>() {
			@Override
			public TableCell<Sell_Detail, Void> call(final TableColumn<Sell_Detail, Void> param) {
				final TableCell<Sell_Detail, Void> cell = new TableCell<Sell_Detail, Void>() {
					private final Button btn = new Button("Quitar");
					{
						  btn.setOnAction((ActionEvent event) -> {
							  Sell_Detail detail = table.getSelectionModel().getSelectedItem();
							  table.getItems().remove(detail);
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
    void btnAddToCart(MouseEvent event) throws NumberFormatException, SQLException {
    	Alert alert;
    	if (input_name_product.getText().equals(null)) {
			alert = new Alert(AlertType.ERROR, "Es necesario buscar el producto para agregarlo al carrito", ButtonType.OK);
			alert.showAndWait();
		} else {
			TextInputDialog dialog = new TextInputDialog();
			dialog.setTitle("Realizar compra");
			dialog.setContentText("Ingrese la cantidad a comprar:");
			Optional<String> result = dialog.showAndWait();
			if (result.isPresent()){
				MySQLConnection MySQL = new MySQLConnection();
				Product product = MySQL.getProduct(input_name_product.getText());
				if (Integer.parseInt(result.get()) > product.getQuantity()) {
					alert = new Alert(AlertType.ERROR, "La cantidad ingresada supera la cantidad en almacen", ButtonType.OK);
					alert.showAndWait();
				} else {
					Sell_Detail detail = new Sell_Detail(MySQL.getProduct(input_name_product.getText()), Integer.parseInt(result.get()));
					detail.setPrice(product.getPrice());
					detail.setQuantity(Integer.parseInt(result.get()));
					detail.setSubtotal(Integer.parseInt(result.get()));
					listDetails.add(detail);
					table.setItems(listDetails);
					for (Sell_Detail item : table.getItems()) {
						total = total + col_subtotal.getCellObservableValue(item).getValue();
					}
					labelTotal.setText("IVA 16%\nTotal: "+total);
					total = 0;
				}
			} else {
				alert = new Alert(AlertType.ERROR, "Es necesario ingresar la cantidad a comprar", ButtonType.OK);
				alert.showAndWait();
			}
		}
    }

    @FXML
    void btnBack(MouseEvent event) throws Exception {
    	HomeController homeController = new HomeController();
    	homeController.showView(event);
    }

    @FXML
    void btnCancel(MouseEvent event) throws Exception {
    	Alert alert;
    	alert = new Alert(AlertType.CONFIRMATION);
    	alert.setTitle("Cancelacion");
    	alert.setContentText("Desea cancelar la compra?");
    	Optional<ButtonType> result = alert.showAndWait();
    	if (result.get() == ButtonType.OK) {
			alert = new Alert(AlertType.INFORMATION, "Compra cancelada", ButtonType.OK);
			alert.showAndWait();
			HomeController homeController = new HomeController();
			homeController.showView(event);
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

    @FXML
    void btnPay(MouseEvent event) {

    }
    
    public void showView(Event event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../../../views/Sells/post.fxml"));
        Scene scene = new Scene(root);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.getIcons().add(new Image("/assets/images/legado_farmacia.png"));
        appStage.setScene(scene);
        appStage.toFront();
        appStage.show();
    }
}