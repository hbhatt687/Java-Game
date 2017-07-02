package com.tutorial.main;

import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

/**
 * Provides audio to the game for the background and buttons. 
 * 
 * @author harshbhatt
 */
public class AudioPlayer {
	// HashMaps for using the correct sound/music to go with what the game is asking.
	public static Map<String, Sound> soundMap = new HashMap<String, Sound>();
	public static Map<String, Music> musicMap = new HashMap<String, Music>();
	
	/**
	 * Loads the music when this method is called to the appropriate area.
	 */
	public static void load() {
		try {
			soundMap.put("menu", new Sound("resources/Fuzzy_Beep.ogg"));
			musicMap.put("music", new Music("resources/BloodyTears.ogg"));
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Returns music selection.
	 * 
	 * @param key is the string relating to the sound.
	 * @return
	 */
	public static Music getMusic(String key) {
		return musicMap.get(key);
	}
	
	/**
	 * Returns sound selection.
	 * 
	 * @param key is the string relating to the sound.
	 * @return
	 */
	public static Sound getSound(String key) {
		return soundMap.get(key);
	}
}
