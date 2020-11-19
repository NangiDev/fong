package se.nangidev.fong.desktop;

import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import se.nangidev.fong.FongMain;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.title = "Fong";
		config.width = 600/2;
		config.height = 960/2;
		config.resizable = false;
		config.addIcon("icon128.png", FileType.Internal);
		config.addIcon("icon32.png", FileType.Internal);
		config.addIcon("icon16.png", FileType.Internal);

		new LwjglApplication(new FongMain(), config);
	}
}
