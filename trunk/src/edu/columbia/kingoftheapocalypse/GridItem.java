package edu.columbia.kingoftheapocalypse;

import java.awt.Dimension;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class GridItem {
	private Image mImage = null;
	public int mX, mY;
	
	public JButton getButton() {
		JButton jbutton = new JButton();
		jbutton.setPreferredSize(new Dimension(30, 30));
		if (mImage != null)
			jbutton.setIcon(new ImageIcon(mImage));
		return jbutton;
	}
	
	public void setImage(Image image) {
		mImage = image;
	}
}
