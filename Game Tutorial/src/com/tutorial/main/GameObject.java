/*
 * Author: Harsh Bhatt
 * Purpose: Refers to ALL of the game objects.
 */
package com.tutorial.main;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject {

	protected float x, y; // it can only be accessed by something that inherits the game object
	protected ID id;
	protected float velX, velY; // speed in the X and Y direction
	
	public GameObject(float x, float y, ID id) {
		this.x = x;
		this.y = y;
		this.id = id;
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
	public abstract Rectangle getBounds();	// handles all of the collisions
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
	
	public void setId(ID id) {
		this.id = id;
	}
	
	public ID getId() {
		return id;
	}
	
	public void setVelX( int velX) {
		this.velX = velX;
	}
	
	public void setVelY(int velY) {
		this.velY = velY;
	}
	
	public float getVelX() {
		return velX;
	}
	
	public float getVelY() {
		return velY;
	}
	
	
	
}
