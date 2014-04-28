package com.me.fong;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
		
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();

		cfg.title = "F�ng";
		cfg.width = 600;
		cfg.height = 960;

		cfg.resizable = false;
		
		new LwjglApplication(new MyGame(), cfg);
	}
}