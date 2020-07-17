package farmacia_legado.Controllers.Category;

import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import farmacia_legado.Main;
import farmacia_legado.MySQLConnection;
import farmacia_legado.Controllers.HomeController;
import farmacia_legado.Models.Category;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Profile implements Initializable {

	@FXML // fx:id="titlePage"
	private Label titlePage; // Value injected by FXMLLoader

	@FXML // fx:id="logo1"
	private ImageView logo1; // Value injected by FXMLLoader

	@FXML // fx:id="input_name"
	private TextField input_name; // Value injected by FXMLLoader

	@FXML // fx:id="btnMaster"
	private Button btnMaster; // Value injected by FXMLLoader

	@FXML // fx:id="comboxFather"
	private ComboBox<String> comboxFather; // Value injected by FXMLLoader

	@FXML // fx:id="imgView"
	private ImageView imgView; // Value injected by FXMLLoader

	@FXML // fx:id="menuButtonNavbar"
	private MenuButton menuButtonNavbar; // Value injected by FXMLLoader

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

	private static int pkCategory;

	@FXML
	void btnBack(MouseEvent event) throws Exception {
		Index indexCategory = new Index();
		indexCategory.showView(event);
	}

	@FXML
	void btnEdit(MouseEvent event) throws Exception {
		Edit editCategory = new Edit();
		editCategory.setPkCategory(getPkCategory());
		editCategory.showView(event);
	}

	@FXML
	void btnHomeLogo(MouseEvent event) {	}

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
				Index indexCategories = new Index();
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
				Main main = new Main();
				try {
					main.showView(event);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		menuButtonNavbar.setText(MySQLConnection.User_username);
		MySQLConnection MySQL = new MySQLConnection();
		try {
			Category category = MySQL.getCategory(getPkCategory());
			titlePage.setText("Categoría: " + category.getName());
			input_name.setText(category.getName());
			input_name.setEditable(false);
			comboxFather.setVisible(true);
			comboxFather.setValue(category.getFather_Id());
			String pathImg = System.getProperty("user.dir") + "\\src\\assets\\images\\categories\\"
					+ category.getImage() + ".jpg";
			File imgFile = new File(pathImg);
			Image image = new Image("file:" + imgFile.getAbsolutePath());
			imgView.setImage(image);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void showView(Event event) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("../../../views/Category/show.fxml"));
		Scene scene = new Scene(root);
		Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		appStage.getIcons().add(new Image("/assets/images/legado_farmacia.png"));
		appStage.setScene(scene);
		appStage.toFront();
		appStage.show();
	}

	public int getPkCategory() {
		return pkCategory;
	}

	public void setPkCategory(int pkCategory) {
		Profile.pkCategory = pkCategory;
	}
}
