package rotl.gfx;

import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

public final class Sounds {
	
	private static Sounds instance = null;

	private final Clip menuMusic;
	private final Clip gameMusic;
	private final Clip battleMusic;
	
	private Sounds() {
		
		menuMusic = this.openClip("/sounds/menu_music.wav");
		gameMusic = this.openClip("/sounds/game_music_1.wav");
		battleMusic = this.openClip("/sounds/battle_music_1.wav");
	}
	
	public static Sounds getInstance() {
		
		if (instance == null)
			instance = new Sounds();
		
		return instance;
	}
	
	private Clip openClip(String path) {
		
		final AudioInputStream inputStream;
		final Clip clip;
		
		try {
			
			inputStream = AudioSystem.getAudioInputStream(getClass().getResource(path));
			clip = AudioSystem.getClip();
			clip.open(inputStream);
			
		} catch (UnsupportedAudioFileException | LineUnavailableException | IOException ex) {
			
			return null;
		}
		
        return clip;
	}
	
	public void loopMenuMusic() {
		
		this.menuMusic.loop(Clip.LOOP_CONTINUOUSLY);
	}
	
	public void loopGameMusic() {
		
		this.gameMusic.loop(Clip.LOOP_CONTINUOUSLY);
	}
	
	public void loopBattleMusic() {
		
		this.battleMusic.loop(Clip.LOOP_CONTINUOUSLY);
	}
	
	public void stopMenuMusic() {
		this.menuMusic.stop();
	}
	
	public void stopGameMusic() {
		this.gameMusic.stop();
	}
	
	public void stopBattleMusic() {
		this.battleMusic.stop();
	}
}