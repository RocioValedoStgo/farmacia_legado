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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Edit implements Initializable {

	@FXML // fx:id="titlePage"
	private Label titlePage; // Value injected by FXMLLoader

	@FXML // fx:id="logo1"
	private ImageView logo1; // Value injected by FXMLLoader

	@FXML // fx:id="subtitlePage"
	private Label subtitlePage; // Value injected by FXMLLoader

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
			titlePage.setText("Editando al proveedor: " + provider.getName());
			input_name.setText(provider.getName());
			input_address.setText(provider.getAddress());
			input_phone.setText(provider.getPhone());
			input_email.setText(provider.getEmail());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static int getPkProvider() {
		return pkProvider;
	}

	public static void setPkProvider(int pkProvider) {
		Edit.pkProvider = pkProvider;
	}

	@FXML
	void btnBack(MouseEvent event) throws Exception {
		Profile profileProvider = new Profile();
		Profile.setPkProvider(getPkProvider());
		profileProvider.showView(event);
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

		optionCategories.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				farmacia_legado.Controllers.Product.Index indexCategories = new farmacia_legado.Controllers.Product.Index();
				try {
					indexCategories.showView(event);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		optionProducts.setOnAction(new EventHandler<ActionEvent>() {
			farmacia_legado.Controllers.Product.Index indexProducts = new farmacia_legado.Controllers.Product.Index();
			@Override
			public void handle(ActionEvent arg0) {
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
				Main main = new Main();
				try {
					main.showView(event);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	@FXML
	void btnSave(MouseEvent event) throws Exception {
		Alert alert;
		if (input_name.getText().equals(null) || input_address.getText().equals(null) || input_phone.getText().equals(null) || input_email.getText().equals(null)) {
			alert = new Alert(AlertType.ERROR, "No es posible dejar un campo vacio", ButtonType.OK);
			alert.showAndWait();
		} else {
			MySQLConnection MySQL = new MySQLConnection();
			if (MySQL.editProvider(getPkProvider(), input_name.getText(), input_address.getText(), input_phone.getText(), input_email.getText()) == 1) {
				alert = new Alert(AlertType.INFORMATION, "Proveedor editado con exito", ButtonType.OK);
				alert.showAndWait();
				Profile profileProvider = new Profile();
				Profile.setPkProvider(getPkProvider());
				profileProvider.showView(event);
			} else {
				alert = new Alert(AlertType.ERROR, "Ocurrio un error al editar el proveedor", ButtonType.OK);
				alert.showAndWait();
			}
		}
	}

	public void showView(Event event) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("../../../views/Provider/edit.fxml"));
		Scene scene = new Scene(root);
		Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		appStage.getIcons().add(new Image("/assets/images/legado_farmacia.png"));
		appStage.setScene(scene);
		appStage.toFront();
		appStage.show();
	}
}
