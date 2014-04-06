package com.me.fong;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class MyGame implements ApplicationListener {
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Texture texture;
	private Sprite sprite;
	private PauseScreen pauseScr;
	private HighscoreScreen HighscoreScr;
	private InstructionsScreen InstructionScr;
	private LoadingScreen LoadingScr;
	private MainMenuScreen mainMenuScr;
	private OptionScreen optionScr;
	private GameOverScreen gameOverScr;
	private GameScreen gameScr;
	private CreditsScreen creditsScr;
	private HighscoreData HighscoreData;
	private GameState gameState;
	
	@Override
	public void create() {		
		
		gameState = GameState.MainMenu;
		
		float screenWidth = Gdx.graphics.getWidth();
		float screenHeight = Gdx.graphics.getHeight();
		
		camera = new OrthographicCamera(1, screenHeight/screenWidth);
		batch = new SpriteBatch();
	}

	@Override
	public void dispose() {
		batch.dispose();
	}

	@Override
	public void render() {		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		sprite.draw(batch);
		batch.end();
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
}
