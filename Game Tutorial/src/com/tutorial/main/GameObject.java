package com.tutorial.main;

import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * This class will refer to all of the objects in the game.
 * 
 * @author Harsh
 */
public abstract class GameObject {
	// it can only be accessed by something that inherits the game object
	protected float x, y; 
	protected ID id;
	// speed in the X and Y direction
	protected float velX, velY; 
	
	/**
	 * Constructor for a basic game object. 
	 * @param x will refer to a width.
	 * @param y will refer to a height. 
	 * @param id will refer to an enum type. 
	 */
	public GameObject(float x, float y, ID id) {
		this.x = x;
		this.y = y;
		this.id = id;
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
	// handles all of the collisions
	public abstract Rectangle getBounds();	
	
	/**
	 * @param x is the desired width.
	 */
	public void setX(int x) {
		this.x = x;
	}
	
	/**
	 * @param y is the desired height.
	 */
	public void setY(int y) {
		this.y = y;
	}
	
	/**
	 * @return the width.
	 */
	public float getX() {
		return x;
	}
	
	/**
	 * @return the height.
	 */
	public float getY() {
		return y;
	}
	
	/**
	 * @param id is the enum type.
	 */
	public void setId(ID id) {
		this.id = id;
	}
	
	/**
	 * @return the enum type.
	 */
	public ID getId() {
		return id;
	}
	
	/**
	 * @param velX is the velocity in the X-axis.
	 */
	public void setVelX( int velX) {
		this.velX = velX;
	}
	
	/**
	 * @param velY is the velocity in the Y-axis.
	 */
	public void setVelY(int velY) {
		this.velY = velY;
	}
	
	/**
	 * @return the X-axis velocity.
	 */
	public float getVelX() {
		return velX;
	}
	
	/**
	 * @return the Y-Axis velocity.
	 */
	public float getVelY() {
		return velY;
	}
}
