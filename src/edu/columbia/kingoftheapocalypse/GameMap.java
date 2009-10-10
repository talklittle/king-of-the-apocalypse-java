package edu.columbia.kingoftheapocalypse;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class GameMap {

	
	GridItem[][] mBoard;
	
	public GameMap(File inputFile) {
		
		try {
			BufferedReader in = new BufferedReader(new FileReader(inputFile));
		
			// first line = width
			int width = Integer.parseInt(in.readLine());
			// second line = height
			int height = Integer.parseInt(in.readLine());
			
			mBoard = new GridItem[width][height];
		
			// x starts from 0 at top, to height-1 at bottom
			for (int y = 0; y < height; y++) {
				for (int x = 0; x < width; x++) {
					char inChar;
					inChar = (char) in.read();
					switch (inChar) {
					case '\r':
						continue;
					case '\n':
						// End of row. pad till you get to width
						for ( ; y < width; y++) {
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
}
