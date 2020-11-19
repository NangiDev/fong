package se.nangidev.fong;

public class SoundManager {

	public SoundManager(){
		se.nangidev.fong.Assets.music.setVolume(0.0f);
		se.nangidev.fong.Assets.music.play();
		se.nangidev.fong.Assets.music.setLooping(true);
		tick();
	}
	
	public void tick(){
		if(FongMain.musicOn)
			se.nangidev.fong.Assets.music.setVolume(0.2f);
		else
			Assets.music.setVolume(0.0f);
	}
}
