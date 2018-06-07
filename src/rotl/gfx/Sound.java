package rotl.gfx;

import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

public class Sound {
	
	private String path;
	private Clip clip;
	private AudioInputStream inputStream;
	private static Sound instance = null;
	private Sound(String path) {
		
		try {
			inputStream = AudioSystem.getAudioInputStream(getClass().getResource(path));
		} catch (UnsupportedAudioFileException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
        	clip = AudioSystem.getClip();
			clip.open(inputStream);
			
		} catch (LineUnavailableException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		
	}
	
	public static Sound getInstance(String path) {
		
		if (instance == null)
			instance = new Sound(path);
		
		return instance;
	}
	
	public static final Sound battleMusic =new Sound ("/sounds/battle_music_1.wav");
	
	public static final Sound gameMusic = new Sound ("/sounds/game_music_1.wav");
	
	public static final Sound menuMusic = new Sound("/sounds/menu_music.wav");
	
	public void loop() {
    	clip.loop(Clip.LOOP_CONTINUOUSLY);           

	}
	
	public void stop() {
		clip.stop();

	}

}

