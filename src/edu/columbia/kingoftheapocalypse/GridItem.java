package edu.columbia.kingoftheapocalypse;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class GridItem {
	public Image mImage = null;
	public Runnable mRunnable = null;
	/** Changes depending on current phase of game, and based on players' actions */
	public boolean mClickEnabled = false;
	public JButton mButton = null;
	public int mX, mY;
	
	public JButton getButton() {
		JButton jbutton = new JButton();
		jbutton.setIcon(new ImageIcon(mImage));
		return jbutton;
	}
}
