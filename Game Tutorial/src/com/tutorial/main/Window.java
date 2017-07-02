package com.tutorial.main;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

/**
 * This is the window the game will be displayed on.
 * 
 * @author Harsh
 */
public class Window extends Canvas{
	private static final long serialVersionUID = -240840600533728354L;
	
	/**
	 * Constructor
	 * @param width
	 * @param height
	 * @param title is the title of the game.
	 * @param game is the current game in session.
	 */
	public Window( int width, int height, String title, Game game) {
		JFrame frame = new JFrame(title);
		
		frame.setPreferredSize(new Dimension(width, height));
		frame.setMaximumSize(new Dimension(width, height));
		frame.setMinimumSize(new Dimension(width, height));
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.add(game);
		frame.setVisible(true);
		game.start();
	}
}
