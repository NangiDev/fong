package com.me.fong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.utils.GdxRuntimeException;

public class MainMenuScreen implements Screen {

	private MyGame game;
	private TextureCombiner newHeader;
	private Texture header;
	private Texture headerNormals;
	private Label signature;
	private TextButton startButton;
	private TextButton highscoreButton;
	private TextButton howToPlayButton;
	private TextButton optionsButton;
	private TextButton creditsButton;
	private ShaderProgram shader;
	private LightSource light;
	
	private String vertexShader;
	private String fragmentShader;
	
	//Constants
	public static final float DEFAULT_LIGHT_Z = 0.1f;
	public static final float AMBIENT_INTENSITY = 0.2f;
	public static final float LIGHT_INTENSITY = 5f;
	
	public static final Vector3 LIGHT_POS = new Vector3(0f,0f,DEFAULT_LIGHT_Z);
	
	//Light RGB and intensity (alpha)
	public static final Vector3 LIGHT_COLOR = new Vector3(1f, 1f, 1f);
 
	//Ambient RGB and intensity (alpha)
	public static final Vector3 AMBIENT_COLOR = new Vector3(0.6f, 0.6f, 1f);
 
	//Attenuation coefficients for light falloff
	public static final Vector3 FALLOFF = new Vector3(.4f, 3f, 20f);

	public MainMenuScreen(MyGame myGame) {
		this.game = myGame;
		
		
		
		header = new Texture(Gdx.files.internal("menu/logotype.png"));
		headerNormals = new Texture(Gdx.files.internal("menu/logotypeNormal.png"));
		newHeader = new TextureCombiner("menu/logotype.png");
		
		light = new LightSource(game.batch);
		light.setDefaultLight();
		
		//S�tt upp uniformer
		game.normalShader.begin();
		game.normalShader.setUniformi("u_normals", 1);
		
		game.normalShader.setUniformf("LightColor", light.getColor().x, light.getColor().y, light.getColor().z, light.getIntensity());
		game.normalShader.setUniformf("AmbientColor", AMBIENT_COLOR.x, AMBIENT_COLOR.y, AMBIENT_COLOR.z, AMBIENT_INTENSITY);
		game.normalShader.setUniformf("Falloff", light.getFallOff());
		game.normalShader.end();
		
		game.batch = new SpriteBatch(1000, game.normalShader);
		game.batch.setShader(game.normalShader);
		
		
		
	}

	@Override
	public void render(float delta) {
		update(delta);
		draw(delta);
	}

	public void update(float delta) {
		if(Gdx.input.isTouched()){
			System.out.println("LIGHT_POS updated");
			float x = (Gdx.input.getX() / game.screenWidth);
			float y = 1-(Gdx.input.getY() / game.screenHeight);
			light.setPos(x, y);
		}
		
		/*float x = ((MyGame.screenWidth * 0.5f) - (header.getWidth() * 0.5f * MyGame.scaleX) + header.getWidth() / 2) / game.screenWidth;
		float y = (MyGame.screenHeight * 0.7f + header.getHeight() / 2) / game.screenHeight;
		
		

		light.setPos(x, y);*/
		
	}

	public void draw(float delta) {
		game.batch.begin();
		game.batch.setShader(game.defaultShader);
		game.drawBackground();
		
		game.batch.setShader(game.normalShader);
		
		game.normalShader.setUniformf("LightPos", light.getPos());
		
		//headerNormals.bind(1);
		//header.bind(0);
		
		newHeader.bind();
		
		game.batch.draw(newHeader.texture, (MyGame.screenWidth * 0.5f)
				- (newHeader.texture.getWidth() * 0.5f * MyGame.scaleX),
				MyGame.screenHeight * 0.7f, newHeader.texture.getWidth()
						* MyGame.scaleX, newHeader.texture.getHeight()
						* MyGame.scaleY);
		
		game.batch.setShader(game.defaultShader);
		
		signature.draw(game.batch, 1);

		game.table.draw(game.batch, 1);

		game.batch.end();
	}

	@Override
	public void resize(int width, int height) {
		game.camera.setToOrtho(false, width, height);
		game.batch.setProjectionMatrix(game.camera.combined);
		
		game.normalShader.begin();
		game.batch.setShader(game.normalShader);
		game.normalShader.setUniformf("Resolution", width, height);
		game.normalShader.end();
	}

	@Override
	public void show() {
		createScreen();
		setupMenuLayout();
	}

	@Override
	public void hide() {
		game.table.clearChildren();
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
	
	private void createScreen(){
		//this.header = new Texture(Gdx.files.internal("menu/logotype.png"));
		//this.headerNormals = new Texture(Gdx.files.internal("menu/logotypeNormal.png"));
		signature = new Label("A game made by\nJoel Setterberg &\nJonatan Elsgard", game.smalllabelStyle);
		signature.setAlignment(Align.center);
		signature.setWidth(MyGame.screenWidth);
		signature.setWrap(true);
		signature.setPosition(0, signature.getHeight() * 0.5f * MyGame.scaleY);
		

		startButton = new MenuButton("Start", game.mediumButtonStyle,
				GameState.Game, game);
		highscoreButton = new MenuButton("Highscore", game.mediumButtonStyle,
				GameState.Highscore, game);
		howToPlayButton = new MenuButton("How To Play", game.mediumButtonStyle,
				GameState.Instructions, game);
		optionsButton = new MenuButton("Options", game.mediumButtonStyle,
				GameState.Options, game);
		creditsButton = new MenuButton("Credits", game.mediumButtonStyle,
				GameState.Credits, game);
		
		System.out.println("new MainMenuScreen created");
	}

	private void setupMenuLayout() {
		game.table.add().row().padBottom(25.0f * MyGame.scaleY);
		game.table.add(startButton).row().padBottom(25.0f * MyGame.scaleY);
		game.table.add(highscoreButton).row().padBottom(25.0f * MyGame.scaleY);
		game.table.add(optionsButton).row().padBottom(25.0f * MyGame.scaleY);
		game.table.add(howToPlayButton).row().padBottom(25.0f * MyGame.scaleY);
		game.table.add(creditsButton);
		
		game.table.padTop(header.getHeight() * 1.5f * MyGame.scaleY);
	}
}
