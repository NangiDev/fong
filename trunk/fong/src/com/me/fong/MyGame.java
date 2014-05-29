package com.me.fong;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
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
	public static boolean musicOn = true;
	public static boolean soundOn = true;
	public static boolean lightOn = true;
	public static boolean gameRunning = false;

	public static float screenWidth;
	public static float screenHeight;
	public static float scaleX;
	public static float scaleY;
	public static int score = 0;
	public static float difficulty = 1.0f;
	public OrthographicCamera camera;
	public SpriteBatch batch;

	private int backgroundSpeed = 100;
	private Texture backgroundNormal;
	public static float backgroundStrafe = 0;
	private Preferences prefs;

	public BitmapFont fontLarge, fontMedium, fontSmall;
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
	
	public HighscoreManager highscoreManager;
	public EntityManager entityManager;
	private SoundManager soundManager;

	public Screen previousScreen;
	public GameScreen gameScreen;
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
		screenWidth = Gdx.graphics.getWidth();
		screenHeight = Gdx.graphics.getHeight();
		scaleX = screenWidth / 600;
		scaleY = screenHeight / 960;
		batch = new SpriteBatch();
		camera = new OrthographicCamera(1, screenHeight / screenWidth);
		camera.setToOrtho(false, screenWidth, screenHeight);
		batch.setProjectionMatrix(camera.combined);

		highscoreManager = new HighscoreManager();
		readFromSavefile();
		entityManager = new EntityManager(this);
		soundManager = new SoundManager();
		Assets.setUpSoundPrios();

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
		textFieldStyle.fontColor = Assets.myGreen;
		skin.add("textfieldcursor", new Texture("menu/cursor.png"));
		textFieldStyle.cursor = skin.getDrawable("textfieldcursor");

		largelabelStyle = new LabelStyle();
		largelabelStyle.font = skin.getFont("fontLarge");
		largelabelStyle.fontColor = Assets.myYellow;

		listStyle = new ListStyle();

		mediumlabelStyle = new LabelStyle();
		mediumlabelStyle.font = skin.getFont("fontMedium");
		mediumlabelStyle.fontColor = Assets.myYellow;

		smalllabelStyle = new LabelStyle();
		smalllabelStyle.font = skin.getFont("fontSmall");
		smalllabelStyle.fontColor = Assets.myYellow;

		largeButtonStyle = new TextButtonStyle();
		largeButtonStyle.font = skin.getFont("fontLarge");
		largeButtonStyle.fontColor = Assets.myYellow;

		mediumButtonStyle = new TextButtonStyle();
		mediumButtonStyle.font = skin.getFont("fontMedium");
		mediumButtonStyle.fontColor = Assets.myYellow;

		smallButtonStyle = new TextButtonStyle();
		smallButtonStyle.font = skin.getFont("fontSmall");
		smallButtonStyle.fontColor = Assets.myYellow;

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
		stage.dispose();
		skin.dispose();
	}

	@Override
	public void render() {
		if(getScreen() != previousScreen && previousScreen != null){
			Gdx.graphics.getGL20().glClearColor( 1, 0, 0, 1 );
			Gdx.graphics.getGL20().glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );
			previousScreen = getScreen();
		}
		
		super.render();
		if (Gdx.input.isKeyPressed(Keys.ESCAPE)){
			System.exit(0);
			Gdx.app.exit();
		}
		soundManager.tick();
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

	public void readFromSavefile() {
		prefs = Gdx.app.getPreferences("FongSaveFile");
		if (prefs.get().size() > 0) {
			soundOn = prefs.getBoolean("soundOn");
			musicOn = prefs.getBoolean("musicOn");
			lightOn = prefs.getBoolean("lightOn");

			for (int i = 1; i <= 5; i++) {
				String str = prefs.getString("" + i);
				String[] parts = str.split(" ");
				highscoreManager.addScore(new Score(parts[0], Integer
						.parseInt(parts[1])));
			}
		} else {
			prefs.putBoolean("soundOn", true);
			prefs.putBoolean("musicOn", true);
			prefs.putBoolean("lightOn", true);
			prefs.putString("1", "Föng 0");
			prefs.putString("2", "Föng 0");
			prefs.putString("3", "Föng 0");
			prefs.putString("4", "Föng 0");
			prefs.putString("5", "Föng 0");
			prefs.flush();

			soundOn = prefs.getBoolean("soundOn");
			musicOn = prefs.getBoolean("musicOn");
			lightOn = prefs.getBoolean("lightOn");

			for (int i = 1; i <= 5; i++) {
				String str = prefs.getString("" + i);
				String[] parts = str.split(" ");
				highscoreManager.addScore(new Score(parts[0], Integer
						.parseInt(parts[1])));
			}
		}
	}

	// Creates fontbitmaps from .ttf font
	private void createFont() {
		int largeSize = (int) (screenWidth / 8);
		int mediumSize = (int) (screenWidth / 12);
		int smallSize = (int) (screenWidth / 24);

		FreeTypeFontGenerator gen = new FreeTypeFontGenerator(
				Gdx.files.internal("Fonts/font.ttf"));
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
		previousScreen = getScreen();
		switch (gameState) {
		case Game:
			if (gameScreen == null)
				gameScreen = new GameScreen(this);
			setScreen(gameScreen);
			gameRunning = true;
			entityManager.setDefaultSunPosition();
			break;
		case Pause:
			if (pauseScreen == null)
				pauseScreen = new PauseScreen(this);
			setScreen(pauseScreen);
			gameRunning = false;
			break;
		case Options:
			if (optionScreen == null)
				optionScreen = new OptionScreen(this);
			setScreen(optionScreen);
			gameRunning = false;
			break;
		case Loading:
			if (loadingScreen == null)
				loadingScreen = new LoadingScreen(this);
			setScreen(loadingScreen);
			gameRunning = false;
			break;
		case Credits:
			if (creditsScreen == null)
				creditsScreen = new CreditsScreen(this);
			setScreen(creditsScreen);
			gameRunning = false;
			break;
		case MainMenu:
			if (mainMenuScreen == null)
				mainMenuScreen = new MainMenuScreen(this);
			setScreen(mainMenuScreen);
			gameRunning = false;
			break;
		case GameOver:
			if (gameOverScreen == null)
				gameOverScreen = new GameOverScreen(this);
			setScreen(gameOverScreen);
			gameRunning = false;
			break;
		case Highscore:
			if (highscoreScreen == null)
				highscoreScreen = new HighscoreScreen(this);
			setScreen(highscoreScreen);
			gameRunning = false;
			break;
		case Instructions:
			if (instructionsScreen == null)
				instructionsScreen = new InstructionsScreen(this);
			setScreen(instructionsScreen);
			gameRunning = false;
			break;
		default:
			break;
		}
	}

	// Call this in each screen class that wants same background as main.
	public void drawBackground(float delta) {
		entityManager.shaderManager.switchToNormalShader(batch);
		
		Assets.NORMALS_BY_NAME.get("defaultNormal").bind(1);
		Assets.backgroundBlue.bind(0);
		
		for (int x = -1; x < (screenWidth / (Assets.backgroundBlue.getWidth() * scaleX)+1); x++) {
			for (int y = 0; y < (screenHeight
					/ (Assets.backgroundBlue.getHeight() * scaleY) + 1); y++) {
				
				batch.draw(Assets.backgroundBlue,
						x * Assets.backgroundBlue.getWidth() * scaleX + backgroundStrafe, y
								* Assets.backgroundBlue.getHeight() * scaleY
								- backgroundSpeed,
						Assets.backgroundBlue.getWidth() * scaleX,
						Assets.backgroundBlue.getHeight() * scaleY);
			}
		}
		
		entityManager.shaderManager.switchToDefaultShader(batch);
		if (backgroundSpeed > Assets.backgroundBlue.getHeight() * scaleY)
			backgroundSpeed = 0;
		
		if (backgroundStrafe > Assets.backgroundBlue.getWidth() * scaleX)
			backgroundStrafe = 0;

		if (backgroundStrafe < -(Assets.backgroundBlue.getWidth() * scaleX))
			backgroundStrafe = 0;

		backgroundSpeed += 750 * delta * scaleY;
	}
}
