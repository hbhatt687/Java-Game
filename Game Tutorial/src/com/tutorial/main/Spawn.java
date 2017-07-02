package com.tutorial.main;

import java.util.Random;

/**
 * Spawns enemies onto the screen depending on conditions
 * such as the level.
 * 
 * @author Harsh
 */
public class Spawn {
	private Handler handler;
	private HUD hud;
	private Game game;
	private Random r = new Random();
	private int scoreKeep = 0;
	
	/**
	 * Constructor for the spawning system. 
	 * @param handler
	 * @param hud
	 * @param game is the game in progress.
	 */
	public Spawn(Handler handler, HUD hud, Game game) {
		this.handler = handler;
		this.hud = hud;
		this.game = game;
	}
	
	/**
	 * This will update the game and add a new enemy onto the
	 * screen depending on what level the player reaches.
	 */
	public void tick() {
		scoreKeep++;
		
		// changes level based on score
		if(scoreKeep >= 500) {		
			scoreKeep = 0;
			hud.setLevel(hud.getLevel() + 1);
			
			// adds enemy based on level
			if (game.difficulty == 0) {
				if(hud.getLevel() == 2) { 		
					handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), 
							r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
				} else if (hud.getLevel() == 3) { 		
					handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), 
							r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
				} else if (hud.getLevel() == 4) {
					handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), 
							r.nextInt(Game.HEIGHT - 50), ID.FastEnemy, handler));
				} else if (hud.getLevel() == 5) {
					handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH - 50), 
							r.nextInt(Game.HEIGHT - 50), ID.SmartEnemy, handler));
				} else if (hud.getLevel() == 6) {
					handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), 
							r.nextInt(Game.HEIGHT - 50), ID.FastEnemy, handler));
				} else if (hud.getLevel() == 7) {
					handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), 
							r.nextInt(Game.HEIGHT - 50), ID.FastEnemy, handler));
				} else if (hud.getLevel() == 10) {
					handler.clearEnemies();
					handler.addObject(new EnemyBoss((Game.WIDTH / 2) - 48, 
							-120, ID.EnemyBoss, handler));
				}
				// game is on Hard difficulty.
			} else if (game.difficulty == 1) {
				if (hud.getLevel() == 2) { 		
					handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), 
							r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
				} else if (hud.getLevel() == 3) { 		
					handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), 
							r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
				} else if (hud.getLevel() == 4) {
					handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), 
							r.nextInt(Game.HEIGHT - 50), ID.FastEnemy, handler));
				} else if (hud.getLevel() == 5) {
					handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH - 50), 
							r.nextInt(Game.HEIGHT - 50), ID.SmartEnemy, handler));
				} else if (hud.getLevel() == 6) {
					handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), 
							r.nextInt(Game.HEIGHT - 50), ID.FastEnemy, handler));
				} else if (hud.getLevel() == 7) {
					handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), 
							r.nextInt(Game.HEIGHT - 50), ID.FastEnemy, handler));
				} else if (hud.getLevel() == 10) {
					handler.clearEnemies();
					handler.addObject(new EnemyBoss((Game.WIDTH / 2) - 48, 
							-120, ID.EnemyBoss, handler));
				}
			}
		}
	}
}
