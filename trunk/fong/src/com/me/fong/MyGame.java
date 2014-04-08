package com.me.fong;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
//Testing testing, Ubuntu
public class MyGame extends Game {
	public SpriteBatch batch;
	public BitmapFont fontLarge, fontMedium, fontSmall;
	public float screenWidth, screenHeight;
	public float scaleX, scaleY;

	private OrthographicCamera camera;
	private HighscoreData HighscoreData;

	private int tilesW, tilesH;
	private Texture backgoundTexture;

	private GameScreen gameScreen;
	private PauseScreen pauseScreen;
	private OptionScreen optionScreen;
	private LoadingScreen loadingScreen;
	private CreditsScreen creditsScreen;
	private MainMenuScreen mainMenuScreen;
	private GameOverScreen gameOverScreen;
	private HighscoreScreen highscoreScreen;
	private InstructionsScreen instructionsScreen;

	@Override
	public void create() {
		this.batch = new SpriteBatch();
		this.screenWidth = Gdx.graphics.getWidth();
		this.screenHeight = Gdx.graphics.getHeight();
		this.scaleX = screenWidth / 600;
		this.scaleY = screenHeight / 960;
		this.camera = new OrthographicCamera(1, screenHeight / screenWidth);
		this.backgoundTexture = new Texture(Gdx.files.internal("purple.png"));
		backgoundTexture.setWrap(TextureWrap.Repeat, TextureWrap.Repeat);
		createFont();
		switchToScreen(GameState.MainMenu);
	}

	@Override
	public void dispose() {
		batch.dispose();
		backgoundTexture.dispose();
		fontSmall.dispose();
		fontLarge.dispose();
	}

	// Call this in each screen class that wants same background as main.
	public void drawBackground() {
		batch.draw(backgoundTexture, 0, 0,
				backgoundTexture.getWidth() * tilesW,
				backgoundTexture.getHeight() * tilesH, 0, tilesH, tilesW, 0);
	}

	@Override
	public void render() {
		super.render();
	}

	@Override
	public void resize(int width, int height) {
		tilesW = width / backgoundTexture.getWidth() + 1;
		tilesH = height / backgoundTexture.getHeight() + 1;
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	// Adds ChangeListener to specific button in menues
	public TextButton setupButton(String text, Skin skin,
			final GameState gameState) {
		TextButton button = new TextButton(text, skin);

		button.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				switchToScreen(gameState);
			}
		});
		
		return button;
	}

	// Creates fontbitmaps from .ttf font
	private void createFont() {
		FreeTypeFontGenerator gen = new FreeTypeFontGenerator(
				Gdx.files.internal("fonts/font.ttf"));
		FreeTypeFontParameter FTFP = new FreeTypeFontParameter();
		FTFP.size = (int) (this.screenWidth / 8);
		this.fontLarge = gen.generateFont(FTFP);
		this.fontLarge.getRegion().getTexture()
				.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		this.fontLarge.setColor(Color.WHITE);
		
		FTFP.size = (int) (this.screenWidth / 10);
		this.fontMedium = gen.generateFont(FTFP);
		this.fontMedium.getRegion().getTexture()
				.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		this.fontMedium.setColor(Color.WHITE);

		FTFP.size = (int) (FTFP.size / 2);
		this.fontSmall = gen.generateFont(FTFP);
		this.fontSmall.getRegion().getTexture()
				.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		this.fontSmall.setColor(Color.WHITE);
	}

	// Call this function to switch screens
	public void switchToScreen(GameState gameState) {
		switch (gameState) {
		case Game:
			if (gameScreen == null)
				gameScreen = new GameScreen(this);
			setScreen(gameScreen);
			break;
		case Pause:
			if (pauseScreen == null)
				pauseScreen = new PauseScreen(this);
			setScreen(pauseScreen);
			break;
		case Options:
			if (optionScreen == null)
				optionScreen = new OptionScreen(this);
			setScreen(optionScreen);
			break;
		case Loading:
			if (loadingScreen == null)
				loadingScreen = new LoadingScreen(this);
			setScreen(loadingScreen);
			break;
		case Credits:
			if (creditsScreen == null)
				creditsScreen = new CreditsScreen(this);
			setScreen(creditsScreen);
			break;
		case MainMenu:
			if (mainMenuScreen == null)
				mainMenuScreen = new MainMenuScreen(this);
			setScreen(mainMenuScreen);
			break;
		case GameOver:
			if (gameOverScreen == null)
				gameOverScreen = new GameOverScreen(this);
			setScreen(gameOverScreen);
			break;
		case Highscore:
			if (highscoreScreen == null)
				highscoreScreen = new HighscoreScreen(this);
			setScreen(highscoreScreen);
			break;
		case Instructions:
			if (instructionsScreen == null)
				instructionsScreen = new InstructionsScreen(this);
			setScreen(instructionsScreen);
			break;
		default:
			break;
		}
	}
}
