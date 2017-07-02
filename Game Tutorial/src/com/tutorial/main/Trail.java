package com.tutorial.main;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 * Basically this class is used as an aesthetic to the 
 * enemy types or particles in the screen. The blocks
 * will leave a trail behind them as they move around.
 * 
 * @author Harsh
 */
public class Trail extends GameObject{
	private float alpha = 1;
	private float life;
	private Handler handler;
	private Color color;
	
	private int width, height;
	
	// life can be a value between 0.001 - 0.1
	// life of the particle
	
	/**
	 * Constructor
	 * @param x is the x-axis spawn location.
	 * @param y is the y-axis spawn location.
	 * @param id is the enum type it relates to.
	 * @param color is the color of the trail.
	 * @param width
	 * @param height
	 * @param life is how long the trail will be.
	 * @param handler
	 */
	public Trail(float x, float y, ID id, Color color, int width, int height, float life, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		this.color = color;
		this.width = width;
		this.height = height;
		this.life = life;
	}

	/*
	 * (non-Javadoc)
	 * @see com.tutorial.main.GameObject#tick()
	 * 
	 * Deals with the life expectancy of the trail.
	 */
	public void tick() {
		if (alpha > life) {
			alpha -= (life - 0.0001f);
		} else {
			handler.removeObject(this);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.tutorial.main.GameObject#render(java.awt.Graphics)
	 */
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setComposite(makeTransparent(alpha));
		
		g.setColor(color);
		g.fillRect((int)x, (int)y, width, height);
		
		g2d.setComposite(makeTransparent(1));
	}
	
	private AlphaComposite makeTransparent(float aplha) {	// renders out transparencies
		int type = AlphaComposite.SRC_OVER;
		return (AlphaComposite.getInstance(type, alpha));
	}

	/*
	 * (non-Javadoc)
	 * @see com.tutorial.main.GameObject#getBounds()
	 */
	public Rectangle getBounds() {
		return null;
	}
}
