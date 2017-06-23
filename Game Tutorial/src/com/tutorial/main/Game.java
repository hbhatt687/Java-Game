/*
 * Author: Harsh Bhatt
 * This is a game I will be creating by following a tutorial on Youtube from 
 * RealTutsGML.
 * Purpose: main class where everything that takes place in the game will go.
 */
package com.tutorial.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable{
	
	private static final long serialVersionUID = 1550691097823471818L;
	
	public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;
	
	private Thread thread; // how the entire game is going to run in the thread
	private boolean running = false; // is the thread on or not?
	
	private Random r;
	private Handler handler; // create an instance of the handler
	private HUD hud;
	private Spawn spawner;
	private Menu menu;
	
	public enum STATE {		// states for our game
		Menu,
		Help,
		Game,
		End
	};
	
	public static STATE gameState = STATE.Menu;
	
	public Game() {
		handler = new Handler();
		hud = new HUD();
		menu = new Menu(this, handler, hud);
		this.addKeyListener(new KeyInput(handler)); // make sure game is "listening" for key
		this.addMouseListener(menu);
		
		new Window(WIDTH, HEIGHT, "Dodge Block", this);
		
		spawner = new Spawn(handler, hud);
		r = new Random();
		
		if (gameState == STATE.Game) {		// only if in game state
			handler.addObject(new Player(WIDTH/2-32, HEIGHT/2-32, ID.Player, handler)); // added an individual player object!
			handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
		} else {
			for (int i = 0; i < 15; i++) {
				handler.addObject(new MenuParticle(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.MenuParticle, handler)); // added an individual player object!

			}
		}
		
	}
	
	public synchronized void start(){
		thread = new Thread(this);
		thread.start(); // starts the thread
		running = true;
	}
	
	public synchronized void stop(){
		try {
			thread.join();
			running = false;
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		 this.requestFocus(); 	// do not need to click on the screen to have keyboard input
		 long lastTime = System.nanoTime(); // the game loop code...
	     double amountOfTicks = 60.0;
	     double ns = 1000000000 / amountOfTicks;
	     double delta = 0;
	     long timer = System.currentTimeMillis();
	     int frames = 0;
	     while (running) {
	    	 long now = System.nanoTime();
	    	 delta += (now - lastTime) / ns;
	    	 lastTime = now;
	    	 while (delta >= 1) {
	    		 tick();
	    		 delta--;
	    	 }
	    	 if(running) {
	    		 render();
	    	 }
	    	 frames++;
	    	 
	    	 if(System.currentTimeMillis() - timer > 1000) {
	    		 timer += 1000;
	    		 System.out.println("FPS " + frames);
	    		 frames = 0;
	    	 }
	     }
	     stop();
	}
	
	private void tick() {
		handler.tick();
		if(gameState == STATE.Game) {		// update only if in game
			hud.tick();
			spawner.tick();
			
			if (HUD.HEALTH <= 0) {
				HUD.HEALTH = 100;
				gameState = STATE.End;
				handler.clearEnemies();
				for (int i = 0; i < 15; i++) {
					handler.addObject(new MenuParticle(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.MenuParticle, handler)); // added an individual player object!

				}
			}
			
		}else if(gameState == STATE.Menu || gameState == STATE.End) {
			menu.tick();
		}
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy(); // set the frames to a small limit
		if (bs == null) {
			this.createBufferStrategy(3); // how many buffers it creates
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.black);		// background color
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		handler.render(g);
		
		if(gameState == STATE.Game) {	// render only if in game
			hud.render(g);
		}else if(gameState == STATE.Menu || gameState == STATE.Help || gameState == STATE.End) {
			menu.render(g);
		}
		
		
		g.dispose();
		bs.show();
	}
	
	public static float clamp(float var, float min, float max) { // so we never go past room dimensions
		if(var >= max) {
			return var = max;
		} else if(var <= min) {
			return var = min;
		} else {
			return var;
		}
	}
	
	
	public static void main(String args[]) {
		new Game();
	}
}
