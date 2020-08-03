package farmacia_legado.Controllers.User;

import java.net.URL;
import java.util.ResourceBundle;
import farmacia_legado.Main;
import farmacia_legado.MySQLConnection;
import farmacia_legado.Controllers.HomeController;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Save implements Initializable {

    @FXML // fx:id="titlePage"
    private Label titlePage; // Value injected by FXMLLoader

    @FXML // fx:id="logo1"
    private ImageView logo1; // Value injected by FXMLLoader

    @FXML // fx:id="subtitlePage"
    private Label subtitlePage; // Value injected by FXMLLoader

    @FXML // fx:id="input_name"
    private TextField input_name; // Value injected by FXMLLoader

    @FXML // fx:id="btnMaster"
    private Button btnMaster; // Value injected by FXMLLoader

    @FXML // fx:id="comboxTurn"
    private ComboBox<String> comboxTurn; // Value injected by FXMLLoader

    @FXML // fx:id="input_email"
    private TextField input_email; // Value injected by FXMLLoader

    @FXML // fx:id="input_last_name"
    private TextField input_last_name; // Value injected by FXMLLoader

    @FXML // fx:id="input_username"
    private TextField input_username; // Value injected by FXMLLoader

    @FXML // fx:id="input_phone"
    private TextField input_phone; // Value injected by FXMLLoader

    @FXML // fx:id="input_password"
    private PasswordField input_password; // Value injected by FXMLLoader

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
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		comboxTurn.getItems().addAll("Matutino", "Vespertino");
	}

    @FXML
    void btnBack(MouseEvent event) throws Exception {
    	Index indexUsers = new Index();
    	indexUsers.showView(event);
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

    @FXML
    void btnSave(MouseEvent event) throws Exception {
    	Alert alert;
    	if (input_name.getText().equals(null) || input_last_name.getText().equals(null) || input_username.getText().equals(null) || input_phone.getText().equals(null) || input_email.getText().equals(null) || input_password.getText().equals(null) || comboxTurn.getValue().equals(null)) {
    		alert = new Alert(AlertType.ERROR, "Es necesario ingresar toda la informacion", ButtonType.OK);
    		alert.showAndWait();
		} else {
			MySQLConnection MySQL = new MySQLConnection();
			if (MySQL.register(false, input_name.getText(), input_last_name.getText(), input_username.getText(), input_phone.getText(), comboxTurn.getValue(), 3, input_email.getText(), input_password.getText())) {
				alert = new Alert(AlertType.INFORMATION, "Usuario registrado", ButtonType.OK);
				alert.showAndWait();
				Index indexUsers = new Index();
				indexUsers.showView(event);
			} else {
				alert = new Alert(AlertType.ERROR, "Ocurrio un error al registrar el usuario", ButtonType.OK);
				alert.showAndWait();
			}
		}
    }
    
    public void showView(Event event) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("../../../views/User/post.fxml"));
		Scene scene = new Scene(root);
		Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		appStage.getIcons().add(new Image("/assets/images/legado_farmacia.png"));
		appStage.setScene(scene);
		appStage.toFront();
		appStage.show();
	}
}
