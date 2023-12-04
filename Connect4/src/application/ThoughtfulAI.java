package application;

import java.util.ArrayList;

import application.SampleController.SpotData;
import javafx.scene.control.Button;

public class ThoughtfulAI {
	private int lastRow;
	private int lastCol;
	private int horizontalCol;
	
	public ThoughtfulAI() {
		lastRow = 0;
		lastCol = 0;
		horizontalCol = 0;
	}
	
	// Randomized Moves by the AI
	public ArrayList<ArrayList<SpotData>> thoughtfulAiMove(boolean winState, String currentTurn, ArrayList<Button> spotButtons, ArrayList<ArrayList<SpotData>> spots) {
		// Nothing happens if the board is in a Win State
		if(!winState) {

		}
		return spots;
	}
	
	public boolean horizontalMoveCheck1(ArrayList<ArrayList<SpotData>> spots, String currentTurn) {
		int counter = 0;
		int emptyCounter = 0;
		
		// Handles case where there is 3 in a row to win
		for(int row = 5; row > -1; row--) {
			for(int col = 0; col < 7; col++) {
				// Handles finding if there is a row with 3 spots
				if((spots.get(row).get(col).getSpotState()).equals(currentTurn)) {
					counter++;
					emptyCounter = 0;
				}
				else if((spots.get(row).get(col).getSpotState()).equals("empty")) {
					emptyCounter++;
					if(emptyCounter > 1) {
						counter = 0;
					}
				}
				else {
					counter = 0;
					emptyCounter = 0;
				}
				
				// Checks if there are any winning moves
				if(counter + emptyCounter >= 4) {
					if(winCheckHorizontal(row, spots)) {
						lastRow = row;
						lastCol = horizontalCol;
						return true;
					}
					
				}
			}
		}
		
		return false;
		
//		int counter = 0;
//		int maxCounter = 0;
//		int rowCounter = 0;
//		horizontalRow = 0;
//		
//		for(int row = 0; row < 6; row++) {
//			for(int col = 0; col < 7; col++) {
//				// Handles counting the maximum possible number of moves in that row
//				if((spots.get(row).get(col).getSpotState()).equals(currentTurn) || (spots.get(row).get(col).getSpotState()).equals("empty")) {
//					counter++;
//					if(maxCounter < counter) {
//						maxCounter = counter;
//						horizontalRow = row;
//					}
//				}
//				else {
//					counter = 0;
//				}
//				
//				// Handles counting the highest amount of the current turn's spots are in a row in the row
//				if((spots.get(row).get(col).getSpotState()).equals(currentTurn)) {
//					rowCounter++;
//					if(currentHorizontalCount)
//				}
//			}
//			counter = 0;
//		}
//		
//		return maxCounter;
	}
	
	public boolean verticalMoveCheck(ArrayList<ArrayList<SpotData>> spots, String currentTurn) {
		return false;
	}
	
	public boolean aiValidPlay(int row, int col, ArrayList<ArrayList<SpotData>> spots) {
		// Checks for if the spot is already taken by a player
		if(!(spots.get(row-1).get(col-1).getSpotState()).equals("empty")) {
			return false;
		}
		
		// Checks for if the spot isn't at the lowest position possible
		if(row-1 < 5) {
			if(spots.get(row).get(col-1).getSpotState().equals("empty")) {
				return false;
			}
		}
		
		return true;
	}
	
	// Checks for wins in the horizontal
	public boolean winCheckHorizontal(int row, ArrayList<ArrayList<SpotData>> spots) {
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
			if(counter >= 4 && aiValidPlay(row, col, spots)) {
//				System.out.println("PASS HORIZONTAL");
				horizontalCol = col;
				return true;
			}
//				System.out.println("COUNTER " + Integer.toString(row + 1) + Integer.toString(col + 1) + ": " + Integer.toString(counter));
		}
		return false;
	}
	
	public String aiLogOutputParser(int row, int col, int turnCounter, String currentTurn) {
		String output = "Turn " + Integer.toString(turnCounter) + " (AI): ";
		if(currentTurn.equals("Yellow")) {
			output = output + "Red at Row ";
		}
		else {
			output = output + "Yellow at Row ";
		}
		output = output + Integer.toString(row) + ": Col " + Integer.toString(col);
		
		return output;
	}
	
	public int getLastRow() {
		return lastRow;
	}
	
	public int getLastCol() {
		return lastCol;
	}
}
