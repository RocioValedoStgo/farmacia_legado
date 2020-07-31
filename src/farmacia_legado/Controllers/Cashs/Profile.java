package farmacia_legado.Controllers.Cashs;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import farmacia_legado.Main;
import farmacia_legado.MySQLConnection;
import farmacia_legado.Controllers.HomeController;
import farmacia_legado.Controllers.Sells.Ticket;
import farmacia_legado.Models.Sell;
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
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;

public class Profile implements Initializable {

	@FXML // fx:id="logo"
	private ImageView logo; // Value injected by FXMLLoader

	@FXML // fx:id="titlePage"
	private Label titlePage;
	
	@FXML // fx:id="labelTotal"
	private Label labelTotal;

	@FXML // fx:id="table"
	private TableView<Sell> table; // Value injected by FXMLLoader

	private TableColumn<Sell, Integer> col_id = new TableColumn<Sell, Integer>("ID");

	private TableColumn<Sell, Float> col_total = new TableColumn<Sell, Float>("Total");

	private TableColumn<Sell, String> col_created = new TableColumn<Sell, String>("Hecha el");

	private TableColumn<Sell, String> col_options = new TableColumn<Sell, String>("Opciones");

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

	private static int pkCash;
	private static String dateInitial;
	private static String dateFinal;
	private float auxTotal = 0;

	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		menuButtonNavbar.setText(MySQLConnection.User_username);
		if (getDateFinal().isEmpty())
			titlePage.setText("Ventas del " + getDateInitial().substring(0, 10) + " - actual");
		else
			titlePage.setText(
					"Ventas del " + getDateInitial().substring(0, 10) + " al " + getDateFinal().substring(0, 10));
		col_id.setPrefWidth(50);
		col_id.setStyle("-fx-aligment: CENTER;");
		col_id.setStyle("-fx-font-size: 15px");
		col_total.setPrefWidth(150);
		col_total.setStyle("-fx-aligment: CENTER;");
		col_total.setStyle("-fx-font-size: 15px");
		col_created.setPrefWidth(150);
		col_created.setStyle("-fx-aligment: CENTER;");
		col_created.setStyle("-fx-font-size: 15px");
		table.getColumns().addAll(col_id, col_total, col_created, col_options);
		addButtonShow();
		col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
		col_total.setCellValueFactory(new PropertyValueFactory<>("total"));
		col_created.setCellValueFactory(new PropertyValueFactory<>("created"));
		MySQLConnection MySQL = new MySQLConnection();
		try {
			table.setItems(MySQL.getSells(getPkCash()));
			for (Sell item : table.getItems()) {
				auxTotal = auxTotal + col_total.getCellObservableValue(item).getValue();
			}
			labelTotal.setText("Venta total: $"+auxTotal);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void addButtonShow() {
		TableColumn<Sell, Void> colBtn = new TableColumn<Sell, Void>();
		Callback<TableColumn<Sell, Void>, TableCell<Sell, Void>> cellFactory = new Callback<TableColumn<Sell, Void>, TableCell<Sell, Void>>() {
			@Override
			public TableCell<Sell, Void> call(final TableColumn<Sell, Void> param) {
				final TableCell<Sell, Void> cell = new TableCell<Sell, Void>() {
					private final Button btn = new Button("Ver");
					{
						btn.setOnAction((ActionEvent event) -> {
							Sell sell = getTableView().getItems().get(getIndex());
							Ticket.setPkSell(sell.getId());
							Ticket ticket = new Ticket();
							try {
								ticket.showView(event);
							} catch (Exception e) {
								e.printStackTrace();
							}
						});
					}

					@Override
					public void updateItem(Void item, boolean empty) {
						super.updateItem(item, empty);
						if (empty) {
							setGraphic(null);
						} else {
							setGraphic(btn);
						}
					}
				};
				return cell;
			}
		};
		colBtn.setCellFactory(cellFactory);
		col_options.getColumns().add(colBtn);
	}

	@FXML
	void btnBack(MouseEvent event) throws Exception {
		Index indexCashs = new Index();
		indexCashs.showView(event);
	}

	@FXML
	void btnExport(MouseEvent event) {

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

	public void showView(Event event) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("../../../views/Cash Register/show.fxml"));
		Scene scene = new Scene(root);
		Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		appStage.getIcons().add(new Image("/assets/images/legado_farmacia.png"));
		appStage.setScene(scene);
		appStage.toFront();
		appStage.show();
	}

	/**
	 * @return the pkCash
	 */
	public static int getPkCash() {
		return pkCash;
	}

	/**
	 * @param pkCash the pkCash to set
	 */
	public static void setPkCash(int pkCash) {
		Profile.pkCash = pkCash;
	}

	/**
	 * @return the dateInitial
	 */
	public static String getDateInitial() {
		return dateInitial;
	}

	/**
	 * @param dateInitial the dateInitial to set
	 */
	public static void setDateInitial(String dateInitial) {
		Profile.dateInitial = dateInitial;
	}

	/**
	 * @return the dateFinal
	 */
	public static String getDateFinal() {
		return dateFinal;
	}

	/**
	 * @param dateFinal the dateFinal to set
	 */
	public static void setDateFinal(String dateFinal) {
		Profile.dateFinal = dateFinal;
	}
}