package com.tutorial.main;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * This class focuses on importing the custom sprite image.
 * 
 * @author Harsh
 */
public class BufferedImageLoader {
	BufferedImage image;
	
	/**
	 * The method will provide an image when called.
	 * 
	 * @param path is the path name for the image location.
	 * @return will be the image itself.
	 */
	public BufferedImage loadImage(String path) {
		try {
			image = ImageIO.read(getClass().getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return image; 
	}
}
