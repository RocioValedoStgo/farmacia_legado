package farmacia_legado.Controllers;

import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import farmacia_legado.MySQLConnection;
import farmacia_legado.Controllers.Category.Index;
import farmacia_legado.Controllers.Sells.Save;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class HomeController implements Initializable{
	
	@FXML // fx:id="labelGreet"
    private Label labelGreet; // Value injected by FXMLLoader

    @FXML // fx:id="labelDate"
    private Label labelDate; // Value injected by FXMLLoader

    @FXML
    void btnBuy(MouseEvent event) throws Exception {
    	Save saveSell = new Save();
    	saveSell.showView(event);
    }

    @FXML
    void btnCashRegister(MouseEvent event) throws Exception {
    	farmacia_legado.Controllers.Cashs.Index indexCashs = new farmacia_legado.Controllers.Cashs.Index();
    	indexCashs.showView(event);
    }

    @FXML
    void btnCategories(MouseEvent event) throws Exception {
    	Index indexCategories = new Index();
    	indexCategories.showView(event);
    }

    @FXML
    void btnProducts(MouseEvent event) throws Exception {
    	farmacia_legado.Controllers.Product.Index indexProducts = new farmacia_legado.Controllers.Product.Index();
    	indexProducts.showView(event);
    }

    @FXML
    void btnProviders(MouseEvent event) throws Exception {
    	farmacia_legado.Controllers.Provider.Index indexProviders = new farmacia_legado.Controllers.Provider.Index();
    	indexProviders.showView(event);
    }

    @FXML
    void btnSells(MouseEvent event) throws Exception {
    	farmacia_legado.Controllers.Sells.Index indexSells = new farmacia_legado.Controllers.Sells.Index();
    	indexSells.showView(event);
    }

    @FXML
    void btnUsers(MouseEvent event) throws Exception {
    	farmacia_legado.Controllers.User.Index indexUsers = new farmacia_legado.Controllers.User.Index();
    	indexUsers.showView(event);
    }
    
    public void showView(Event event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../../views/home.fxml"));
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
    	MySQLConnection MySQL = new MySQLConnection();
    	SimpleDateFormat auxFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    	try {
			if (!MySQL.checkSellZero(auxFormat.format(new Date()))) {
				Alert alert;
				TextInputDialog dialog = new TextInputDialog("walter");
				dialog.setTitle("Venta 0");
				dialog.setContentText("Por favor ingrese la venta 0:");
				Optional<String> result = dialog.showAndWait();
				if (result.isPresent()){
				    int cash_id = MySQL.getCashActive();
				    if (MySQL.saveSell(Float.parseFloat(result.get()), 0, 0, cash_id) == 1) {
				    	alert = new Alert(AlertType.INFORMATION, "Venta 0 registrada", ButtonType.OK);
				    	alert.showAndWait();
				    } else {
				    	alert = new Alert(AlertType.ERROR, "Ocurrio un error al guardar la venta 0", ButtonType.OK);
				    	alert.showAndWait();
				    }
				} else {
					alert = new Alert(AlertType.ERROR, "Es necesario ingresar la venta 0", ButtonType.OK);
					alert.showAndWait();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
