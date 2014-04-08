package com.me.fong;

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
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox.CheckBoxStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;

public class MyGame extends Game {
	public SpriteBatch batch;
	public BitmapFont fontLarge, fontMedium, fontSmall;
	public float screenWidth, screenHeight;
	public float scaleX, scaleY;
	public boolean musicOn, soundOn, lightOn;

	public Skin skin;
	public Stage stage;
	public Table table;
	
	public CheckBoxStyle mediumCheckBoxStyle;
	
	public TextButtonStyle largeButtonStyle;
	public TextButtonStyle mediumButtonStyle;
	public TextButtonStyle smallButtonStyle;

	public LabelStyle largelabelStyle;
	public LabelStyle mediumlabelStyle;
	public LabelStyle smalllabelStyle;

	private OrthographicCamera camera;
	private HighscoreData HighscoreData;

	private int tilesW, tilesH;
	private Texture backgoundTexture;
	
	private Color myPurple, myGreen, myYellow;

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
		screenWidth = Gdx.graphics.getWidth();
		screenHeight = Gdx.graphics.getHeight();
		scaleX = screenWidth / 600;
		scaleY = screenHeight / 960;
		this.camera = new OrthographicCamera(1, screenHeight / screenWidth);
		this.backgoundTexture = new Texture(Gdx.files.internal("purple.png"));
		backgoundTexture.setWrap(TextureWrap.Repeat, TextureWrap.Repeat);

		myPurple = new Color(94.0f/255.0f,63.0f/255.0f,107.0f/255.0f,1.0f);
		myGreen = new Color(125.0f/255.0f,149.0f/255.0f,85.0f/255.0f,1.0f);
		myYellow = new Color(208.0f/255.0f,200.0f/255.0f,159.0f/255.0f,1.0f);
		
		
		createFont();
		setupLayout();
		switchToScreen(GameState.MainMenu);
	}

	public void setupLayout() {
		this.stage = new Stage();
		this.skin = new Skin();
		this.table = new Table(skin);
		
		Gdx.input.setInputProcessor(stage);
		
		skin.add("fontLarge", fontLarge);
		skin.add("fontMedium", fontMedium);
		skin.add("fontSmall", fontSmall);
				
		largelabelStyle = new LabelStyle();
		largelabelStyle.font = skin.getFont("fontLarge");
		largelabelStyle.fontColor = myYellow;
		
		mediumlabelStyle = new LabelStyle();
		mediumlabelStyle.font = skin.getFont("fontMedium");
		mediumlabelStyle.fontColor = myYellow;
		
		smalllabelStyle = new LabelStyle();
		smalllabelStyle.font = skin.getFont("fontSmall");
		smalllabelStyle.fontColor = myYellow;
		
		largeButtonStyle = new TextButtonStyle();
		largeButtonStyle.font = skin.getFont("fontLarge");
		largeButtonStyle.fontColor = myYellow;
		largeButtonStyle.overFontColor = myGreen;
		
		mediumButtonStyle = new TextButtonStyle();
		mediumButtonStyle.font = skin.getFont("fontMedium");
		mediumButtonStyle.fontColor = myYellow;
		mediumButtonStyle.overFontColor = myGreen;
		
		smallButtonStyle = new TextButtonStyle();
		smallButtonStyle.font = skin.getFont("fontSmall");
		smallButtonStyle.fontColor = myYellow;
		smallButtonStyle.overFontColor = myGreen;
		
		skin.add("default", largelabelStyle);
		skin.add("default", mediumlabelStyle);
		skin.add("default", smalllabelStyle);
		skin.add("largeButton", largeButtonStyle);
		skin.add("mediumButton", mediumButtonStyle);
		skin.add("smallButton", smallButtonStyle);
		
		table.setFillParent(true);
		table.top();
		stage.addActor(table);
	}

	@Override
	public void dispose() {
		batch.dispose();
		backgoundTexture.dispose();
		stage.dispose();
		skin.dispose();
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
		stage.getViewport().update(width, height, true);
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	// Creates fontbitmaps from .ttf font
	private void createFont() {
		int largeSize = (int) (this.screenWidth / 6);
		int mediumSize = (int) (this.screenWidth / 12);
		int smallSize = (int) (this.screenWidth / 24);

		FreeTypeFontGenerator gen = new FreeTypeFontGenerator(
				Gdx.files.internal("fonts/font.ttf"));
		FreeTypeFontParameter FTFP = new FreeTypeFontParameter();

		FTFP.size = largeSize;
		this.fontLarge = gen.generateFont(FTFP);
		this.fontLarge.getRegion().getTexture()
				.setFilter(TextureFilter.Linear, TextureFilter.Linear);

		FTFP.size = mediumSize;
		this.fontMedium = gen.generateFont(FTFP);
		this.fontMedium.getRegion().getTexture()
				.setFilter(TextureFilter.Linear, TextureFilter.Linear);

		FTFP.size = smallSize;
		this.fontSmall = gen.generateFont(FTFP);
		this.fontSmall.getRegion().getTexture()
				.setFilter(TextureFilter.Linear, TextureFilter.Linear);
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
