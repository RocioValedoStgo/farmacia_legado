package farmacia_legado;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Label label = new Label("Connection to MySQL: " + MySQLConnection.getConnection());
		Group root = new Group();
		root.getChildren().addAll(label);
		label.setLayoutX(120);
		label.setLayoutY(200);
		Scene scene = new Scene(root, 640, 400);
		primaryStage.setScene(scene);
		primaryStage.getIcons().add(new Image("/assets/images/legado_farmacia.png"));
		primaryStage.setTitle("Farmacia Legado");
		primaryStage.show();
	}
}
