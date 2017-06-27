/*
 * Author: Harsh Bhatt
 * Purpose: maintain/update all of our objects in the room. loops through
 * all of the objects and individually update and render them.
 */
package com.tutorial.main;

import java.awt.Graphics;
import java.util.ArrayList;

public class Handler {

	ArrayList<GameObject> object = new ArrayList<GameObject>();
	
	public int speed = 5;
	
	public void tick() {
		for(int i = 0; i < object.size(); i++) { // loops through EVERY game object
			GameObject tempObject = object.get(i); // allows us to get ID of what object we are at
			
			tempObject.tick();
		}
	}
	
	public void render(Graphics g) { 
		for(int i = 0; i < object.size(); i++) { // renders all of the game objects
			GameObject tempObject = object.get(i);
			
			tempObject.render(g);
		}
	}
	
	public void clearEnemies() {
		for(int i = 0; i < object.size(); i++) { // renders all of the game objects
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
	 * handles adding and removing objects from our list
	 */
	public void addObject(GameObject object) {
		this.object.add(object);
	}
	
	public void removeObject(GameObject object) {
		this.object.remove(object);
	}
	
	
}
