package com.me.fong;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class SoundManager {
	private boolean musicOn, soundOn;
	private BufferedReader bufferedReader;
	private String filename = "Options.txt";
	private String input;

	public SoundManager(boolean musicOn, boolean soundOn){
		this.musicOn = musicOn;
		this.soundOn = soundOn;
		Assets.music.play();
		Assets.music.setLooping(true);
		updateSoundManager(this.musicOn, this.soundOn);
	}
	
	public void updateSoundManager(boolean musicOn, boolean soundOn){
		if(musicOn)
			Assets.music.setVolume(1.0f);
		else
			Assets.music.setVolume(0.0f);
	}
}
