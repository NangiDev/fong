package com.me.fong;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.List.ListStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;

public class MyGame extends Game {
	public SpriteBatch batch;
	public BitmapFont fontLarge, fontMedium, fontSmall;
	public static float screenWidth, screenHeight;
	public static float scaleX, scaleY;
	public boolean musicOn, soundOn, lightOn;

	public Skin skin;
	public Stage stage;
	public Table table;
	
	public TextFieldStyle textFieldStyle;
	
	public TextButtonStyle largeButtonStyle;
	public TextButtonStyle mediumButtonStyle;
	public TextButtonStyle smallButtonStyle;

	public LabelStyle largelabelStyle;
	public LabelStyle mediumlabelStyle;
	public LabelStyle smalllabelStyle;
	
	public ListStyle listStyle;

	public HighscoreManager HighscoreManager;
	public Screen currentScreen;
	
	public Color myDarkGreen, myGreen, myYellow;

	private OrthographicCamera camera;
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
		screenWidth = Gdx.graphics.getWidth();
		screenHeight = Gdx.graphics.getHeight();
		scaleX = screenWidth / 600;
		scaleY = screenHeight / 960;
		camera = new OrthographicCamera(1, screenHeight / screenWidth);
		this.backgoundTexture = new Texture(Gdx.files.internal("purple.png"));
				
		myDarkGreen = new Color(71.0f/255.0f,97.0f/255.0f,28.0f/255.0f,1.0f);
		myGreen = new Color(125.0f/255.0f,149.0f/255.0f,85.0f/255.0f,1.0f);
		myYellow = new Color(208.0f/255.0f,197.0f/255.0f,141.0f/255.0f,1.0f);
		
		HighscoreManager = new HighscoreManager();
		
		Gdx.input.setCatchBackKey(true);
		
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
		
		textFieldStyle = new TextFieldStyle();
		textFieldStyle.font = skin.getFont("fontMedium");
		textFieldStyle.fontColor = myGreen;
		skin.add("textfieldcursor", new Texture("menu/cursor.png"));
		textFieldStyle.cursor = skin.getDrawable("textfieldcursor");

		largelabelStyle = new LabelStyle();
		largelabelStyle.font = skin.getFont("fontLarge");
		largelabelStyle.fontColor = myYellow;
		
		listStyle = new ListStyle();
		
		mediumlabelStyle = new LabelStyle();
		mediumlabelStyle.font = skin.getFont("fontMedium");
		mediumlabelStyle.fontColor = myYellow;
		
		smalllabelStyle = new LabelStyle();
		smalllabelStyle.font = skin.getFont("fontSmall");
		smalllabelStyle.fontColor = myYellow;
		
		largeButtonStyle = new TextButtonStyle();
		largeButtonStyle.font = skin.getFont("fontLarge");
		largeButtonStyle.fontColor = myYellow;
		
		mediumButtonStyle = new TextButtonStyle();
		mediumButtonStyle.font = skin.getFont("fontMedium");
		mediumButtonStyle.fontColor = myYellow;
		
		smallButtonStyle = new TextButtonStyle();
		smallButtonStyle.font = skin.getFont("fontSmall");
		smallButtonStyle.fontColor = myYellow;
		
		skin.add("default", largelabelStyle);
		skin.add("default", mediumlabelStyle);
		skin.add("default", smalllabelStyle);
		skin.add("largeButton", largeButtonStyle);
		skin.add("mediumButton", mediumButtonStyle);
		skin.add("smallButton", smallButtonStyle);
		skin.add("listStyle", listStyle);
		
		table.top();
		table.setFillParent(true);
		table.debug();
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
		for (int y = 0; y < MyGame.screenHeight/backgoundTexture.getHeight(); y++) {
			for (int x = 0; x < MyGame.screenWidth/backgoundTexture.getWidth(); x++) {
				batch.draw(backgoundTexture, x * backgoundTexture.getWidth(), y * backgoundTexture.getHeight());
			}
		}
	}

	@Override
	public void render() {
		Gdx.graphics.getGL20().glClearColor( 1, 0, 0, 1 );
		Gdx.graphics.getGL20().glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );
		super.render();
	}

	@Override
	public void resize(int width, int height) {
		
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	// Creates fontbitmaps from .ttf font
	private void createFont() {
		int largeSize = (int) (MyGame.screenWidth / 8);
		int mediumSize = (int) (MyGame.screenWidth / 12);
		int smallSize = (int) (MyGame.screenWidth / 24);

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
		currentScreen = getScreen();
	}
}
