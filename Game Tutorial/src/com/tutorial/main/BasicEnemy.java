package com.tutorial.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 * This will be the default enemy that attacks the player.
 * 
 * @author harshbhatt
 *
 */
public class BasicEnemy extends GameObject{
	private BufferedImage enemy_image;

	/**
	 * Constructor for the basic enemy that specifies its attributes.
	 * 
	 * @param x is the x-axis spawn location.
	 * @param y is the y-axis spawn location.
	 * @param id is the enum it relates to 
	 * @param handler will handle its operation
	 */
	public BasicEnemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		velX = 5;
		velY = 5;
		
		// texture for enemy
		SpriteSheet ss = new SpriteSheet(Game.sprite_sheet);
		enemy_image = ss.grabImage(1, 2, 16, 16);
	}
	
	/**
	 * This is the hit box.
	 */
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 16, 16);
	}

	/*
	 * (non-Javadoc)
	 * @see com.tutorial.main.GameObject#tick()
	 */
	public void tick() {
		x += velX;
		y += velY;
		
		if(y <= 0 || y >= Game.HEIGHT - 32) { // bounces the enemy
			velY *= -1;
		}
		if(x <= 0 || x >= Game.WIDTH - 16) { // bounces the enemy
			velX *= -1;
		}	
	}

	/*
	 * (non-Javadoc)
	 * @see com.tutorial.main.GameObject#render(java.awt.Graphics)
	 */
	public void render(Graphics g) {
		g.drawImage(enemy_image, (int)x, (int)y, null);
	}

}
