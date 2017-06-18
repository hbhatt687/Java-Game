package com.tutorial.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

	private Handler handler;
	
	public KeyInput(Handler handler) {
		this.handler = handler;
	}
		
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode(); // when u press the key, it will display number value for it
		
		for(int i = 0; i < handler.object.size(); i++) { // only perform action for certain objects in the game
			GameObject  tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.Player) {
				// key events for player 1
				
				if(key == KeyEvent.VK_W) { // if we hit W, move object up by 1
					tempObject.setVelY(-5);
				}
				if(key == KeyEvent.VK_S) {
					tempObject.setVelY(5);
				}
				if(key == KeyEvent.VK_D) {
					tempObject.setVelX(5);
				}
				if(key == KeyEvent.VK_A) {
					tempObject.setVelX(-5);
				}
			}
			
			if (tempObject.getId() == ID.Player2) {
				if(key == KeyEvent.VK_UP) { // if we hit W, move object up by 1
					tempObject.setVelY(-5);
				}
				if(key == KeyEvent.VK_DOWN) {
					tempObject.setVelY(5);
				}
				if(key == KeyEvent.VK_RIGHT) {
					tempObject.setVelX(5);
				}
				if(key == KeyEvent.VK_LEFT) {
					tempObject.setVelX(-5);
				}
			}
		}
		
	}
	
	public void keyReleased(KeyEvent e) { // using velX/Y is more efficient
		int key = e.getKeyCode();
		
		for(int i = 0; i < handler.object.size(); i++) { // only perform action for certain objects in the game
			GameObject  tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.Player) {
				// key events for player 1
				
				if(key == KeyEvent.VK_W) { // if we hit W, move object up by 1
					tempObject.setVelY(0);
				}
				if(key == KeyEvent.VK_S) {
					tempObject.setVelY(0);
				}
				if(key == KeyEvent.VK_D) {
					tempObject.setVelX(0);
				}
				if(key == KeyEvent.VK_A) {
					tempObject.setVelX(0);
				}
			}
			
			if (tempObject.getId() == ID.Player2) {
				if(key == KeyEvent.VK_UP) { // if we hit W, move object up by 1
					tempObject.setVelY(0);
				}
				if(key == KeyEvent.VK_DOWN) {
					tempObject.setVelY(0);
				}
				if(key == KeyEvent.VK_RIGHT) {
					tempObject.setVelX(0);
				}
				if(key == KeyEvent.VK_LEFT) {
					tempObject.setVelX(0);
				}
			}
		}
	}
	
}
