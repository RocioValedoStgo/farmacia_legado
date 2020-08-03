package farmacia_legado.Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import farmacia_legado.Main;
import farmacia_legado.MySQLConnection;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class RegisterController implements Initializable{
	@FXML // fx:id="input_name"
	private TextField input_name; // Value injected by FXMLLoader

	@FXML // fx:id="input_last_name"
	private TextField input_last_name; // Value injected by FXMLLoader

	@FXML // fx:id="input_username"
	private TextField input_username; // Value injected by FXMLLoader

	@FXML // fx:id="input_email"
	private TextField input_email; // Value injected by FXMLLoader

	@FXML // fx:id="input_password"
	private PasswordField input_password; // Value injected by FXMLLoader

	@FXML // fx:id="input_phone"
	private TextField input_phone; // Value injected by FXMLLoader

	@FXML // fx:id="combox_turn"
	private ComboBox<String> combox_turn; // Value injected by FXMLLoader
	
	@FXML
	void btnBack(MouseEvent event) throws Exception {
		Main main = new Main();
		main.showView(event);
	}

	@FXML
	void btnRegister(MouseEvent event) throws Exception {
		MySQLConnection MySQL = new MySQLConnection();
		String input_turn = combox_turn.getValue();
		if (MySQL.register(true, input_name.getText(), input_last_name.getText(), input_username.getText(), input_phone.getText(), input_turn, 3, input_email.getText(), input_password.getText())) {
			HomeController home = new HomeController();
			home.showView(event);
    	} else {
			Alert alert = new Alert(AlertType.ERROR, "Ocurrio un error con el registro", ButtonType.OK);
			alert.showAndWait();
		}
	}

	public void showView(Event event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../../views/register.fxml"));
        Scene scene = new Scene(root);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.getIcons().add(new Image("/assets/images/legado_farmacia.png"));
        appStage.setScene(scene);
        appStage.toFront();
        appStage.show();
    }
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		combox_turn.getItems().removeAll(combox_turn.getItems());
		combox_turn.getItems().addAll("Matutino", "Vespertino");
		combox_turn.getSelectionModel().select("Ingrese el turno en el que laborara");
	}
}
