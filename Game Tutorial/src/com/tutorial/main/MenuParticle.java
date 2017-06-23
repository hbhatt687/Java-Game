package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class MenuParticle extends GameObject{
	
	private Handler handler;
	
	Random r = new Random();
	
	private Color color;

	public MenuParticle(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		
		// adds variety to particles
		velX = (r.nextInt(5 - -5) + -5);
		velY = (r.nextInt(5 - -5) + -5);
		
		// if we have 0 velocity
		if (velX == 0) {
			velX = 1;
		}
		
		if (velY == 0) {
			velY = 1;
		}
		
		color = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));	// red, green, blue
	}
	
	public Rectangle getBounds() {	// hit box
		return new Rectangle((int)x, (int)y, 16, 16);
	}

	
	public void tick() {
		x += velX;
		y += velY;
		
		if(y <= 0 || y >= Game.HEIGHT - 32) { // bounces the enemy
			velY *= -1;
		}
		if(x <= 0 || x >= Game.WIDTH - 16) { // bounces the enemy
			velX *= -1;
		}
		
		handler.addObject(new Trail(x, y, ID.Trail, color, 16, 16, 0.05f, handler));
		
	}

	
	public void render(Graphics g) {
		g.setColor(Color.CYAN);
		g.fillRect((int)x, (int)y, 16, 16);
		
	}

}
