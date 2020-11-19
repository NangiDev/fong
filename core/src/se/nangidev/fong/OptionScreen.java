package se.nangidev.fong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;

public class OptionScreen implements Screen {
	private FongMain game;
	private Shadable header;
	private TextButton backButton;
	private se.nangidev.fong.MenuButton toggleMusicButton;
	private se.nangidev.fong.MenuButton toggleSoundFxButton;
	private se.nangidev.fong.MenuButton toggleLightFxButton;
	private Slider amountOfLight;
	private Table innerTable;
	private Label music;
	private Label soundFx;
	private Label lightFx;
	private Preferences prefs;
	private Label lightExplain;
	private Label amountOfLightLabel;

	public OptionScreen(FongMain FongMain) {
		this.game = FongMain;

		this.innerTable = new Table(game.skin);
		music = new Label("Music", game.mediumlabelStyle);
		soundFx = new Label("Sound", game.mediumlabelStyle);
		lightFx = new Label("Light", game.mediumlabelStyle);
		backButton = new se.nangidev.fong.MenuButton("Back", game.mediumButtonStyle,
				se.nangidev.fong.GameState.MainMenu, game);
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
		
		if(toggleLightFxButton.getBoolean() != FongMain.lightOn  || toggleMusicButton.getBoolean() != FongMain.musicOn || toggleSoundFxButton.getBoolean() != FongMain.soundOn){
			saveOptionState();
			game.soundManager.tick();
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
				(FongMain.screenWidth * 0.5f)
						- (Assets.options.getWidth() * 0.5f * FongMain.scaleX),
				FongMain.screenHeight * 0.7f, game.entityManager, false);
		game.entityManager.addEntity(header);
		this.toggleMusicButton = new se.nangidev.fong.MenuButton("", game.mediumButtonStyle,
				FongMain.musicOn);
		this.toggleSoundFxButton = new se.nangidev.fong.MenuButton("", game.mediumButtonStyle,
				FongMain.soundOn);
		this.toggleLightFxButton = new MenuButton("", game.mediumButtonStyle,
				FongMain.lightOn);

		this.amountOfLight = new Slider(1, 4, 1, false, game.mediumSliderStyle);
		amountOfLight.setVisible(true);
		amountOfLight.setValue(FongMain.lightCounter);
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
		FongMain.musicOn = toggleMusicButton.getBoolean();
		FongMain.soundOn = toggleSoundFxButton.getBoolean();
		FongMain.lightOn = toggleLightFxButton.getBoolean();
		FongMain.lightCounter = (int) amountOfLight.getValue();

		prefs = Gdx.app.getPreferences("FongSaveFile");
		prefs.putBoolean("musicOn", FongMain.musicOn);
		prefs.putBoolean("soundOn", FongMain.soundOn);
		prefs.putBoolean("lightOn", FongMain.lightOn);
		prefs.putInteger("lightCount", FongMain.lightCounter);
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

		innerTable.add().padBottom(50.0f * FongMain.scaleY).expandX().row();
		innerTable.add(music).align(Align.left)
				.padRight(60.0f * FongMain.scaleX);
		innerTable.add(toggleMusicButton).align(Align.right)
				.padBottom(25.0f * FongMain.scaleY).row();

		innerTable.add(soundFx).align(Align.left)
				.padRight(60.0f * FongMain.scaleX);
		innerTable.add(toggleSoundFxButton).align(Align.right)
				.padBottom(25.0f * FongMain.scaleY).row();

		innerTable.add(lightFx).align(Align.left)
				.padRight(60.0f * FongMain.scaleX);
		innerTable.add(toggleLightFxButton).align(Align.center)
				.padBottom(25.0f * FongMain.scaleY).row();

		if (toggleLightFxButton.getBoolean()) {
			innerTable.add(lightExplain).align(Align.left)
			.padRight(0.0f * FongMain.scaleX).row();
			
			innerTable.add(amountOfLight).align(Align.left)
					.padRight(0.0f * FongMain.scaleX).row();
			innerTable.add(amountOfLightLabel).align(Align.right)
					.padBottom(25.0f * FongMain.scaleY).row();
		}

		game.table.add(innerTable).row();
		game.table.add(backButton);

		game.table.padTop(header.getTexture().getHeight() * 1.5f
				* FongMain.scaleY);
	}
}
