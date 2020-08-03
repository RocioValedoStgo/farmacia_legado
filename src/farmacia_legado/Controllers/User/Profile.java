package farmacia_legado.Controllers.User;

import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Profile implements Initializable {

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
    
    @FXML // fx:id="comboxRol"
    private ComboBox<String> comboxRol; // Value injected by FXMLLoader

    @FXML // fx:id="input_email"
    private TextField input_email; // Value injected by FXMLLoader

    @FXML // fx:id="input_last_name"
    private TextField input_last_name; // Value injected by FXMLLoader

    @FXML // fx:id="input_username"
    private TextField input_username; // Value injected by FXMLLoader

    @FXML // fx:id="input_phone"
    private TextField input_phone; // Value injected by FXMLLoader

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
    
    private static int pkUser;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		MySQLConnection MySQL = new MySQLConnection();
		try {
			menuButton.setText(MySQLConnection.User_username);
			User user = MySQL.getUser(getPkUser());
			titlePage.setText("Usuario:"+user.getFull_name());
			input_name.setText(user.getName());
			input_last_name.setText(user.getLast_name());
			input_username.setText(user.getUsername());
			input_phone.setText(user.getPhone());
			input_email.setText(user.getEmail());
			comboxTurn.setValue(user.getTurn());
			comboxRol.setValue(user.getRol());
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
    	Edit editUser = new Edit();
    	Edit.setPkUser(getPkUser());
    	editUser.showView(event);
    }
    
    public void showView(Event event) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("../../../views/User/show.fxml"));
		Scene scene = new Scene(root);
		Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		appStage.getIcons().add(new Image("/assets/images/legado_farmacia.png"));
		appStage.setScene(scene);
		appStage.toFront();
		appStage.show();
	}
    
    public static int getPkUser() {
		return pkUser;
	}

	public static void setPkUser(int pkUser) {
		Profile.pkUser= pkUser;
	}
}