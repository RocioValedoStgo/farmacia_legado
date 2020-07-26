package farmacia_legado.Controllers.Product;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.security.SecureRandom;
import java.sql.SQLException;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
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

	@FXML // fx:id="comboxProvider"
	private ComboBox<String> comboxProvider; // Value injected by FXMLLoader

	@FXML // fx:id="imgView"
	private ImageView imgView; // Value injected by FXMLLoader

	@FXML // fx:id="textArea_description"
	private TextArea textArea_description; // Value injected by FXMLLoader

	@FXML // fx:id="input_price"
	private TextField input_price; // Value injected by FXMLLoader

	@FXML // fx:id="input_quantity"
	private TextField input_quantity; // Value injected by FXMLLoader

	@FXML // fx:id="comboxFather"
	private ComboBox<String> comboxFather; // Value injected by FXMLLoader

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
	
	private Stage stage;
	private File imgFile;
	private FileChooser fileChooser;
	private static final String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
	private static final String CHAR_UPPER = CHAR_LOWER.toUpperCase();
	private static final String NUMBER = "0123456789";
	private static final String DATA_FOR_RANDOM_STRING = CHAR_LOWER + CHAR_UPPER + NUMBER;
	private static SecureRandom random = new SecureRandom();
	private boolean band = false;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		menuButtonNavbar.setText(MySQLConnection.User_username);
		MySQLConnection MySQL = new MySQLConnection();
		try {
			comboxFather.getItems().addAll(MySQL.getCategories());
			comboxProvider.getItems().addAll(MySQL.getProviders());
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}

	@FXML
	void btnBack(MouseEvent event) throws Exception {
		Index indexProducts = new Index();
		indexProducts.showView(event);
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
    	if (input_name.getText().equals(null) || input_price.getText().equals(null) || input_quantity.getText().equals(null) || textArea_description.getText().equals(null) || comboxFather.getValue().equals(null)) {
    		alert = new Alert(AlertType.ERROR, "Es necesario ingresar todos los datos", ButtonType.OK);
    		alert.showAndWait();
    	} else {
    		MySQLConnection MySQL = new MySQLConnection();
    		String image;
    		if (band) {
    			image = saveImage(imgFile);	
			} else {
				image = null;
			}
    		if (MySQL.saveProduct(input_name.getText(), textArea_description.getText(), image, Float.parseFloat(input_price.getText()), Integer.parseInt(input_quantity.getText()), Integer.parseInt(comboxProvider.getValue().substring(0,1)), Integer.parseInt(comboxFather.getValue().substring(0, 1)))) {
    			alert = new Alert(AlertType.INFORMATION, "Producto guardado", ButtonType.OK);
    			alert.showAndWait();
    			Index indexProducts = new Index();
    			indexProducts.showView(event);
    		} else {
    			alert = new Alert(AlertType.ERROR, "Ocurrio un error", ButtonType.OK);
    			alert.showAndWait();
    		}
    	}
    }

	@FXML
	void btnUploadImage(MouseEvent event) {
		stage = new Stage();
		fileChooser = new FileChooser();
		fileChooser.setTitle("Buscar imagen para el nuevo producto");
		fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("All Images", "*.*"));
		imgFile = fileChooser.showOpenDialog(stage);
		if (imgFile != null) {
			Image image = new Image("file:" + imgFile.getAbsolutePath());
			imgView.setImage(image);
			band = true;
		} else {
			Alert alert = new Alert(AlertType.ERROR, "Debe de seleccionar una imagen", ButtonType.OK);
			alert.showAndWait();
		}
	}
	
	private String saveImage(File imgFile) throws IOException {
    	String name = generateRandomString(8);
    	String pathDestination = System.getProperty("user.dir") + "\\src\\assets\\images\\products\\"+name+".jpg";
    	Path copiedFile = Files.copy(FileSystems.getDefault().getPath(imgFile.getAbsolutePath()), FileSystems.getDefault().getPath(pathDestination), StandardCopyOption.REPLACE_EXISTING);
    	if (Files.exists(copiedFile)) {
    		return name;
    	} else {
    		Alert alert = new Alert(AlertType.ERROR, "Ocurrio un error al guardar la imagen", ButtonType.CLOSE);
    		alert.showAndWait();
    	}
    	return null;
    }

	private static String generateRandomString(int length) {
		if (length < 1)
			throw new IllegalArgumentException();
		StringBuilder sb = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			// 0-62 (exclusive), random returns 0-61
			int rndCharAt = random.nextInt(DATA_FOR_RANDOM_STRING.length());
			char rndChar = DATA_FOR_RANDOM_STRING.charAt(rndCharAt);
			// debug
			sb.append(rndChar);
		}
		return sb.toString();
	}
	
	public void showView(Event event) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("../../../views/Product/post.fxml"));
		Scene scene = new Scene(root);
		Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		appStage.getIcons().add(new Image("/assets/images/legado_farmacia.png"));
		appStage.setScene(scene);
		appStage.toFront();
		appStage.show();
	}
}
