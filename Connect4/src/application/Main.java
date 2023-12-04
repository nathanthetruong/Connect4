package application;
	
import java.util.ArrayList;

import application.SampleController.SpotData;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	private ArrayList<ArrayList<SpotData>> spots;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("Sample.fxml"));
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// Loads in all the default values of the Spots list
	public ArrayList<ArrayList<SpotData>> loadAllSpots() {
		ArrayList<ArrayList<SpotData>> newSpots = new ArrayList<ArrayList<SpotData>>();
		
		for(int row = 0; row < 6; row++) {
			newSpots.add(new ArrayList<SpotData>());
			for(int col = 0; col < 7; col++) {
				SpotData newSpot = new SpotData(row + 1, col + 1, "empty");
				newSpots.get(row).add(newSpot);
				System.out.println("TEST: spot" + Integer.toString(row+1) + Integer.toString(col+1));
			}
		}
		return newSpots;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
