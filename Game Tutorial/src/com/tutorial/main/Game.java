package com.tutorial.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.Random;
/**
 * Main class that will run the game. Starts a thread 
 * and renders all of the graphics onto the window.
 * 
 * @author harshbhatt
 *
 */
public class Game extends Canvas implements Runnable{
	
	private static final long serialVersionUID = 1550691097823471818L;
	
	// This is what the window dimensions should be.
	public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;
	
	private Thread thread; 
	private boolean running = false; 
	
	// This is to let us know if the game is paused or not.
	public static boolean paused = false;
	
	// difficulty of the game; 0 = normal and 1 = hard
	public int difficulty = 0;
	
	private Random r;
	private Handler handler; 
	private HUD hud;
	private Spawn spawner;
	private Menu menu;
	private Shop shop;
	
	/**
	 * Different states of the game that will be useful for
	 * navigation and upgrades
	 */
	public enum STATE {		
		Menu,
		selectDifficulty,
		Help,
		Shop,
		Game,
		End
	};
	
	// Sets the first state of the game to the menu. 
	public static STATE gameState = STATE.Menu;
	
	// Used to make custom-made images easier to implement.
	public static BufferedImage sprite_sheet;
	
	/**
	 * Constructor for the game that spawns the necessary 
	 * objects in based on what game state is active.
	 */
	public Game() {
		// load sprite sheet into the game
		BufferedImageLoader loader = new BufferedImageLoader();
		sprite_sheet = loader.loadImage("/sprite_sheet.png");
		
		handler = new Handler();
		hud = new HUD();
		shop = new Shop(handler, hud);
		menu = new Menu(this, handler, hud);
		this.addKeyListener(new KeyInput(handler, this));
		this.addMouseListener(menu);
		this.addMouseListener(shop);
		
		// add background music
		AudioPlayer.load();
		AudioPlayer.getMusic("music").loop();
		
		new Window(WIDTH, HEIGHT, "Dodge Block", this);
		
		spawner = new Spawn(handler, hud, this);
		r = new Random();
		
		/*
		 * If we are in the game state, then we will add a player and an enemy, 
		 * else we are obviously not playing the game so we will spawn the 
		 * background menu particles.
		 */
		if (gameState == STATE.Game) {	
			handler.addObject(new Player(WIDTH/2-32, HEIGHT/2-32, ID.Player, handler)); 
			handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
		} else {
			for (int i = 0; i < 15; i++) {
				handler.addObject(new MenuParticle(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.MenuParticle, handler)); 
			}
		}	
	}
	
	/**
	 * This essentially starts the thread or the game.
	 */
	public synchronized void start(){
		thread = new Thread(this);
		thread.start(); // starts the thread
		running = true;
	}
	
	/**
	 * Stops the thread and provides an error as to what occurred. 
	 */
	public synchronized void stop(){
		try {
			thread.join();
			running = false;
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This method contains the game loop code, it essentially keeps the game 
	 * running at a variable amount of frames per second. There are different
	 * variations of game loop codes available for use on the Internet.
	 */
	public void run() {
		 this.requestFocus();
		 long lastTime = System.nanoTime();
	     double amountOfTicks = 60.0;
	     double ns = 1000000000 / amountOfTicks;
	     double delta = 0;
	     long timer = System.currentTimeMillis();
	    
	     @SuppressWarnings("unused")
		int frames = 0;
	     while (running) {
	    	 long now = System.nanoTime();
	    	 delta += (now - lastTime) / ns;
	    	 lastTime = now;
	    	 while (delta >= 1) {
	    		 tick();
	    		 delta--;
	    	 }
	    	 if (running) {
	    		 render();
	    	 }
	    	 frames++;
	    	 
	    	 if (System.currentTimeMillis() - timer > 1000) {
	    		 timer += 1000;
	    		 frames = 0;
	    	 }
	     }
	     stop();
	}
	
	/**
	 * Essentially updates the game.
	 * If the game is not paused, then it will continue updating the game as normal.
	 */
	private void tick() {
		if (gameState == STATE.Game) {		
			if (!paused) {
				hud.tick();
				spawner.tick();
				handler.tick();
				
				if (HUD.HEALTH <= 0) {
					HUD.HEALTH = 100;
					gameState = STATE.End;
					handler.clearEnemies();
					for (int i = 0; i < 15; i++) {
						handler.addObject(new MenuParticle(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.MenuParticle, handler));
					}
				}
			}
		} else if (gameState == STATE.Menu || gameState == STATE.End 
				|| gameState == STATE.selectDifficulty) {
			menu.tick();
			handler.tick();
		}
	}
	
	/**
	 * This method will provide the graphical elements of the game to 
	 * be displayed on the screen. Each item that needs to be displayed
	 * should have a renders method.
	 */
	private void render() {
		// set the frames to a small limit
		BufferStrategy bs = this.getBufferStrategy(); 
		if (bs == null) {
			this.createBufferStrategy(3); 
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.black);		
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		// display paused
		if (paused) {
			g.setColor(Color.white);
			g.drawString("PAUSED", 100, 100);
		}
		
		// render only if in game
		if (gameState == STATE.Game) {
			hud.render(g);
			handler.render(g);
		} else if (gameState == STATE.Shop) {
			shop.render(g);
		} else if (gameState == STATE.Menu || gameState == STATE.Help 
				|| gameState == STATE.End || gameState == STATE.selectDifficulty) {
			menu.render(g);
			handler.render(g);
		}
		
		
		g.dispose();
		bs.show();
	}
	
	/**
	 * This method is to ensure we never go passed the window's dimensions 
	 * while the player or enemy is floating around in the game.
	 * 
	 * @param var will be the size of the x-value.
	 * @param min will be the minimum size of the window.
	 * @param max will be the maximum size of the window.
	 * @return a float value of var that is keeping the object inside the "room."
	 */
	public static float clamp(float var, float min, float max) { 
		if (var >= max) {
			return var = max;
		} else if (var <= min) {
			return var = min;
		} else {
			return var;
		}
	}
	
	/**
	 * Starts up the game.
	 */
	public static void main(String args[]) {
		new Game();
	}
}
