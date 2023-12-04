package application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.util.*;

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
	@FXML Button aiButton;
	@FXML Label aiLabel;
	
	// Instantiates variables for functionality
	ArrayList<ArrayList<SpotData>> spots = loadAllSpots();
	boolean winState = false;
	boolean thoughtfulAIOn = false;
	String currentTurn = "Yellow";
	final int CHAR_TO_INT = 48;
	int turnCounter = 0;
	public ArrayList<Button> spotButtons;
	RandomAI randomAi = new RandomAI();
	
	@FXML
	public void initialize() {
		initializeSpotArray();
	}
	
	// Initializes Array of Spot Buttons for the AI to control their properties
	public void initializeSpotArray() {
		spotButtons = new ArrayList<Button>();
		spotButtons.add(spot11);
		spotButtons.add(spot12);
		spotButtons.add(spot13);
		spotButtons.add(spot14);
		spotButtons.add(spot15);
		spotButtons.add(spot16);
		spotButtons.add(spot17);
		
		spotButtons.add(spot21);
		spotButtons.add(spot22);
		spotButtons.add(spot23);
		spotButtons.add(spot24);
		spotButtons.add(spot25);
		spotButtons.add(spot26);
		spotButtons.add(spot27);
		
		spotButtons.add(spot31);
		spotButtons.add(spot32);
		spotButtons.add(spot33);
		spotButtons.add(spot34);
		spotButtons.add(spot35);
		spotButtons.add(spot36);
		spotButtons.add(spot37);
		
		spotButtons.add(spot41);
		spotButtons.add(spot42);
		spotButtons.add(spot43);
		spotButtons.add(spot44);
		spotButtons.add(spot45);
		spotButtons.add(spot46);
		spotButtons.add(spot47);
		
		spotButtons.add(spot51);
		spotButtons.add(spot52);
		spotButtons.add(spot53);
		spotButtons.add(spot54);
		spotButtons.add(spot55);
		spotButtons.add(spot56);
		spotButtons.add(spot57);
		
		spotButtons.add(spot61);
		spotButtons.add(spot62);
		spotButtons.add(spot63);
		spotButtons.add(spot64);
		spotButtons.add(spot65);
		spotButtons.add(spot66);
		spotButtons.add(spot67);
	}
	
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
		
		System.out.println(logOutputParser(row, col));
		winState = winCheck(row-1, col-1);
		if(winState) {
			if(currentTurn.equals("Yellow")) {
				System.out.println("Red Wins!");
			}
			else {
				System.out.println("Yellow Wins!");
			}
		}
		
		// When the first move is played, whatever AI setting is selected is locked in until reset
		aiButton.setDisable(true);
		
		// Chooses between thoughtful and random AI
		if(thoughtfulAIOn) {
			
		}
		else {
			//randomAi();
			spots = randomAi.randomAiMove(winState, currentTurn, spotButtons, spots);
			int lastRow = randomAi.getLastRow();
			int lastCol = randomAi.getLastCol();
			turnCounter++;
			// Handles changing turns for AI
			if(currentTurn.equals("Yellow")) {
				currentTurn = "Red";
			}
			else {
				currentTurn = "Yellow";
			}
			System.out.println(randomAi.aiLogOutputParser(lastRow, lastCol, turnCounter, currentTurn));
			winState = winCheck(lastRow-1, lastCol-1);
		}
	}
	
	// Handles the Thoughtful AI Toggle button
	public void aiToggle(ActionEvent event) {
		if(thoughtfulAIOn) {
			thoughtfulAIOn = false;
			aiButton.setText("Turn On Thoughtful AI");
			aiLabel.setText("Thoughtful AI: Off");
		}
		else {
			thoughtfulAIOn = true;
			aiButton.setText("Turn Off Thoughtful AI");
			aiLabel.setText("Thoughtful AI: On");
		}
	}
	
//	// Randomized Moves by the AI
//	public void randomAi() {
//		// Nothing happens if the board is in a Win State
//		if(!winState) {
//			int randomRow = (int)((Math.random() * 6) + 1);
//			int randomCol = (int)((Math.random() * 7) + 1);
////			System.out.println("ROW: " + Integer.toString(randomRow) + "; Col: " + Integer.toString(randomCol));
//			
//			// Regenerates a new spot if the generated one is filled
//			while(!aiValidPlay(randomRow, randomCol) && randomRow < 7 && randomCol < 8) {
////				System.out.println("ROW: " + Integer.toString(randomRow) + "; Col: " + Integer.toString(randomCol));
//				randomRow = (int)((Math.random() * 6) + 1);
//				randomCol = (int)((Math.random() * 7) + 1);
//			}
//			int spotPosition = ((randomRow - 1) * 7) + (randomCol - 1);
////			System.out.println("SPOT POSITION: " + Integer.toString(spotPosition));
//			Button tempSpot = spotButtons.get(spotPosition);
//			
////			System.out.println(spotButtons.get(spotPosition).getId());
//			
//			turnCounter++;
//			spots.get(randomRow-1).get(randomCol-1).setSpotState(currentTurn);
//			
//			// Handles changing turns
//			if(currentTurn.equals("Yellow")) {
//				tempSpot.setStyle("-fx-background-color: yellow; -fx-background-radius: 37.5; -fx-pref-height: 75; -fx-pref-width: 75");
//				currentTurn = "Red";
//			}
//			else {
//				tempSpot.setStyle("-fx-background-color: red; -fx-background-radius: 37.5; -fx-pref-height: 75; -fx-pref-width: 75");
//				currentTurn = "Yellow";
//			}
//			
//			System.out.println(logOutputParser(randomRow, randomCol));
//			winState = winCheck(randomRow-1, randomCol-1);
//		}
//	}
	
	public void thoughtfulAi() {
		// Nothing happens if the board is in a Win State
		if(!winState) {
			
		}
	}

//	public boolean aiValidPlay(int row, int col) {
//		// Checks for if the spot is already taken by a player
//		if(!(spots.get(row-1).get(col-1).getSpotState()).equals("empty")) {
//			return false;
//		}
//		
//		// Checks for if the spot isn't at the lowest position possible
//		if(row-1 < 5) {
//			if(spots.get(row).get(col-1).getSpotState().equals("empty")) {
//				return false;
//			}
//		}
//		
//		return true;
//	}
	
	public boolean validPlay(int row, int col) {
//		System.out.println("ROW: " + Integer.toString(row) + "; COL: " + Integer.toString(col));
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
		if(row-1 < 5) {
			if(spots.get(row).get(col-1).getSpotState().equals("empty")) {
				System.out.println("\nINVALID SPOT, THERE ARE OPEN SPOTS BENEATH THIS ONE.\n");
				return false;
			}
		}
		
		return true;
	}
	
	public String logOutputParser(int row, int col) {
		String output = "Turn " + Integer.toString(turnCounter) + ": ";
		if(currentTurn.equals("Yellow")) {
			output = output + "Red at Row ";
		}
		else {
			output = output + "Yellow at Row ";
		}
		output = output + Integer.toString(row) + ": Col " + Integer.toString(col);
		
		return output;
	}
	
	// Checks for wins in the horizontal
	public boolean winCheckHorizontal(int row) {
		String currentState = "empty";
		int counter = 0;
		
		for(int col = 0; col < 7; col++) {
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
			if(counter == 4) {
//				System.out.println("PASS HORIZONTAL");
				return true;
			}
//				System.out.println("COUNTER " + Integer.toString(row + 1) + Integer.toString(col + 1) + ": " + Integer.toString(counter));
		}
		return false;
	}
	
	// Checks for wins in the vertical
	public boolean winCheckVertical(int col) {
		String currentState = "empty";
		int counter = 0;
		
		for(int row = 0; row < 6; row++) {
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
			if(counter == 4) {
//				System.out.println("PASS VERTICAL");
				return true;
			}
//				System.out.println("COUNTER " + Integer.toString(row + 1) + Integer.toString(col + 1) + ": " + Integer.toString(counter));
		}
		return false;
	}
	
	// Checks for wins in the diagonal (Bottom Left to Top Right)
	public boolean winCheckForwardDiagonal(int row, int col) {
		String currentState = "empty";
		int counter = 0;
		
		// Starts at the farthest Bottom left possible in the diagonal that contains the recent spot
		while(row < 5 && col > 0) {
			row++;
			col--;
		}

//		System.out.println("ROW: " + Integer.toString(row) + "; COL: " + Integer.toString(col));
		
		while(row > -1 && col < 7) {
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
			if(counter == 4) {
//				System.out.println("PASS FORWARD");
				return true;
			}
			
			row--;
			col++;
		}
		
		return false;
	}
	
	// Checks for wins in the diagonal (Bottom Right to Top Left)
	public boolean winCheckBackwardDiagonal(int row, int col) {
		String currentState = "empty";
		int counter = 0;
		
		// Starts at the farthest Bottom left possible in the diagonal that contains the recent spot
		while(row < 5 && col < 6) {
			row++;
			col++;
		}

//		System.out.println("ROW: " + Integer.toString(row) + "; COL: " + Integer.toString(col));
		
		while(row > -1 && col > -1) {
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
			if(counter == 4) {
//				System.out.println("PASS FORWARD");
				return true;
			}
			
			row--;
			col--;
		}
		
		return false;
	}
	
	// Calls all the different win checks at once
	public boolean winCheck(int row, int col) {
		if(winCheckHorizontal(row) || winCheckVertical(col)) {
			return true;
		}
		
		if(winCheckForwardDiagonal(row, col) || winCheckBackwardDiagonal(row, col)) {
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
		thoughtfulAIOn = false;
		aiButton.setDisable(false);
		aiLabel.setText("Thoughtful AI: Off");
		turnCounter = 0;
	}
}
