package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

/**
 * This class has the attributes for the enemy boss.
 * 
 * @author Harsh
 */
public class EnemyBoss extends GameObject{
	
	private Handler handler;
	private Random r = new Random();
	
	private int timer = 80;
	private int timer2 = 50;
	
	/**
	 * Constructor for the enemy boss.
	 * 
	 * @param x is the width.
	 * @param y is the height.
	 * @param id is the enum type it relates to.
	 * @param handler will handle this object's operations.
	 */
	public EnemyBoss(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		
		velX = 0;
		velY = 2;
		
	}
	
	/**
	 * @return the rectangle's hit box.
	 */
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 96, 96);
	}

	/**
	 * Essentially deals with the boss's movements.
	 */
	public void tick() {
		x += velX;
		y += velY;
		
		// stops the boss in the screen
		if (timer <= 0) {		
			velY = 0;
		} else {
			timer--;
		}
		
		if (timer <= 0) {
			timer2--;
		}
		
		// begin boss's abilities
		if (timer2 <= 0) {		
			if (velX == 0) {
				velX = 2;
			}
			
			if (velX > 0) {
				velX += 0.005f;
			} else if (velX < 0) {
				velX -= 0.005f;
			}
			
			velX = Game.clamp(velX, -10, 10);
			
			int spawn = r.nextInt(10);
			if (spawn == 0) {
				handler.addObject(new EnemyBossBullet((int)x + 48,
						(int)y + 48, ID.BasicEnemy, handler));
			}
		}
	
		 // bounces the enemy
		if (x <= 0 || x >= Game.WIDTH - 96) {
			velX *= -1;
		}
		
	}

	/**
	 * This deals with the graphics of the enemy boss.
	 */
	public void render(Graphics g) {
		g.setColor(Color.ORANGE);
		g.fillRect((int)x, (int)y, 96, 96);
	}

}
