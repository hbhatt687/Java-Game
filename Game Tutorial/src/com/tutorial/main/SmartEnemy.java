package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * The smart enemy is a variation of the basic enemy as
 * it will actually follow the player around the screen. 
 * This is different from it randomly jumping around the 
 * screen like the basic enemy.
 * 
 * @author Harsh
 *
 */
public class SmartEnemy extends GameObject{
	private Handler handler;
	private GameObject player;
	/**
	 * Constructor for the smart enemy and its attributes.
	 * 
	 * @param x is the x-axis spawn location.
	 * @param y is the y-axis spawn location.
	 * @param id is the enum type it relates to.
	 * @param handler will handle the operations.
	 */
	public SmartEnemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		
		
		for (int i = 0; i < handler.object.size(); i++) { 
			// if a certain variable gets the ID
			// then set the variable to player
			if (handler.object.get(i).getId() == ID.Player) {	
				player = handler.object.get(i);
			}
		}
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
		
		float diffX = x - player.getX() - 16;
        float diffY = y - player.getY() - 16;
        float distance = (float) Math.sqrt((x-player.getX()) * 
        		(x-player.getX()) + (y-player.getY()) * 
        		(y-player.getY()));
        velX = ((-1 / distance) * diffX);
        velY = ((-1 / distance) * diffY);
        handler.addObject(new Trail( x, y, ID.Trail, 
        		Color.green, 16, 16, 0.02f,handler));
	}

	/*
	 * (non-Javadoc)
	 * @see com.tutorial.main.GameObject#render(java.awt.Graphics)
	 */
	public void render(Graphics g) {
		g.setColor(Color.green);
		g.fillRect((int)x, (int)y, 16, 16);
	}
}
