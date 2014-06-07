package com.me.fong;

public class SoundManager {

	public SoundManager(){
		Assets.music.setVolume(0.0f);
		Assets.music.play();
		Assets.music.setLooping(true);
		tick();
	}
	
	public void tick(){
		if(MyGame.musicOn)
			Assets.music.setVolume(0.2f);
		else
			Assets.music.setVolume(0.0f);
	}
}