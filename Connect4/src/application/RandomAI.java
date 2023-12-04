package application;

import java.util.ArrayList;

import application.SampleController.SpotData;
import javafx.scene.control.Button;

public class RandomAI {
	private int lastRow;
	private int lastCol;
	
	public RandomAI() {
		lastRow = 0;
		lastCol = 0;
	}
	
	// Randomized Moves by the AI
	public ArrayList<ArrayList<SpotData>> randomAiMove(boolean winState, String currentTurn, ArrayList<Button> spotButtons, ArrayList<ArrayList<SpotData>> spots) {
		// Nothing happens if the board is in a Win State
		if(!winState) {
			int randomRow = (int)((Math.random() * 6) + 1);
			int randomCol = (int)((Math.random() * 7) + 1);
//			System.out.println("ROW: " + Integer.toString(randomRow) + "; Col: " + Integer.toString(randomCol));
			
			// Regenerates a new spot if the generated one is filled
			while(!aiValidPlay(randomRow, randomCol, spots) && randomRow < 7 && randomCol < 8) {
//				System.out.println("ROW: " + Integer.toString(randomRow) + "; Col: " + Integer.toString(randomCol));
				randomRow = (int)((Math.random() * 6) + 1);
				randomCol = (int)((Math.random() * 7) + 1);
			}
			int spotPosition = ((randomRow - 1) * 7) + (randomCol - 1);
//			System.out.println("SPOT POSITION: " + Integer.toString(spotPosition));
			Button tempSpot = spotButtons.get(spotPosition);
			
//			System.out.println(spotButtons.get(spotPosition).getId());
			
			spots.get(randomRow-1).get(randomCol-1).setSpotState(currentTurn);
			
			// Handles changing turns
			if(currentTurn.equals("Yellow")) {
				tempSpot.setStyle("-fx-background-color: yellow; -fx-background-radius: 37.5; -fx-pref-height: 75; -fx-pref-width: 75");
			}
			else {
				tempSpot.setStyle("-fx-background-color: red; -fx-background-radius: 37.5; -fx-pref-height: 75; -fx-pref-width: 75");
			}

			lastRow = randomRow;
			lastCol = randomCol;
			
//			System.out.println(logOutputParser(randomRow, randomCol));
//			winState = winCheck(randomRow-1, randomCol-1);
		}
		return spots;
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
