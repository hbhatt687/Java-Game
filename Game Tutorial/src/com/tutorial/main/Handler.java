package com.tutorial.main;

import java.awt.Graphics;
import java.util.ArrayList;

/**
 * Maintain/update all of our objects in the room. Loops through
 * all of the objects and individually update and render them.
 * 
 * @author Harsh
 */
public class Handler {

	ArrayList<GameObject> object = new ArrayList<GameObject>();
	// The default speed of the object.
	public int speed = 5;
	
	/**
	 *  Loops through EVERY game object
	 *  & allows us to get ID of what object we are at.
	 */
	public void tick() {
		for (int i = 0; i < object.size(); i++) { 
			GameObject tempObject = object.get(i); 
			tempObject.tick();
		}
	}
	
	/**
	 * Renders all of the game objects.
	 * @param g
	 */
	public void render(Graphics g) { 
		for (int i = 0; i < object.size(); i++) { 
			GameObject tempObject = object.get(i);
			tempObject.render(g);
		}
	}
	
	/**
	 * Clears all of the enemies off of the screen.
	 */
	public void clearEnemies() {
		for (int i = 0; i < object.size(); i++) { 
			GameObject tempObject = object.get(i);
			
			if (tempObject.getId() == ID.Player) {
				object.clear();
				
				if (Game.gameState != Game.STATE.End) {
					addObject(new Player((int) tempObject.getX(), (int)tempObject.getY(), ID.Player, this));
				}
			}
		}
	}
	
	/*
	 * Handles adding and removing objects from our list
	 */
	public void addObject(GameObject object) {
		this.object.add(object);
	}
	
	public void removeObject(GameObject object) {
		this.object.remove(object);
	}
}
