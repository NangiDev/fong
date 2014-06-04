package com.me.fong;

import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
		
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();

		cfg.title = "Föng";
		cfg.width = 600/2;
		cfg.height = 960/2;
		cfg.resizable = false;
		cfg.addIcon("icon128.png", FileType.Internal);
		cfg.addIcon("icon32.png", FileType.Internal);
		cfg.addIcon("icon16.png", FileType.Internal);
		
		new LwjglApplication(new MyGame(), cfg);
	}
}