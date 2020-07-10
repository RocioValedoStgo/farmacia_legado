package farmacia_legado;

import javafx.application.Application;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Main extends Application {
	
	@FXML // fx:id="input_password"
    private PasswordField input_password; // Value injected by FXMLLoader

    @FXML // fx:id="input_username"
    private TextField input_username; // Value injected by FXMLLoader


	public static void main(String[] args) {
		launch(args);
	}
	
    @FXML
    void btnLogin(MouseEvent event) throws Exception {
    	if (input_username.equals(null) || input_password.equals(null)) {
    		Alert alert = new Alert(AlertType.ERROR, "Es necesario ingresar los datos solicitados", ButtonType.OK);
    		alert.showAndWait();
    	} else {
    		MySQLConnection MySQL = new MySQLConnection();
        	if (MySQL.login(input_username.getText(), input_password.getText())) {
        		HomeController home = new HomeController();
        		home.showView(event);
        	} else {
        		Alert alert = new Alert(AlertType.ERROR, "Las credenciales ingresadas no coinciden con algun registro", ButtonType.OK);
        		alert.showAndWait();
        	}
    	}
    }

    @FXML
    void newUser(MouseEvent event) throws Exception {
    	Register register = new Register();
    	register.showView(event);
    }
    
    public void showView(Event event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../views/login.fxml"));
        Scene scene = new Scene(root);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.getIcons().add(new Image("/assets/images/legado_farmacia.png"));
        appStage.setScene(scene);
        appStage.toFront();
        appStage.show();
    }

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("../views/login.fxml"));
        primaryStage.getIcons().add(new Image("/assets/images/legado_farmacia.png"));
		primaryStage.setScene(new Scene(root, 1000, 600));
		primaryStage.setTitle("Farmacia Legado");
		primaryStage.show();
	}
}
