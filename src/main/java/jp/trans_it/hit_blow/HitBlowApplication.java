package jp.trans_it.hit_blow;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HitBlowApplication extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(MainFrame.class.getResource("MainFrame.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("Hit & Blow");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
