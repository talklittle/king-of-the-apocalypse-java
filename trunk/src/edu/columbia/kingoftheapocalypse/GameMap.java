package edu.columbia.kingoftheapocalypse;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;

import javax.swing.JPanel;

public class GameMap {

	int mWidth, mHeight;
	GridItem[][] mBoard;
	Unit selectedUnit;
	
	GridLayout mapLayout;
    
	public GameMap(File inputFile) {
		try {
			BufferedReader in = new BufferedReader(new FileReader(inputFile));
		
			// first line = width
			mWidth = Integer.parseInt(in.readLine());
			// second line = height
			mHeight = Integer.parseInt(in.readLine());
			
			mapLayout = new GridLayout(mHeight, mWidth);
			mBoard = new GridItem[mHeight][mWidth];
		
			// x starts from 0 at top, to height-1 at bottom
			for (int row = 0; row < mHeight; row++) {
				String line = in.readLine();
				for (int col = 0; col < mWidth; col++) {
					try {
						char inChar = line.charAt(col);
						switch (inChar) {
						// Blank square
						case ' ':
							continue;
						// Unit
						case '1':
						case '2':
							mBoard[row][col] = new GridItem();
							if (inChar == '1')
								mBoard[row][col].setImage(Unit.defaultImages[0]);
							else if (inChar == '2')
								mBoard[row][col].setImage(Unit.defaultImages[1]);
							break;
						}
					} catch (IndexOutOfBoundsException e) {
						// End of row. pad till you get to width
						for ( ; col < mWidth; col++) {
							mBoard[row][col] = new GridItem();
							mBoard[row][col].setImage(null);
						}
						break;
					}
				}
			}
		} catch (IOException e) {
			// TODO: report error. Quit?
		}
	}
	
	/** Clear the provided panel and fill it with JButtons representing the grid */
	public void populatePanel(JPanel panel) {
		panel.removeAll();
		for (int row = 0; row < mHeight; row++) {
        	for (int col = 0; col < mWidth; col++) {
        		panel.add(mBoard[row][col].getButton());
        	}
        }
	}
	
	public void setListeners(int gameState) {
		switch (gameState) {
		case Constants.GAME_STATE_STOP:
		case Constants.GAME_STATE_PAUSE:
			// Remove all action listeners
			for (int row = 0; row < mHeight; row++) {
				for (int col = 0; col < mWidth; col++) {
					for (ActionListener al : mBoard[row][col].getButton().getActionListeners()) {
						mBoard[row][col].getButton().removeActionListener(al);
					}
				}
			}
			break;
		
		case Constants.GAME_STATE_PLAY:
			// Add action listeners depending on what is at that grid location
			for (int row = 0; row < mHeight; row++) {
				for (int col = 0; col < mWidth; col++) {
					for (ActionListener al : mBoard[row][col].getButton().getActionListeners()) {
						mBoard[row][col].getButton().removeActionListener(al);
					}
				}
			}
			break;
		default:
			break;
		}
	}
	
	public void initNewTurn() {
		selectedUnit = null;
	}
	
	public GridLayout getLayout() {
		return mapLayout;
	}
	
	class GridItemComparator implements Comparator<GridItem> {
		public int compare(GridItem a, GridItem b) {
			if (a.mX < b.mX) {
				return -1;
			} else if (a.mX > b.mX) {
				return 1;
			} else {
				if (a.mY < b.mY) {
					return -1;
				} else if (a.mY > b.mY) {
					return 1;
				} else {
					return 0;
				}
			}
		}
	}
}
