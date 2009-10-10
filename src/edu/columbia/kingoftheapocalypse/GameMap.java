package edu.columbia.kingoftheapocalypse;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;

import javax.swing.JPanel;

public class GameMap {

	int mWidth, mHeight;
	GridItem[][] mBoard;
	
	public GameMap(File inputFile) {
		
		try {
			BufferedReader in = new BufferedReader(new FileReader(inputFile));
		
			// first line = width
			mWidth = Integer.parseInt(in.readLine());
			// second line = height
			mHeight = Integer.parseInt(in.readLine());
			
			mBoard = new GridItem[mWidth][mHeight];
		
			// x starts from 0 at top, to height-1 at bottom
			for (int y = 0; y < mHeight; y++) {
				for (int x = 0; x < mWidth; x++) {
					char inChar;
					inChar = (char) in.read();
					switch (inChar) {
					case '\r':
						continue;
					case '\n':
						// End of row. pad till you get to width
						for ( ; y < mWidth; y++) {
							mBoard[x][y] = new GridItem();
							mBoard[x][y].mImage = null;
							mBoard[x][y].mRunnable = null;
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
		for (int x = 0; x < mWidth; x++) {
        	for (int y = 0; y < mHeight; y++) {
        		panel.add(mBoard[x][y].getButton());
        	}
        }
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
