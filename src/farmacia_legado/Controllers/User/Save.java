package farmacia_legado.Controllers.User;

import farmacia_legado.Main;
import farmacia_legado.Controllers.HomeController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class Save {

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

    @FXML // fx:id="imgView"
    private ImageView imgView; // Value injected by FXMLLoader

    @FXML // fx:id="input_email"
    private TextField input_email; // Value injected by FXMLLoader

    @FXML // fx:id="comboxRol"
    private ComboBox<Integer> comboxRol; // Value injected by FXMLLoader

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

    @FXML
    void btnBack(MouseEvent event) {

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
				try {
					Index indexUsers = new Index();
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
    void btnSave(MouseEvent event) {

    }

    @FXML
    void btnUploadImage(MouseEvent event) {

    }

}
