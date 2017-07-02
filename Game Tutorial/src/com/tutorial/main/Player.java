package com.tutorial.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * This is the main player in the game. It is essentially
 * a block that can be controlled by key input
 * and moved around the screen.
 * 
 * @author Harsh
 */
public class Player extends GameObject{
	Random r = new Random();
	Handler handler;
	private BufferedImage player_image;
	
	/**
	 * Constructor for the player, deals with its attributes.
	 * 
	 * @param x is the width.
	 * @param y is the height.
	 * @param id is the enum type.
	 * @param handler will handle the player operations.
	 */
	public Player(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		
		// player texture.
		SpriteSheet ss = new SpriteSheet(Game.sprite_sheet);
		player_image = ss.grabImage(1, 1, 32, 32); 
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.tutorial.main.GameObject#getBounds()
	 */
	public Rectangle getBounds() {	
		return new Rectangle((int)x, (int)y, 32, 32);
	}

	/*
	 * (non-Javadoc)
	 * @see com.tutorial.main.GameObject#tick()
	 */
	public void tick() {
		x += velX;
		y += velY;
		
		// sets boundaries for player
		x = Game.clamp(x, 0, Game.WIDTH -32); 
		y = Game.clamp(y, 0, Game.HEIGHT -60);
		
		collision();
	}
	
	/**
	 * This method deals with collisions with the enemy.
	 * Basically the player loses health if he/she
	 * touches an enemy object.
	 */
	private void collision() {
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if (tempObject.getId() == ID.BasicEnemy || tempObject.getId() == ID.FastEnemy 
					|| tempObject.getId() == ID.SmartEnemy) {
				if (getBounds().intersects(tempObject.getBounds())) {
					// collision code
					HUD.HEALTH -= 2;
				}
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.tutorial.main.GameObject#render(java.awt.Graphics)
	 */
	public void render(Graphics g) {
		g.drawImage(player_image, (int)x, (int)y, null);
	}
}
