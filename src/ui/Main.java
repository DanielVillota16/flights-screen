package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("FlightsScreen.fxml"));
		Scene scene = new Scene(root);
		stage.setTitle("Catch the Pacman");
		stage.setScene(scene);
		stage.show();
	}

}
