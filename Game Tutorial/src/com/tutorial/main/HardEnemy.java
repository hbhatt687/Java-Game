package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * This is a harder enemy than the basic enemy.
 * Essentially this enemy type will have a 
 * randomized speed each time it hits the walls
 * of the game, making it harder to dodge. 
 * 
 * @author Harsh
 */
public class HardEnemy extends GameObject{
	private Handler handler;
	private Random r = new Random();
	private BufferedImage hardEnemy_image;
	
	/**
	 * Constructor for the Hard enemy, giving it its
	 * attributes. 
	 * 
	 * @param x is the width.
	 * @param y is the height.
	 * @param id is the enum type.
	 * @param handler is going to handle the movements/graphics.
	 */
	public HardEnemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		
		velX = 5;
		velY = 5;
		
		// texture for enemy
		SpriteSheet ss = new SpriteSheet(Game.sprite_sheet);
		hardEnemy_image = ss.grabImage(1, 4, 16, 16);
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
		
		// bounces the enemy
		if (y <= 0 || y >= Game.HEIGHT - 32) { 
			if (velY < 0) {
				velY = -(r.nextInt(7) + 1) * -1;
			} else {
				velY = (r.nextInt(7) + 1) * -1;
			}
		}
		
		// bounces the enemy
		if (x <= 0 || x >= Game.WIDTH - 16) {
			if (velX < 0) {
				velX = -(r.nextInt(7) + 1) * -1;
			} else {
				velX = (r.nextInt(7) + 1) * -1;
			}
		}
		
		handler.addObject(new Trail(x, y, ID.Trail,
				Color.yellow, 16, 16, 0.02f, handler));	
	}

	/*
	 * (non-Javadoc)
	 * @see com.tutorial.main.GameObject#render(java.awt.Graphics)
	 */
	public void render(Graphics g) {
		g.drawImage(hardEnemy_image, (int)x, (int)y, null);
	}
}
