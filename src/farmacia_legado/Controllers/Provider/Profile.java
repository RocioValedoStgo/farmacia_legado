/**
 * Sample Skeleton for 'show.fxml' Controller Class
 */

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
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Profile implements Initializable{

    @FXML // fx:id="titlePage"
    private Label titlePage; // Value injected by FXMLLoader

    @FXML // fx:id="logo1"
    private ImageView logo1; // Value injected by FXMLLoader

    @FXML // fx:id="input_name"
    private TextField input_name; // Value injected by FXMLLoader

    @FXML // fx:id="input_address"
    private TextArea input_address; // Value injected by FXMLLoader

    @FXML // fx:id="input_email"
    private TextField input_email; // Value injected by FXMLLoader

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
    
    private static int pkProvider;
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		menuButton.setText(MySQLConnection.User_username);
		MySQLConnection MySQL = new MySQLConnection();
		try {
			Provider provider = MySQL.getProvider(getPkProvider());
			titlePage.setText("Proveedor: " + provider.getName());
			input_name.setText(provider.getName());
			input_address.setText(provider.getAddress());
			input_phone.setText(provider.getPhone());
			input_email.setText(provider.getEmail());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

    @FXML
    void btnBack(MouseEvent event) throws Exception {
    	Index indexProvider = new Index();
    	indexProvider.showView(event);
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
    	Edit editProvider = new Edit();
    	Edit.setPkProvider(getPkProvider());
    	editProvider.showView(event);
    }

	public static int getPkProvider() {
		return pkProvider;
	}

	public static void setPkProvider(int pkProvider) {
		Profile.pkProvider = pkProvider;
	}
	
	public void showView(Event event) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("../../../views/Provider/show.fxml"));
		Scene scene = new Scene(root);
		Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		appStage.getIcons().add(new Image("/assets/images/legado_farmacia.png"));
		appStage.setScene(scene);
		appStage.toFront();
		appStage.show();
	}
}