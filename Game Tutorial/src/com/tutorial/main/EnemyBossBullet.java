package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

/**
 * These are just the little blocks of squares
 * that the enemy boss will project in random
 * directions in the boss fight section of the game.
 * 
 * @author Harsh
 *
 */
public class EnemyBossBullet extends GameObject{
	private Handler handler;
	private Random r = new Random();
	/**
	 * The constructor for the bullets and their attributes.
	 * 
	 * @param x is the x-axis spawn location.
	 * @param y is the y-axis spawn location.
	 * @param id is the enum type it relates to.
	 * @param handler will handles its operations.
	 */
	public EnemyBossBullet(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		
		velX = (r.nextInt(5 - -5) + -5);	// random Number from -5 to 5
		velY = 5;
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
		
		if (y >= Game.HEIGHT) {
			handler.removeObject(this);
		}
		
		handler.addObject(new Trail(x, y, ID.Trail, 
				Color.red, 16, 16, 0.02f, handler));
	}

	/*
	 * (non-Javadoc)
	 * @see com.tutorial.main.GameObject#render(java.awt.Graphics)
	 */
	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect((int)x, (int)y, 16, 16);
	}
}
