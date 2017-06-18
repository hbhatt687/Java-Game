/*
 * Author: Harsh Bhatt
 * Purpose: maintain/update all of our objects in the room. loops through
 * all of the objects and individually update and render them.
 */
package com.tutorial.main;

import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {

	LinkedList<GameObject> object = new LinkedList<GameObject>();
	
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
