package com.tutorial.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.tutorial.main.Game.STATE;

/**
 * This deals with all of the keyboard input that the player
 * executes. Basically moves the player up, down, left, 
 * or right on the screen when pressing either W, A
 * , S, or D.
 * 
 * @author Harsh
 */
public class KeyInput extends KeyAdapter{
	private Handler handler;
	private boolean[] keyDown = new boolean[4];
	
	Game game;
	
	/**
	 * Constructor for the key input.
	 * @param handler will handle the objects relating to the key
	 * input.
	 * 
	 * @param game
	 */
	public KeyInput(Handler handler, Game game){
		this.handler = handler;
		this.game = game;
		
		// used to fix the sticky-key issue. 
		keyDown[0]=false;
		keyDown[1]=false;
		keyDown[2]=false;
		keyDown[3]=false;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.awt.event.KeyAdapter#keyPressed(java.awt.event.KeyEvent)
	 */
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();     
		
		for (int i=0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if (tempObject.getId()==ID.Player) {
				//key events for player 
				
				if (key == KeyEvent.VK_W){ tempObject.setVelY(-handler.speed); keyDown[0]=true; }
				if (key == KeyEvent.VK_S){ tempObject.setVelY(handler.speed); keyDown[1]=true; }
				if (key == KeyEvent.VK_D){ tempObject.setVelX(handler.speed); keyDown[2]=true; }
				if (key == KeyEvent.VK_A){ tempObject.setVelX(-handler.speed); keyDown[3]=true; }
			}
	
		}
		
		// for pausing the game
		if (key == KeyEvent.VK_P) {
			if (Game.gameState == STATE.Game) {
				if (Game.paused) {
					Game.paused = false;
				} else {
					Game.paused = true;
				}
			}
			
		}
		
		// Close the game if the player hits "esc" key.
		if(key == KeyEvent.VK_ESCAPE) System.exit(1);
		
		// Key for accessing the shop. 
		if (key == KeyEvent.VK_SPACE) {
			if (Game.gameState == STATE.Game) {
				Game.gameState = STATE.Shop;
			} else if (Game.gameState == STATE.Shop) {
				Game.gameState = STATE.Game;
			}
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.awt.event.KeyAdapter#keyReleased(java.awt.event.KeyEvent)
	 */
	public void keyReleased(KeyEvent e){
		int key = e.getKeyCode();
		for (int i=0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if (tempObject.getId()==ID.Player) {
				//key events for player 
				
				if (key == KeyEvent.VK_W) keyDown[0]=false;
				if (key == KeyEvent.VK_S) keyDown[1]=false;
				if (key == KeyEvent.VK_D) keyDown[2]=false;
				if (key == KeyEvent.VK_A) keyDown[3]=false;
				
				//vertical movement
				if (!keyDown[0] && !keyDown[1]) tempObject.setVelY(0);
				//horizontal movement
				if (!keyDown[2] && !keyDown[3]) tempObject.setVelX(0);
			}
		}
	}
}