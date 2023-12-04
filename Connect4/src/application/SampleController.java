package application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import java.util.*;

import application.SampleController.SpotData;

public class SampleController {
	// Instantiates all the buttons
	@FXML Button spot11;
	@FXML Button spot12;
	@FXML Button spot13;
	@FXML Button spot14;
	@FXML Button spot15;
	@FXML Button spot16;
	@FXML Button spot17;
	@FXML Button spot21;
	@FXML Button spot22;
	@FXML Button spot23;
	@FXML Button spot24;
	@FXML Button spot25;
	@FXML Button spot26;
	@FXML Button spot27;
	@FXML Button spot31;
	@FXML Button spot32;
	@FXML Button spot33;
	@FXML Button spot34;
	@FXML Button spot35;
	@FXML Button spot36;
	@FXML Button spot37;
	@FXML Button spot41;
	@FXML Button spot42;
	@FXML Button spot43;
	@FXML Button spot44;
	@FXML Button spot45;
	@FXML Button spot46;
	@FXML Button spot47;
	@FXML Button spot51;
	@FXML Button spot52;
	@FXML Button spot53;
	@FXML Button spot54;
	@FXML Button spot55;
	@FXML Button spot56;
	@FXML Button spot57;
	@FXML Button spot61;
	@FXML Button spot62;
	@FXML Button spot63;
	@FXML Button spot64;
	@FXML Button spot65;
	@FXML Button spot66;
	@FXML Button spot67;
	@FXML Button resetButton;
	
	// Instantiates variables for functionality
	ArrayList<ArrayList<SpotData>> spots = loadAllSpots();
	boolean winState = false;
	boolean thoughtfulAI = false;
	String currentTurn = "Yellow";
	final int CHAR_TO_INT = 48;
	int turnCounter = 0;
	
	// Handles the data of each spot's location
	public static class SpotData {
		private String spotId;
		private String spotState;
		
		public SpotData(int rowId, int colId, String state) {
			spotId = "spot" + Integer.toString(rowId) + Integer.toString(colId);
			spotState = state;
		}
		
		public String getSpotId() {
			return spotId;
		}
		
		public String getSpotState() {
			return spotState;
		}
		
		public void setSpotState(String newState) {
			spotState = newState;
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
			}
		}
		return newSpots;
	}
	
	// Prints out the ArrayList of Spots ID
	public void printTestSpotsId() {
		for(int row = 0; row < 6; row++) {
			for(int col = 0; col < 7; col++) {
				String output = (spots.get(row).get(col)).getSpotId();
				System.out.println(output);
			}
		}
	}
	
	// Prints out the ArrayList of Spots State
	public void printTestSpotsState() {
		for(int row = 0; row < 6; row++) {
			for(int col = 0; col < 7; col++) {
				String output1 = (spots.get(row).get(col)).getSpotId();
				String output2 = (spots.get(row).get(col)).getSpotState();
				System.out.println(output1 + ": " + output2);
			}
		}
		System.out.println("");
	}
	
	// Deals with Presses of a Spot
	@FXML
	public void spotPress(ActionEvent event) {
		Button spotButton = (Button) event.getSource();
		
		// Handles changing the current spot's state
		String spotButtonId = spotButton.getId();
		int row = (int)spotButtonId.charAt(4) - CHAR_TO_INT;
		int col = (int)spotButtonId.charAt(5) - CHAR_TO_INT;

		if(!validPlay(row, col)) {
			return;
		}
		
		turnCounter++;
		spots.get(row-1).get(col-1).setSpotState(currentTurn);
		
		// Handles changing turns
		if(currentTurn.equals("Yellow")) {
			spotButton.setStyle("-fx-background-color: yellow; -fx-background-radius: 37.5; -fx-pref-height: 75; -fx-pref-width: 75");
			currentTurn = "Red";
		}
		else {
			spotButton.setStyle("-fx-background-color: red; -fx-background-radius: 37.5; -fx-pref-height: 75; -fx-pref-width: 75");
			currentTurn = "Yellow";
		}
		
		winState = winCheck();
	}

	public boolean validPlay(int row, int col) {
		// Checks for if there is a winner already
		if(winState == true) {
			if(currentTurn.equals("Yellow")) {
				System.out.println("\nRed has already won!\nPress \"Restart Board\" to play again.\n");
			}
			else {
				System.out.println("\nYellow has already won!\nPress \"Restart Board\" to play again.\n");
			}
			return false;
		}
		
		// Checks for if the spot is already taken by a player
		if(!(spots.get(row-1).get(col-1).getSpotState()).equals("empty")) {
			System.out.println("\nALREADY TAKEN\n");
			return false;
		}
		
		// Checks for if the spot isn't at the lowest position possible
		if(row-1 != 5) {
			if(spots.get(row).get(col-1).getSpotState().equals("empty")) {
				System.out.println("\nINVALID SPOT, THERE ARE OPEN SPOTS BENEATH THIS ONE.\n");
				return false;
			}
		}
		
		return true;
	}
	
	// Checks for wins in the horizontal
	public boolean winCheckHorizontal() {
		String currentState = "empty";
		int counter = 0;
		
		for(int row = 0; row < 6; row++) {
			for(int col = 0; col < 7; col++) {
				if(counter == 4) {
					return true;
				}
				if(!(spots.get(row).get(col).getSpotState()).equals(currentState)) {
					currentState = spots.get(row).get(col).getSpotState();
					if(currentState.equals("Yellow") || currentState.equals("Red")) {
						counter = 1;
					}
					else {
						counter = 0;
					}
				}
				else if(!currentState.equals("empty")) {
					counter++;
				}
			}
		}
		return false;
	}
	
	// Checks for wins in the vertical
	public boolean winCheckVertical() {
		String currentState = "empty";
		int counter = 0;
		
		for(int col = 0; col < 7; col++) {
			for(int row = 0; row < 6; row++) {
				if(counter == 4) {
					return true;
				}
				if(!(spots.get(row).get(col).getSpotState()).equals(currentState)) {
					currentState = spots.get(row).get(col).getSpotState();
					if(currentState.equals("Yellow") || currentState.equals("Red")) {
						counter = 1;
					}
					else {
						counter = 0;
					}
				}
				else if(!currentState.equals("empty")) {
					counter++;
				}
			}
		}
		return false;
	}
	
	// Checks for wins in the diagonal (Bottom Left to Top Right)
	public boolean winCheckForwardDiagonal() {
		String currentState = "empty";
		int counter = 0;
		int rowTemp = 3;
		int colTemp;
		int col = 0;
		
		while(col < 4) {
			colTemp = col;
			for(int row = rowTemp; row > -1; row--) {
				if(counter == 4) {
					return true;
				}
				if(!(spots.get(row).get(colTemp).getSpotState()).equals(currentState)) {
					currentState = spots.get(row).get(colTemp).getSpotState();
					if(currentState.equals("Yellow") || currentState.equals("Red")) {
						counter = 1;
					}
					else {
						counter = 0;
					}
				}
				else if(!currentState.equals("empty")) {
					counter++;
				}
				colTemp++;
			}
			if(rowTemp < 6) {
				rowTemp++;	
			}
			else {
				col++;
			}
		}
		return false;
	}
	
	// Calls all the different win checks at once
	public boolean winCheck() {
		if(winCheckHorizontal() || winCheckVertical()) {
			return true;
		}
		
		if(winCheckForwardDiagonal()) {
			return true;
		}
		
		return false;
	}
	
	// Resets the color of the Spot
	public void spotReset(Button spot) {
		spot.setStyle("-fx-background-color: white; -fx-background-radius: 37.5; -fx-pref-height: 75; -fx-pref-width: 75");
	}
	
	// Resets the entire board when pressed
	@FXML
	public void resetPress(ActionEvent event) {
		spotReset(spot11);
		spotReset(spot12);
		spotReset(spot13);
		spotReset(spot14);
		spotReset(spot15);
		spotReset(spot16);
		spotReset(spot17);
		
		spotReset(spot21);
		spotReset(spot22);
		spotReset(spot23);
		spotReset(spot24);
		spotReset(spot25);
		spotReset(spot26);
		spotReset(spot27);
		
		spotReset(spot31);
		spotReset(spot32);
		spotReset(spot33);
		spotReset(spot34);
		spotReset(spot35);
		spotReset(spot36);
		spotReset(spot37);
		
		spotReset(spot41);
		spotReset(spot42);
		spotReset(spot43);
		spotReset(spot44);
		spotReset(spot45);
		spotReset(spot46);
		spotReset(spot47);
		
		spotReset(spot51);
		spotReset(spot52);
		spotReset(spot53);
		spotReset(spot54);
		spotReset(spot55);
		spotReset(spot56);
		spotReset(spot57);
		
		spotReset(spot61);
		spotReset(spot62);
		spotReset(spot63);
		spotReset(spot64);
		spotReset(spot65);
		spotReset(spot66);
		spotReset(spot67);
		
		spots = loadAllSpots();
		winState = false;
		currentTurn = "Yellow";
		thoughtfulAI = false;
		turnCounter = 0;
	}
}
