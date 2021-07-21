package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

//import javafx.scene.control.*;

public class BookstoreGUIAPP extends Application {

	@Override
	public void start(Stage primaryStage) {
		FXMLLoader loader = new FXMLLoader();
		try {
			BorderPane root = loader.load(new FileInputStream("src/view/BookstoreView.fxml"));
			primaryStage.setScene(new Scene(root,300,400));
		} catch (FileNotFoundException e) {
			System.out.println("Can't open the fxml file");
		} catch (IOException e) {
			System.out.println("Problem reading fxml file");
			e.printStackTrace();
		}
		primaryStage.show();
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
