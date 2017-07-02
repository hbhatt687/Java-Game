package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;

/**
 * This is the Heads Up Display for the game. It will display 
 * the health most importantly.
 * 
 * @author Harsh
 */
public class HUD {
	public int bounds = 0;
	public static float HEALTH = 100; 		
	// dynamically change health bar
	private float greenValue = 255;			
	
	private int score = 0;
	private int level = 1;
	
	/**
	 * Dynamically changes the health's value and 
	 * also changes the score.
	 */
	public void tick() {
		HEALTH = Game.clamp(HEALTH, 0, 100 + (bounds / 2));
		greenValue = HEALTH * 2;
		greenValue = Game.clamp(greenValue, 0, 255);
		
		score++;
	}
	
	/**
	 * Renders the health bar on the top right of the screen.
	 * @param g
	 */
	public void render(Graphics g){
		g.setColor(Color.gray);
		g.fillRect(15, 15, 200 + bounds, 32);
		g.setColor(new Color(75, (int)greenValue, 0));
		g.fillRect(15, 15, (int)HEALTH * 2, 32);
		// sets border around health bar
		g.setColor(Color.white);		
		g.drawRect(15, 15, 200 + bounds, 32);
		
		g.drawString("Score: " + score, 15, 64);
		g.drawString("Level: " + level, 15, 80);
		g.drawString("Space for Shop", 15, 94);
	}
	
	/**
	 * @param score is the score the player receives.
	 */
	public void setScore(int score) {
		this.score = score;
	}
	
	/**
	 * @return the player score.
	 */
	public int getScore() {
		return score;
	}
	
	/**
	 * @return the level the player is on.
	 */
	public int getLevel() {
		return level;
	}
	
	/**
	 * @param level is the level that the game should be set to.
	 */
	public void setLevel(int level) {
		this.level = level;
	}
}
