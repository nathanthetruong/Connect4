package application;

import java.util.ArrayList;

import application.SampleController.SpotData;
import javafx.scene.control.Button;

public class ThoughtfulAI {
	private int lastRow;
	private int lastCol;
	
	public ThoughtfulAI() {
		lastRow = 0;
		lastCol = 0;
	}
	
	// Randomized Moves by the AI
	public ArrayList<ArrayList<SpotData>> thoughtfulAiMove(boolean winState, String currentTurn, ArrayList<Button> spotButtons, ArrayList<ArrayList<SpotData>> spots) {
		// Nothing happens if the board is in a Win State
		if(!winState) {
			if(horizontalPriority1(spots, currentTurn)) {
				spots.get(lastRow).get(lastCol).setSpotState(currentTurn);
				spotUpdate(lastRow, lastCol, currentTurn, spotButtons);
			}
			else if(verticalPriority1(spots, currentTurn)) {
				spots.get(lastRow).get(lastCol).setSpotState(currentTurn);
				spotUpdate(lastRow, lastCol, currentTurn, spotButtons);
			}
			else if(forwardPriority1(spots, currentTurn)) {
				spots.get(lastRow).get(lastCol).setSpotState(currentTurn);
				spotUpdate(lastRow, lastCol, currentTurn, spotButtons);
			}
			else if(backwardPriority1(spots, currentTurn)) {
				spots.get(lastRow).get(lastCol).setSpotState(currentTurn);
				spotUpdate(lastRow, lastCol, currentTurn, spotButtons);
			}
			else { // Random Move
				randomAiMove(winState, currentTurn, spotButtons, spots);
			}
		}
		return spots;
	}
	
	public void spotUpdate(int row, int col, String currentTurn, ArrayList<Button> spotButtons) {
		int spotPosition = (lastRow * 7) + lastCol;
		Button tempSpot = spotButtons.get(spotPosition);
		
		// Handles changing colors
		if(currentTurn.equals("Yellow")) {
			tempSpot.setStyle("-fx-background-color: yellow; -fx-background-radius: 37.5; -fx-pref-height: 75; -fx-pref-width: 75");
		}
		else {
			tempSpot.setStyle("-fx-background-color: red; -fx-background-radius: 37.5; -fx-pref-height: 75; -fx-pref-width: 75");
		}
	}
	
	// Case where there is a potential win for AI horizontally
	public boolean horizontalPriority1(ArrayList<ArrayList<SpotData>> spots, String currentTurn) {
		for(int row = 5; row > -1; row--) {
			for(int col = 0; col < 7; col++) {
				if((spots.get(row).get(col).getSpotState()).equals("empty") && aiValidPlay(row+1, col+1, spots)) {
					spots.get(row).get(col).setSpotState(currentTurn);
					if(winCheckHorizontal(row, spots)) {
						lastRow = row;
						lastCol = col;
						return true;	
					}
					spots.get(row).get(col).setSpotState("empty");
				}
			}
		}
		
		return false;
	}
	
	// Case where there is a potential win for AI vertically
	public boolean verticalPriority1(ArrayList<ArrayList<SpotData>> spots, String currentTurn) {
		for(int col = 0; col < 7; col++) {
			for(int row = 5; row > -1; row--) {
				if((spots.get(row).get(col).getSpotState()).equals("empty") && aiValidPlay(row+1, col+1, spots)) {
					spots.get(row).get(col).setSpotState(currentTurn);
					if(winCheckVertical(col, spots)) {
						lastRow = row;
						lastCol = col;
						return true;	
					}
					spots.get(row).get(col).setSpotState("empty");
				}
			}
		}
		
		return false;
	}
	
	// Case where there is a potential win for AI in the forward diagonal
	public boolean forwardPriority1(ArrayList<ArrayList<SpotData>> spots, String currentTurn) {
//		System.out.println("FOR");
		// Handles case where there is a 3 in a forward diagonal to win for the first half
		for(int row = 3; row < 6; row++) {
			int tempRow = row;
			int col = 0;
			
			while(tempRow > -1 && col < 7) {
				if((spots.get(tempRow).get(col).getSpotState()).equals("empty") && aiValidPlay(tempRow+1, col+1, spots)
						&& winCheckForwardDiagonal(tempRow, col, spots)) {
					lastRow = tempRow;
					lastCol = col;
					return true;
				}
				tempRow--;
				col++;
			}
		}
		
		// Handles case where there is a 3 in a forward diagonal to win for the second half
		for(int col = 1; col < 4; col++) {
			int tempCol = col;
			int row = 5;
			
			while(row > -1 && tempCol < 7) {
				if((spots.get(row).get(tempCol).getSpotState()).equals("empty") && aiValidPlay(row+1, tempCol+1, spots)
						&& winCheckForwardDiagonal(row, tempCol, spots)) {
					lastRow = row;
					lastCol = tempCol;
					return true;
				}	
				row--;
				tempCol++;
			}
		}
		
		return false;
	}
	
	// Case where there is a potential win for AI in the backward diagonal
	public boolean backwardPriority1(ArrayList<ArrayList<SpotData>> spots, String currentTurn) {
//		System.out.println("BAC");
		// Handles case where there is a 3 in a forward diagonal to win for the first half
		for(int row = 3; row < 6; row++) {
			int tempRow = row;
			int col = 6;
			
			while(tempRow > -1 && col > -1) {
//				System.out.println("ROW: " + Integer.toString(tempRow) + "; COL: " + Integer.toString(col));
				if((spots.get(tempRow).get(col).getSpotState()).equals("empty") && aiValidPlay(tempRow+1, col+1, spots)
						&& winCheckBackwardDiagonal(tempRow, col, spots)) {
					lastRow = tempRow;
					lastCol = col;
					return true;
				}
				tempRow--;
				col--;
			}
		}

		// Handles case where there is a 3 in a backward diagonal to win for the second half
		for(int col = 5; col > 2; col--) {
			int tempCol = col;
			int row = 5;
			
			while(row > -1 && tempCol > -1) {
//				System.out.println("ROW: " + Integer.toString(row) + "; COL: " + Integer.toString(tempCol));
				if((spots.get(row).get(tempCol).getSpotState()).equals("empty") && aiValidPlay(row+1, tempCol+1, spots)
						&& winCheckBackwardDiagonal(row, tempCol, spots)) {
					lastRow = row;
					lastCol = tempCol;
					return true;
				}	
				row--;
				tempCol--;
			}
		}
		
		return false;
	}
	
	// Randomized Moves by the AI
	public ArrayList<ArrayList<SpotData>> randomAiMove(boolean winState, String currentTurn, ArrayList<Button> spotButtons, ArrayList<ArrayList<SpotData>> spots) {
		System.out.println("RAN");
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

			lastRow = randomRow - 1;
			lastCol = randomCol - 1;
			
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
			if(counter >= 4) {
//				System.out.println("PASS HORIZONTAL");
				return true;
			}
//			System.out.println("COUNTER " + Integer.toString(row + 1) + Integer.toString(col + 1) + ": " + Integer.toString(counter));
		}
		return false;
	}
	
	// Checks for wins in the vertical
	public boolean winCheckVertical(int col, ArrayList<ArrayList<SpotData>> spots) {
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
		}
		return false;
	}
	
	// Checks for wins in the diagonal (Bottom Left to Top Right)
	public boolean winCheckForwardDiagonal(int row, int col, ArrayList<ArrayList<SpotData>> spots) {
		String currentState = "empty";
		int counter = 0;
		
		// Starts at the farthest Bottom left possible in the diagonal that contains the recent spot
		while(row < 5 && col > 0) {
			row++;
			col--;
		}
		
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
	public boolean winCheckBackwardDiagonal(int row, int col, ArrayList<ArrayList<SpotData>> spots) {
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
