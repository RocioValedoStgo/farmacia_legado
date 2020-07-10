package farmacia_legado;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class HomeController implements Initializable{
	
	@FXML // fx:id="labelGreet"
    private Label labelGreet; // Value injected by FXMLLoader

    @FXML // fx:id="labelDate"
    private Label labelDate; // Value injected by FXMLLoader

    @FXML
    void btnBuy(MouseEvent event) {

    }

    @FXML
    void btnCashRegister(MouseEvent event) {

    }

    @FXML
    void btnCategories(MouseEvent event) {

    }

    @FXML
    void btnProducts(MouseEvent event) {

    }

    @FXML
    void btnProviders(MouseEvent event) {

    }

    @FXML
    void btnSells(MouseEvent event) {

    }

    @FXML
    void btnUsers(MouseEvent event) {

    }
    
    public void showView(Event event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../views/home.fxml"));
        Scene scene = new Scene(root);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.getIcons().add(new Image("/assets/images/legado_farmacia.png"));
        appStage.setScene(scene);
        appStage.toFront();
        appStage.show();
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		String dateString = format.format( new Date());
    	labelGreet.setText("Bienvenido "+MySQLConnection.User_fullname);
    	labelDate.setText(dateString.toString());
	}
}
