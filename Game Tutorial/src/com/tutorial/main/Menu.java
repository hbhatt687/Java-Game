package com.tutorial.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import com.tutorial.main.Game.STATE;

/**
 * This menu will be the first thing the player sees when 
 * starting up the game. It will have a Play, Help, 
 * and Quit option. 
 * 
 * @author Harsh
 */
public class Menu extends MouseAdapter{
	private Game game;
	private Handler handler;
	private HUD hud;
	private Random r = new Random();
	
	/**
	 * The constructor for the menu. 
	 * 
	 * @param game
	 * @param handler will handle the operations pertaining to
	 * the menu.
	 * 
	 * @param hud will be needed here to remove it if the 
	 * player is in the menu screen after playing the game.
	 */
	public Menu(Game game, Handler handler, HUD hud) {
		this.game = game;
		this.handler = handler;
		this.hud = hud;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.awt.event.MouseAdapter#mousePressed(java.awt.event.MouseEvent)
	 */
	public void mousePressed(MouseEvent e) {
		// stores x and y positions into variables
		int mx = e.getX();		
		int my = e.getY();
		
		// makes sure we are in menu and not in some game
		if (Game.gameState == STATE.Menu) {   
			// play button
			// change game state to game
			if (mouseOver(mx, my, 210, 150, 200, 64)) { 		
				Game.gameState = STATE.selectDifficulty;
				
				// play sound bite
				AudioPlayer.getSound("menu").play();
				
				return;
			}
			
			// help button
			if (mouseOver(mx, my, 210, 250, 200, 64)) {
				Game.gameState = STATE.Help;
				
				// play sound bite
				AudioPlayer.getSound("menu").play();
			}
			
			// quit button
			if (mouseOver(mx, my, 210, 350, 200, 64)) {
				System.exit(1);
			}
		}
		
		// makes sure we are in menu and not in some game
		if (Game.gameState == STATE.selectDifficulty) {   
			// normal button
			// change game state to game
			if (mouseOver(mx, my, 210, 150, 200, 64)) { 
				Game.gameState = STATE.Game;
				handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player, handler)); // added an individual player object
				handler.clearEnemies();
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
				
				game.difficulty = 0;
				
				// play sound bite
				AudioPlayer.getSound("menu").play();
			}
			
			// hard button
			if (mouseOver(mx, my, 210, 250, 200, 64)) {
				Game.gameState = STATE.Game;
				handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player, handler)); // added an individual player object
				handler.clearEnemies();
				handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
				
				game.difficulty = 1;
				
				// play sound bite
				AudioPlayer.getSound("menu").play();
			}
			
			// back button
			if (mouseOver(mx, my, 210, 350, 200, 64)) {
				Game.gameState = STATE.Menu;
				
				// play sound bite
				AudioPlayer.getSound("menu").play();
				
				return;
			}
		}
		
		
		// back button for help
		if (Game.gameState == STATE.Help) {
			// returns us to the main menu
			if (mouseOver(mx, my, 210, 350, 200, 64)) { 	
				Game.gameState = STATE.Menu;
				
				// play sound bite
				AudioPlayer.getSound("menu").play();
				
				return;
			}
		}
		
		// try again button
		if (Game.gameState == STATE.End) {
			// returns us to the main menu
			if (mouseOver(mx, my, 210, 350, 200, 64)) { 	
				Game.gameState = STATE.Menu;
				
				// reset the score and level
				hud.setLevel(1);
				hud.setScore(0);
				
				// play sound bite
				AudioPlayer.getSound("menu").play();
			}
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.awt.event.MouseAdapter#mouseReleased(java.awt.event.MouseEvent)
	 */
	public void mouseReleased(MouseEvent e) {}
	
	/**
	 * Checks to see if you clicked within the box
	 * @param mx player x-axis click location.
	 * @param my player y-axis click location.
	 * @param x object x- location.
	 * @param y object y-location. 
	 * @param width
	 * @param height
	 * @return
	 */
	private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
		if (mx > x && mx < x + width) {
			if (my > y && my < y + height) {
				return true;
			} else {
				return false;
			}
		}else {
			return false;
		}
	}
	
	/**
	 * Deals with updating the menu.
	 */
	public void tick() {}
	
	/**
	 * Deals with rendering the menu on the screen.
	 */
	public void render(Graphics g) {
		if (Game.gameState == STATE.Menu) {
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 30);
			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Dodge Block", 150, 70);
			
			g.setFont(fnt2);
			g.drawRect(210, 150, 200, 64);
			g.drawString("Play", 270, 190);
			
			g.drawRect(210, 250, 200, 64);
			g.drawString("Help", 270, 290);
			
			g.drawRect(210, 350, 200, 64);
			g.drawString("Quit", 270, 390);
		} else if (Game.gameState == STATE.Help) {
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 30);
			Font fnt3 = new Font("arial", 1, 20);

			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Help", 240, 70);
			
			g.setFont(fnt3);
			g.drawString("Use WASD keys to move player and dodge enemies.", 50, 200);
			
			g.setFont(fnt2);
			g.drawRect(210, 350, 200, 64);
			g.drawString("Back", 270, 390);
		} else if (Game.gameState == STATE.End) {
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 30);
			Font fnt3 = new Font("arial", 1, 20);

			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Game Over", 180, 70);
			
			g.setFont(fnt3);
			g.drawString("You lost with a score of: " + hud.getScore(), 175, 200);
			
			g.setFont(fnt2);
			g.drawRect(210, 350, 200, 64);
			g.drawString("Retry", 275, 390);
		} else if (Game.gameState == STATE.selectDifficulty) {
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 30);
			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("SELECT DIFFICULTY", 80, 70);
			
			g.setFont(fnt2);
			g.drawRect(210, 150, 200, 64);
			g.drawString("Normal", 260, 190);
			
			g.drawRect(210, 250, 200, 64);
			g.drawString("Hard", 270, 290);
			
			g.drawRect(210, 350, 200, 64);
			g.drawString("Back", 270, 390);
		}
		
	}

}
