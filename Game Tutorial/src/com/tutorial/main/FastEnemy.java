package com.tutorial.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 * FastEnemy is another enemy type that has a greater speed than
 * the basic enemy.
 * 
 * @author Harsh
 */
public class FastEnemy extends GameObject{
	
	@SuppressWarnings("unused")
	private Handler handler;
	private BufferedImage fastEnemy_image;

	/**
	 * Constructor for the fast enemy, creates its attributes.
	 * 
	 * @param x is the width.
	 * @param y is the height.
	 * @param id is the enum type it relates to.
	 * @param handler will handle the operations relating to its movement.
	 */
	public FastEnemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		
		velX = 2;
		velY = 9;
		
		// texture for enemy
		SpriteSheet ss = new SpriteSheet(Game.sprite_sheet);
		fastEnemy_image = ss.grabImage(1, 3, 16, 16);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.tutorial.main.GameObject#getBounds()
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
	g.drawImage(fastEnemy_image, (int)x, (int)y, null);
	}

}
