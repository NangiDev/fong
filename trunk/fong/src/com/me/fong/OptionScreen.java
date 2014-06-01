package com.me.fong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class OptionScreen implements Screen {
	private MyGame game;
	private Shadable header;
	private TextButton backButton;
	private MenuButton toggleMusicButton;
	private MenuButton toggleSoundFxButton;
	private MenuButton toggleLightFxButton;
	private Slider amountOfLight;
	private Table innerTable;
	private Label music;
	private Label soundFx;
	private Label lightFx;
	private Preferences prefs;
	private Label lightExplain;
	private Label amountOfLightLabel;

	public OptionScreen(MyGame myGame) {
		this.game = myGame;

		this.innerTable = new Table(game.skin);
		music = new Label("Music", game.mediumlabelStyle);
		soundFx = new Label("Sound", game.mediumlabelStyle);
		lightFx = new Label("Light", game.mediumlabelStyle);
		backButton = new MenuButton("Back", game.mediumButtonStyle,
				GameState.MainMenu, game);
		lightExplain = new Label("Lights/unit:", game.smalllabelStyle);
		amountOfLightLabel = new Label("0", game.mediumlabelStyle);

		System.out.println("new OptionScreen created");
	}

	@Override
	public void render(float delta) {
		game.batch.begin();
		game.drawBackground(delta);
		update(delta);
		draw(delta);
		game.batch.end();
	}

	public void update(float delta) {
		if (Gdx.input.isKeyPressed(Keys.BACK)) {
			game.switchToScreen(GameState.MainMenu);
		}
		game.entityManager.tick(delta);
		
		if(toggleLightFxButton.getBoolean() != MyGame.lightOn  || toggleMusicButton.getBoolean() != MyGame.musicOn || toggleSoundFxButton.getBoolean() != MyGame.soundOn){
			saveOptionState();
		}
	}

	public void draw(float delta) {
		game.table.draw(game.batch, 1);
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void show() {
		this.header = new Shadable(game.batch, Assets.options,
				(MyGame.screenWidth * 0.5f)
						- (Assets.options.getWidth() * 0.5f * MyGame.scaleX),
				MyGame.screenHeight * 0.7f, game.entityManager, false);
		game.entityManager.addEntity(header);
		this.toggleMusicButton = new MenuButton("", game.mediumButtonStyle,
				MyGame.musicOn);
		this.toggleSoundFxButton = new MenuButton("", game.mediumButtonStyle,
				MyGame.soundOn);
		this.toggleLightFxButton = new MenuButton("", game.mediumButtonStyle,
				MyGame.lightOn);

		this.amountOfLight = new Slider(1, 10, 1, false, game.mediumSliderStyle);
		amountOfLight.setVisible(true);
		amountOfLight.setValue(MyGame.lightCounter);
		amountOfLight.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				amountOfLightLabel.setText("" + (int)amountOfLight.getValue());
			}
		});
		amountOfLightLabel.setText("" + (int)amountOfLight.getValue());
		setupMenuLayout();
	}

	@Override
	public void hide() {
		saveOptionState();
		game.entityManager.clearEntityList();
		game.table.clearChildren();
		innerTable.clearChildren();
	}

	private void saveOptionState() {
		MyGame.musicOn = toggleMusicButton.getBoolean();
		MyGame.soundOn = toggleSoundFxButton.getBoolean();
		MyGame.lightOn = toggleLightFxButton.getBoolean();
		MyGame.lightCounter = (int) amountOfLight.getValue();

		prefs = Gdx.app.getPreferences("FongSaveFile");
		prefs.putBoolean("musicOn", MyGame.musicOn);
		prefs.putBoolean("soundOn", MyGame.soundOn);
		prefs.putBoolean("lightOn", MyGame.lightOn);
		prefs.putInteger("lightCount", MyGame.lightCounter);
		prefs.flush();
		
		setupMenuLayout();
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
	}

	private void setupMenuLayout() {
		game.table.clearChildren();
		innerTable.clearChildren();

		innerTable.add().row().padBottom(25.0f * MyGame.scaleY);
		innerTable.add(music).align(Align.left)
				.padRight(60.0f * MyGame.scaleX);
		innerTable.add(toggleMusicButton).align(Align.right).row()
				.padBottom(25.0f * MyGame.scaleY);

		innerTable.add(soundFx).align(Align.left)
				.padRight(60.0f * MyGame.scaleX);
		innerTable.add(toggleSoundFxButton).align(Align.right).row()
				.padBottom(25.0f * MyGame.scaleY);

		innerTable.add(lightFx).align(Align.left)
				.padRight(60.0f * MyGame.scaleX);
		innerTable.add(toggleLightFxButton).align(Align.center).row()
				.padBottom(25.0f * MyGame.scaleY);

		if (toggleLightFxButton.getBoolean()) {
			innerTable.add(lightExplain).align(Align.left)
			.padRight(0.0f * MyGame.scaleX).row();
			
			innerTable.add(amountOfLight).align(Align.left)
					.padRight(0.0f * MyGame.scaleX).fillX();
			innerTable.add(amountOfLightLabel).align(Align.right).row()
					.padBottom(25.0f * MyGame.scaleY);
		}

		game.table.add(innerTable).row();
		game.table.add(backButton);

		game.table.padTop(header.getTexture().getHeight() * 1.5f
				* MyGame.scaleY);
	}
}
