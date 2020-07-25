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
import farmacia_legado.Models.Product;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
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

	private static int pkProduct;
	private boolean band = false;
	private Stage stage;
	private File imgFile;
	private FileChooser fileChooser;
	private static final String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
	private static final String CHAR_UPPER = CHAR_LOWER.toUpperCase();
	private static final String NUMBER = "0123456789";
	private static final String DATA_FOR_RANDOM_STRING = CHAR_LOWER + CHAR_UPPER + NUMBER;
	private static SecureRandom random = new SecureRandom();
	private static String nameImage;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		menuButtonNavbar.setText(MySQLConnection.User_username);
		MySQLConnection MySQL = new MySQLConnection();
		try {
			Product product = MySQL.getProduct(getPkProduct());
			titlePage.setText("Editando la categoría: " + product.getName());
			input_name.setText(product.getName());
			input_price.setText(String.valueOf(product.getPrice()));
			input_quantity.setText(String.valueOf(product.getQuantity()));
			textArea_description.setText(product.getDescription());
			comboxFather.setValue(String.valueOf(product.getCategory_Id()));
			comboxFather.getItems().addAll(MySQL.getCategories());
			comboxProvider.setValue(String.valueOf(product.getProvider_Id()));
			comboxProvider.getItems().addAll(MySQL.getProviders());
			String pathImg = System.getProperty("user.dir") + "\\src\\assets\\images\\products\\" + product.getImage()
					+ ".jpg";
			nameImage = product.getImage();
			File imgFile = new File(pathImg);
			Image image = new Image("file:" + imgFile.getAbsolutePath());
			imgView.setImage(image);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void btnBack(MouseEvent event) throws Exception {
		farmacia_legado.Controllers.Product.Index indexProducts = new farmacia_legado.Controllers.Product.Index();
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
	void btnSave(MouseEvent event) throws NumberFormatException, SQLException, Exception {
		Alert alert;
		if (input_name.getText().equals(null) || input_price.getText().equals(null) || input_quantity.getText().equals(null) || textArea_description.getText().equals(null) || comboxFather.getValue().equals(null)) {
			alert = new Alert(AlertType.ERROR, "No es posible dejar los campos vacios", ButtonType.OK);
			alert.showAndWait();
		} else {
			MySQLConnection MySQL = new MySQLConnection();
			if (band) {
				destroyImage(nameImage);
				String imageName = saveImage(imgFile);
				if (MySQL.editProductImage(getPkProduct(), input_name.getText(), textArea_description.getText(), Float.parseFloat(input_price.getText()), Integer.parseInt(input_quantity.getText()), Integer.parseInt(comboxProvider.getValue().substring(0, 1)), Integer.parseInt(comboxFather.getValue().substring(0,1)), imageName) == 1) {
					alert = new Alert(AlertType.INFORMATION, "Producto editado con exito!", ButtonType.OK);
					alert.showAndWait();
					farmacia_legado.Controllers.Product.Profile profileProduct = new farmacia_legado.Controllers.Product.Profile();
					profileProduct.setPkProduct(getPkProduct());
					profileProduct.showView(event);
				} else {
					alert = new Alert(AlertType.ERROR, "Ocurrio un error al editar el producto", ButtonType.OK);
					alert.showAndWait();
				}
			} else {
				if (MySQL.editProduct(getPkProduct(), input_name.getText(), textArea_description.getText(), Float.parseFloat(input_price.getText()), Integer.parseInt(input_quantity.getText()), Integer.parseInt(comboxProvider.getValue().substring(0, 1)), Integer.parseInt(comboxFather.getValue())) == 1) {
					alert = new Alert(AlertType.INFORMATION, "Producto editado con exito!", ButtonType.OK);
					alert.showAndWait();
					farmacia_legado.Controllers.Product.Profile profileProduct = new farmacia_legado.Controllers.Product.Profile();
					profileProduct.setPkProduct(getPkProduct());
					profileProduct.showView(event);
				} else {
					alert = new Alert(AlertType.ERROR, "Ocurrio un error al editar el producto", ButtonType.OK);
					alert.showAndWait();
				}
			}
		}
	}

	@FXML
	void btnUploadImage(MouseEvent event) {
		stage = new Stage();
		fileChooser = new FileChooser();
		fileChooser.setTitle("Buscar imagen para el producto");
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
		String pathDestination = System.getProperty("user.dir") + "\\src\\assets\\images\\products\\" + name + ".jpg";
		Path copiedFile = Files.copy(FileSystems.getDefault().getPath(imgFile.getAbsolutePath()),
				FileSystems.getDefault().getPath(pathDestination), StandardCopyOption.REPLACE_EXISTING);
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

	private boolean destroyImage(String nameFile) throws IOException {
		Alert alert;
		String pathDestination = System.getProperty("user.dir") + "\\src\\assets\\images\\products\\" + nameFile
				+ ".jpg";
		if (Files.deleteIfExists(FileSystems.getDefault().getPath(pathDestination))) {
			alert = new Alert(Alert.AlertType.INFORMATION, "Imagen eliminada");
			alert.showAndWait();
			return true;
		} else {
			alert = new Alert(Alert.AlertType.ERROR, "Ocurrio un error");
			alert.showAndWait();
			return false;
		}
	}

	public void showView(Event event) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("../../../views/Product/edit.fxml"));
		Scene scene = new Scene(root);
		Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		appStage.getIcons().add(new Image("/assets/images/legado_farmacia.png"));
		appStage.setScene(scene);
		appStage.toFront();
		appStage.show();
	}

	public int getPkProduct() {
		return pkProduct;
	}

	public void setPkProduct(int pk) {
		Edit.pkProduct = pk;
	}
}
