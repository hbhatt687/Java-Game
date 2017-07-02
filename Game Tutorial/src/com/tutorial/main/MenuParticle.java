package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

/**
 * Background particles in the menu just to add a cool
 * aesthetic to the game.
 * 
 * @author Harsh
 */
public class MenuParticle extends GameObject{
	private Handler handler;
	Random r = new Random();
	private Color color;
	
	/**
	 * Constructor for the particle, deals with how it will
	 * appear on screen.
	 * 
	 * @param x is the x-axis spawn location.
	 * @param y is the y-axis spawn location.
	 * @param id is the enum type.
	 * @param handler will handle the operations.
	 */
	public MenuParticle(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		
		// adds variety to particles
		velX = (r.nextInt(5 - -5) + -5);
		velY = (r.nextInt(5 - -5) + -5);
		
		// if we have 0 velocity
		if (velX == 0) {
			velX = 1;
		}
		
		if (velY == 0) {
			velY = 1;
		}
		
		// red, green, blue
		color = new Color(r.nextInt(255), r.nextInt(255),
				r.nextInt(255));	
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
			velY *= -1;
		}
		
		if (x <= 0 || x >= Game.WIDTH - 16) {
			velX *= -1;
		}
		
		handler.addObject(new Trail(x, y, ID.Trail, color,
				16, 16, 0.05f, handler));
	}

	/*
	 * (non-Javadoc)
	 * @see com.tutorial.main.GameObject#render(java.awt.Graphics)
	 */
	public void render(Graphics g) {
		g.setColor(Color.CYAN);
		g.fillRect((int)x, (int)y, 16, 16);
	}
}
