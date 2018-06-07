package rotl.gfx;

import java.applet.Applet;
import java.applet.AudioClip;

public class Sound {
	
	private AudioClip audioClip;
	private static Sound instance = null;
	
	private Sound(String path) {
		audioClip = Applet.newAudioClip(getClass().getResource(path));
		
	}
	
	public static Sound getInstance(String path) {
		
		if (instance == null)
			instance = new Sound(path);
		
		return instance;
	}
	
	public static final Sound battleMusic =new Sound ("/sounds/battle_music_1.wav");
	
	public static final Sound gameMusic =	new Sound ("/sounds/game_music_1.wav");
	
	public static final Sound menuMusic = new Sound("/sounds/menu_music.wav");
	
	public void play() {
				audioClip.play();

	}
	
	public void stop() {
				audioClip.stop();

	}
	
	public void loop() {
				audioClip.loop();
	}
}

