package com.tutorial.main;

import java.awt.image.BufferedImage;

/**
 * This class deals with the custom sprite images used
 * in the game. These images apply to the player and the enemies.
 * 
 * @author Harsh
 */
public class SpriteSheet {
	private BufferedImage sprite;
	
	/*
	 * Constructor.
	 */
	public SpriteSheet(BufferedImage ss) {
		this.sprite = ss;
	}
	
	/**
	 * This will select a portion of the entire sprite sheet so it
	 * can be used to assign to an object later.
	 * 
	 * @param col is the column
	 * @param row is the row
	 * @param width
	 * @param height
	 * @return a sub-image to assign.
	 */
	public BufferedImage grabImage(int col, int row,
			int width, int height) {
		
		return sprite.getSubimage((row * 32) - 32, (col * 32) - 32, 
				width, height);
	}
}
